/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame.pets;

public class Cat extends Pet {
    
    //higher value is more difficult
    private static final int HUNGER_MULTIPLIER = 2;
    private static final int BOREDOM_MULTIPLIER = 5;
    private static final int CLEANLINESS_MULTIPLIER = 1;
    private static final Art art = new CatArt();
    
    @Override
    public Art getArt(){
        return art;
    }
    
    @Override
    public int getHungerMultiplier(){
        return HUNGER_MULTIPLIER;
    }
    @Override
    public int getBoredomMultiplier(){
        return BOREDOM_MULTIPLIER;
    }
    @Override
    public int getCleanlinessMultiplier(){
        return CLEANLINESS_MULTIPLIER;
    }
}
