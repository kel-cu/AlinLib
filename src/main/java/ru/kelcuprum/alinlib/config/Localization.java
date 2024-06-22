package ru.kelcuprum.alinlib.config;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;

import net.minecraft.util.GsonHelper;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Localization {
    public static Parser defaultParser = (s) -> AlinLib.starScript.run(AlinLib.starScript.compile(s));
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
            Path localizationFile = Path.of(String.format("%s/%s.json", filePath, getCodeLocalization()));
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
            AlinLib.log(e.getLocalizedMessage(), Level.ERROR);
        }
    }
    public void setParser(Parser parser){
        this.parser = parser;
    }

    // FOR EVERYTHING FUNCTION NOT IN THIS CLASS

    public static String getRounding(double number){
        return String.valueOf(getRounding(number, false));
    }
    public static String getRounding(double number, boolean isToInt){
        String value = String.valueOf(getDoubleRounding(number, isToInt));
        if(isToInt) value = value.split("\\.")[0];
        return value;
    }

    public static double getDoubleRounding(double number){return getDoubleRounding(number, false);}
    public static double getDoubleRounding(double number, boolean isToInt){
        return isToInt ? (int) number : round(number, 3);
    }

    public static Component getText(String key){
        return Component.translatable(key);
    }

    public static String getStringText(String key){
        return toString(getText(key));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static Component toText(String text){
        return Component.literal(text);
    }

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
