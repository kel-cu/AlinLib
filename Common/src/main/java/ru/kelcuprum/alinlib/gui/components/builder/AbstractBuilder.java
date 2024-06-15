package ru.kelcuprum.alinlib.gui.components.builder;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public abstract class AbstractBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected int color = InterfaceUtils.Colors.CLOWNFISH;
    protected Component title;
    protected Component description;
    protected InterfaceUtils.DesignType designType = AlinLib.getDefaultDesignType();

    public AbstractBuilder(){
        this(Component.empty());
    }
    public AbstractBuilder(Component title){
        this.title = title;
    }
    // Title
    public AbstractBuilder setTitle(String title){
        this.title = Component.translatable(title);
        return this;
    }
    public AbstractBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    public Component getTitle(){
        return this.title;
    }
    // Description
    public AbstractBuilder setDescription(String description){
        this.description = Component.translatable(description);
        return this;
    }
    public AbstractBuilder setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription() {
        return this.description;
    }
    //
    public AbstractBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    public InterfaceUtils.DesignType getDesignType(){
        return this.designType;
    }
    // Color
    public AbstractBuilder setColor(int color){
        this.color = color;
        return this;
    }
    public int getColor(){
        return this.color;
    }
    // Position
    public AbstractBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public AbstractBuilder setX(int x){
        this.x = x;
        return this;
    }
    public AbstractBuilder setY(int y){
        this.y = y;
        return this;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    // Size
    public AbstractBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public AbstractBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public AbstractBuilder setHeight(int height){
        this.height = height;
        return this;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public abstract AbstractWidget build();
}
