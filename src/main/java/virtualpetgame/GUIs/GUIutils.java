/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame.GUIs;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

public class GUIutils {

    /**
     * Sets the window position to the center of the monitor
     * 
     * @param frame the frame to be centered
     */
    public static void centerWindow(JFrame frame) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] allDevices = env.getScreenDevices();
        GraphicsConfiguration config = allDevices[0].getDefaultConfiguration();

        int screenX = config.getBounds().width;
        int screenY = config.getBounds().height;

        int frameX = frame.getWidth();
        int frameY = frame.getHeight();

        int windowPosX = (screenX - frameX) / 2 + config.getBounds().x;
        int windowPosY = (screenY - frameY) / 2 + config.getBounds().y;

        frame.setLocation(windowPosX, windowPosY);
    }
}
