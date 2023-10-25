package virtualpetgame;

import javax.swing.JButton;
import javax.swing.JFrame;
import virtualpetgame.GUIs.*;

public class GUI {

    private JFrame currentFrame;

    private final Game game;

    public GUI(Game game) {
        this.game = game;
    }

    public void waitForButton() {

        JButton waiterButton = getWaitButton();

        if (waiterButton != null) {

            synchronized (waiterButton) {
                try {
                    waiterButton.wait();
                } catch (InterruptedException ex) {
                    //shouldnt happen and its kinda doomed if it does
                }
            }
        }
    }

    private void setCurrentFrame(JFrame frame) {
        this.currentFrame = frame;
    }

    private JButton getWaitButton() {
        if (this.currentFrame instanceof GetWaitButton) {
            return ((GetWaitButton) this.currentFrame).getWaitButton();
        } else {
            return null;
        }
    }

    public String getPetType() {
        if (currentFrame instanceof CreateNewSave) {
            return ((CreateNewSave) currentFrame).getPetType();
        } else {
            return null;
        }
    }

    public String getSaveName() {
        if (currentFrame instanceof CreateNewSave) {
            return ((CreateNewSave) currentFrame).getSaveName();
        } else {
            return null;
        }
    }
    
    public String getOption() {
        if (currentFrame instanceof GetOption) {
            return ((GetOption) currentFrame).getOption();
        } else {
            return null;
        }
    }

    public void showError() {
        if (currentFrame instanceof ShowError) {
            ((ShowError) currentFrame).showError();
        }
    }

    public void showFirstRunHelp() {
        this.setCurrentFrame(new FirstRunHelp());
        this.currentFrame.setTitle("Welcome!");
        this.currentFrame.setVisible(true);
    }

    public void showCreateSave() {
        this.setCurrentFrame(new CreateNewSave());
        this.currentFrame.setTitle("Create new save");
        this.currentFrame.setVisible(true);
    }
    
    public void showWelcomeMenu(boolean cont) {
        this.setCurrentFrame(new WelcomeMenu(cont));
        this.currentFrame.setTitle("Welcome");
        this.currentFrame.setVisible(true);
    }
}
