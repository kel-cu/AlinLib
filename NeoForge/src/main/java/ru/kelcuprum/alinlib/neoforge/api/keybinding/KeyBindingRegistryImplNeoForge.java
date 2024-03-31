package ru.kelcuprum.alinlib.neoforge.api.keybinding;

import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import ru.kelcuprum.alinlib.api.keybinding.KeyBindingRegistryImpl;

public class KeyBindingRegistryImplNeoForge {

    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        KeyBindingRegistryImpl.MODDED_KEY_BINDINGS.forEach(event::register);
    }
}
