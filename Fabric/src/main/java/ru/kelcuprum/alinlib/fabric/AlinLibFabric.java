package ru.kelcuprum.alinlib.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import ru.kelcuprum.alinlib.AlinLib;

public class AlinLibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AlinLib.isFabricLoader = true;
        AlinLib.VERSION = FabricLoader.getInstance().getModContainer(AlinLib.MODID).get().getMetadata().getVersion().getFriendlyString();
        AlinLib.onInitializeClient();
    }
}
