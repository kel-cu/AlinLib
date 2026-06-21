package ru.kelcuprum.alinlib.utils.stealth;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.info.World;

import java.util.HashMap;

public class GRUIStealth extends AbstractStealthManager {
    public static HashMap<String, Double> funnyCoordinatesX = new HashMap<>();
    public static HashMap<String, Double> funnyCoordinatesZ = new HashMap<>();
    public GRUIStealth() {
        super("imgrui", "ImGRUI Ver.");
    }

    @Override
    public double getX(Entity player) {
        return getFunnyValueCoordinate(player.getX(), (AlinLib.MINECRAFT.isLocalServer() || AlinLib.MINECRAFT.hasSingleplayerServer()) ? "single" : AlinLib.MINECRAFT.getCurrentServer().ip, World.getCodeName(), true);
    }

    @Override
    public double getY(Entity player) {
        return player.getY();
    }

    @Override
    public double getZ(Entity player) {
        return getFunnyValueCoordinate(player.getZ(), (AlinLib.MINECRAFT.isLocalServer() || AlinLib.MINECRAFT.hasSingleplayerServer()) ? "single" : AlinLib.MINECRAFT.getCurrentServer().ip, World.getCodeName(), false);
    }
    public static double getFunnyValueCoordinate(double coordinate, String server, String world, boolean isX) {
        String info = server + "-" + world;
        double value;
        if (isX ? funnyCoordinatesX.containsKey(info) : funnyCoordinatesZ.containsKey(info))
            value = isX ? funnyCoordinatesX.get(info) : funnyCoordinatesZ.get(info);
        else {
            while (true) {
                int i = Math.random() < 0.5 ? -1 : 1;
                value = 2 * Math.random() * i;
                if ((value > -1.25 && value < -0.75) || (value > 0.75 && value < 1.25)) {
                    if (isX) funnyCoordinatesX.put(info, value);
                    else funnyCoordinatesZ.put(info, value);
                    AlinLib.LOG.log(info + ": " + value + (isX ? " x" : " z"));
                    break;
                }
            }
        }
        if (!isX) {
            assert AlinLib.MINECRAFT.player != null;
            if (AlinLib.MINECRAFT.player.getX() > 0 && coordinate > 0 && value > 0) value *= -1;
        }
        return coordinate * value;
    }

    @Override
    public String getName(String player) {
        return "?".repeat(player.length());
    }

    @Override
    public Direction getDirection(Direction direction) {
        if(direction == null) return Direction.NORTH;
        else return switch (direction) {
            case DOWN -> Direction.UP;
            case UP -> Direction.DOWN;
            case NORTH -> Direction.EAST;
            case SOUTH -> Direction.WEST;

            case WEST -> Direction.NORTH;
            case EAST -> Direction.SOUTH;
        };
    }
}
