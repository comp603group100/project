package virtualpetgame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartupGUI extends JFrame {
    
    private JPanel panel;
    
    public StartupGUI(boolean savesExist, boolean canContinue) {
        //also want to sort out if played before or not
        
        if (!savesExist && !canContinue) {
            //If no saves exist and no continueable game exists.
            
        }
        else if (savesExist && !canContinue) {
            //if saves exist but the previously played game doesnt.
            
        }
        else if (savesExist && canContinue){
            //if saves exist, and the previously played game still exists.
        }
        else {
            //error, other options arent possible
        }
    }
}
