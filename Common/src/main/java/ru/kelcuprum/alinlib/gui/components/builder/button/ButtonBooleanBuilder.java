package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.base.ButtonBoolean;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class ButtonBooleanBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected Component title = Component.empty();
    protected InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    protected ButtonBoolean.OnPress onPress = null;
    protected Config config;
    protected String configType;
    protected boolean defaultValue;
    public ButtonBooleanBuilder(boolean defaultValue){
        this(Component.empty(), defaultValue);
    }
    public ButtonBooleanBuilder(Component title, boolean defaultValue){
        this(title, defaultValue, null);
    }
    public ButtonBooleanBuilder(Component title, boolean defaultValue, ButtonBoolean.OnPress onPress){
        this.title = title;
        this.defaultValue = defaultValue;
        this.onPress = onPress;
    }
    // DesignType
    public ButtonBooleanBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // OnPress
    public ButtonBooleanBuilder setOnPress(ButtonBoolean.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    // Config
    public ButtonBooleanBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }
    // Title
    public ButtonBooleanBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // Position
    public ButtonBooleanBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public ButtonBooleanBuilder setX(int x){
        this.x = x;
        return this;
    }
    public ButtonBooleanBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public ButtonBooleanBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public ButtonBooleanBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public ButtonBooleanBuilder setHeight(int height){
        this.height = height;
        return this;
    }

    public Button build(){
        if(this.config != null && this.configType != null) return new ButtonConfigBoolean(x, y, width, height, designType, config, configType, defaultValue, title);
        return new ButtonBoolean(x, y, width, height, designType, defaultValue, title, onPress);
    }
}
