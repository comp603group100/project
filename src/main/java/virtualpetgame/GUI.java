package virtualpetgame;

import javax.swing.JButton;
import javax.swing.JFrame;
import virtualpetgame.GUIs.*;

public class GUI {

    private JFrame currentFrame;
    private JButton waiterButton;

    private Game game;

    public GUI(Game game) {
        this.game = game;
    }

    public void waitForButton() {

        if (waiterButton != null) {

            synchronized (waiterButton) {
                try {
                    waiterButton.wait();
                } catch (InterruptedException ex) {
                    //idc rn
                }
            }
        }
    }

    private void setCurrentFrame(JFrame frame) {
        this.currentFrame = frame;
    }

    private void setWaitButton(Object o) {
        if (o instanceof GetWaitButton) {
            GetWaitButton waiter = (GetWaitButton) o;
            this.waiterButton = waiter.getWaitButton();
        } else {
            this.waiterButton = null;
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
        this.setWaitButton(this.currentFrame);
        this.currentFrame.setVisible(true);
    }

    public void showCreateSave() {
        this.setCurrentFrame(new CreateNewSave(game));
        this.currentFrame.setTitle("Create new save");
        this.setWaitButton(this.currentFrame);
        this.currentFrame.setVisible(true);
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
}
