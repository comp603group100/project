/**
 * @author group100 (19094184, 19088716)
 */
package virtualpetgame.pets;

public class MonkeyArt extends Art {

    @Override
    public String[] neutral() {
        return new String[]{
            "            .-`-,\\__",
            "              .\"`   `,",
            "            .'_.  ._  `;.",
            "        __ / `      `  `.\\ .--.",
            "       /--,| o)   o)     )`_.-,)",
            "      |    ;.-----.__ _-');   /",
            "       '--./         `.`/  `\"`",
            "          :   '`      |.",
            "          |   ____    //",
            "           \\         /'",
            "            `------' \\",
            "             _/       `--..."
        };
    }

    @Override
    public String[] happy() {
        return new String[]{
            "            .-`-,\\__",
            "              .\"`   `,",
            "            .'_.  ._  `;.",
            "        __ / `      `  `.\\ .--.",
            "       /--,| 0)   0)     )`_.-,)",
            "      |    ;.-----.__ _-');   /",
            "       '--./         `.`/  `\"`",
            "          :   '`      |.",
            "          | \\     /  //",
            "           \\ '---'   /'",
            "            `------' \\",
            "             _/       `--..."
        };
    }

    @Override
    public String[] sad() {
        return new String[]{
            "            .-`-,\\__",
            "              .\"`   `,",
            "            .'_.  ._  `;.",
            "        __ / `      `  `.\\ .--.",
            "       /--,| o)   o)     )`_.-,)",
            "      |    ;.-----.__ _-');   /",
            "       '--./         `.`/  `\"`",
            "          :   '`      |.",
            "          |   ____    //",
            "           \\ /    \\  /'",
            "            `------' \\",
            "             _/       `--..."
        };
    }

    @Override
    public String[] stressed() {
        return new String[]{
            "            .-`-,\\__",
            "              .\"`   `,",
            "            .'_.  ._  `;.",
            "        __ / `      `  `.\\ .--.",
            "       /--,| @)   @)     )`_.-,)",
            "      |    ;.-----.__ _-');   /",
            "       '--./         `.`/  `\"`",
            "          :   '`      |.",
            "          |      __   //",
            "           \\         /'",
            "            `------' \\",
            "             _/       `--..."
        };
    }

    @Override
    public String[] dead() {
        return new String[]{
            "            .-`-,\\__",
            "              .\"`   `,",
            "            .'_.  ._  `;.",
            "        __ / `      `  `.\\ .--.",
            "       /--,| X)   X)     )`_.-,)",
            "      |    ;.-----.__ _-');   /",
            "       '--./         `.`/  `\"`",
            "          :   '`      |.",
            "          |   ====    //",
            "           \\         /'",
            "            `------' \\",
            "             _/       `--..."
        };
    }
}
