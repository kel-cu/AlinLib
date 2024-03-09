package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigDouble;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderDouble;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SliderDoubleBuilder {
    protected int x = 0;
    protected int y = 0;
    protected double min = 0;
    protected double max = 1;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected Component title = Component.empty();
    protected InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    protected SliderDouble.OnPress onPress = null;
    protected Config config;
    protected String configType;
    protected double defaultValue;
    public SliderDoubleBuilder(){
        this(Component.empty());
    }
    public SliderDoubleBuilder(Component title){
        this(title, null);
    }
    public SliderDoubleBuilder(Component title, SliderDouble.OnPress onPress){
        this.title = title;
        this.onPress = onPress;
    }
    // DesignType
    public SliderDoubleBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // OnPress
    public SliderDoubleBuilder setOnPress(SliderDouble.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    // Title
    public SliderDoubleBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // Position
    public SliderDoubleBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public SliderDoubleBuilder setX(int x){
        this.x = x;
        return this;
    }
    public SliderDoubleBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public SliderDoubleBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public SliderDoubleBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public SliderDoubleBuilder setHeight(int height){
        this.height = height;
        return this;
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
