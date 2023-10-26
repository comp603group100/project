/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    
    Game game;
    
    public GameTest() {
        this.game = new Game();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDoubleInitFailure() {
        this.game.init();
        if (this.game.init() != Game.INIT_ALREADY_COMPLETE)
            fail("Didn't detect double initialisation");
    }
}
