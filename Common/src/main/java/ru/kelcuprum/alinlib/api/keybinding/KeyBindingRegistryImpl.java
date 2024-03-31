package ru.kelcuprum.alinlib.api.keybinding;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.mixin.keybinding.KeyBindingAccessor;


public final class KeyBindingRegistryImpl {
    public static final List<KeyMapping> MODDED_KEY_BINDINGS = new ReferenceArrayList<>(); // ArrayList with identity based comparisons for contains/remove/indexOf etc., required for correctly handling duplicate keybinds

    private KeyBindingRegistryImpl() {
    }

    private static Map<String, Integer> getCategoryMap() {
        return KeyBindingAccessor.alinlib_getCategoryMap();
    }

    public static boolean addCategory(String categoryTranslationKey) {
        Map<String, Integer> map = getCategoryMap();

        if (map.containsKey(categoryTranslationKey)) {
            return false;
        }

        Optional<Integer> largest = map.values().stream().max(Integer::compareTo);
        int largestInt = largest.orElse(0);
        map.put(categoryTranslationKey, largestInt + 1);
        return true;
    }

    public static KeyMapping registerKeyMapping(KeyMapping binding) {
        if (AlinLib.isFabricLoader && Minecraft.getInstance().options != null) {
            throw new IllegalStateException("GameOptions has already been initialised");
        }

        for (KeyMapping existingKeyMappings : MODDED_KEY_BINDINGS) {
            if (existingKeyMappings == binding) {
                throw new IllegalArgumentException("Attempted to register a key binding twice: " + binding.getTranslatedKeyMessage());
            } else if (existingKeyMappings.getTranslatedKeyMessage().equals(binding.getTranslatedKeyMessage())) {
                throw new IllegalArgumentException("Attempted to register two key bindings with equal ID: " + binding.getTranslatedKeyMessage() + "!");
            }
        }

        // This will do nothing if the category already exists.
        addCategory(binding.getCategory());
        MODDED_KEY_BINDINGS.add(binding);
        return binding;
    }

    /**
     * Processes the KeyMappings array for our modded ones by first removing existing modded KeyMappings and readding them,
     * we can make sure that there are no duplicates this way.
     */
    public static KeyMapping[] process(KeyMapping[] keysAll) {
        List<KeyMapping> newKeysAll = Lists.newArrayList(keysAll);
        newKeysAll.removeAll(MODDED_KEY_BINDINGS);
        newKeysAll.addAll(MODDED_KEY_BINDINGS);
        return newKeysAll.toArray(new KeyMapping[0]);
    }
}