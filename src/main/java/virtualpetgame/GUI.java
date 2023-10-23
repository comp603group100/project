package virtualpetgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import virtualpetgame.GUIs.*;

public class GUI {

    private JFrame currentFrame;
    private JButton waiterButton;

    public void waitForButton(){

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
        }
        else {
            this.waiterButton = null;
        }
    }

    public void showFirstRunHelp() {
        this.setCurrentFrame(new FirstRunHelp());
        
        this.currentFrame.setVisible(true);
    }
}
