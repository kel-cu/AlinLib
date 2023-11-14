package ru.kelcuprum.alinlib.gui.components.sliders.flat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;

import java.awt.*;

public class FlatSliderInteger extends AbstractSliderButton {
    public final int defaultConfig;
    public final Config config;
    public final String typeConfig;
    public final int min;
    public final int max;
    public int displayValue;
    public String typeInteger = "";
    public FlatSliderInteger(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, int min, int max, Component component) {
        super(x, y, width, height, component, ((double) (config.getInt(typeConfig, defaultConfig) - min) /(max-min)));
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.displayValue = this.config.getInt(this.typeConfig, this.defaultConfig);
        this.min = min;
        this.max = max;
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

    public void setTypeInteger(String type){
        this.typeInteger = type;
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float tick) {
        float state = !active ? 3 : isHovered ? 2 : 1;
        final float f = state / 2 * 0.9F + 0.1F;
        final int color = (int) (255.0F * f);

        guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), color / 2 << 24);
        if(isHoveredOrFocused()){
            int x = this.getX() + (int)(this.value * (double)(this.width - 4));
            int y = this.getY()+(getHeight() - 8) / 2;
            guiGraphics.fill(x, y, x+4, y+Minecraft.getInstance().font.lineHeight, new Color(isFocused() ? 0xFFFFEE31 : 0xFF31FF83, true).getRGB());
        }

        guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        Component volumeState = Component.translatable(displayValue+typeInteger);
        guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth()-Minecraft.getInstance().font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
    }

    @Override
    protected void updateMessage() {

    }

    @Override
    protected void applyValue() {
        int selValue = (int) ((this.max-this.min)*this.value);
        this.displayValue = this.min+selValue;
        this.config.setInt(this.typeConfig, this.displayValue);

    }
}
