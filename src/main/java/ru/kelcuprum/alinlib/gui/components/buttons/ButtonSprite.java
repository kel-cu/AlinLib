package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

public class ButtonSprite extends Button {
    private ResourceLocation icon;
    private final int iconWidth, iconHeight;


    public ButtonSprite(int x, int y, int width, int height, ResourceLocation icon, Component label, OnPress onPress){
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, Colors.SEADRIVE, icon, label, width, height, onPress);
    }
    public ButtonSprite(int x, int y, int width, int height, InterfaceUtils.DesignType type, ResourceLocation icon, Component label, OnPress onPress){
        this(x, y, width, height, type, Colors.SEADRIVE, icon, label, width, height, onPress);
    }
    public ButtonSprite(int x, int y, int width, int height, ResourceLocation icon, Component label, int iconWidth, int iconHeight, OnPress onPress){
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, Colors.SEADRIVE, icon, label, iconWidth, iconHeight, onPress);
    }
    public ButtonSprite(int x, int y, int width, int height, InterfaceUtils.DesignType type, ResourceLocation icon, Component label, int iconWidth, int iconHeight, OnPress onPress){
        this(x, y, width, height, type, Colors.SEADRIVE, icon, label, iconWidth, iconHeight, onPress);
    }
    public ButtonSprite(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, ResourceLocation icon, Component label, int iconWidth, int iconHeight, OnPress onPress) {
        super(x, y, width, height, type, color, label, onPress);
        this.icon = icon;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
    }

    @Override
    public void setMessage(Component component) {
        super.setMessage(component);
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.color);
            guiGraphics.blit(this.icon, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), iconWidth, iconHeight);
        }
    }
    public void setIcon(ResourceLocation icon){
        this.icon = icon;
    }
}
