package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class ButtonBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected int color = InterfaceUtils.Colors.CLOWNFISH;
    protected Component title;
    protected InterfaceUtils.DesignType designType = AlinLib.getDefaultDesignType();
    protected Button.OnPress onPress;
    public ButtonBuilder(){
        this(Component.empty());
    }
    public ButtonBuilder(Component title){
        this(title, null);
    }
    public ButtonBuilder(Component title, Button.OnPress onPress){
        this.title = title;
        this.onPress = onPress;
    }
    // DesignType
    public ButtonBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // Color
    public ButtonBuilder setColor(int color){
        this.color = color;
        return this;
    }
    // OnPress
    public ButtonBuilder setOnPress(Button.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    // Title
    public ButtonBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // Position
    public ButtonBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public ButtonBuilder setX(int x){
        this.x = x;
        return this;
    }
    public ButtonBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public ButtonBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public ButtonBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public ButtonBuilder setHeight(int height){
        this.height = height;
        return this;
    }

    public Button build(){
        return new Button(x, y, width, height, designType, color, title, onPress);
    }
}
