package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class ButtonWithColor extends Button {
    private final int color;

    public ButtonWithColor(int x, int y, int width, int height, Component label, int color, OnPress onPress) {
        super(x, y, width, height, label, onPress, DEFAULT_NARRATION);
        this.color = color;
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public void setYPos(int i) {
        this.setY(i);
    }

    public void setXPos(int i) {
        this.setX(i);
    }


    public void setPos(int i, int j) {
        this.setPosition(i, j);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = !active ? 3 : isHovered ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int colorBackground = (int) (255.0F * f);

            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, colorBackground / 2 << 24);
            guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), new Color(color, true).getRGB());
            guiGraphics.drawCenteredString(Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

}