package ru.kelcuprum.alinlib.api;

import net.minecraft.client.KeyMapping;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;

public class KeyMappingHelper {
    public static OnRegister onRegister;

    public static KeyMapping register(KeyMapping mapping){
        if(onRegister != null) return onRegister.onRegister(mapping);
        else {
            AlinLib.log("KeyMapping registration did not occur, there is no registration function", Level.ERROR);
            return mapping;
        }
    }

    public interface OnRegister {
        KeyMapping onRegister(KeyMapping mapping);
    }
}
