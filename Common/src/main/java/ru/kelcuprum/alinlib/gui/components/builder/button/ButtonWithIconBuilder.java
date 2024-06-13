package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonWithIcon;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.Icons.CLOWNFISH;

public class ButtonWithIconBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected int color = InterfaceUtils.Colors.SPECKLE[0];
    protected boolean isCenter = true;
    protected Component title;
    protected ResourceLocation icon;
    protected InterfaceUtils.DesignType designType = AlinLib.getDefaultDesignType();
    protected ButtonWithIcon.OnPress onPress;
    public ButtonWithIconBuilder(){
        this(Component.empty(), CLOWNFISH);
    }
    public ButtonWithIconBuilder(Component title, ResourceLocation icon){
        this(title, icon, null);
    }
    public ButtonWithIconBuilder(Component title, ResourceLocation icon, ButtonWithIcon.OnPress onPress){
        this.title = title;
        this.icon = icon;
        this.onPress = onPress;
    }
    // DesignType
    public ButtonWithIconBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // Color
    public ButtonWithIconBuilder setColor(int color){
        this.color = color;
        return this;
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
    // Title
    public ButtonWithIconBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    public ButtonWithIconBuilder setIcon(ResourceLocation icon){
        this.icon = icon;
        return this;
    }
    // Position
    public ButtonWithIconBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public ButtonWithIconBuilder setX(int x){
        this.x = x;
        return this;
    }
    public ButtonWithIconBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public ButtonWithIconBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public ButtonWithIconBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public ButtonWithIconBuilder setHeight(int height){
        this.height = height;
        return this;
    }

    public Button build(){
        return new ButtonWithIcon(x, y, width, height, designType, color, title, icon, isCenter, onPress);
    }
}
