package ru.kelcuprum.barium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kelcuprum.barium.command.BariumCommand;
import ru.kelcuprum.barium.config.Config;

public class Barium implements ClientModInitializer {
    public static final Logger LOG = LogManager.getLogger("Barium");
    public static Config bariumConfig = new Config("config/Barium/config.json");
    @Override
    public void onInitializeClient() {
        bariumConfig.load();
        log("Hello, world!");
        ClientCommandRegistrationCallback.EVENT.register(BariumCommand::register);
    }
    public static void log(String message) { log(message, Level.INFO);}
    public static void log(String message, Level level) { LOG.log(level, "[" + LOG.getName() + "] " + message); }
}
