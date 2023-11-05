package ru.kelcuprum.alinlib.gui.components.selector;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;

import java.util.Arrays;

public class SelectorStringButton extends AbstractButton {
    public int currentPosition = 0;
    public String[] list;
    public Config config;
    public String typeConfig;
    public SelectorStringButton(int x, int y, int width, int height, String[] list, Config config, String typeConfig, String defaultVolume, Component label) {
        super(x, y, width, height, label);

        this.typeConfig = typeConfig;
        this.config = config;
        this.list = list;

        this.currentPosition = Arrays.stream(this.list).toList().indexOf(this.config.getString(typeConfig, defaultVolume));
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
    public void onPress() {
        this.currentPosition++;
        if(this.list.length == this.currentPosition) this.currentPosition = 0;
        this.config.setString(this.typeConfig, this.list[this.currentPosition]);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = !active ? 3 : isHovered ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);
            // BASE
            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
            guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), 0xFF31FF83);
            guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            // VOLUME
            Component volumeState = Component.literal(this.list[this.currentPosition]);
            guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth()-Minecraft.getInstance().font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

}