/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

public class Renderer {

    private ActivePet activePet;

    /**
     * Sets the active pet used by the renderer.
     *
     * @param activePet the activePet object to get information from.
     */
    protected void setActivePet(ActivePet activePet) {
        this.activePet = activePet;
    }

    /**
     * Update what's shown on the screen. Clears the console window and then re
     * prints everything. Should only be called when necessary.
     */
    public void update() {
        for (int i = 0; i <= 50; i++) {
            System.out.println();
        }
        this.drawAll();
    }

    /**
     * Draws all the info needed for the game.
     */
    private void drawAll() {

        if (null != this.activePet.state) //print the pet, 
        //TODO: we can probably tidy this into a function and pass in the state
        //we will need to refactor the pet/art classes probably
        {
            switch (this.activePet.state) {
                case HAPPY:
                    //happy
                    for (String line : this.activePet.getArt().happy()) {
                        System.out.println(line);
                    }
                    break;
                case NEUTRAL:
                    //neutral
                    for (String line : this.activePet.getArt().neutral()) {
                        System.out.println(line);
                    }
                    break;
                case SAD:
                    //sad
                    for (String line : this.activePet.getArt().sad()) {
                        System.out.println(line);
                    }
                    break;
                case STRESSED:
                    //stressed
                    for (String line : this.activePet.getArt().stressed()) {
                        System.out.println(line);
                    }
                    break;
                case DEAD:
                    //dead
                    for (String line : this.activePet.getArt().dead()) {
                        System.out.println(line);
                    }
                    break;
                default:
                    break;
            }
        }

        //line to seperate things
        System.out.println();

        //print the status bars
        this.printStatBar("Hunger", this.activePet.getHunger());
        this.printStatBar("Boredom", this.activePet.getBoredom());
        this.printStatBar("Cleanliness", this.activePet.getCleanliness());
        System.out.println("       Mood: " + this.activePet.state);
        System.out.println("       Money: $" + this.activePet.getMoney());

        if (this.activePet.state != State.DEAD) {
            System.out.print("\nEnter an option:\n"
                    + "[1] Feed (" + ((ActivePet.FEED_COST == 0) ? "Free" : "$" + ActivePet.FEED_COST)
                    + "), [2] Play (" + ((ActivePet.PLAY_COST == 0) ? "Free" : "$" + ActivePet.PLAY_COST)
                    + "), [3] Clean (" + ((ActivePet.CLEAN_COST == 0) ? "Free" : "$" + ActivePet.CLEAN_COST)
                    + "), [X] Exit: ");
        } else {
            System.out.println("Uh oh! Your pet died! Game over.\n"
                    + "Your save has been automatically deleted. Press enter to continue.");
        }
    }

    /**
     * Prints a nice looking stats bar. Will trim words over 12 in length.
     *
     * @param str The name/title of the bar
     * @param value the percentage value to print (0-100).
     */
    private void printStatBar(String str, int value) {

        final int MAX_WORD_LENGTH = 12;

        int roundedValue = (int) (5 * (Math.round((double) value / 5)));

        if (str.length() > MAX_WORD_LENGTH) {
            str = str.substring(0, MAX_WORD_LENGTH - 1);
        }

        for (int i = 0; i < MAX_WORD_LENGTH - str.length(); i++) {
            System.out.print(" ");
        }

        System.out.print(str + ": [");

        for (int i = 1; i <= roundedValue / 5; i++) {
            System.out.print("=");
        }

        for (int i = 20; i > roundedValue / 5; i--) {
            System.out.print(" ");
        }

        System.out.print("][ ");

        for (int i = 0; i < 3 - Integer.toString(value).length(); i++) {
            System.out.print(" ");
        }

        System.out.println(value + "% ]");
    }
}
