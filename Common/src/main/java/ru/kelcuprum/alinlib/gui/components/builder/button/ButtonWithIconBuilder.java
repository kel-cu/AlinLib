package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonWithIcon;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.Icons.CLOWNFISH;

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
    //
    // Title
    public ButtonWithIconBuilder setTitle(String title){
        return (ButtonWithIconBuilder) super.setTitle(title);
    }
    public ButtonWithIconBuilder setTitle(Component title){
        return (ButtonWithIconBuilder) super.setTitle(title);
    }
    // Description
    public ButtonWithIconBuilder setDescription(String description){
        return (ButtonWithIconBuilder) super.setDescription(description);
    }
    public ButtonWithIconBuilder setDescription(Component description){
        return (ButtonWithIconBuilder) super.setDescription(description);
    }
    //
    public ButtonWithIconBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (ButtonWithIconBuilder) super.setDesignType(designType);
    }
    // Position
    public ButtonWithIconBuilder setPosition(int x, int y){
        return (ButtonWithIconBuilder) super.setPosition(x, y);
    }
    public ButtonWithIconBuilder setX(int x){
        return (ButtonWithIconBuilder) super.setX(x);
    }
    public ButtonWithIconBuilder setY(int y){
        return (ButtonWithIconBuilder) super.setY(y);
    }
    // Size
    public ButtonWithIconBuilder setSize(int width, int height){
        return (ButtonWithIconBuilder) super.setSize(width, height);
    }
    public ButtonWithIconBuilder setWidth(int width){
        return (ButtonWithIconBuilder) super.setWidth(width);
    }
    public ButtonWithIconBuilder setHeight(int height){
        return (ButtonWithIconBuilder) super.setHeight(height);
    }
    //
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
