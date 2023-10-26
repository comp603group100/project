/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame.pets;

import java.io.Serializable;

//ensure methods are correctly named, serialize for saving.

public abstract class Pet implements Serializable{
    public abstract Art getArt();
    
    //not sure if should use int or float yet
    public abstract int getHungerMultiplier();
    public abstract int getBoredomMultiplier();
    public abstract int getCleanlinessMultiplier();
}