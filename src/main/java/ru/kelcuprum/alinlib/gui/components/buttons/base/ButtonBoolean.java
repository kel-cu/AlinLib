package ru.kelcuprum.alinlib.gui.components.buttons.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

public class ButtonBoolean extends Button {
    public boolean value;
    public Component volumeState;
    private OnPress onPress;
    public final String buttonMessage;

    public ButtonBoolean(int x, int y, int width, int height, Component label, OnPress onPress) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, label, onPress);
    }
    public ButtonBoolean(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, OnPress onPress) {
        this(x, y, width, height, type, true, label, onPress);
    }
    public ButtonBoolean(int x, int y, int width, int height, InterfaceUtils.DesignType type, Boolean current, Component label, OnPress onPress) {
        super(x, y, width, height, type, label, null);
        this.buttonMessage = label.getString();
        this.value = current;
        this.onPress = onPress;
        this.setColor(this.value ? InterfaceUtils.Colors.SEADRIVE : InterfaceUtils.Colors.GROUPIE);
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
        if(InterfaceUtils.isDoesNotFit(getMessage(), getWidth(), getHeight())){
            this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(Minecraft.getInstance().font, buttonMessage, getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth()-Minecraft.getInstance().font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }
    // Получить
    public boolean getValue() { return value; }
    // Заменить
    public ButtonBoolean setValue(boolean value){
        this.value = value;
        this.volumeState = this.value ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
        this.setColor(this.value ? InterfaceUtils.Colors.SEADRIVE : InterfaceUtils.Colors.GROUPIE);
        this.setMessage(Component.literal(buttonMessage +": ").append(volumeState));
        return this;
    }public ButtonBoolean setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(Boolean volume);
    }

}