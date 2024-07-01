package ru.kelcuprum.alinlib.gui.components.builder;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public abstract class AbstractBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected boolean active = true;
    protected boolean visible = true;
    protected Component title;
    protected Component description;
    protected AbstractStyle style = GuiUtils.getSelected();

    public AbstractBuilder(){
        this(Component.empty());
    }
    public AbstractBuilder(Component title){
        this.title = title;
    }
    // Title
    public <T extends AbstractBuilder> T setTitle(String title){
        this.title = Component.translatable(title);
        return (T) this;
    }
    public <T extends AbstractBuilder> T setTitle(Component title){
        this.title = title;
        return (T) this;
    }
    public Component getTitle(){
        return this.title;
    }
    // Description
    public <T extends AbstractBuilder> T setDescription(String description){
        this.description = Component.translatable(description);
        return (T) this;
    }
    public <T extends AbstractBuilder> T setDescription(Component description){
        this.description = description;
        return (T) this;
    }
    public Component getDescription() {
        return this.description;
    }
    //
    public <T extends AbstractBuilder> T setStyle(AbstractStyle style){
        this.style = style;
        return (T) this;
    }
    public AbstractStyle getStyle(){
        return this.style;
    }
    // Position
    public <T extends AbstractBuilder> T setPosition(int x, int y){
        setX(x).setY(y);
        return (T) this;
    }
    public <T extends AbstractBuilder> T setX(int x){
        this.x = x;
        return (T) this;
    }
    public <T extends AbstractBuilder> T setY(int y){
        this.y = y;
        return (T) this;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    // Size
    public <T extends AbstractBuilder> T setSize(int width, int height){
        setWidth(width).setHeight(height);
        return (T) this;
    }
    public <T extends AbstractBuilder> T setWidth(int width){
        this.width = width;
        return (T) this;
    }
    public <T extends AbstractBuilder> T setHeight(int height){
        this.height = height;
        return (T) this;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    //
    public <T extends AbstractBuilder> T setActive(boolean active){
        this.active = active;
        return (T) this;
    }
    public boolean getActive(){
        return this.active;
    }
    //
    public <T extends AbstractBuilder> T setVisible(boolean visible){
        this.visible = visible;
        return (T) this;
    }
    public boolean getVisible(){
        return this.visible;
    }

    public abstract AbstractWidget build();
}
