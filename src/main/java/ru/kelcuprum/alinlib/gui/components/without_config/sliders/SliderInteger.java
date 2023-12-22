package ru.kelcuprum.alinlib.gui.components.without_config.sliders;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import java.awt.*;

public class SliderInteger extends AbstractSliderButton {
    private final InterfaceUtils.DesignType type;
    public final int min;
    public final int max;
    public int displayValue;
    public String typeInteger = "";
    public final String buttonMessage;
    Component volumeState;
    private final OnPress onPressFunction;
    public SliderInteger(int x, int y, int width, int height, int current, int min, int max, Component component, OnPress onPress) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, current, min, max, component, onPress);
    }
    public SliderInteger(int x, int y, int width, int height, InterfaceUtils.DesignType type, int current, int min, int max, Component component, OnPress onPress) {
        super(x, y, width, height, component, ((double) (current - min) /(max-min)));
        this.type = type;
        this.onPressFunction = onPress;
        this.displayValue = current;
        this.min = min;
        this.max = max;
        this.buttonMessage = component.getString();
    }
    public void setActive(boolean active){
        this.active = active;
    }


    @Override
    public void setX(int x) {
        super.setX(x);
    }
    @Override
    public void setY(int y) {
        super.setY(y);
    }
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
    }

    public void setTypeInteger(String type){
        this.typeInteger = type;
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float tick) {
        this.type.renderSliderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), Colors.SEADRIVE, this.value, this);

        volumeState = Component.translatable(displayValue+typeInteger);
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
        int selValue = (int) ((this.max-this.min)*this.value);
        this.displayValue = this.min+selValue;
        this.onPressFunction.onPress(this.displayValue);
    }

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(int volume);
    }
}
