/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import virtualpetgame.pets.*;

public class Game {

    //game objects
    Tick tick;
    public ActivePet activePet; //same as below, need to control pet status through the game from the gui
    public FileIO fileIO; //public so we can manage the files from anything that interfaces with the game (i.e. the GUI)
    Autosave autosave;
    GameDataManager gameDBM;
    GUI gui;

    //Threads
    Thread tickThread;
    Thread autosaveThread;

    /**
     * Constructor to create a new game object. After creating the game object,
     * init() can be called to set up the remaining required objects for the
     * game. Then, the start() method can be called to start the game.
     */
    public Game() {
        this.tick = new Tick();
        this.gameDBM = new GameDataManager();
        this.fileIO = new FileIO(gameDBM);
        this.autosave = new Autosave(this.fileIO);
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

            if (fileIO.fileExists(gui.getSaveName())) {
                gui.showError();
            } else {
                fileIO.setFileName(gui.getSaveName());

                switch (gui.getPetType()) {
                    case ("Cat"):
                        this.activePet = new ActivePet(new Cat());
                        break;
                    case ("Dog"):
                        this.activePet = new ActivePet(new Dog());
                        break;
                    case ("Monkey"):
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
            createNewSave();
        } else {
            boolean canContinueGame = !(fileIO.savesExist() && (gameDBM.getPreviousGame() == null || gameDBM.getPreviousGame().equals("")));
            gui.showWelcomeMenu(canContinueGame);
            gui.waitForButton();
            switch (gui.getOption()) {
                case ("load"):
                    gui.showLoadMenu();
                    gui.waitForButton();
                    fileIO.setFileName(
                            fileIO.getFileFromIndex(
                                    Integer.parseInt(
                                            gui.getOption()
                                    )));
                    this.activePet = fileIO.load();
                    break;
                case ("new"):
                    createNewSave();
                    break;
                case ("cont"):
                    fileIO.setFileName(gameDBM.getPreviousGame());
                    this.activePet = fileIO.load();
                    break;
                default:
                    System.out.println("[ERROR] Unhandled case in welcome menu");
                    break;
            }
        }

        //final check to make sure things worked properly.
        if (activePet == null) {
            return INIT_FAIL_CORRUPT_SAVE;
        }

        //Create threads
        this.tickThread = new Thread(tick, "tick");
        this.autosaveThread = new Thread(autosave, "autosave");

        //Set required info for game to work.
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

        this.gui.showMainGame(this);
        if (gui.mainGame.verify() != 0)
            return;
        this.gui.mainGame.update();

        //MAIN GAME LOOP - very simple, waits for tick events, then updates the screen if things have changed.
        while (this.running) {

            if (this.tick.ticked()) {
                this.gui.mainGame.update();

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
            this.gui.mainGame.dead(); //show the death dialog so that the user knows what happened
            this.fileIO.delete();
        } else {
            fileIO.save();
        }
    }
    
    //lets us update the gui from wherever we want
    public void updateGUI() {
        this.gui.mainGame.update();
    }
}
