/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import virtualpetgame.GUIs.MainGame;

public class MainGameGuiManager {

    MainGame mainGameGui; //the main game gui object
    Game game; //the game controller object

    /**
     * Creates the MainGameGuiManager
     * 
     * @param game the game object which controls the game
     * @param mainGameGui the GUI to display the information to
     */
    protected MainGameGuiManager(Game game, MainGame mainGameGui) {
        this.mainGameGui = mainGameGui;
        this.game = game;
    }

    /**
     * Verifies the GUI has been created correctly
     * 
     * @return an int error code
     */
    protected int verify() {
        //want to check that gui components are working here, will do later
        return 0;
    }

    /**
     * Updates the GUI based on data from the game
     */
    protected void update() {
        this.mainGameGui.setArt(this.getArt());
        this.mainGameGui.setHunger(this.game.activePet.getHunger());
        this.mainGameGui.setBoredom(this.game.activePet.getBoredom());
        this.mainGameGui.setCleanliness(this.game.activePet.getCleanliness());
        this.mainGameGui.setMood((this.game.activePet.state).toString());
        this.mainGameGui.setMoney(this.game.activePet.getMoney());
        this.mainGameGui.tick();
        this.mainGameGui.repaint();
    }

    /**
     * Updates the GUI one final time, and then displays the death dialog.
     */
    protected void dead() {
        this.update();
        this.mainGameGui.showDeathDialog();
    }
    
    /**
     * Retrieves the art from the ActivePet
     * 
     * @return a String, formatted with newlines, containing the ASCII art of the pet.
     */
    private String getArt() {
        
        String art = "";
        
        switch (this.game.activePet.state) {
            case HAPPY:
                //happy
                for (String line : this.game.activePet.getArt().happy()) {
                    art += line + "\n";
                }
                break;
            case NEUTRAL:
                //neutral
                for (String line : this.game.activePet.getArt().neutral()) {
                    art += line + "\n";
                }
                break;
            case SAD:
                //sad
                for (String line : this.game.activePet.getArt().sad()) {
                    art += line + "\n";
                }
                break;
            case STRESSED:
                //stressed
                for (String line : this.game.activePet.getArt().stressed()) {
                    art += line + "\n";
                }
                break;
            case DEAD:
                //dead
                for (String line : this.game.activePet.getArt().dead()) {
                    art += line + "\n";
                }
                break;
            default:
                break;
        }

        return art;
    }
}
