package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
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
