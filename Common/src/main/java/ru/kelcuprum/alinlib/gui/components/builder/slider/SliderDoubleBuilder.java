package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigDouble;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderDouble;

public class SliderDoubleBuilder extends AbstractBuilder {
    public double min = 0;
    public double max = 1;
    public SliderDouble.OnPress onPress;
    public Config config;
    public String configType;
    public double defaultValue;
    public SliderDoubleBuilder(){
        this(Component.empty());
    }
    public SliderDoubleBuilder(Component title){
        this(title, null);
    }
    public SliderDoubleBuilder(Component title, SliderDouble.OnPress onPress){
        super(title);
        this.onPress = onPress;
    }

    //
    // Title
    public SliderDoubleBuilder setTitle(String title){
        return (SliderDoubleBuilder) super.setTitle(title);
    }
    public SliderDoubleBuilder setTitle(Component title){
        return (SliderDoubleBuilder) super.setTitle(title);
    }
    // Description
    public SliderDoubleBuilder setDescription(String description){
        return (SliderDoubleBuilder) super.setDescription(description);
    }
    public SliderDoubleBuilder setDescription(Component description){
        return (SliderDoubleBuilder) super.setDescription(description);
    }
    //
    public SliderDoubleBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (SliderDoubleBuilder) super.setDesignType(designType);
    }
    // Position
    public SliderDoubleBuilder setPosition(int x, int y){
        return (SliderDoubleBuilder) super.setPosition(x, y);
    }
    public SliderDoubleBuilder setX(int x){
        return (SliderDoubleBuilder) super.setX(x);
    }
    public SliderDoubleBuilder setY(int y){
        return (SliderDoubleBuilder) super.setY(y);
    }
    // Size
    public SliderDoubleBuilder setSize(int width, int height){
        return (SliderDoubleBuilder) super.setSize(width, height);
    }
    public SliderDoubleBuilder setWidth(int width){
        return (SliderDoubleBuilder) super.setWidth(width);
    }
    public SliderDoubleBuilder setHeight(int height){
        return (SliderDoubleBuilder) super.setHeight(height);
    }
    //
    // OnPress
    public SliderDoubleBuilder setOnPress(SliderDouble.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public SliderDouble.OnPress getOnPress(){
        return this.onPress;
    }
    //
    public SliderDoubleBuilder setMin(double min){
        this.min = min;
        return this;
    }
    public SliderDoubleBuilder setMax(double max){
        this.max = max;
        return this;
    }
    public SliderDoubleBuilder setDefaultValue(double defaultValue){
        this.defaultValue = defaultValue;
        return this;
    }
    //
    public SliderDoubleBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }

    public SliderDouble build(){
        if(this.config != null && this.configType != null) return new SliderConfigDouble(x, y, width, height, designType, config, configType, defaultValue, min, max, title);
        return new SliderDouble(x, y, width, height, designType, defaultValue, min, max, title, onPress);
    }
}
