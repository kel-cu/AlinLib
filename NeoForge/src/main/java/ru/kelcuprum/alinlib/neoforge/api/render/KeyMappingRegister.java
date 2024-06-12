package ru.kelcuprum.alinlib.neoforge.api.render;

import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import java.util.ArrayList;
import java.util.List;

public class KeyMappingRegister {

    public static final List<KeyMapping> EXAMPLE_MAPPING = new ArrayList<>();

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        for(KeyMapping mapping : EXAMPLE_MAPPING) {
            event.register(mapping);
        }
    }
}
