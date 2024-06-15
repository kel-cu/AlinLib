package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigFloat;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderFloat;

public class SliderFloatBuilder extends AbstractBuilder {
    public float min = 0;
    public float max = 1;

    public SliderFloat.OnPress onPress;
    public Config config;
    public String configType;
    public float defaultValue;
    public SliderFloatBuilder(){
        this(Component.empty());
    }
    public SliderFloatBuilder(Component title){
        this(title, null);
    }
    public SliderFloatBuilder(Component title, SliderFloat.OnPress onPress){
        super(title);
        this.onPress = onPress;
    }

    //
    // Title
    public SliderFloatBuilder setTitle(String title){
        return (SliderFloatBuilder) super.setTitle(title);
    }
    public SliderFloatBuilder setTitle(Component title){
        return (SliderFloatBuilder) super.setTitle(title);
    }
    // Description
    public SliderFloatBuilder setDescription(String description){
        return (SliderFloatBuilder) super.setDescription(description);
    }
    public SliderFloatBuilder setDescription(Component description){
        return (SliderFloatBuilder) super.setDescription(description);
    }
    //
    public SliderFloatBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (SliderFloatBuilder) super.setDesignType(designType);
    }
    // Color
    public SliderFloatBuilder setColor(int color){
        return (SliderFloatBuilder) super.setColor(color);
    }
    // Position
    public SliderFloatBuilder setPosition(int x, int y){
        return (SliderFloatBuilder) super.setPosition(x, y);
    }
    public SliderFloatBuilder setX(int x){
        return (SliderFloatBuilder) super.setX(x);
    }
    public SliderFloatBuilder setY(int y){
        return (SliderFloatBuilder) super.setY(y);
    }
    // Size
    public SliderFloatBuilder setSize(int width, int height){
        return (SliderFloatBuilder) super.setSize(width, height);
    }
    public SliderFloatBuilder setWidth(int width){
        return (SliderFloatBuilder) super.setWidth(width);
    }
    public SliderFloatBuilder setHeight(int height){
        return (SliderFloatBuilder) super.setHeight(height);
    }
    //
    // OnPress
    public SliderFloatBuilder setOnPress(SliderFloat.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public SliderFloat.OnPress getOnPress(){
        return this.onPress;
    }
    //
    public SliderFloatBuilder setMin(float min){
        this.min = min;
        return this;
    }
    public SliderFloatBuilder setMax(float max){
        this.max = max;
        return this;
    }
    public SliderFloatBuilder setDefaultValue(float defaultValue){
        this.defaultValue = defaultValue;
        return this;
    }
    //
    public SliderFloatBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }

    public SliderFloat build(){
        if(this.config != null && this.configType != null) return new SliderConfigFloat(x, y, width, height, designType, config, configType, defaultValue, min, max, title);
        return new SliderFloat(x, y, width, height, designType, defaultValue, min, max, title, onPress);
    }
}
