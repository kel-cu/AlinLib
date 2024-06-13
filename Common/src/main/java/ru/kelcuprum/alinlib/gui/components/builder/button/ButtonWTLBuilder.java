package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonWithTwoLabels;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class ButtonWTLBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected int color = InterfaceUtils.Colors.SPECKLE[0];
    protected Component leftLabel;
    protected Component rightLabel;
    protected InterfaceUtils.DesignType designType = AlinLib.getDefaultDesignType();
    protected ButtonWithTwoLabels.OnPress onPress;
    public ButtonWTLBuilder(){
        this(Component.empty(), Component.empty());
    }
    public ButtonWTLBuilder(Component leftLabel, Component rightLabel){
        this(leftLabel, rightLabel, null);
    }
    public ButtonWTLBuilder(Component leftLabel, Component rightLabel, ButtonWithTwoLabels.OnPress onPress){
        this.leftLabel = leftLabel;
        this.rightLabel = rightLabel;
        this.onPress = onPress;
    }
    // DesignType
    public ButtonWTLBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // Color
    public ButtonWTLBuilder setColor(int color){
        this.color = color;
        return this;
    }
    // OnPress
    public ButtonWTLBuilder setOnPress(ButtonWithTwoLabels.OnPress onPress){
        this.onPress = onPress;
        return this;
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
    // Position
    public ButtonWTLBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public ButtonWTLBuilder setX(int x){
        this.x = x;
        return this;
    }
    public ButtonWTLBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public ButtonWTLBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public ButtonWTLBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public ButtonWTLBuilder setHeight(int height){
        this.height = height;
        return this;
    }

    public Button build(){
        return new ButtonWithTwoLabels(x, y, width, height, designType, color, leftLabel, rightLabel, onPress);
    }
}
