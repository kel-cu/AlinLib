package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonWithIcon;

import static ru.kelcuprum.alinlib.gui.Icons.CLOWNFISH;

public class ButtonWithIconBuilder extends AbstractBuilder {
    public boolean isCenter = true;
    public ResourceLocation icon;
    public ButtonWithIcon.OnPress onPress;
    public ButtonWithIconBuilder(){
        this(Component.empty(), CLOWNFISH);
    }
    public ButtonWithIconBuilder(Component title, ResourceLocation icon){
        this(title, icon, null);
    }
    public ButtonWithIconBuilder(Component title, ResourceLocation icon, ButtonWithIcon.OnPress onPress){
        super(title);
        this.icon = icon;
        this.onPress = onPress;
    }
    // Center
    public ButtonWithIconBuilder setCentered(boolean center){
        this.isCenter = center;
        return this;
    }
    // OnPress
    public ButtonWithIconBuilder setOnPress(ButtonWithIcon.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public ButtonWithIcon.OnPress getOnPress(){
        return this.onPress;
    }

    public Button build(){
        return new ButtonWithIcon(x, y, width, height, designType, title, icon, isCenter, onPress);
    }
}
