package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
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
