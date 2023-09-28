/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

public class Main {
    public static void main(String[] args) {
        
        Game g = new Game();
        
        int init_code = g.init();
        
        if (init_code != Game.INIT_SUCCESS) { //initialse game, and check if it started correctly.
            System.out.println("Game failed to start.");
            
            if(init_code == Game.INIT_FAIL_CORRUPT_SAVE)
                System.out.println("Corrupted or incompatible save.");
        }
        else {
            System.out.println("Game started!");
            g.start(); //actually begin the game
        }
    }
}
