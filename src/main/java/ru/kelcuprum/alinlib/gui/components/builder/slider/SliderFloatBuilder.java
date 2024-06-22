package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
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
        if(this.config != null && this.configType != null) return new SliderConfigFloat(x, y, width, height, style, config, configType, defaultValue, min, max, title);
        return new SliderFloat(x, y, width, height, style, defaultValue, min, max, title, onPress);
    }
}
