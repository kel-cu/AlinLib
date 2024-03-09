package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigPercent;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderPercent;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SliderPercentBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected Component title = Component.empty();
    protected InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    protected SliderPercent.OnPress onPress = null;
    protected Config config;
    protected String configType;
    protected double defaultValue;
    public SliderPercentBuilder(){
        this(Component.empty());
    }
    public SliderPercentBuilder(Component title){
        this(title, null);
    }
    public SliderPercentBuilder(Component title, SliderPercent.OnPress onPress){
        this.title = title;
        this.onPress = onPress;
    }
    // DesignType
    public SliderPercentBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // OnPress
    public SliderPercentBuilder setOnPress(SliderPercent.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    // Title
    public SliderPercentBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // Position
    public SliderPercentBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public SliderPercentBuilder setX(int x){
        this.x = x;
        return this;
    }
    public SliderPercentBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public SliderPercentBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public SliderPercentBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public SliderPercentBuilder setHeight(int height){
        this.height = height;
        return this;
    }
    public SliderPercentBuilder setDefaultValue(double defaultValue){
        this.defaultValue = defaultValue;
        return this;
    }
    //
    public SliderPercentBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }

    public SliderPercent build(){
        if(this.config != null && this.configType != null) return new SliderConfigPercent(x, y, width, height, designType, config, configType, defaultValue, title);
        return new SliderPercent(x, y, width, height, designType, defaultValue, title, onPress);
    }
}
