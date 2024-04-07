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
    public static String getHealth(){
        if(AlinLib.MINECRAFT.player == null) return "-";
        return Localization.DF.format(AlinLib.MINECRAFT.player.getHealth()/2);
    }
    public static String getMaxHealth(){
        if(AlinLib.MINECRAFT.player == null) return "-";
        return Localization.DF.format(AlinLib.MINECRAFT.player.getAttributeValue(Attributes.MAX_HEALTH)/2);
    }
    public static String getPercentHealth(){
        if(AlinLib.MINECRAFT.player == null) return "-";
        return Localization.DF.format((AlinLib.MINECRAFT.player.getHealth()*100)/AlinLib.MINECRAFT.player.getMaxHealth());
    }
    public static String getArmor(){
        if(AlinLib.MINECRAFT.player == null) return "-";
        return Localization.DF.format(AlinLib.MINECRAFT.player.getArmorValue()/2);
    }
    public static String getX(){
        if(AlinLib.MINECRAFT.getCameraEntity() == null) return "-";
        return Localization.getRounding(AlinLib.MINECRAFT.getCameraEntity().getX(), !AlinLib.bariumConfig.getBoolean("USE_EXTENDED_COORDINATES", false));
    }
    public static String getY(){
        if(AlinLib.MINECRAFT.getCameraEntity() == null) return "-";
        return Localization.getRounding(AlinLib.MINECRAFT.getCameraEntity().getY(), !AlinLib.bariumConfig.getBoolean("USE_EXTENDED_COORDINATES", false));
    }
    public static String getZ(){
        if(AlinLib.MINECRAFT.getCameraEntity() == null) return "-";
        return Localization.getRounding(AlinLib.MINECRAFT.getCameraEntity().getZ(), !AlinLib.bariumConfig.getBoolean("USE_EXTENDED_COORDINATES", false));
    }
    public static String getDirection(boolean oneSymbol){
        if(AlinLib.MINECRAFT.player == null) return "-";
        Direction direction = AlinLib.MINECRAFT.player.getDirection();
        return switch (direction) {
            case NORTH -> oneSymbol ? "N" : AlinLib.localization.getLocalization("north", false, false);
            case SOUTH -> oneSymbol ? "S" : AlinLib.localization.getLocalization("south", false, false);
            case WEST -> oneSymbol ? "W" : AlinLib.localization.getLocalization("west", false, false);
            case EAST -> oneSymbol ? "E" : AlinLib.localization.getLocalization("east", false, false);
            default -> oneSymbol ? "?" : AlinLib.localization.getLocalization("unknown", false, false);
        };
    }
}