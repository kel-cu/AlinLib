package ru.kelcuprum.alinlib.api.keybinding;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import ru.kelcuprum.alinlib.mixin.keybinding.KeyBindingAccessor;

import java.util.Objects;

public final class KeyBindingHelper {
    private KeyBindingHelper() {
    }

    /**
     * Registers the KeyMapping and add the KeyMapping category if required.
     *
     * @param KeyMapping the KeyMapping
     * @return the KeyMapping itself
     * @throws IllegalArgumentException when a key binding with the same ID is already registered
     */
    public static KeyMapping registerKeyMapping(KeyMapping KeyMapping) {
        Objects.requireNonNull(KeyMapping, "key binding cannot be null");
        return KeyBindingRegistryImpl.registerKeyMapping(KeyMapping);
    }

    /**
     * Returns the configured KeyCode bound to the KeyMapping from the player's settings.
     *
     * @param KeyMapping the KeyMapping
     * @return configured KeyCode
     */
    public static InputConstants.Key getBoundKeyOf(KeyMapping KeyMapping) {
        return ((KeyBindingAccessor) KeyMapping).alinlib_getBoundKey();
    }
}