/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame.pets;

public class CatArt extends Art {

    @Override
    public String[] neutral() {
        return new String[]{
            "    /\\_____/\\       ",
            "   /  o   o  \\      ",
            "  ( == _-_ == )     ",
            "   )         (      ",
            "  (           )     ",
            " ( (  )   (  ) )    ",
            "(__(__)___(__)__)   "};
    }

    @Override
    public String[] happy() {
        return new String[]{
            "    /\\_____/\\       ",
            "   /  o   o  \\      ",
            "  ( = \\___/ = )     ",
            "   )         (      ",
            "  (           )     ",
            " ( (  )   (  ) )    ",
            "(__(__)___(__)__)   "};
    }

    @Override
    public String[] sad() {
        return new String[]{
            "    /\\_____/\\       ",
            "   / `o   o` \\      ",
            "  (  == ^ ==  )     ",
            "   )         (      ",
            "  (           )     ",
            " ( (  )   (  ) )    ",
            "(__(__)___(__)__)   "};
    }

    @Override
    public String[] stressed() {
        return new String[]{
            "    /\\_____/\\       ",
            "   /  @   @  \\      ",
            "  (    =^=    )     ",
            "   )         (      ",
            "  (           )     ",
            " ( (  )   (  ) )    ",
            "(__(__)___(__)__)   "};
    }

    @Override
    public String[] dead() {
        return new String[]{
            "    /\\_____/\\       ",
            "   /  X   X  \\      ",
            "  (  == _ ==  )     ",
            "   )         (      ",
            "  (           )     ",
            " ( (  )   (  ) )    ",
            "(__(__)___(__)__)   "};
    }
}
