package ru.kelcuprum.alinlib.gui.components.builder.slider;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderInteger;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SliderIntegerBuilder {
    protected int x = 0;
    protected int y = 0;
    protected int min = 0;
    protected int max = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected Component title = Component.empty();
    protected InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    protected SliderInteger.OnPress onPress = null;
    protected Config config;
    protected String configType;
    protected int defaultValue;
    public SliderIntegerBuilder(){
        this(Component.empty());
    }
    public SliderIntegerBuilder(Component title){
        this(title, null);
    }
    public SliderIntegerBuilder(Component title, SliderInteger.OnPress onPress){
        this.title = title;
        this.onPress = onPress;
    }
    // DesignType
    public SliderIntegerBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // OnPress
    public SliderIntegerBuilder setOnPress(SliderInteger.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    // Title
    public SliderIntegerBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // Position
    public SliderIntegerBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public SliderIntegerBuilder setX(int x){
        this.x = x;
        return this;
    }
    public SliderIntegerBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public SliderIntegerBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public SliderIntegerBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public SliderIntegerBuilder setHeight(int height){
        this.height = height;
        return this;
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

    public SliderInteger build(){
        if(this.config != null && this.configType != null) return new SliderConfigInteger(x, y, width, height, designType, config, configType, defaultValue, min, max, title);
        return new SliderInteger(x, y, width, height, designType, defaultValue, min, max, title, onPress);
    }
}
