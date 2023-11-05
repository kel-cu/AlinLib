package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;

import java.awt.*;

public class BooleanButton extends AbstractButton {
    public boolean volume;
    public final boolean defaultConfig;
    public final Config config;
    public final String typeConfig;
    public BooleanButton(int x, int y, int width, int height, Config config, String typeConfig, boolean defaultConfig, Component label) {
        super(x, y, width, height, label);
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.volume = config.getBoolean(typeConfig, defaultConfig);
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

    public void resetVolume(){
        this.volume = defaultConfig;
        this.config.setBoolean(typeConfig, this.volume);
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

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

}