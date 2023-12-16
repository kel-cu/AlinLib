package ru.kelcuprum.alinlib;

import com.terraformersmc.modmenu.gui.ModsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import ru.kelcuprum.alinlib.command.AlinLibCommand;
import ru.kelcuprum.alinlib.config.Config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AlinLib implements ClientModInitializer {
    public static final Logger LOG = LogManager.getLogger("AlinaLib");
    public static Config bariumConfig = new Config("config/AlibLib/config.json");
    public static HttpClient webClient = HttpClient.newHttpClient();
    @Override
    public void onInitializeClient() {
        bariumConfig.load();
        log("Hello, world!");
        ClientCommandRegistrationCallback.EVENT.register(AlinLibCommand::register);
    }
    public static void log(Component message) { log(message, Level.INFO);}
    public static void log(Component message, Level level) { log(message.getString(), level);}
    public static void log(String message) { log(message, Level.INFO);}
    public static void log(String message, Level level) { LOG.log(level, "[" + LOG.getName() + "] " + message); }

    public static JSONObject getJSONByURL(String url) throws IOException, InterruptedException {
        if(webClient == null) webClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = webClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}
