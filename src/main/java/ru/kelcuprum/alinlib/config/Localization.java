package ru.kelcuprum.alinlib.config;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import net.minecraft.util.GsonHelper;
import ru.kelcuprum.alinlib.AlinLib;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Localization {
    private static final int codes = 23;
    private static final Map<String, String> formatCodes = IntStream.range(0, codes)
            .boxed()
            .collect(Collectors.toMap(List.of(new String[]{
                    "&4", "&c", "&6", "&e", "&z", "&a", "&b", "&3", "&1", "&9", "&d", "&5", "&f", "&7", "&8", "&0",
                    "&r", "&l", "&o", "&n", "&m", "&k", "&x"
            })::get, List.of(new String[]{
                    "§4", "§c", "§6", "§e", "§z", "§a", "§b", "§3", "§1", "§9", "§d", "§5", "§f", "§7", "§8", "§0",
                    "§r", "§l", "§o", "§n", "§m", "§k", "§x"
            })::get));
    public String modID;
    public String filePath;
    public Localization(String modID, String filePath){
        this.modID = modID;
        this.filePath = filePath;
    }
    private String getCodeLocalization(){
        try{
            return Minecraft.getInstance().options.languageCode;
        } catch (Exception e){
            return "en_us";
        }
    }
    public JsonObject getJSONFile(){
        try {
            Minecraft CLIENT = Minecraft.getInstance();
            File localizationFile = new File(CLIENT.gameDirectory + filePath + getCodeLocalization() + ".json");
            if (localizationFile.exists()) {
                return GsonHelper.parse(Files.readString(localizationFile.toPath()));
            } else {
                return new JsonObject();
            }
        } catch (Exception ex){
            AlinLib.log(ex.getLocalizedMessage());
            return new JsonObject();
        }
    }
    public String getLocalization(String key){
        return getLocalization(key, false);
    }
    public String getLocalization(String key, boolean clearColor){
        String text;
        try {
            JsonObject JSONLocalization = getJSONFile();
            if(JSONLocalization.get(key) != null && !JSONLocalization.get(key).isJsonNull()) text = getText(modID+ "." + key).getString();
            else text = JSONLocalization.get(key).getAsString();
        } catch (Exception ex) {
            AlinLib.log(ex.getLocalizedMessage());
            text = getText(modID+ "." + key).getString();
        }
        return clearColor ? clearFormatCodes(text) : fixFormatCodes(text);
    }
    public void setLocalization(String type, String text){
        try {
            JsonObject JSONLocalization = getJSONFile();
            JSONLocalization.addProperty(type, text);
            Minecraft CLIENT = Minecraft.getInstance();
            File localizationFile = new File(CLIENT.gameDirectory + filePath+getCodeLocalization()+".json");
            Files.createDirectories(localizationFile.toPath().getParent());
            Files.writeString(localizationFile.toPath(), JSONLocalization.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // FOR EVERYTHING FUNCTION NOT IN THIS CLASS
    public static String getRounding(double number){return getRounding(number, false);}
    public static String getRounding(double number, boolean isToInt){
        String text = String.format("%.3f", number);
        if(isToInt) text = text.substring(0, text.length()-4);
        return text;
    }

    /**
     * Получение локализации через функцию Minecraft
     * @param key
     * @return
     */
    public static Component getText(String key){
        return Component.translatable(key);
    }

    /**
     * Перевод String в MutableText
     * @return MutableText
     */
    public static Component toText(String text){
        return Component.literal(text);
    }

    /**
     * Перевод Text в String
     * @return MutableText
     */
    public static String toString(Component text){
        return text.getString();
    }
    public static String clearFormatCodes(String text) {
        return text.replaceAll("([§&][a-f0-9k-orz])", "").replaceAll("#[a-fA-F0-9]{6}", "");
    }

    public static String fixFormatCodes(String text) {
        for (String formatCode : formatCodes.keySet()) {
            text = text.replaceAll(formatCode, formatCodes.get(formatCode));
        }
        return text;
    }
}
