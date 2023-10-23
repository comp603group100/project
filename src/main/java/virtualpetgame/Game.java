/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import java.util.Scanner;
import virtualpetgame.pets.*;

public class Game {

    //game objects
    Scanner keyboard;
    Renderer renderer;
    Tick tick;
    ActivePet activePet;
    FileIO fileIO;
    Autosave autosave;
    InputHandler inputHandler;
    GameDataManager gameDBM;
    GUI gui;

    //Threads
    Thread tickThread;
    Thread autosaveThread;
    Thread inputHandlerThread;

    /**
     * Constructor to create a new game object. After creating the game object,
     * init() can be called to set up the remaining required objects for the
     * game. Then, the start() method can be called to start the game.
     */
    public Game() {
        this.keyboard = new Scanner(System.in);
        this.renderer = new Renderer();
        this.tick = new Tick();
        this.gameDBM = new GameDataManager();
        this.fileIO = new FileIO(gameDBM);
        this.autosave = new Autosave(this.fileIO);
        this.inputHandler = new InputHandler(this);
        this.gui = new GUI(this);
    }

    //flags for checking game state
    private boolean initialised = false;
    private boolean started = false;
    private boolean running = false;

    //initialisation errors
    public static final int INIT_SUCCESS = 0;
    public static final int INIT_FAIL = 1;
    public static final int INIT_ALREADY_COMPLETE = 2;
    public static final int INIT_FAIL_CORRUPT_SAVE = 3;

    /**
     * Private method to create a new save. Prompts the user for a save name,
     * and lets them choose the pet type they want.
     */
    private void createNewSave() {
        boolean saveCreated = false;
        
        while (saveCreated == false) {
            gui.showCreateSave();
            gui.waitForButton();
            
            if(fileIO.fileExists(gui.getSaveName())){
                gui.showError();
            }
            else{
                fileIO.setFileName(gui.getSaveName());
                
                switch (gui.getPetType()){
                    case("Cat"):
                        this.activePet = new ActivePet(new Cat());
                        break;
                    case("Dog"):
                        this.activePet = new ActivePet(new Dog());
                        break;
                    case("Monkey"):
                        this.activePet = new ActivePet(new Monkey());
                        break;
                }
                
                fileIO.setActivePet(this.activePet);
                saveCreated = true;
            }
        }
    }

    /**
     * Check if the game is currently running.
     *
     * @return a Boolean, true if the game is running, false otherwise.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the running state of the game.
     *
     * @param state the running state to set the game to. Setting it to false
     * will stop the game.
     */
    public void setRunning(boolean state) {
        this.running = state;
    }

    /**
     * Initialises the game.
     *
     * @return an integer value to signify wether the game has initialized
     * successfully. A return value of 0 is success, other values are errors.
     */
    public int init() {

        //guard clause to ensure game is not already started
        if (initialised == true) {
            return INIT_ALREADY_COMPLETE;
        }

        //check if saves exist, then decide what to do.
        if (fileIO.savesExist() == false) {
            if (!gameDBM.getPlayedBefore()) {
                gui.showFirstRunHelp();
                gui.waitForButton();
                gameDBM.setPlayedBefore(true);
            }

            System.out.println("You have no saved games.");
            createNewSave();

        } else if (fileIO.savesExist() && (gameDBM.getPreviousGame() == null || gameDBM.getPreviousGame().equals(""))) {
            System.out.print("Would you like to load a save, or start a new game?\n"
                    + "Enter: [1] Load or [2] New: ");

            boolean choosing = true;
            while (choosing) {

                String input = keyboard.nextLine().toLowerCase();
                switch (input) {
                    case "l":
                    case "load":
                    case "1": {
                        fileIO.setFileName(fileIO.chooseFile());
                        this.activePet = fileIO.load();
                        choosing = false;
                        break;
                    }
                    case "n":
                    case "new":
                    case "2": {
                        createNewSave();
                        choosing = false;
                        break;
                    }
                    default: {
                        System.out.print("Bad choice, please enter 1 or 2: ");
                    }
                }

            }
        } else {
            System.out.print("Would you like to continue your last played save, load a save, or start a new game?\n"
                    + "Enter: [1] Continue, [2] Load or [3] New: ");

            boolean choosing = true;

            while (choosing) {

                String input = keyboard.nextLine().toLowerCase();
                switch (input) {
                    case "c":
                    case "continue":
                    case "cont":
                    case "1": {
                        fileIO.setFileName(gameDBM.getPreviousGame());
                        this.activePet = fileIO.load();
                        choosing = false;
                        break;
                    }
                    case "l":
                    case "load":
                    case "2": {
                        fileIO.setFileName(fileIO.chooseFile());
                        this.activePet = fileIO.load();
                        choosing = false;
                        break;
                    }
                    case "n":
                    case "new":
                    case "3": {
                        createNewSave();
                        choosing = false;
                        break;
                    }
                    default: {
                        System.out.print("Bad choice, please enter 1, 2, or 3: ");
                    }
                }

            }

        }

        //final check to make sure things worked properly.
        if (activePet == null) {
            return INIT_FAIL_CORRUPT_SAVE;
        }

        //Create threads
        this.tickThread = new Thread(tick, "tick");
        this.autosaveThread = new Thread(autosave, "autosave");
        this.inputHandlerThread = new Thread(inputHandler, "inputHandler");

        //Set required info for game to work.
        this.renderer.setActivePet(activePet);
        this.tick.setActivePet(activePet);

        initialised = true;
        return INIT_SUCCESS;
    }

    /**
     * Checks that the game has been initialised, and hasn't already been
     * started, Then starts the game.
     */
    public void start() {

        if (this.initialised == false) {
            System.out.println("Game needs to be initialised before running!!!");
            return;
        } else if (this.started == true) {
            return;
        }

        this.started = true; //set the game state to started.
        this.running = true; //also update the running flag

        this.tickThread.start(); //begin tick thread, for game event updates.
        this.autosaveThread.start(); //begin autosave thread. Also saves the game as soon as it starts.
        this.inputHandlerThread.start(); //begin the input handler thread

        this.renderer.update(); //first draw, otherwise nothing will show until next tick event

        //MAIN GAME LOOP - very simple, waits for tick events, then updates the screen if things have changed.
        while (this.running) {

            if (this.tick.eventOccured()) {
                this.renderer.update();

                if (this.activePet.state == State.DEAD) {
                    //end game if pet dead
                    this.running = false;
                }
            }
        }

        //end game sequence
        this.tick.stopThread();
        this.autosaveThread.interrupt(); //need to interrupt the 1 minute sleep ops
        this.autosave.stopThread();

        if (this.activePet.state == State.DEAD) {
            /*
            Game pauses here due to the inputHandler waiting for input.
            This is intentional, so an AFK user can see that their game has ended when they return.
             */

            this.fileIO.delete();
        } else {
            System.out.println("Game ended. Your progress has been automatically saved.");

            //Save progress.
            fileIO.save();
        }
    }
}
