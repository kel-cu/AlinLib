package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;

import java.awt.*;

public class BooleanButton extends Button {
    public boolean volume;
    public boolean defaultConfig;
    public Config config;
    public String typeConfig;
    public BooleanButton(int x, int y, int width, int height, Config config, String typeConfig, boolean defaultConfig, Component label) {
        super(x, y, width, height, label, Button::onPress, DEFAULT_NARRATION);
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.volume = config.getBoolean(typeConfig, defaultConfig);
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

    public void setActive(boolean active){
        this.active = active;
    }
    @Override
    public void onPress() {
        if(!active) return;
        this.volume = !this.volume;
        config.setBoolean(typeConfig, this.volume);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = !active ? 3 : isHovered ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);

            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
            guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), new Color(volume ? 0xFF31FF83 : 0xB6FF3131, true).getRGB());
            guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            Component volumeState = Component.translatable("alinlib.boolean." + (this.volume ? "true" : "false"));
            guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth()-Minecraft.getInstance().font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

}