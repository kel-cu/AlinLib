package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigScreenBuilder {
    protected Component title;
    protected AbstractStyle style;
    protected List<AbstractWidget> panelWidgets = new ArrayList<>();
    protected List<AbstractWidget> widgets = new ArrayList<>();
    protected OnTick onTick;
    protected OnTickScreen onTickScreen;
    protected Screen parent;
    protected int panelSize = AlinLib.bariumConfig.getBoolean("CONFIG_SCREEN.SMALL_PANEL_SIZE", false) ?  130 : 190;
    protected int yL = 40;
    protected int yC = 5;

    public ConfigScreenBuilder(Screen parent) {
        this(parent, Component.literal("Change me please"));
    }
    public ConfigScreenBuilder(Screen parent, Component title) {
        this(parent, title, GuiUtils.getSelected());
    }
    public ConfigScreenBuilder(Screen parent, Component title, AbstractStyle style){
        this.parent = parent;
        this.title = title;
        this.style = style;
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
    public Component getTitle(){
        return this.title;
    }
    //
    public ConfigScreenBuilder setType(AbstractStyle style) {
        this.style = style;
        return this;
    }
    public AbstractStyle getType() {
        return this.style;
    }
    //
    public ConfigScreenBuilder setPanelSize(int panelSize) {
        this.panelSize = panelSize;
        return this;
    }
    public int getPanelSize() {
        return this.panelSize;
    }
    //
    public ConfigScreenBuilder addPanelWidget(AbstractBuilder builder){
        return addPanelWidget(builder.build());
    }
    public ConfigScreenBuilder addPanelWidget(AbstractWidget widget){
        widget.setWidth(this.panelSize-10);
        widget.setX(5);
        widget.setY(yL);
        yL+=widget.getHeight()+5;
        this.panelWidgets.add(widget);
        return this;
    }

    public ConfigScreenBuilder addWidget(AbstractBuilder builder){
        return addWidget(builder.build());
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
    public ConfigScreenBuilder setOnTickScreen(OnTickScreen onTickScreen){
        this.onTickScreen = onTickScreen;
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
        void onTick(ConfigScreenBuilder builder);
    }
    public interface OnTickScreen {
        void onTick(ConfigScreenBuilder builder, AbstractConfigScreen screen);
    }
}
