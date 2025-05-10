package ru.kelcuprum.alinlib.gui;

import ru.kelcuprum.alinlib.AlinLib;

public interface Colors {
    int ALINA = 0xFF9D1248;
    int[] SPECKLE = {0xFFffdc78, 0xFFcbbaa6};
    int SEADRIVE = 0xFF79c738;
    int CLOWNFISH = 0xFFf1ae31;
    int SELFISH = 0xFFff366e;
    int GROUPIE = 0xFFfc1a47;
    int KENNY = 0xFF627921;
    int CONVICT = 0xFFffdc32;
    int SEABIRD = 0xFFf1ae31;
    int TETRA = 0xFFff67d1;
    int DROPLET = 0xffab2b4e;
    int FORGOT = 0xFF4f3e60;
    int WHITE = 0xFFFFFFFF;
    int BLACK = 0xFF000000;
    int BLACK_ALPHA = 0x37000000;

    // Mods
    int SODIUM = 0xFF92e0d0;
    int EMBEDDIUM = 0xFFcf7cb6;
    int WEBSITE_WATERPLAYER = 0XFFd03b18;

    // 	Catppuccin Mocha
    int CPM_ROSEWATER = 0xfff5e0dc;
    int CPM_FLAMINGO = 0xfff2cdcd;
    int CPM_PINK = 0xfff5c2e7;
    int CPM_MAUVE = 0xffcba6f7;
    int CPM_RED = 0xfff38ba8;
    int CPM_MAROON = 0xffeba0ac;
    int CPM_PEACH = 0xfffab387;
    int CPM_YELLOW = 0xfff9e2af;
    int CPM_GREEN = 0xffa6e3a1;
    int CPM_TEAL = 0xff94e2d5;
    int CPM_SKY = 0xff89dceb;
    int CPM_SAPPHIRE = 0xff74c7ec;
    int CPM_BLUE = 0xff89b4fa;
    int CPM_LAVENDER = 0xffb4befe;
    // Windows 98 Title
    int WIN98_BLUE = 0xFF000080;
    int WIN98_LIGHT_BLUE = 0xFF2089ce;

    static int getCheckBoxColor(){
        return switch (AlinLib.bariumConfig.getNumber("CHECKBOX.COLOR", 0).intValue()){
            case 0 -> Colors.GROUPIE;
            case 1 -> Colors.ALINA;
            case 2 -> Colors.SEADRIVE;
            case 3 -> Colors.TETRA;
            case 4 -> Colors.CONVICT;
            case 5 -> Colors.SEABIRD;
            case 6 -> Colors.SODIUM;
            case 7 -> Colors.EMBEDDIUM;
            case 8 -> Colors.WHITE;
            case 9 -> Colors.WEBSITE_WATERPLAYER;

            case 10 -> Colors.CPM_ROSEWATER;
            case 11 -> Colors.CPM_FLAMINGO;
            case 12 -> Colors.CPM_PINK;
            case 13 -> Colors.CPM_MAUVE;
            case 14 -> Colors.CPM_RED;
            case 15 -> Colors.CPM_MAROON;
            case 16 -> Colors.CPM_PEACH;
            case 17 -> Colors.CPM_YELLOW;
            case 18 -> Colors.CPM_GREEN;
            case 19 -> Colors.CPM_TEAL;
            case 20 -> Colors.CPM_SKY;
            case 21 -> Colors.CPM_SAPPHIRE;
            case 22 -> Colors.CPM_BLUE;
            case 23 -> Colors.CPM_LAVENDER;

            default -> AlinLib.bariumConfig.getNumber("CHECKBOX.COLOR.CUSTOM", Colors.SPECKLE[0]).intValue();
        };
    }
    static int getScrollerColor(){
        return switch (AlinLib.bariumConfig.getNumber("SCROLLER.COLOR", 0).intValue()){
            case 0 -> Colors.WHITE;
            case 1 -> Colors.GROUPIE;
            case 2 -> Colors.ALINA;
            case 3 -> Colors.SEADRIVE;
            case 4 -> Colors.TETRA;
            case 5 -> Colors.CONVICT;
            case 6 -> Colors.SEABIRD;
            case 7 -> Colors.SODIUM;
            case 8 -> Colors.EMBEDDIUM;
            case 9 -> Colors.WEBSITE_WATERPLAYER;

            case 10 -> Colors.CPM_ROSEWATER;
            case 11 -> Colors.CPM_FLAMINGO;
            case 12 -> Colors.CPM_PINK;
            case 13 -> Colors.CPM_MAUVE;
            case 14 -> Colors.CPM_RED;
            case 15 -> Colors.CPM_MAROON;
            case 16 -> Colors.CPM_PEACH;
            case 17 -> Colors.CPM_YELLOW;
            case 18 -> Colors.CPM_GREEN;
            case 19 -> Colors.CPM_TEAL;
            case 20 -> Colors.CPM_SKY;
            case 21 -> Colors.CPM_SAPPHIRE;
            case 22 -> Colors.CPM_BLUE;
            case 23 -> Colors.CPM_LAVENDER;
            default -> AlinLib.bariumConfig.getNumber("CHECKBOX.COLOR.CUSTOM", Colors.CPM_LAVENDER).intValue();
        };
    }
    static int getHorizontalRuleColor(){
        return switch (AlinLib.bariumConfig.getNumber("HORIZONTAL_RULE.COLOR", 0).intValue()){
            case 0 -> Colors.WHITE-0x7F000000;
            case 1 -> Colors.GROUPIE-0x7F000000;
            case 2 -> Colors.ALINA-0x7F000000;
            case 3 -> Colors.SEADRIVE-0x7F000000;
            case 4 -> Colors.TETRA-0x7F000000;
            case 5 -> Colors.CONVICT-0x7F000000;
            case 6 -> Colors.SEABIRD-0x7F000000;
            case 7 -> Colors.SODIUM-0x7F000000;
            case 8 -> Colors.EMBEDDIUM-0x7F000000;
            case 9 -> Colors.WEBSITE_WATERPLAYER-0x7F000000;

            case 10 -> Colors.CPM_ROSEWATER-0x7F000000;
            case 11 -> Colors.CPM_FLAMINGO-0x7F000000;
            case 12 -> Colors.CPM_PINK-0x7F000000;
            case 13 -> Colors.CPM_MAUVE-0x7F000000;
            case 14 -> Colors.CPM_RED-0x7F000000;
            case 15 -> Colors.CPM_MAROON-0x7F000000;
            case 16 -> Colors.CPM_PEACH-0x7F000000;
            case 17 -> Colors.CPM_YELLOW-0x7F000000;
            case 18 -> Colors.CPM_GREEN-0x7F000000;
            case 19 -> Colors.CPM_TEAL-0x7F000000;
            case 20 -> Colors.CPM_SKY-0x7F000000;
            case 21 -> Colors.CPM_SAPPHIRE-0x7F000000;
            case 22 -> Colors.CPM_BLUE-0x7F000000;
            case 23 -> Colors.CPM_LAVENDER-0x7F000000;
            default -> AlinLib.bariumConfig.getNumber("HORIZONTAL_RULE.COLOR.CUSTOM", Colors.CPM_LAVENDER).intValue();
        };
    }

    static int getWMButton(){
        return switch (AlinLib.bariumConfig.getNumber("BUTTON.WM.COLOR", 23).intValue()){
            case 0 -> Colors.GROUPIE;
            case 1 -> Colors.ALINA;
            case 2 -> Colors.SEADRIVE;
            case 3 -> Colors.TETRA;
            case 4 -> Colors.CONVICT;
            case 5 -> Colors.SEABIRD;
            case 6 -> Colors.SODIUM;
            case 7 -> Colors.EMBEDDIUM;
            case 8 -> Colors.WEBSITE_WATERPLAYER;

            case 9 -> Colors.CPM_ROSEWATER;
            case 10 -> Colors.CPM_FLAMINGO;
            case 11 -> Colors.CPM_PINK;
            case 12 -> Colors.CPM_MAUVE;
            case 13 -> Colors.CPM_RED;
            case 14 -> Colors.CPM_MAROON;
            case 15 -> Colors.CPM_PEACH;
            case 16 -> Colors.CPM_YELLOW;
            case 17 -> Colors.CPM_GREEN;
            case 18 -> Colors.CPM_TEAL;
            case 19 -> Colors.CPM_SKY;
            case 20 -> Colors.CPM_SAPPHIRE;
            case 21 -> Colors.CPM_BLUE;
            case 22 -> Colors.CPM_LAVENDER;
            default -> AlinLib.bariumConfig.getNumber("BUTTON.WM.COLOR.CUSTOM", 0xff8261d3).intValue();
        };
    }

    static int getWinColor(){
        return switch (AlinLib.bariumConfig.getNumber("BUTTON.WIN.COLOR", 21).intValue()){
            case 0 -> 0xff989898;
            case 1 -> Colors.GROUPIE;
            case 2 -> Colors.ALINA;
            case 3 -> Colors.SEADRIVE;
            case 4 -> Colors.TETRA;
            case 5 -> Colors.CONVICT;
            case 6 -> Colors.SEABIRD;
            case 7 -> Colors.SODIUM;
            case 8 -> Colors.EMBEDDIUM;
            case 9 -> Colors.WEBSITE_WATERPLAYER;

            case 10 -> Colors.CPM_PINK;
            case 11 -> Colors.CPM_MAUVE;
            case 12 -> Colors.CPM_RED;
            case 13 -> Colors.CPM_MAROON;
            case 14 -> Colors.CPM_PEACH;
            case 15 -> Colors.CPM_YELLOW;
            case 16 -> Colors.CPM_GREEN;
            case 17 -> Colors.CPM_TEAL;
            case 18 -> Colors.CPM_SKY;
            case 19 -> Colors.CPM_SAPPHIRE;
            case 20 -> Colors.CPM_BLUE;
            default -> AlinLib.bariumConfig.getNumber("BUTTON.WIN.COLOR.CUSTOM", ALINA).intValue();
        };
    }

    static int[] getWinTitleGradientColor(){
        return new int[]{AlinLib.bariumConfig.getNumber("BUTTON.WIN.GRADIENT.START", WIN98_BLUE).intValue(), AlinLib.bariumConfig.getNumber("BUTTON.WIN.GRADIENT.END", WIN98_LIGHT_BLUE).intValue()};
    }
}