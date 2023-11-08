package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;

import java.awt.*;

public class SliderInteger extends AbstractSliderButton {
    public final int defaultConfig;
    public final Config config;
    public final String typeConfig;
    public final int min;
    public final int max;
    public int displayValue;
    public String typeInteger = "";
    public SliderInteger(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, int min, int max, Component component) {
        super(x, y, width, height, component, ((double) (config.getInt(typeConfig, defaultConfig) - min) /(max-min)));
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.displayValue = this.config.getInt(this.typeConfig, this.defaultConfig);
        this.min = min;
        this.max = max;
    }

    public void setTypeInteger(String type){
        this.typeInteger = type;
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float tick) {
        // this.getX() + (int)(this.value * (double)(this.width - 8)), this.getY(), 8, this.getHeight()
        //
        float state = !active ? 3 : isHovered ? 2 : 1;
        final float f = state / 2 * 0.9F + 0.1F;
        final int color = (int) (255.0F * f);

        guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
        guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), new Color(isFocused() ? 0xFFFFEE31 : 0xFF31FF83, true).getRGB());
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
