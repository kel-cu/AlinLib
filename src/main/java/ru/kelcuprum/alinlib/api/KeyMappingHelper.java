package ru.kelcuprum.alinlib.api;

import net.minecraft.client.KeyMapping;
import ru.kelcuprum.alinlib.AlinLib;

public class KeyMappingHelper {
    //#if FABRIC
    public static OnRegister onRegister = net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper::registerKeyMapping;
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
            AlinLib.LOG.error("KeyMapping registration did not occur, there is no registration function");
            return mapping;
        }
    }

    public interface OnRegister {
        KeyMapping onRegister(KeyMapping mapping);
    }
}
