/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame;

import java.io.Serializable;
import virtualpetgame.pets.*;

//Better than using static finals
enum State implements Serializable {
    HAPPY("Happy"),
    NEUTRAL("Neutral"),
    SAD("Sad"),
    STRESSED("Stressed"),
    DEAD("Dead");

    private final String str;

    State(String s) {
        this.str = s;
    }
    
    @Override
    public String toString() {
        return str;
    }
}

public class ActivePet implements Serializable {

    //cost prices
    public static final int FEED_COST = 10;
    public static final int PLAY_COST = 0;
    public static final int CLEAN_COST = 5;

    //pts to adjust when funcs are called
    private static final int FEED_POINTS = 5;
    private static final int PLAY_POINTS = 5;
    private static final int CLEAN_POINTS = 5;

    //Multipliers and art are gathered from the pet object passed into the constructor.
    private final Art art;
    private final int hungerMultiplier;
    private final int boredomMultiplier;
    private final int cleanlinessMultiplier;

    private int hunger;
    private int boredom;
    private int cleanliness;
    private int money;
    private final String petType;
    State state;

    /**
     * Creates a new ActivePet (i.e. a game save), of specified pet type
     *
     * @param pet the type of pet to use for the game.
     */
    public ActivePet(Pet pet) {
        this.art = pet.getArt();
        this.hungerMultiplier = pet.getHungerMultiplier();
        this.boredomMultiplier = pet.getBoredomMultiplier();
        this.cleanlinessMultiplier = pet.getCleanlinessMultiplier();
        this.petType = pet.getClass().getSimpleName();
        this.hunger = 0;
        this.boredom = 0;
        this.cleanliness = 100;
        this.money = 15;
        this.state = State.HAPPY;
    }

    public String getPetType() {
        return petType;
    }

    public Art getArt() {
        return art;
    }

    public int getHunger() {
        return hunger;
    }

    public int getBoredom() {
        return boredom;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public int getMoney() {
        return money;
    }

    /**
     * Increases the pet's money. Increases differently based on pet state.
     */
    public void increaseMoney() {
        if (null != state) {
            switch (state) {
                case HAPPY:
                    this.money += 3;
                    break;
                case NEUTRAL:
                    this.money += 2;
                    break;
                case SAD:
                    this.money += 1;
                    break;
                default: //lower states earn no money.
                    break;
            }
        }
    }

    /**
     * increases pet's hunger, using the formula h(new) = h + (factor *
     * multiplier). The multiplier is decided by the pet type.
     *
     * @param factor the factor to use in the formula. Low values preferred.
     */
    public void increaseHunger(int factor) {
        this.hunger += (factor * hungerMultiplier);

        if (this.hunger > 100) {
            this.hunger = 100;
        }

        this.updateState();
    }

    /**
     * increases pet's boredom, using the formula b(new) = b + (factor *
     * multiplier). The multiplier is decided by the pet type.
     *
     * @param factor the factor to use in the formula. Low values preferred.
     */
    public void increaseBoredom(int factor) {
        this.boredom += (factor * boredomMultiplier);

        if (this.boredom > 100) {
            this.boredom = 100;
        }

        this.updateState();
    }

    /**
     * decreases pet's cleanliness, using the formula c(new) = c - (factor *
     * multiplier). The multiplier is decided by the pet type.
     *
     * @param factor the factor to use in the formula. Low values preferred.
     */
    public void decreaseCleanliness(int factor) {
        this.cleanliness -= (factor * cleanlinessMultiplier);

        if (this.cleanliness < 0) {
            this.cleanliness = 0;
        }

        this.updateState();
    }

    public void spend(int n) {
        if (this.money - n >= 0) {
            this.money -= n;
        }
    }

    /**
     * Feeds the pet, decreasing it's hunger by 5.
     */
    public void feed() {
        this.hunger -= FEED_POINTS;

        if (this.hunger < 0) {
            this.hunger = 0;
        }

        this.updateState();
    }

    /**
     * plays with the pet, decreasing boredom by 5.
     */
    public void play() {
        this.boredom -= PLAY_POINTS;

        if (this.boredom < 0) {
            this.boredom = 0;
        }

        this.updateState();
    }

    /**
     * cleans the pet, increasing cleanliness by 5.
     */
    public void clean() {
        this.cleanliness += CLEAN_POINTS;

        if (this.cleanliness > 100) {
            this.cleanliness = 100;
        }

        this.updateState();
    }

    /**
     * Called automatically after changing pet's stats, but can also be called
     * manually if needed. Ensures the pet's emotional state (i.e artwork) is
     * consistent with it's stats.
     */
    public void updateState() {
        if (hunger <= 20 && boredom <= 20 && cleanliness >= 75) { //happy
            this.state = State.HAPPY;
        } else if ((hunger <= 50 || boredom <= 50) && cleanliness >= 50) { //neutral
            this.state = State.NEUTRAL;
        } else if (hunger <= 75 || boredom <= 75 || cleanliness >= 25) { //sad
            this.state = State.SAD;
        } else if (hunger < 100 || boredom < 100 || cleanliness > 0) { //stressed
            this.state = State.STRESSED;
        } else if (hunger == 100 && boredom == 100 && cleanliness == 0) { //dead
            this.state = State.DEAD;
        }
    }
}
