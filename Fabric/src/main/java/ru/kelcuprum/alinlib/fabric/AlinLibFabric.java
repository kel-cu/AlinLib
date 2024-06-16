package ru.kelcuprum.alinlib.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.api.KeyMappingHelper;

public class AlinLibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AlinLib.isFabricLoader = true;
        AlinLib.VERSION = FabricLoader.getInstance().getModContainer(AlinLib.MODID).get().getMetadata().getVersion().getFriendlyString();
        KeyMappingHelper.onRegister = KeyBindingHelper::registerKeyBinding;
        AlinLib.onInitializeClient();
    }
}
