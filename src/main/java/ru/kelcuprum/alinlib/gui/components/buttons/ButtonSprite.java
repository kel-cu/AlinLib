package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;

public class ButtonSprite extends Button {
    private ResourceLocation icon;
    private final int iconWidth, iconHeight;

    public ButtonSprite(int x, int y, int width, int height, ResourceLocation icon, Component label, OnPress onPress) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, icon, label, onPress);
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
    }
    public void setIcon(ResourceLocation icon){
        this.icon = icon;
    }
}
