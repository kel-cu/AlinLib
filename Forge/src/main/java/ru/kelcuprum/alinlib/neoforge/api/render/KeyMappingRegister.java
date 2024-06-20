package ru.kelcuprum.alinlib.neoforge.api.render;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
