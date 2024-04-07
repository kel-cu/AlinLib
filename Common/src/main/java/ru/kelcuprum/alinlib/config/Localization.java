package ru.kelcuprum.alinlib.config;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;

import net.minecraft.util.GsonHelper;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.parser.StarScript;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Localization {
    public static DecimalFormat DF = new DecimalFormat("#.##");
    public static Parser defaultParser = (s) -> StarScript.run(StarScript.compile(s));
    private Parser parser = defaultParser;
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
    // Получить
    private String getCodeLocalization(){
        try{
            return AlinLib.MINECRAFT.options.languageCode;
        } catch (Exception e){
            return "en_us";
        }
    }
    public JsonObject getJSONFile(){
        try {
            Path localizationFile = Path.of(String.format("%s/%s.json", filePath, getCodeLocalization()));//new File(CLIENT.gameDirectory + filePath + getCodeLocalization() + ".json");
            if (localizationFile.toFile().exists()) {
                return GsonHelper.parse(Files.readString(localizationFile));
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
        return getLocalization(key, clearColor, true);
    }
    public String getLocalization(String key, boolean clearColor, boolean parse){
        return getLocalization(key, clearColor, true, parse);
    }
    public String getLocalization(String key, boolean clearColor, boolean fixFormatCodes, boolean parse){
        String text;
        try {
            JsonObject JSONLocalization = getJSONFile();
            if(!JSONLocalization.has(key) || JSONLocalization.get(key).isJsonNull()) text = getText(modID+ "." + key).getString();
            else text = JSONLocalization.get(key).getAsString();
        } catch (Exception ex) {
            AlinLib.log(ex.getLocalizedMessage());
            text = getDefaultLocalization(key);
        }
        text = clearColor ? clearFormatCodes(text) : fixFormatCodes ? fixFormatCodes(text) : text;
        return parse ? getParsedText(text) : text;
    }
    public String getParsedText(String content){
        if(parser == null) return content;
        return parser.parser(content);
    }
    public String getDefaultLocalization(String key){
        return getText(modID+ "." + key).getString();
    }
    public void resetLocalization(String key){
        setLocalization(key, getDefaultLocalization(key));
    }
    public interface Parser {
        String parser(String content);
    }
    // Заменить
    public void setLocalization(String type, String text){
        try {
            JsonObject JSONLocalization = getJSONFile();
            JSONLocalization.addProperty(type, text);
            Path localizationFile = Path.of(String.format("%s/%s.json", filePath, getCodeLocalization()));
            Files.createDirectories(localizationFile.getParent());
            Files.writeString(localizationFile, JSONLocalization.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setParser(Parser parser){
        this.parser = parser;
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
     * Получение локализации через функцию Minecraft, но в виде String
     * @param key
     * @return
     */
    public static String getStringText(String key){
        return toString(getText(key));
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
