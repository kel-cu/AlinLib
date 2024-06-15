package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderInteger;

public class SliderIntegerBuilder extends AbstractBuilder {
    public int min = 0;
    public int max = 1;
    public SliderInteger.OnPress onPress;
    public Config config;
    public String configType;
    public int defaultValue;
    public SliderIntegerBuilder(){
        this(Component.empty());
    }
    public SliderIntegerBuilder(Component title){
        this(title, null);
    }
    public SliderIntegerBuilder(Component title, SliderInteger.OnPress onPress){
        super(title);
        this.onPress = onPress;
    }

    //
    // Title
    public SliderIntegerBuilder setTitle(String title){
        return (SliderIntegerBuilder) super.setTitle(title);
    }
    public SliderIntegerBuilder setTitle(Component title){
        return (SliderIntegerBuilder) super.setTitle(title);
    }
    // Description
    public SliderIntegerBuilder setDescription(String description){
        return (SliderIntegerBuilder) super.setDescription(description);
    }
    public SliderIntegerBuilder setDescription(Component description){
        return (SliderIntegerBuilder) super.setDescription(description);
    }
    //
    public SliderIntegerBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (SliderIntegerBuilder) super.setDesignType(designType);
    }
    // Color
    public SliderIntegerBuilder setColor(int color){
        return (SliderIntegerBuilder) super.setColor(color);
    }
    // Position
    public SliderIntegerBuilder setPosition(int x, int y){
        return (SliderIntegerBuilder) super.setPosition(x, y);
    }
    public SliderIntegerBuilder setX(int x){
        return (SliderIntegerBuilder) super.setX(x);
    }
    public SliderIntegerBuilder setY(int y){
        return (SliderIntegerBuilder) super.setY(y);
    }
    // Size
    public SliderIntegerBuilder setSize(int width, int height){
        return (SliderIntegerBuilder) super.setSize(width, height);
    }
    public SliderIntegerBuilder setWidth(int width){
        return (SliderIntegerBuilder) super.setWidth(width);
    }
    public SliderIntegerBuilder setHeight(int height){
        return (SliderIntegerBuilder) super.setHeight(height);
    }
    //
    // OnPress
    public SliderIntegerBuilder setOnPress(SliderInteger.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public SliderInteger.OnPress getOnPress(){
        return this.onPress;
    }
    //
    public SliderIntegerBuilder setMin(int min){
        this.min = min;
        return this;
    }
    public SliderIntegerBuilder setMax(int max){
        this.max = max;
        return this;
    }
    public SliderIntegerBuilder setDefaultValue(int defaultValue){
        this.defaultValue = defaultValue;
        return this;
    }
    //
    public SliderIntegerBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }

    public SliderInteger build(){
        if(this.config != null && this.configType != null) return new SliderConfigInteger(x, y, width, height, designType, config, configType, defaultValue, min, max, title);
        return new SliderInteger(x, y, width, height, designType, defaultValue, min, max, title, onPress);
    }
}
