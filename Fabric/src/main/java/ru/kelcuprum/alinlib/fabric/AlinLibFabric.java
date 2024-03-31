package ru.kelcuprum.alinlib.fabric;

import net.fabricmc.api.ClientModInitializer;
import ru.kelcuprum.alinlib.AlinLib;

public class AlinLibFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AlinLib.isFabricLoader = true;
        AlinLib.onInitializeClient();
    }
}
