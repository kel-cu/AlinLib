package ru.kelcuprum.alinlib.gui.components.sliders.base;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Description;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SliderPercent extends AbstractSliderButton implements Description {
    public final InterfaceUtils.DesignType type;
    public final String buttonMessage;
    public OnPress onPress;

    public SliderPercent(int x, int y, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), 0, label, null);
    }
    public SliderPercent(int x, int y, InterfaceUtils.DesignType type, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, 0, label, null);
    }
    public SliderPercent(int x, int y, InterfaceUtils.DesignType type, double position, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, label, null);
    }
    //
    public SliderPercent(int x, int y, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), 0, label, onPress);
    }
    public SliderPercent(int x, int y, InterfaceUtils.DesignType type, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, 0, label, onPress);
    }
    public SliderPercent(int x, int y, InterfaceUtils.DesignType type, double position, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, label, onPress);
    }
    //
    public SliderPercent(int x, int y, int width, int height, Component label) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), 0, label, null);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label) {
        this(x, y, width, height, type, 0, label, null);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, double position, Component label) {
        this(x, y, width, height, type, position, label, null);
    }
    //
    public SliderPercent(int x, int y, int width, int height, Component label, OnPress onPress) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), 0, label, onPress);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, OnPress onPress) {
        this(x, y, width, height, type, 0, label, onPress);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, double position, Component label, OnPress onPress) {
        super(x, y, width, height, label, position);
        this.type = type;
        this.onPress = onPress;
        this.buttonMessage = label.getString();
        this.setMessage(Component.literal(buttonMessage).append(": ").append(getComponentValue()));
    }
    // Получить
    public Component getComponentValue(){
        return Component.literal(Localization.getRounding(getValue() * 100,   true)+"%");
    }
    public double getValue(){
        return this.value;
    }

    // Заменить
    public SliderPercent setActive(boolean active){
        this.active = active;
        return this;
    }
    public SliderPercent setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }

    // Рендер
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        if(this.visible){
            renderBackground(guiGraphics, mouseX, mouseY, tick);
            renderText(guiGraphics, mouseX, mouseY, tick);
        }
    }
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        this.type.renderSliderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.value, this);
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        if(InterfaceUtils.isDoesNotFit(Component.literal(buttonMessage).append(": ").append(getComponentValue()), getWidth(), getHeight())){
            if(isHoveredOrFocused()){
                this.setMessage(getComponentValue());
            } else {
                this.setMessage(Component.literal(buttonMessage).append(": ").append(getComponentValue()));
            }
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        } else {
            if(isHovered()){
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getComponentValue(), getX() + (getWidth()/2) - (AlinLib.MINECRAFT.font.width(getComponentValue().getString())/2), getY() + (getHeight() - 8) / 2, 0xffffff);
            } else {
                guiGraphics.drawString(AlinLib.MINECRAFT.font, buttonMessage, getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
                // VOLUME
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getComponentValue(), getX() + getWidth() - AlinLib.MINECRAFT.font.width(getComponentValue().getString()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
            }
        }
    }



    // BASE
    @Override
    protected void updateMessage() {

    }

    @Override
    protected void applyValue(){
        if(this.onPress != null) this.onPress.onPress(this.value);
    }
    // Мелочи

    public interface OnPress {
        void onPress(double value);
    }

    protected Component description;
    public SliderPercent setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
