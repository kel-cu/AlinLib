package ru.kelcuprum.alinlib.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private String _filePath;
    private JsonObject _jsonConfiguration = new JsonObject();
    private final boolean _isFile;

    public Config(String filePath) {
        this._filePath = filePath;
        this._isFile = true;
        load();
    }

    public Config(JsonObject jsonConfiguration) {
        this._jsonConfiguration = jsonConfiguration;
        this._isFile = false;
    }
    // -=-=-=-=-=-=-=-=-=-=-=-
    // EXPERIMENT
    // -=-=-=-=-=-=-=-=-=-=-=-
    private final Map<String, Boolean> booleanDefaults = new HashMap<>();
    private final Map<String, String> stringDefaults = new HashMap<>();
    private final Map<String, Number> numberDefaults = new HashMap<>();
    private final Map<String, JsonObject> jsonObjectDefaults = new HashMap<>();
    private final Map<String, JsonArray> jsonArrayDefaults = new HashMap<>();
    private boolean defaultBooleanValue = false;
    private String defaultStringValue = "";
    private Number defaultNumberValue = 0;
    private JsonObject defaultJsonObjectValue = new JsonObject();
    private JsonArray defaultJsonArrayValue = new JsonArray();
    //
    public Config setDefaultValue(Boolean value){
        this.defaultBooleanValue = value;
        return this;
    }
    public Config setDefaultValue(String value){
        this.defaultStringValue = value;
        return this;
    }
    public Config setDefaultValue(Number value){
        this.defaultNumberValue = value;
        return this;
    }
    public Config setDefaultValue(JsonObject value){
        this.defaultJsonObjectValue = value;
        return this;
    }
    public Config setDefaultValue(JsonArray value){
        this.defaultJsonArrayValue = value;
        return this;
    }
    //
    public Config registerValue(String key, Boolean value){
        booleanDefaults.put(key, value);
        return this;
    }
    public Boolean getBoolean(String key){
        if(booleanDefaults.get(key) == null) return getBoolean(key, defaultBooleanValue);
        else return getBoolean(key, booleanDefaults.get(key));
    }
    public Config registerValue(String key, String value){
        stringDefaults.put(key, value);
        return this;
    }
    public String getString(String key){
        if(stringDefaults.get(key) == null) return getString(key, defaultStringValue);
        else return getString(key, stringDefaults.get(key));
    }
    public Config registerValue(String key, Number value){
        numberDefaults.put(key, value);
        return this;
    }
    public Number getNumber(String key){
        if(numberDefaults.get(key) == null) return getNumber(key, defaultNumberValue);
        else return getNumber(key, numberDefaults.get(key));
    }
    public Config registerValue(String key, JsonObject value){
        jsonObjectDefaults.put(key, value);
        return this;
    }
    public JsonObject getJsonObject(String key){
        if(jsonObjectDefaults.get(key) == null) return getJsonObject(key, defaultJsonObjectValue);
        else return getJsonObject(key, jsonObjectDefaults.get(key));
    }
    public Config registerValue(String key, JsonArray value){
        jsonArrayDefaults.put(key, value);
        return this;
    }
    public JsonArray getJsonArray(String key){
        if(jsonArrayDefaults.get(key) == null) return getJsonArray(key, defaultJsonArrayValue);
        else return getJsonArray(key, jsonArrayDefaults.get(key));
    }
    // -=-=-=-=-=-=-=-=-=-=-=-

    /**
     * Сохранение конфигурации
     */
    public void save() {
        if (!_isFile) return;
        final Path configFile = Path.of(_filePath);

        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, _jsonConfiguration.toString());
        } catch (IOException e) {
            AlinLib.log(e.getLocalizedMessage(), Level.ERROR);
        }
    }

    /**
     * Загрузка файла конфигов
     */
    public void load() {
        if (!_isFile) return;
        final Path configFile = Path.of(_filePath);
        try {
            _jsonConfiguration = configFile.toFile().exists() ? GsonHelper.parse(Files.readString(configFile)) : new JsonObject();
        } catch (Exception e) {
            AlinLib.log(e.getLocalizedMessage(), Level.ERROR);
            save();
        }
    }

    /**
     * Сброс конфигурации
     */
    public Config reset() {
        this._jsonConfiguration = new JsonObject();
        save();
        return this;
    }

    /**
     * Преобразование в JSON
     */
    public JsonObject toJSON() {
        return this._jsonConfiguration;
    }

    /**
     * Преобразование в JSON
     */
    public String toString() {
        return this._jsonConfiguration.toString();
    }

    /**
     * Проверка мембера на нул
     */
    public boolean isJsonNull(String type) {
        if (this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();

        if (!this._jsonConfiguration.has(type))
            return true;

        return this._jsonConfiguration.get(type).isJsonNull();
    }

    /**
     * Получение Boolean значения
     */
    public boolean getBoolean(String type, boolean defaultValue) {
        if (this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();
        if (!isJsonNull(type) && !(this._jsonConfiguration.get(type).getAsJsonPrimitive().isBoolean()))
            setBoolean(type, defaultValue);
        return isJsonNull(type) ? defaultValue : this._jsonConfiguration.get(type).getAsBoolean();
    }

    /**
     * Задать значения Boolean
     */
    public Config setBoolean(String type, boolean newValue) {
        this._jsonConfiguration.addProperty(type, newValue);
        save();
        return this;
    }

    /**
     * Получение String значения
     */

    public String getString(String type, String defaultValue) {
        if (this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();
        if (!isJsonNull(type) && !(this._jsonConfiguration.get(type).getAsJsonPrimitive().isString()))
            setString(type, defaultValue);
        return isJsonNull(type) ? defaultValue : this._jsonConfiguration.get(type).getAsString();
    }

    /**
     * Задать значения String
     */
    public Config setString(String type, String newValue) {
        this._jsonConfiguration.addProperty(type, newValue);
        save();
        return this;
    }

    /**
     * Получение Number значения
     */

    public Number getNumber(String type, Number defaultValue) {
        if (this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();
        if (!isJsonNull(type) && !(this._jsonConfiguration.get(type).getAsJsonPrimitive().isNumber()))
            setNumber(type, defaultValue);
        return isJsonNull(type) ? defaultValue : this._jsonConfiguration.get(type).getAsNumber();
    }

    /**
     * Задать значения Number
     */
    public Config setNumber(String type, Number newValue) {
        this._jsonConfiguration.addProperty(type, newValue);
        save();
        return this;
    }

    /**
     * Получение JsonObject значения
     */

    public JsonObject getJsonObject(String type, JsonObject defaultValue) {
        if (this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();
        if (!isJsonNull(type) && !(this._jsonConfiguration.get(type).getAsJsonPrimitive().isJsonObject()))
            setJsonObject(type, defaultValue);
        return isJsonNull(type) ? defaultValue : this._jsonConfiguration.get(type).getAsJsonObject();
    }

    /**
     * Задать значения JsonObject
     */
    public Config setJsonObject(String type, JsonObject newValue) {
        this._jsonConfiguration.add(type, newValue);
        save();
        return this;
    }

    /**
     * Получение JsonArray значения
     */

    public JsonArray getJsonArray(String type, JsonArray defaultValue) {
        if (this._jsonConfiguration == null) this._jsonConfiguration = new JsonObject();
        if (!isJsonNull(type) && !(this._jsonConfiguration.get(type).getAsJsonPrimitive().isJsonObject()))
            setJsonArray(type, defaultValue);
        return isJsonNull(type) ? defaultValue : this._jsonConfiguration.get(type).getAsJsonArray();
    }

    /**
     * Задать значения JsonArray
     */
    public Config setJsonArray(String type, JsonArray newValue) {
        this._jsonConfiguration.add(type, newValue);
        save();
        return this;
    }

}