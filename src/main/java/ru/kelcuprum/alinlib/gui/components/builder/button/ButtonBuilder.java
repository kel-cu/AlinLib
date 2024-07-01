package ru.kelcuprum.alinlib.gui.components.builder.button;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;

public class ButtonBuilder extends AbstractBuilder {
    public Button.OnPress onPress;
    public boolean isCentered = true;
    public Component rightTitle;
    public ResourceLocation sprite;
    public int textureWidth = width;
    public int textureHeight = height;

    public ResourceLocation icon;

    public ButtonBuilder(){
        this(Component.empty());
    }
    public ButtonBuilder(Component title){
        this(title, (Button.OnPress) null);
    }
    public ButtonBuilder(Component title, Component rightTitle){
        this(title, rightTitle, null);
    }

    public ButtonBuilder(Component title, Button.OnPress onPress){
        this(title, null, onPress);
    }
    public ButtonBuilder(Component title, Component rightTitle, Button.OnPress onPress){
        super(title);
        this.title = title;
        this.rightTitle = rightTitle;
        this.onPress = onPress;
    }
    //
    public ButtonBuilder setRightTitle(Component rightTitle) {
        this.rightTitle = rightTitle;
        return this;
    }
    public Component getRightTitle() {
        return this.rightTitle;
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
    // Icon
    public ButtonBuilder setIcon(ResourceLocation icon){
        this.icon = icon;
        return this;
    }

    // Sprite
    public ButtonBuilder setSprite(ResourceLocation sprite){
        this.sprite = sprite;
        return this;
    }
    public ButtonBuilder setTextureSize(int textureWidth, int textureHeight){
        setTextureWidth(textureWidth).setTextureHeight(textureHeight);
        return this;
    }
    public ButtonBuilder setTextureWidth(int textureWidth){
        this.textureWidth = textureWidth;
        return this;
    }

    @Override
    public <T extends AbstractBuilder> T setWidth(int width) {
        if(this.width == this.textureWidth) this.textureWidth = width;
        return super.setWidth(width);
    }

    public ButtonBuilder setTextureHeight(int textureHeight){
        this.textureHeight = textureHeight;
        return this;
    }

    @Override
    public <T extends AbstractBuilder> T setHeight(int height) {
        if(this.height == this.textureHeight) this.textureHeight = height;
        return super.setHeight(height);
    }

    //
    public Button.OnPress getOnPress(){
        return this.onPress;
    }

    public Button build(){
        return new Button(this);
    }
}
