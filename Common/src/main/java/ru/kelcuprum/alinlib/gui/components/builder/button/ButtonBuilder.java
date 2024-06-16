package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;

public class ButtonBuilder extends AbstractBuilder {
    public Button.OnPress onPress;
    public boolean isCentered;
    public ButtonBuilder(){
        this(Component.empty());
    }
    public ButtonBuilder(Component title){
        this(title, null);
    }
    public ButtonBuilder(Component title, Button.OnPress onPress){
        super(title);
        this.title = title;
        this.onPress = onPress;
    }
    //
    // Title
    public ButtonBuilder setTitle(String title){
        return (ButtonBuilder) super.setTitle(title);
    }
    public ButtonBuilder setTitle(Component title){
        return (ButtonBuilder) super.setTitle(title);
    }
    // Description
    public ButtonBuilder setDescription(String description){
        return (ButtonBuilder) super.setDescription(description);
    }
    public ButtonBuilder setDescription(Component description){
        return (ButtonBuilder) super.setDescription(description);
    }
    //
    public ButtonBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (ButtonBuilder) super.setDesignType(designType);
    }
    // Position
    public ButtonBuilder setPosition(int x, int y){
        return (ButtonBuilder) super.setPosition(x, y);
    }
    public ButtonBuilder setX(int x){
        return (ButtonBuilder) super.setX(x);
    }
    public ButtonBuilder setY(int y){
        return (ButtonBuilder) super.setY(y);
    }
    // Size
    public ButtonBuilder setSize(int width, int height){
        return (ButtonBuilder) super.setSize(width, height);
    }
    public ButtonBuilder setWidth(int width){
        return (ButtonBuilder) super.setWidth(width);
    }
    public ButtonBuilder setHeight(int height){
        return (ButtonBuilder) super.setHeight(height);
    }
    //

    public ButtonBuilder setCentered(boolean bl){
        this.isCentered = bl;
        return this;
    }
    public boolean getCentered(){
        return this.isCentered;
    }
    // OnPress
    public ButtonBuilder setOnPress(Button.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public Button.OnPress getOnPress(){
        return this.onPress;
    }

    public Button build(){
        return new Button(x, y, width, height, designType, title, onPress);
    }
}
