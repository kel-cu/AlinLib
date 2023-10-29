package ru.kelcuprum.barium.gui.components.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.barium.config.Config;

import java.awt.*;

public class BooleanButton extends Button {
    public boolean volume;
    public boolean defaultVolume;
    public Config configuration;
    public String config;
    public BooleanButton(int x, int y, int width, int height, Component label, String volume, boolean defaultVolume, Config configuration) {
        super(x, y, width, height, label, Button::onPress, DEFAULT_NARRATION);
        this.configuration = configuration;
        this.config = volume;
        this.defaultVolume = defaultVolume;
        this.volume = configuration.getBoolean(volume, defaultVolume);
    }

    @Override
    public void onPress() {
        this.volume = !this.volume;
        configuration.setBoolean(config, this.volume);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = 2;
            if (!active) {
                state = 5;
            } else if (isHovered) {
                state = 4;
            }
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);

            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
            guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), new Color(volume ? 0xFF31FF83 : 0xB6FF3131, true).getRGB());
            guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            Component volumeState = Component.translatable("barium.boolean." + (this.volume ? "true" : "false"));
            guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth()-Minecraft.getInstance().font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

}