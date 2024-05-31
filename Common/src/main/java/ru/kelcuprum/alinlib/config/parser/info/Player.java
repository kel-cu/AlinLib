package ru.kelcuprum.alinlib.config.parser.info;

import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Localization;

public class Player {
    public static String getItemName(){
        if(AlinLib.MINECRAFT.player == null) return "-";
        ItemStack main_hand = AlinLib.MINECRAFT.player.getItemInHand(InteractionHand.MAIN_HAND);
        String main_hand_item = main_hand.getItem().toString();
        if(main_hand_item.equals("air") && AlinLib.bariumConfig.getBoolean("VIEW_ITEM_OFF_HAND", false)){
            ItemStack off_hand = AlinLib.MINECRAFT.player.getItemInHand(InteractionHand.OFF_HAND);
            String off_hand_item = off_hand.getItem().toString();
            if(off_hand_item.equals("air")) return null;
            else return off_hand.getHoverName().getString();
        } else {
            if(main_hand_item.equals("air")) return null;
            else return main_hand.getHoverName().getString();
        }
    }
    public static int getItemCount(){
        if(AlinLib.MINECRAFT.player == null) return 0;
        ItemStack main_hand = AlinLib.MINECRAFT.player.getItemInHand(InteractionHand.MAIN_HAND);
        String main_hand_item = main_hand.getItem().toString();
        if(main_hand_item.equals("air") && AlinLib.bariumConfig.getBoolean("VIEW_ITEM_OFF_HAND", false)){
            ItemStack off_hand = AlinLib.MINECRAFT.player.getItemInHand(InteractionHand.OFF_HAND);
            String off_hand_item = off_hand.getItem().toString();
            if(off_hand_item.equals("air")) return 0;
            else return off_hand.getCount();
        } else {
            if(main_hand_item.equals("air")) return 0;
            else return main_hand.getCount();
        }
    }
    public static double getHealth(){
        if(AlinLib.MINECRAFT.player == null) return 0;
        return Localization.round(AlinLib.MINECRAFT.player.getHealth()/2, 2);
    }
    public static double getMaxHealth(){
        if(AlinLib.MINECRAFT.player == null) return 0;
        return Localization.round(AlinLib.MINECRAFT.player.getAttributeValue(Attributes.MAX_HEALTH)/2, 2);
    }
    public static double getPercentHealth(){
        if(AlinLib.MINECRAFT.player == null) return 0;
        return Localization.round((AlinLib.MINECRAFT.player.getHealth()*100)/AlinLib.MINECRAFT.player.getMaxHealth(), 2);
    }
    public static double getArmor(){
        if(AlinLib.MINECRAFT.player == null) return 0;
        return Localization.round((double) AlinLib.MINECRAFT.player.getArmorValue() /2, 2);
    }
    public static int getHunger(){
        if(AlinLib.MINECRAFT.player == null) return 0;
        return AlinLib.MINECRAFT.player.getFoodData().getFoodLevel();
    }
    public static double getX(){
        if(AlinLib.MINECRAFT.getCameraEntity() == null) return 404;
        double x = AlinLib.MINECRAFT.getCameraEntity().getX();
        if(AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH", false)){
            x = AlinLib.getFunnyValueCoordinate(x, (AlinLib.MINECRAFT.isLocalServer() || AlinLib.MINECRAFT.isSingleplayer()) ? "single" : AlinLib.MINECRAFT.getCurrentServer().ip, World.getCodeName(), true);
        }
        return Localization.getDoubleRounding(x, !AlinLib.bariumConfig.getBoolean("LOCALIZATION.EXTENDED_COORDINATES", false));
    }
    public static double getY(){
        if(AlinLib.MINECRAFT.getCameraEntity() == null) return 404;
        double y = AlinLib.MINECRAFT.getCameraEntity().getY();
        return Localization.getDoubleRounding(y, !AlinLib.bariumConfig.getBoolean("LOCALIZATION.EXTENDED_COORDINATES", false));
    }
    public static double getZ(){
        if(AlinLib.MINECRAFT.getCameraEntity() == null) return 404;
        double z = AlinLib.MINECRAFT.getCameraEntity().getZ();
        if(AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH", false)){
            z = AlinLib.getFunnyValueCoordinate(z, (AlinLib.MINECRAFT.isLocalServer() || AlinLib.MINECRAFT.isSingleplayer()) ? "single" : AlinLib.MINECRAFT.getCurrentServer().ip, World.getCodeName(), false);
        }
        return Localization.getDoubleRounding(z, !AlinLib.bariumConfig.getBoolean("LOCALIZATION.EXTENDED_COORDINATES", false));
    }
    public static String getDirection(boolean oneSymbol){
        if(AlinLib.MINECRAFT.player == null) return "-";
        Direction direction = AlinLib.MINECRAFT.player.getDirection();
        if(AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH", false)){
            double r = Math.random();
            direction = r < 0.25 ? Direction.NORTH : r < 0.5 ? Direction.SOUTH : r < 0.75 ? Direction.WEST : Direction.EAST;
        }
        return switch (direction) {
            case NORTH -> oneSymbol ? "N" : AlinLib.localization.getLocalization("north", false, false);
            case SOUTH -> oneSymbol ? "S" : AlinLib.localization.getLocalization("south", false, false);
            case WEST -> oneSymbol ? "W" : AlinLib.localization.getLocalization("west", false, false);
            case EAST -> oneSymbol ? "E" : AlinLib.localization.getLocalization("east", false, false);
            default -> oneSymbol ? "?" : AlinLib.localization.getLocalization("unknown", false, false);
        };
    }
}
