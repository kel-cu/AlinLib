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
    public final String buttonMessage;
    Component volumeState;
    public FlatSliderInteger(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, int min, int max, Component component) {
        super(x, y, width, height, component, ((double) (config.getInt(typeConfig, defaultConfig) - min) /(max-min)));
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.displayValue = this.config.getInt(this.typeConfig, this.defaultConfig);
        this.min = min;
        this.max = max;
        this.buttonMessage = component.getString();
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

        volumeState = Component.translatable(displayValue+typeInteger);
        if(isDoesNotFit()){
            if(isHoveredOrFocused()){
                this.setMessage(volumeState);
            } else {
                this.setMessage(Component.literal(buttonMessage).append(": ").append(volumeState));
            }
            this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(Minecraft.getInstance().font, buttonMessage, getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            // VOLUME
            guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth() - Minecraft.getInstance().font.width(volumeState.getString()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

    public boolean isDoesNotFit(){
        int size = Minecraft.getInstance().font.width(buttonMessage+": "+volumeState.getString()) + ((getHeight() - 8) / 2)*2;
        return size > getWidth();
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
