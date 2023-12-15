package ru.kelcuprum.alinlib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kelcuprum.alinlib.command.AlinLibCommand;
import ru.kelcuprum.alinlib.config.Config;

public class AlinLib implements ClientModInitializer {
    public static final Logger LOG = LogManager.getLogger("AlinaLib");
    public static Config bariumConfig = new Config("config/AlibLib/config.json");
    @Override
    public void onInitializeClient() {
        bariumConfig.load();
        log("Hello, world!");
        ClientCommandRegistrationCallback.EVENT.register(AlinLibCommand::register);
    }
    public static void log(String message) { log(message, Level.INFO);}
    public static void log(String message, Level level) { LOG.log(level, "[" + LOG.getName() + "] " + message); }
}
