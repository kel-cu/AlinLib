package ru.kelcuprum.alinlib.gui.components.buttons;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class Button extends AbstractButton {
    private final OnPress onPress;

    public Button(int x, int y, int width, int height, Component label, OnPress onPress) {
        super(x, y, width, height, label);
        this.onPress = onPress;

    }
    public void setXPos(int x) {
        this.setX(x);
    }
    public void setYPos(int y) {
        this.setY(y);
    }
    public void setPos(int x, int y) {
        this.setPosition(x, y);
    }
    public void setActive(boolean active){
        this.active = active;
    }
    @Override
    public void onPress() {
        this.onPress.onPress(this);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = !active ? 3 : isHovered ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);

            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
            guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), new Color(0xFF31FF83, true).getRGB());
            guiGraphics.drawCenteredString(Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(Button button);
    }
}
