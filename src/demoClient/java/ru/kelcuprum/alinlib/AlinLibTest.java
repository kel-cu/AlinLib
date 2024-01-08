package ru.kelcuprum.alinlib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import ru.kelcuprum.alinlib.command.AlinLibCommand;
import ru.kelcuprum.alinlib.config.Localization;

public class AlinLibTest implements ClientModInitializer {
    public static final String MODID = "alinlibtest";
    public static Localization localization = new Localization(MODID, "config/AlinLib/lang/");

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(AlinLibCommand::register);
    }
}
