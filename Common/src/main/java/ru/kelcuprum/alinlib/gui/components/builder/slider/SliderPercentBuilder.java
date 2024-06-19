package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
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
    // OnPress
    public SliderPercentBuilder setOnPress(SliderPercent.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public SliderPercent.OnPress getOnPress(){
        return this.onPress;
    }
    //
    public SliderPercentBuilder setDefaultValue(double defaultValue){
        this.defaultValue = defaultValue;
        return this;
    }
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
