package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonSprite;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class ButtonSpriteBuilder extends AbstractBuilder {

    public int textureWidth = DEFAULT_WIDTH();
    public int textureHeight = DEFAULT_HEIGHT;
    public ResourceLocation icon;
    public ButtonSprite.OnPress onPress;
    public ButtonSpriteBuilder(ResourceLocation icon){
        this(icon, null);
    }
    public ButtonSpriteBuilder(ResourceLocation icon, ButtonSprite.OnPress onPress){
        super(Component.empty());
        this.icon = icon;
        this.onPress = onPress;
    }
    //
    // Title
    public ButtonSpriteBuilder setTitle(String title){
        return (ButtonSpriteBuilder) super.setTitle(title);
    }
    public ButtonSpriteBuilder setTitle(Component title){
        return (ButtonSpriteBuilder) super.setTitle(title);
    }
    // Description
    public ButtonSpriteBuilder setDescription(String description){
        return (ButtonSpriteBuilder) super.setDescription(description);
    }
    public ButtonSpriteBuilder setDescription(Component description){
        return (ButtonSpriteBuilder) super.setDescription(description);
    }
    //
    public ButtonSpriteBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (ButtonSpriteBuilder) super.setDesignType(designType);
    }
    // Position
    public ButtonSpriteBuilder setPosition(int x, int y){
        return (ButtonSpriteBuilder) super.setPosition(x, y);
    }
    public ButtonSpriteBuilder setX(int x){
        return (ButtonSpriteBuilder) super.setX(x);
    }
    public ButtonSpriteBuilder setY(int y){
        return (ButtonSpriteBuilder) super.setY(y);
    }
    // Size
    public ButtonSpriteBuilder setSize(int width, int height){
        return (ButtonSpriteBuilder) super.setSize(width, height);
    }
    //
    // OnPress
    public ButtonSpriteBuilder setOnPress(ButtonSprite.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public ButtonSprite.OnPress getOnPress(){
        return this.onPress;
    }
    // Icon
    public ButtonSpriteBuilder setIcon(ResourceLocation icon){
        this.icon = icon;
        return this;
    }
    // Size
    @Override
    public ButtonSpriteBuilder setWidth(int width){
        if(this.width == textureWidth) this.textureWidth = width;
        this.width = width;
        return this;
    }
    @Override
    public ButtonSpriteBuilder setHeight(int height){
        if(this.height == textureHeight) this.textureHeight = height;
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
        return new ButtonSprite(x, y, width, height, designType, icon, textureWidth, textureHeight, title, onPress);
    }
}
