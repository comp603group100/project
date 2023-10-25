/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import java.util.Random;

public class Tick implements Runnable {

    private boolean running;

    private static final int HUNGER = 0;
    private static final int BOREDOM = 1;
    private static final int CLEANLINESS = 2;

    private static final int SLEEP_TIME = 1000; //1 second
    private boolean ticked = false;

    private final Random rand = new Random();

    private ActivePet activePet;

    public void setActivePet(ActivePet activePet) {
        this.activePet = activePet;
    }

    @Override
    public void run() {
        this.running = true;

        while (this.running) {
            try {
                if (rand.nextInt(20) == 0) { //random number for 1/20 chance
                    switch (rand.nextInt(3)) {
                        case (HUNGER):
                            this.activePet.increaseHunger(rand.nextInt(5) + 1);
                            break;
                        case (BOREDOM):
                            this.activePet.increaseBoredom(rand.nextInt(5) + 1);
                            break;
                        case (CLEANLINESS):
                            this.activePet.decreaseCleanliness(rand.nextInt(5) + 1);
                            break;
                    }
                }

                if (rand.nextInt(10) == 0) {
                    this.activePet.increaseMoney();
                }

                ticked = true;

                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                System.err.println("Error in tick thread: sleep operation failed!!!!\n" + ex);
            }
        }

    }

    /**
     * cleanly stops the thread
     */
    public void stopThread() {
        this.running = false;
    }

    /**
     * Checks if a tick event has occurred, and returns true if so. Also resets
     * the check so that subsequent checks are correct.
     *
     * @return Boolean true if an event has occurred, false otherwise.
     */
    public synchronized boolean ticked() {
        if (ticked == false) {
            return ticked;
        }

        ticked = false;
        return true;
    }
}
