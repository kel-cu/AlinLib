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
    int FORGOT = 0xFF4f3e60;
    int WHITE = 0xFFFFFFFF;
    int BLACK = 0xFF000000;
    int BLACK_ALPHA = 0x37000000;

    int SODIUM = 0xFF92e0d0;
    int EMBEDDIUM = 0xFFcf7cb6;
    int WEBSITE_WATERPLAYER = 0XFFd03b18;

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
            default -> AlinLib.bariumConfig.getNumber("CHECKBOX.COLOR.CUSTOM", Colors.SPECKLE[0]).intValue();
        };
    }
}
