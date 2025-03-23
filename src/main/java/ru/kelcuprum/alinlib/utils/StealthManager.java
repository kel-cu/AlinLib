package ru.kelcuprum.alinlib.utils;

import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.utils.stealth.AbstractStealthManager;
import ru.kelcuprum.alinlib.utils.stealth.AlinStealth;
import ru.kelcuprum.alinlib.utils.stealth.GRUIStealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BooleanSupplier;

public class StealthManager {
    public static HashMap<String, AbstractStealthManager> stealthManagers = new HashMap<>();
    public static List<BooleanSupplier> stealthActivators = new ArrayList<>();
    public static String defaultManager = "";

    public static void registerManager(AbstractStealthManager manager){
        if(stealthManagers.containsKey(manager.id)) AlinLib.LOG.log("Manager by id \"%s\" already exists", manager.id);
        else {
            if(stealthManagers.isEmpty()) defaultManager = manager.id;
            stealthManagers.put(manager.id, manager);
        }
    }

    public static String[] getNames(){
        String[] names = new String[stealthManagers.size()];
        int i = 0;
        for(AbstractStealthManager manager : stealthManagers.values()) {
            names[i] = manager.name.getString();
            i++;
        }
        return names;
    }

    public static String[] getIDs(){
        String[] names = new String[stealthManagers.size()];
        int i = 0;
        for(AbstractStealthManager manager : stealthManagers.values()) {
            names[i] = manager.id;
            i++;
        }
        return names;
    }

    public static AbstractStealthManager getStealthManager(){
        return stealthManagers.getOrDefault(AlinLib.bariumConfig.getString("STEALTH_MANAGER", defaultManager), new AlinStealth());
    }

    public static int getPositionOnIDs(String id){
        String[] ids = getIDs();
        int i = 0;
        for(String idInList : ids){
            if(idInList.equals(id)) return i;
            else i++;
        }
        return i;
    }

    public static void registerActiveManager(BooleanSupplier consumer){
        if(stealthActivators.contains(consumer)) AlinLib.LOG.log("Active Manager already exists");
        else {
            stealthActivators.add(consumer);
        }
    }

    public static void registerDefault(){
        registerActiveManager(() -> AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH", false));
        registerManager(new GRUIStealth());
        registerManager(new AlinStealth());
    }

    public static boolean isStealthActive(){

        for(BooleanSupplier consumer : stealthActivators){
            if(consumer.getAsBoolean()) return true;
        }
        return false;
    }

    public static boolean isStealthCoordinates(){
        return AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH.COORDINATES", true);
    }
    public static boolean isStealthDirection(){
        return AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH.DIRECTION", true);
    }
    public static boolean isStealthName(){
        return AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH.NAME", false);
    }
}
