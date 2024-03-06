package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigScreenBuilder {
    protected Component title;
    protected InterfaceUtils.DesignType type;
    protected List<AbstractWidget> panelWidgets = new ArrayList<>();
    protected List<AbstractWidget> widgets = new ArrayList<>();
    protected OnTick onTick;
    protected Screen parent;
    protected int yL = 40;
    protected int yC = 5;

    public ConfigScreenBuilder(Screen parent) {
        this(parent, Component.literal("Change me please"));
    }
    public ConfigScreenBuilder(Screen parent, Component title) {
        this(parent, title, InterfaceUtils.DesignType.ALINA);
    }
    public ConfigScreenBuilder(Screen parent, Component title, InterfaceUtils.DesignType type){
        this.parent = parent;
        this.title = title;
        this.type = type;
    }
    //
    public ConfigScreenBuilder setTitle(String string){
        setTitle(Component.literal(string));
        return this;
    }
    public ConfigScreenBuilder setTitle(Component component) {
        this.title = component;
        return this;
    }
    //
    public ConfigScreenBuilder setType(InterfaceUtils.DesignType type) {
        this.type = type;
        return this;
    }
    //
    public ConfigScreenBuilder addPanelWidget(AbstractWidget widget){
        widget.setWidth(110);
        widget.setX(10);
        widget.setY(yL);
        yL+=widget.getHeight()+5;
        this.panelWidgets.add(widget);
        return this;
    }

    public ConfigScreenBuilder addWidget(AbstractWidget widget){
        if(widget instanceof CategoryBox){
            this.widgets.add(widget);
            widget.setX(140);
            widget.setY(yC);
            yC+=widget.getHeight()+5;
            for(AbstractWidget cW : ((CategoryBox) widget).getValues()){
                this.widgets.add(cW);
                cW.setX(140);
                cW.setY(yC);
                yC+=cW.getHeight()+5;
            }
        } else {
            this.widgets.add(widget);
            widget.setY(yC);
            widget.setX(140);
            yC+=widget.getHeight()+5;
        }
        return this;
    }
    //
    public ConfigScreenBuilder setOnTick(OnTick onTick){
        this.onTick = onTick;
        return this;
    }
    public OnTick getOnTick(){
        return this.onTick;
    }
    //
    public ConfigScreenBuilder setParent(Screen parent){
        this.parent = parent;
        return this;
    }

    public AbstractConfigScreen build() {
        Objects.requireNonNull(this.title, "title == null");
        return new AbstractConfigScreen(this);
    }

    public interface OnTick {
        void onTick(ConfigScreenBuilder value);
    }
}
