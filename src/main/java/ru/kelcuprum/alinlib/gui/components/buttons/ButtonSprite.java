package ru.kelcuprum.alinlib.gui.components.buttons;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

public class ButtonSprite extends AbstractButton {
    private final InterfaceUtils.DesignType type;
    private final int color;
    private final ResourceLocation icon;
    private final int iconWidth, iconHeight;
    private final OnPress onPress;


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
        super(x, y, width, height, label);
        this.icon = icon;
        this.type = type;
        this.color = color;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.onPress = onPress;
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }
    @Override
    public void setY(int y) {
        super.setY(y);
    }
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
    }
    public void setActive(boolean active){
        this.active = active;
    }
    @Override
    public void onPress() {
        this.onPress.onPress(this);
    }

    @Override
    public void setMessage(Component component) {
        super.setMessage(component);
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.color);
//            guiGraphics.blit(icon, getX(), getY(), getWidth(), getHeight(), iconWidth, iconHeight);
            guiGraphics.blit(this.icon, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), iconWidth, iconHeight);
        }
    }
    public boolean isDoesNotFit(){
        int size = Minecraft.getInstance().font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return size > getWidth();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(ButtonSprite button);
    }
}
