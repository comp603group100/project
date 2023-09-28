/**
 * @author group100 (19094184, 19088716)
 * @author ChatGPT (Suggested to use threads, generated game.isRunning check)
 */
package virtualpetgame;

import java.util.Scanner;

class InputHandler implements Runnable {

    private Game game;

    /**
     * InputHandler constructor.
     *
     * @param game Game object to send inputs to.
     */
    public InputHandler(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        Scanner keyboard = new Scanner(System.in);

        while (game.isRunning()) {
            String input = keyboard.nextLine().toLowerCase();
            switch (input) {
                case "1":
                case "feed":
                case "f": {
                    if (game.activePet.getMoney() < ActivePet.FEED_COST) {
                        System.out.print("You can't afford that!\nTry again: ");
                        break;
                    }
                    game.activePet.spend(ActivePet.FEED_COST);
                    game.activePet.feed();
                    game.renderer.update();
                    break;
                }
                case "2":
                case "play":
                case "p": {
                    if (game.activePet.getMoney() < ActivePet.PLAY_COST) {
                        System.out.print("You can't afford that!\nTry again: ");
                        break;
                    }
                    game.activePet.spend(ActivePet.PLAY_COST);
                    game.activePet.play();
                    game.renderer.update();
                    break;
                }
                case "3":
                case "clean":
                case "c": {
                    if (game.activePet.getMoney() < ActivePet.CLEAN_COST) {
                        System.out.print("You can't afford that!\nTry again: ");
                        break;
                    }
                    game.activePet.spend(ActivePet.CLEAN_COST);
                    game.activePet.clean();
                    game.renderer.update();
                    break;
                }
                case "x":
                case "X":
                    game.setRunning(false);
                    break;
                default:
                    if (game.isRunning()) // don't want to print when pet dies
                    {
                        System.out.print("Please choose an available option: ");
                    }
            }

        }
    }
}
