package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigFloat;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderFloat;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SliderFloatBuilder {
    protected int x = 0;
    protected int y = 0;
    protected float min = 0;
    protected float max = 1;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected Component title;
    protected InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    protected SliderFloat.OnPress onPress;
    protected Config config;
    protected String configType;
    protected float defaultValue;
    public SliderFloatBuilder(){
        this(Component.empty());
    }
    public SliderFloatBuilder(Component title){
        this(title, null);
    }
    public SliderFloatBuilder(Component title, SliderFloat.OnPress onPress){
        this.title = title;
        this.onPress = onPress;
    }
    // DesignType
    public SliderFloatBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // OnPress
    public SliderFloatBuilder setOnPress(SliderFloat.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    // Title
    public SliderFloatBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // Position
    public SliderFloatBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public SliderFloatBuilder setX(int x){
        this.x = x;
        return this;
    }
    public SliderFloatBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public SliderFloatBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public SliderFloatBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public SliderFloatBuilder setHeight(int height){
        this.height = height;
        return this;
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
