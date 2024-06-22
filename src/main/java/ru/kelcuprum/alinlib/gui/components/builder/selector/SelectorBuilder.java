package ru.kelcuprum.alinlib.gui.components.builder.selector;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
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
            if(this.defaultString != null) return new SelectorStringButton(x, y, width, height, style, list, config, configType, defaultString, title);
            else return new SelectorIntegerButton(x, y, width, height, style, list, config, configType, defaultInt, title);
        }
        return new SelectorButton(x, y, width, height, style, list, defaultInt, title, onPress);
    }
}
