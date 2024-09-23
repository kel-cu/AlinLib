package ru.kelcuprum.alinlib.api;

import net.minecraft.client.KeyMapping;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;

public class KeyMappingHelper {
    //#if FABRIC
    public static OnRegister onRegister = net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper::registerKeyBinding;
    //#elseif NEOFORGE
    //$$ public static final java.util.List<KeyMapping> EXAMPLE_MAPPING = new java.util.ArrayList<>();
    //$$ public static OnRegister onRegister = (s) -> {
    //$$          EXAMPLE_MAPPING.add(s);
    //$$          return s;
    //$$      };
    //#endif

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
