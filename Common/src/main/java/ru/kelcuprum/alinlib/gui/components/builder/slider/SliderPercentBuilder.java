package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigPercent;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderPercent;

public class SliderPercentBuilder extends AbstractBuilder {
    public SliderPercent.OnPress onPress;
    public Config config;
    public String configType;
    public double defaultValue;
    public SliderPercentBuilder(){
        this(Component.empty());
    }
    public SliderPercentBuilder(Component title){
        this(title, null);
    }
    public SliderPercentBuilder(Component title, SliderPercent.OnPress onPress){
        super(title);
        this.onPress = onPress;
    }

    //
    // Title
    public SliderPercentBuilder setTitle(String title){
        return (SliderPercentBuilder) super.setTitle(title);
    }
    public SliderPercentBuilder setTitle(Component title){
        return (SliderPercentBuilder) super.setTitle(title);
    }
    // Description
    public SliderPercentBuilder setDescription(String description){
        return (SliderPercentBuilder) super.setDescription(description);
    }
    public SliderPercentBuilder setDescription(Component description){
        return (SliderPercentBuilder) super.setDescription(description);
    }
    //
    public SliderPercentBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (SliderPercentBuilder) super.setDesignType(designType);
    }
    // Position
    public SliderPercentBuilder setPosition(int x, int y){
        return (SliderPercentBuilder) super.setPosition(x, y);
    }
    public SliderPercentBuilder setX(int x){
        return (SliderPercentBuilder) super.setX(x);
    }
    public SliderPercentBuilder setY(int y){
        return (SliderPercentBuilder) super.setY(y);
    }
    // Size
    public SliderPercentBuilder setSize(int width, int height){
        return (SliderPercentBuilder) super.setSize(width, height);
    }
    public SliderPercentBuilder setWidth(int width){
        return (SliderPercentBuilder) super.setWidth(width);
    }
    public SliderPercentBuilder setHeight(int height){
        return (SliderPercentBuilder) super.setHeight(height);
    }
    //
    // OnPress
    public SliderPercentBuilder setOnPress(SliderPercent.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public SliderPercent.OnPress getOnPress(){
        return this.onPress;
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
