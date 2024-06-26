package ru.kelcuprum.alinlib.gui.components.builder.selector;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorIntegerButton;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.components.selector.base.SelectorButton;

public class SelectorBuilder extends AbstractBuilder {
    public String[] list;
    public int defaultInt;
    public String defaultString;
    public SelectorButton.OnPress onPress;
    public Config config;
    public String configType;
    public SelectorBuilder(){
        this(Component.empty());
    }
    public SelectorBuilder(Component title){
        this(title, null);
    }
    public SelectorBuilder(Component title, SelectorButton.OnPress onPress){
        super(title);
        this.onPress = onPress;
    }

    //
    // Title
    public SelectorBuilder setTitle(String title){
        return (SelectorBuilder) super.setTitle(title);
    }
    public SelectorBuilder setTitle(Component title){
        return (SelectorBuilder) super.setTitle(title);
    }
    // Description
    public SelectorBuilder setDescription(String description){
        return (SelectorBuilder) super.setDescription(description);
    }
    public SelectorBuilder setDescription(Component description){
        return (SelectorBuilder) super.setDescription(description);
    }
    //
    public SelectorBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (SelectorBuilder) super.setDesignType(designType);
    }
    // Position
    public SelectorBuilder setPosition(int x, int y){
        return (SelectorBuilder) super.setPosition(x, y);
    }
    public SelectorBuilder setX(int x){
        return (SelectorBuilder) super.setX(x);
    }
    public SelectorBuilder setY(int y){
        return (SelectorBuilder) super.setY(y);
    }
    // Size
    public SelectorBuilder setSize(int width, int height){
        return (SelectorBuilder) super.setSize(width, height);
    }
    public SelectorBuilder setWidth(int width){
        return (SelectorBuilder) super.setWidth(width);
    }
    public SelectorBuilder setHeight(int height){
        return (SelectorBuilder) super.setHeight(height);
    }
    //
    // OnPress
    public SelectorBuilder setOnPress(SelectorButton.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public SelectorButton.OnPress getOnPress(){
        return this.onPress;
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
        return new SelectorButton(x, y, width, height, designType, list, defaultInt, title, onPress);
    }
}
