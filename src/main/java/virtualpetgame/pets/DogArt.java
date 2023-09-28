/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame.pets;

public class DogArt extends Art {

    @Override
    public String[] neutral() {
        return new String[]{
            "       /^-^\\",
            "      / o o \\",
            "     /   Y   \\",
            "     V \\ - / V",
            "       / - \\",
            "      /    |",
            "(    /     |",
            " ===/___) ||"
        };
    }

    @Override
    public String[] happy() {
        return new String[]{
            "       /^-^\\",
            "      / o o \\",
            "     /   Y   \\",
            "     V \\ v / V",
            "       / - \\",
            "      /    |",
            "(    /     |",
            " ===/___) ||"
        };
    }

    @Override
    public String[] sad() {
        return new String[]{
            "       /^-^\\",
            "      / o o \\",
            "     /   Y   \\",
            "     V \\ Λ / V",
            "       / - \\",
            "      /    |",
            "(    /     |",
            " ===/___) ||"
        };
    }

    @Override
    public String[] stressed() {
        return new String[]{
            "       /^-^\\",
            "      / @ @ \\",
            "     /   Y   \\",
            "     V \\ Λ / V",
            "       / - \\",
            "      /    |",
            "(    /     |",
            " ===/___) ||"
        };
    }

    @Override
    public String[] dead() {
        return new String[]{
            "       /^-^\\",
            "      / X X \\",
            "     /   Y   \\",
            "     V \\ Λ / V",
            "       / - \\",
            "      /    |",
            "(    /     |",
            " ===/___) ||"
        };
    }
}
