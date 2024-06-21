package ru.kelcuprum.alinlib.gui.components.buttons.base;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class ButtonBoolean extends Button {
    public boolean value;
    public Component volumeState;
    private OnPress onPress;
    public final String buttonMessage;

    public ButtonBoolean(int x, int y, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, InterfaceUtils.DesignType.FLAT, label, onPress);
    }
    public ButtonBoolean(int x, int y, InterfaceUtils.DesignType type, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, true, label, onPress);
    }
    public ButtonBoolean(int x, int y, InterfaceUtils.DesignType type, Boolean current, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, current, label, onPress);
    }
    ////
    public ButtonBoolean(int x, int y, int width, int height, Component label, OnPress onPress) {
        this(x, y, width, height, InterfaceUtils.DesignType.FLAT, label, onPress);
    }
    public ButtonBoolean(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, OnPress onPress) {
        this(x, y, width, height, type, true, label, onPress);
    }
    public ButtonBoolean(int x, int y, int width, int height, InterfaceUtils.DesignType type, Boolean current, Component label, OnPress onPress) {
        super(x, y, width, height, type, label, null);
        this.buttonMessage = label.getString();
        this.value = current;
        this.onPress = onPress;
        volumeState = this.value ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
        this.setMessage(Component.literal(buttonMessage +": ").append(volumeState));
    }
    @Override
    public void onPress() {
        if(!active) return;
        this.setValue(!this.value);
        if(this.onPress != null) this.onPress.onPress(this.value);
    }

    @Override
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if(GuiUtils.isDoesNotFit(getMessage(), getWidthComponent(), getHeight())){
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(AlinLib.MINECRAFT.font, buttonMessage, getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            guiGraphics.drawString(AlinLib.MINECRAFT.font, volumeState, getX() + getWidth()-AlinLib.MINECRAFT.font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }
    // Получить
    public boolean getValue() { return value; }
    // Заменить
    public ButtonBoolean setValue(boolean value){
        this.value = value;
        this.volumeState = this.value ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
        this.setMessage(Component.literal(buttonMessage +": ").append(volumeState));
        return this;
    }
    public ButtonBoolean setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public interface OnPress {
        void onPress(Boolean volume);
    }


    protected Component description;
    public ButtonBoolean setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}