/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame.pets;

import java.io.Serializable;

//ensure art methods share the same names. Serializable for saving/loading.

public abstract class Art implements Serializable{
    public abstract String[] neutral();
    public abstract String[] happy();
    public abstract String[] sad();
    public abstract String[] stressed();
    public abstract String[] dead();
}
