package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
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
    //
    // Title
    public ButtonBooleanBuilder setTitle(String title){
        return (ButtonBooleanBuilder) super.setTitle(title);
    }
    public ButtonBooleanBuilder setTitle(Component title){
        return (ButtonBooleanBuilder) super.setTitle(title);
    }
    // Description
    public ButtonBooleanBuilder setDescription(String description){
        return (ButtonBooleanBuilder) super.setDescription(description);
    }
    public ButtonBooleanBuilder setDescription(Component description){
        return (ButtonBooleanBuilder) super.setDescription(description);
    }
    //
    public ButtonBooleanBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (ButtonBooleanBuilder) super.setDesignType(designType);
    }
    // Color
    public ButtonBooleanBuilder setColor(int color){
        return (ButtonBooleanBuilder) super.setColor(color);
    }
    // Position
    public ButtonBooleanBuilder setPosition(int x, int y){
        return (ButtonBooleanBuilder) super.setPosition(x, y);
    }
    public ButtonBooleanBuilder setX(int x){
        return (ButtonBooleanBuilder) super.setX(x);
    }
    public ButtonBooleanBuilder setY(int y){
        return (ButtonBooleanBuilder) super.setY(y);
    }
    // Size
    public ButtonBooleanBuilder setSize(int width, int height){
        return (ButtonBooleanBuilder) super.setSize(width, height);
    }
    public ButtonBooleanBuilder setWidth(int width){
        return (ButtonBooleanBuilder) super.setWidth(width);
    }
    public ButtonBooleanBuilder setHeight(int height){
        return (ButtonBooleanBuilder) super.setHeight(height);
    }
    //
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
        if(this.config != null && this.configType != null) return new ButtonConfigBoolean(x, y, width, height, designType, config, configType, defaultValue, title);
        return new ButtonBoolean(x, y, width, height, designType, defaultValue, title, onPress);
    }
}
