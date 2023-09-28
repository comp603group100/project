/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

public class Autosave implements Runnable{
    
    private boolean running;
    
    private static final int SLEEP_TIME = 60000; //1 minute
    
    FileIO fileIO;
    
    /**
     * Constructor for the autosaver.
     * 
     * @param saverLoader the SaveLoadSystem object to use for saving.
     */
    public Autosave(FileIO saverLoader){
        this.fileIO = saverLoader;
    }
    
    @Override
    public void run(){
        this.running = true;
        
        while (this.running) {
            try {
                fileIO.save();
                Thread.sleep(SLEEP_TIME); //prevents thread from ending for 1 minute, need to fix.
            } catch (InterruptedException ex) {
                //do nothing, it doesnt matter if the thread is interrupted
                //also is interrupted on purpose if the game is ending.
            }
        }
    }
    
    /**
     * Cleanly stops the thread loop.
     */
    public void stopThread() {
        this.running = false;
    }
}
