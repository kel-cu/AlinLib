package ru.kelcuprum.alinlib.info;

import ru.kelcuprum.alinlib.AlinLib;

import java.util.HashMap;

public class Stealth {
    public static HashMap<String, Double> funnyCoordinatesX$alinLib = new HashMap<>();
    public static HashMap<String, Double> funnyCoordinatesZ$alinLib = new HashMap<>();
    ///
    public static HashMap<String, Double> funnyCoordinatesX$imGRUI = new HashMap<>();
    public static HashMap<String, Double> funnyCoordinatesZ$imGRUI = new HashMap<>();

    public static double getFunnyValueCoordinate(double coordinate, String server, String world, boolean isX) {
        if(AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH", false) && AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH.COORDINATES", true)){
            return switch (AlinLib.bariumConfig.getNumber("STREAMER.STEALTH.COORDINATES.TYPE", 0).intValue()) {
                case 1 -> getFunnyValueCoordinate$kelVersion(coordinate, server, world, isX);
                default -> getFunnyValueCoordinate$ImGRUIVersion(coordinate, server, world, isX);
            };
        } else return coordinate;
    }

    public static double getFunnyValueCoordinate$kelVersion(double coordinate, String server, String world, boolean isX) {
        String info = server + "-" + world;
        double value;
        if (isX ? funnyCoordinatesX$alinLib.containsKey(info) : funnyCoordinatesZ$alinLib.containsKey(info))
            value = isX ? funnyCoordinatesX$alinLib.get(info) : funnyCoordinatesZ$alinLib.get(info);
        else {
            while (true) {
                double r = Math.random();
                int i = Math.random() < 0.5 ? -1 : 1;
                double m = Math.random() * 10;
                value = r * i * m;
                if ((value > -1.25 && value < -0.75) || (value > 0.75 && value < 1.25)) {
                    if (isX) funnyCoordinatesX$alinLib.put(info, value);
                    else funnyCoordinatesZ$alinLib.put(info, value);
                    AlinLib.log(info + ": " + value + (isX ? " x" : " z"));
                    break;
                }
            }
        }
        return coordinate * value;
    }

    public static double getFunnyValueCoordinate$ImGRUIVersion(double coordinate, String server, String world, boolean isX) {
        String info = server + "-" + world;
        double value;
        if (isX ? funnyCoordinatesX$imGRUI.containsKey(info) : funnyCoordinatesZ$imGRUI.containsKey(info))
            value = isX ? funnyCoordinatesX$imGRUI.get(info) : funnyCoordinatesZ$imGRUI.get(info);
        else {
            while (true) {
                int i = Math.random() < 0.5 ? -1 : 1;
                value = 2 * Math.random() * i;
                if ((value > -1.25 && value < -0.75) || (value > 0.75 && value < 1.25)) {
                    if (isX) funnyCoordinatesX$imGRUI.put(info, value);
                    else funnyCoordinatesZ$imGRUI.put(info, value);
                    AlinLib.log(info + ": " + value + (isX ? " x" : " z"));
                    break;
                }
            }
        }
        if (!isX) {
            if (AlinLib.MINECRAFT.cameraEntity.getX() > 0 && coordinate > 0 && value > 0) value *= -1;
        }
        return coordinate * value;
    }

    public static String getParsedName(String name) {
        if (AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH", false) && AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH.NAME", true))
            return "?".repeat(name.length());
        else return name;
    }
}
