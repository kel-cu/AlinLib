package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.sliders.Slider;

public class SliderBuilder extends AbstractBuilder {
    public Number min = 0;
    public Number max = 1;
    public Slider.OnPress onPress;
    public Config config;
    public String configType;
    public Number defaultValue;
    public NUMBER_TYPE type = NUMBER_TYPE.INTEGER;
    public String valueType = "";
    public SliderBuilder(){
        this(Component.empty());
    }
    public SliderBuilder(Component title){
        this(title, null);
    }
    public SliderBuilder(Component title, Slider.OnPress onPress){
        super(title);
        this.onPress = onPress;
    }
    // OnPress
    public SliderBuilder setOnPress(Slider.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public Slider.OnPress getOnPress(){
        return this.onPress;
    }
    //
    public SliderBuilder setMin(int min){
        this.min = min;
        this.type = NUMBER_TYPE.INTEGER;
        return this;
    }
    public SliderBuilder setMax(int max){
        this.max = max;
        this.type = NUMBER_TYPE.INTEGER;
        return this;
    }
    public SliderBuilder setDefaultValue(int defaultValue){
        this.defaultValue = defaultValue;
        this.type = NUMBER_TYPE.INTEGER;
        return this;
    }
    //
    public SliderBuilder setMin(double min){
        this.min = min;
        this.type = NUMBER_TYPE.DOUBLE;
        return this;
    }
    public SliderBuilder setMax(double max){
        this.max = max;
        this.type = NUMBER_TYPE.DOUBLE;
        return this;
    }
    public SliderBuilder setDefaultValue(double defaultValue, boolean isPercent){
        this.defaultValue = defaultValue;
        this.type = isPercent ? NUMBER_TYPE.PERCENT : NUMBER_TYPE.DOUBLE;
        return this;
    }
    //
    public SliderBuilder setMin(float min){
        this.min = min;
        this.type = NUMBER_TYPE.FLOAT;
        return this;
    }
    public SliderBuilder setMax(float max){
        this.max = max;
        this.type = NUMBER_TYPE.FLOAT;
        return this;
    }
    public SliderBuilder setDefaultValue(float defaultValue){
        this.defaultValue = defaultValue;
        this.type = NUMBER_TYPE.FLOAT;
        return this;
    }
    //
    public SliderBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }
    public boolean hasConfigurable(){
        return this.config != null && this.configType != null;
    }
    //
    public SliderBuilder setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getValueType() {
        return valueType;
    }

    public Slider build(){
        return new Slider(this);
    }
    public enum NUMBER_TYPE{
        INTEGER(),
        DOUBLE(),
        FLOAT(),
        PERCENT()
    }
}
