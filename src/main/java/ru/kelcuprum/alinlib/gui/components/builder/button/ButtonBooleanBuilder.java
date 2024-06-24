package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;

public class ButtonBooleanBuilder extends AbstractBuilder {
    public ButtonBoolean.OnPress onPress;
    public Config config;
    public String configType;
    public boolean defaultValue;
    public ButtonBooleanBuilder(boolean defaultValue){
        this(Component.empty(), defaultValue);
    }
    public ButtonBooleanBuilder(Component title, boolean defaultValue){
        this(title, defaultValue, null);
    }
    public ButtonBooleanBuilder(Component title, boolean defaultValue, ButtonBoolean.OnPress onPress){
        super(title);
        this.defaultValue = defaultValue;
        this.onPress = onPress;
    }
    // Default value
    public ButtonBooleanBuilder setDefaultValue(boolean defaultValue){
        this.defaultValue = defaultValue;
        return this;
    }
    public boolean getDefaultValue(){
        return this.defaultValue;
    }
    // OnPress
    public ButtonBooleanBuilder setOnPress(ButtonBoolean.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public ButtonBoolean.OnPress getOnPress(){
        return this.onPress;
    }
    // Config
    public ButtonBooleanBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }
    public boolean hasConfigurable(){
        return this.config != null && this.configType != null;
    }

    public ButtonBoolean build(){
        return new ButtonBoolean(this);
    }
}
