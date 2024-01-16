package ru.kelcuprum.alinlib;

import com.google.gson.JsonObject;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AlinLib implements ClientModInitializer {
    public static final String MODID = "alinlib";
    public static final Logger LOG = LogManager.getLogger("AlinaLib");
    public static Config bariumConfig = new Config("config/AlinLib/config.json").load();
    public static HttpClient webClient = HttpClient.newHttpClient();
    @Override
    public void onInitializeClient() {
        log("This world goes round and round like a carousel in a circus.");
        log("Maybe the world is a circus?)");
    }
    public static void log(Component message) { log(message, Level.INFO);}
    public static void log(Component message, Level level) { log(message.getString(), level);}
    public static void log(String message) { log(message, Level.INFO);}
    public static void log(String message, Level level) { LOG.log(level, "[" + LOG.getName() + "] " + message); }

    public static JsonObject getJSONByURL(String url) throws IOException, InterruptedException {
        if(webClient == null) webClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = webClient.send(request, HttpResponse.BodyHandlers.ofString());
        return GsonHelper.parse(response.body());
    }
}
