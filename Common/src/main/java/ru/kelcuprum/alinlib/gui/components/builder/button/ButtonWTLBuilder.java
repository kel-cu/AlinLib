package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonWithTwoLabels;

public class ButtonWTLBuilder extends AbstractBuilder {
    public Component leftLabel;
    public Component rightLabel;
    public ButtonWithTwoLabels.OnPress onPress;
    public ButtonWTLBuilder(){
        this(Component.empty(), Component.empty());
    }
    public ButtonWTLBuilder(Component leftLabel, Component rightLabel){
        this(leftLabel, rightLabel, null);
    }
    public ButtonWTLBuilder(Component leftLabel, Component rightLabel, ButtonWithTwoLabels.OnPress onPress){
        super(Component.empty());
        this.leftLabel = leftLabel;
        this.rightLabel = rightLabel;
        this.onPress = onPress;
    }
    //
    // Title
    public ButtonWTLBuilder setTitle(String title){
        return (ButtonWTLBuilder) super.setTitle(title);
    }
    public ButtonWTLBuilder setTitle(Component title){
        return (ButtonWTLBuilder) super.setTitle(title);
    }
    // Description
    public ButtonWTLBuilder setDescription(String description){
        return (ButtonWTLBuilder) super.setDescription(description);
    }
    public ButtonWTLBuilder setDescription(Component description){
        return (ButtonWTLBuilder) super.setDescription(description);
    }
    //
    public ButtonWTLBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (ButtonWTLBuilder) super.setDesignType(designType);
    }
    // Position
    public ButtonWTLBuilder setPosition(int x, int y){
        return (ButtonWTLBuilder) super.setPosition(x, y);
    }
    public ButtonWTLBuilder setX(int x){
        return (ButtonWTLBuilder) super.setX(x);
    }
    public ButtonWTLBuilder setY(int y){
        return (ButtonWTLBuilder) super.setY(y);
    }
    // Size
    public ButtonWTLBuilder setSize(int width, int height){
        return (ButtonWTLBuilder) super.setSize(width, height);
    }
    public ButtonWTLBuilder setWidth(int width){
        return (ButtonWTLBuilder) super.setWidth(width);
    }
    public ButtonWTLBuilder setHeight(int height){
        return (ButtonWTLBuilder) super.setHeight(height);
    }
    //
    // OnPress
    public ButtonWTLBuilder setOnPress(ButtonWithTwoLabels.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public ButtonWithTwoLabels.OnPress getOnPress(){
        return this.onPress;
    }
    // Title
    public ButtonWTLBuilder setLeftLabel(Component leftLabel){
        this.leftLabel = leftLabel;
        return this;
    }
    public ButtonWTLBuilder setRightLabel(Component rightLabel){
        this.rightLabel = rightLabel;
        return this;
    }
    public ButtonWTLBuilder setMessages(Component leftLabel, Component rightLabel){
        setLeftLabel(leftLabel);
        setRightLabel(rightLabel);
        return this;
    }

    public Button build(){
        return new ButtonWithTwoLabels(x, y, width, height, designType, leftLabel, rightLabel, onPress);
    }
}
