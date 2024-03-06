package ru.kelcuprum.alinlib.gui.components.builder.selector;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorIntegerButton;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.components.selector.base.SelectorButton;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SelectorBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected int color = InterfaceUtils.Colors.CLOWNFISH;
    protected Component title = Component.empty();
    protected String[] list;
    protected int defaultInt;
    protected String defaultString;
    protected InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    protected SelectorButton.OnPress onPress = null;
    protected Config config;
    protected String configType;
    public SelectorBuilder(){
        this(Component.empty());
    }
    public SelectorBuilder(Component title){
        this(title, null);
    }
    public SelectorBuilder(Component title, SelectorButton.OnPress onPress){
        this.title = title;
        this.onPress = onPress;
    }
    // DesignType
    public SelectorBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // Color
    public SelectorBuilder setColor(int color){
        this.color = color;
        return this;
    }
    // OnPress
    public SelectorBuilder setOnPress(SelectorButton.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    // Title
    public SelectorBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // Position
    public SelectorBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public SelectorBuilder setX(int x){
        this.x = x;
        return this;
    }
    public SelectorBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public SelectorBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public SelectorBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public SelectorBuilder setHeight(int height){
        this.height = height;
        return this;
    }
    //
    public SelectorBuilder setList(String[] list){
        this.list = list;
        return this;
    }
    //
    public SelectorBuilder setValue(int value){
        this.defaultInt = value;
        return this;
    }
    public SelectorBuilder setValue(String value){
        this.defaultString = value;
        return this;
    }
    //
    public SelectorBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }

    public SelectorButton build(){
        if(this.config != null && this.configType != null){
            if(this.defaultString != null) return new SelectorStringButton(x, y, width, height, designType, list, config, configType, defaultString, title);
            else return new SelectorIntegerButton(x, y, width, height, designType, list, config, configType, defaultInt, title);
        }
        return new SelectorButton(x, y, width, height, designType, color, list, defaultInt, title, onPress);
    }
}
