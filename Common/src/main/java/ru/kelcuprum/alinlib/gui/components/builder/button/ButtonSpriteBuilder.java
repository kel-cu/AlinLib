package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonSprite;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class ButtonSpriteBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected int color = InterfaceUtils.Colors.CLOWNFISH;
    protected Component title = Component.empty();

    protected int textureWidth = DEFAULT_WIDTH();
    protected int textureHeight = DEFAULT_HEIGHT;
    protected ResourceLocation icon;
    protected InterfaceUtils.DesignType designType = AlinLib.getDefaultDesignType();
    protected Button.OnPress onPress;
    public ButtonSpriteBuilder(ResourceLocation icon){
        this(icon, null);
    }
    public ButtonSpriteBuilder(ResourceLocation icon, Button.OnPress onPress){
        this.icon = icon;
        this.onPress = onPress;
    }
    // DesignType
    public ButtonSpriteBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // Color
    public ButtonSpriteBuilder setColor(int color){
        this.color = color;
        return this;
    }
    // Color
    public ButtonSpriteBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // OnPress
    public ButtonSpriteBuilder setOnPress(Button.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    // Title
    public ButtonSpriteBuilder setIcon(ResourceLocation icon){
        this.icon = icon;
        return this;
    }
    // Position
    public ButtonSpriteBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public ButtonSpriteBuilder setX(int x){
        this.x = x;
        return this;
    }
    public ButtonSpriteBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public ButtonSpriteBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public ButtonSpriteBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public ButtonSpriteBuilder setHeight(int height){
        this.height = height;
        return this;
    }
    // Texture Size
    public ButtonSpriteBuilder setTextureSize(int textureWidth, int textureHeight){
        setTextureWidth(textureWidth).setTextureHeight(textureHeight);
        return this;
    }
    public ButtonSpriteBuilder setTextureWidth(int textureWidth){
        this.textureWidth = textureWidth;
        return this;
    }
    public ButtonSpriteBuilder setTextureHeight(int textureHeight){
        this.textureHeight = textureHeight;
        return this;
    }

    public ButtonSprite build(){
        return new ButtonSprite(x, y, width, height, designType, color, icon, textureWidth, textureHeight, title, onPress);
    }
}
