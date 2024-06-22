package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
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
        if(this.config != null && this.configType != null) return new SliderConfigInteger(x, y, width, height, style, config, configType, defaultValue, min, max, title);
        return new SliderInteger(x, y, width, height, style, defaultValue, min, max, title, onPress);
    }
}
