package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.base.ButtonBoolean;

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

    public Button build(){
        if(this.config != null && this.configType != null) return new ButtonConfigBoolean(x, y, width, height, style, config, configType, defaultValue, title);
        return new ButtonBoolean(x, y, width, height, style, defaultValue, title, onPress);
    }
}
