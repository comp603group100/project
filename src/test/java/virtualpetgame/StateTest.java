/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import org.junit.Test;
import static org.junit.Assert.*;

public class StateTest {
    
    public StateTest() {
    }

    /**
     * Test of toString method, of class State.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        State instance = State.HAPPY;
        String expResult = "Happy";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
