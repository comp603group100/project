package virtualpetgame;

import virtualpetgame.GUIs.MainGame;

public class MainGameGuiManager {

    MainGame mainGameGui;
    Game game;

    protected MainGameGuiManager(Game game, MainGame mainGameGui) {
        this.mainGameGui = mainGameGui;
        this.game = game;
    }

    protected int verify() {
        //want to check that gui components are working here
        return 0;
    }

    protected void update() {
        /*
        Need to set:
        - Art
        - Hunger
        - Boredom
        - Cleanliness
        - Mood
        - Money
         */
        this.mainGameGui.setArt(this.getArt());
        this.mainGameGui.setHunger(this.game.activePet.getHunger());
        this.mainGameGui.setBoredom(this.game.activePet.getBoredom());
        this.mainGameGui.setCleanliness(this.game.activePet.getCleanliness());
        this.mainGameGui.setMood((this.game.activePet.state).toString());
        this.mainGameGui.setMoney(this.game.activePet.getMoney());
        this.mainGameGui.tick();
        this.mainGameGui.repaint();
    }

    protected void dead() {
        this.update();
        this.mainGameGui.showDeathDialog();
    }
    
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
