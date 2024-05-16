package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class ButtonSprite extends Button {
    private ResourceLocation icon;
    private final int iconWidth, iconHeight;
    // DEFAULT_WIDTH, DEFAULT_HEIGHT

    public ButtonSprite(int x, int y, ResourceLocation icon, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), icon, label, onPress);
    }
    public ButtonSprite(int x, int y, InterfaceUtils.DesignType type, ResourceLocation icon, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, InterfaceUtils.Colors.SEADRIVE, icon, label, onPress);
    }
    public ButtonSprite(int x, int y, InterfaceUtils.DesignType type, int color, ResourceLocation icon, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, color, icon, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, onPress);
    }
    //
    public ButtonSprite(int x, int y, InterfaceUtils.DesignType type, ResourceLocation icon, int iconWidth, int iconHeight, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, InterfaceUtils.Colors.SEADRIVE, icon, iconWidth, iconHeight, label, onPress);
    }

    //
    public ButtonSprite(int x, int y, InterfaceUtils.DesignType type, int color, ResourceLocation icon, int iconWidth, int iconHeight, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, color, icon, iconWidth, iconHeight, label, onPress);
    }
    //////
    public ButtonSprite(int x, int y, int width, int height, ResourceLocation icon, Component label, OnPress onPress) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), icon, label, onPress);
    }
    public ButtonSprite(int x, int y, int width, int height, InterfaceUtils.DesignType type, ResourceLocation icon, Component label, OnPress onPress) {
        this(x, y, width, height, type, InterfaceUtils.Colors.SEADRIVE, icon, label, onPress);
    }
    public ButtonSprite(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, ResourceLocation icon, Component label, OnPress onPress) {
        this(x, y, width, height, type, color, icon, width, height, label, onPress);
    }
    //
    public ButtonSprite(int x, int y, int width, int height, InterfaceUtils.DesignType type, ResourceLocation icon, int iconWidth, int iconHeight, Component label, OnPress onPress) {
        this(x, y, width, height, type, InterfaceUtils.Colors.SEADRIVE, icon, iconWidth, iconHeight, label, onPress);
    }

    //
    public ButtonSprite(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, ResourceLocation icon, int iconWidth, int iconHeight, Component label, OnPress onPress) {
        super(x, y, width, height, type, color, label, onPress);
        this.icon = icon;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
    }
    @Override
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(this.icon, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), iconWidth, iconHeight);
        if(!getMessage().getString().isEmpty() && isHovered()){
            guiGraphics.renderTooltip(AlinLib.MINECRAFT.font, getMessage(), mouseX, mouseY);
        }
    }
    public ButtonSprite setIcon(ResourceLocation icon){
        this.icon = icon;
        return this;
    }


    protected Component description;
    public ButtonSprite setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
