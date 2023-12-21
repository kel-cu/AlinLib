package ru.kelcuprum.alinlib.gui.components.without_config.sliders;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import java.awt.*;

public class SliderPercent extends AbstractSliderButton {
    private final InterfaceUtils.DesignType type;
    public final double current;
    public final String buttonMessage;
    Component volumeState;
    private final OnPress onPressFunction;
    public SliderPercent(int x, int y, int width, int height, int current, Component component, OnPress onPress) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, current, component, onPress);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, double current, Component component, OnPress onPress) {
        super(x, y, width, height, component, current);
        this.type = type;
        this.current = current;
        this.onPressFunction = onPress;
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

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float tick) {
        this.type.renderSliderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), Colors.SEADRIVE, this.value, this);

        volumeState = Component.translatable(Localization.getRounding(this.value * 100,   true)+"%");
        if(isDoesNotFit()){
            if(isHoveredOrFocused()){
                this.setMessage(volumeState);
            } else {
                this.setMessage(Component.literal(buttonMessage).append(": ").append(volumeState));
            }
            this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
        } else {
            if(isHovered()){
                guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + (getWidth()/2) - (Minecraft.getInstance().font.width(volumeState.getString())/2), getY() + (getHeight() - 8) / 2, 0xffffff);
            } else {
                guiGraphics.drawString(Minecraft.getInstance().font, buttonMessage, getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
                // VOLUME
                guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth() - Minecraft.getInstance().font.width(volumeState.getString()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
            }
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
        this.onPressFunction.onPress(this.current);
    }

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(double volume);
    }
}
