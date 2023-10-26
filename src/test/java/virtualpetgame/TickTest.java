/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import virtualpetgame.pets.Cat;

public class TickTest {
    
    Tick tick;
    Thread thread;
    
    public TickTest() {
    }
    
    @Before
    public void setUp() {
        this.tick = new Tick();
        this.tick.setActivePet(new ActivePet(new Cat()));
        this.thread = new Thread(tick);
        this.thread.start();
    }
    
    @After
    public void tearDown() {
        tick.stopThread();
    }

    /**
     * Test of run method, of class Tick.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        try {
            Thread.sleep(3000); //wait 2s
        } catch (InterruptedException ex) {
            fail("Sleep thread interrupted");
        }
        if (!tick.ticked())
            fail("No tick detected after 3 seconds. Ticks should only take 1 second.");
    }

    /**
     * Test of stopThread method, of class Tick.
     */
    @Test
    public void testStopThread() {
        System.out.println("stopThread");
        tick.stopThread();
        
        if(!tick.ticked())
            fail("Thread ticked");
    }
    
}
