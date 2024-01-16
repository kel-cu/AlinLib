package ru.kelcuprum.alinlib.config;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.GsonHelper;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    private String _filePath;
    private JsonObject _jsonConfiguration = new JsonObject();
    private final boolean _isFile;
    public Config(String filePath){
        this._filePath = filePath;
        this._isFile = true;
    }
    public Config(JsonObject jsonConfiguration){
        this._jsonConfiguration = jsonConfiguration;
        this._isFile = false;
    }
    /**
     * Сохранение конфигурации
     */
    public Config save(){
        if(!_isFile) return this;
        Minecraft mc = Minecraft.getInstance();
        final Path configFile = mc.gameDirectory.toPath().resolve(_filePath);

        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, _jsonConfiguration.toString());
        } catch (IOException e) {
            AlinLib.log(e.getLocalizedMessage(), Level.ERROR);
        }
        return this;
    }

    /**
     * Загрузка файла конфигов
     */
    public Config load(){
        if(!_isFile) return this;
        Minecraft mc = Minecraft.getInstance();
        final Path configFile = mc.gameDirectory.toPath().resolve(_filePath);
        try{
            _jsonConfiguration = configFile.toFile().exists() ? GsonHelper.parse(Files.readString(configFile)) : new JsonObject();
        } catch (Exception e){
            AlinLib.log(e.getLocalizedMessage(), Level.ERROR);
            save();
        }
        return this;
    }
    /**
     * Сброс конфигурации
     */
    public Config reset(){
        this._jsonConfiguration = new JsonObject();
        save();
        return this;
    }
    /**
     * Преобразование в JSON
     */
    public JsonObject toJSON(){
        return this._jsonConfiguration;
    }

    /**
     * Преобразование в JSON
     */
    public String toString(){
        return this._jsonConfiguration.toString();
    }

    /**
     * Проверка мембера на нул
     */
    public boolean isJsonNull(String type) {
        if(this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();

        if (!this._jsonConfiguration.has(type))
            return true;

        return this._jsonConfiguration.get(type).isJsonNull();
    }

    /**
     * Получение Boolean значения
     */
    public boolean getBoolean(String type, boolean defaultValue) {
        if(this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();
        if(!isJsonNull(type) && !(this._jsonConfiguration.get(type).getAsJsonPrimitive().isBoolean())) setBoolean(type, defaultValue);
        return isJsonNull(type) ? defaultValue : this._jsonConfiguration.get(type).getAsBoolean();
    }
    /**
     * Задать значения Boolean
     */
    public Config setBoolean(String type, boolean newValue){
        this._jsonConfiguration.addProperty(type, newValue);
        save();
        return this;
    }
    /**
     * Получение String значения
     */

    public String getString(String type, String defaultValue) {
        if(this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();
        if(!isJsonNull(type) && !(this._jsonConfiguration.get(type).getAsJsonPrimitive().isString())) setString(type, defaultValue);
        return isJsonNull(type) ? defaultValue : this._jsonConfiguration.get(type).getAsString();
    }
    /**
     * Задать значения String
     */
    public Config setString(String type, String newValue){
        this._jsonConfiguration.addProperty(type, newValue);
        save();
        return this;
    }

    /**
     * Получение Number значения
     */

    public Number getNumber(String type, Number defaultValue) {
        if(this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();
        if(!isJsonNull(type) && !(this._jsonConfiguration.get(type).getAsJsonPrimitive().isNumber())) setNumber(type, defaultValue);
        return isJsonNull(type) ? defaultValue : this._jsonConfiguration.get(type).getAsNumber();
    }
    /**
     * Задать значения Number
     */
    public Config setNumber(String type, Number newValue){
        this._jsonConfiguration.addProperty(type, newValue);
        save();
        return this;
    }
}