package ru.kelcuprum.alinlib;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebAPI {
    public static HttpClient webClient = HttpClient.newHttpClient();
    public static JsonObject getJsonObject(String url) throws IOException, InterruptedException {
        if(webClient == null) webClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = webClient.send(request, HttpResponse.BodyHandlers.ofString());
        return GsonHelper.parse(response.body());
    }
    public static JsonArray getJsonArray(String url) throws IOException, InterruptedException {
        if (webClient == null) webClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = webClient.send(request, HttpResponse.BodyHandlers.ofString());
        return GsonHelper.parseArray(response.body());
    }
}
