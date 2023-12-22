package ru.kelcuprum.alinlib.gui.components.without_config.buttons;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import java.awt.*;

public class ButtonBoolean extends AbstractButton {
    private final InterfaceUtils.DesignType type;
    public boolean volume;
    public Component volumeState;
    private final OnPress onPressFunction;
    public final String buttonMessage;

    public ButtonBoolean(int x, int y, int width, int height, Component label, OnPress onPress) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, label, onPress);
    }
    public ButtonBoolean(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, OnPress onPress) {
        this(x, y, width, height, type, true, label, onPress);
    }
    public ButtonBoolean(int x, int y, int width, int height, InterfaceUtils.DesignType type, Boolean current, Component label, OnPress onPress) {
        super(x, y, width, height, label);
        this.type = type;
        this.buttonMessage = label.getString();
        this.volume = current;
        this.onPressFunction = onPress;
        this.setMessage(Component.literal(buttonMessage +": ").append(Component.translatable("alinlib.boolean." + (this.volume ? "true" : "false"))));
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
    @Override
    public void onPress() {
        if(!active) return;
        this.volume = !this.volume;
        this.setMessage(Component.literal(buttonMessage +": ").append(Component.translatable("alinlib.boolean." + (this.volume ? "true" : "false"))));
        this.onPressFunction.onPress(this.volume);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.volume ? Colors.SEADRIVE : Colors.GROUPIE);
            volumeState = Component.translatable("alinlib.boolean." + (this.volume ? "true" : "false"));

            if(isDoesNotFit()){
                this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
            } else {
                guiGraphics.drawString(Minecraft.getInstance().font, buttonMessage, getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
                guiGraphics.drawString(Minecraft.getInstance().font, volumeState, getX() + getWidth()-Minecraft.getInstance().font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
            }
        }
    }
    public boolean isDoesNotFit(){
        int size = Minecraft.getInstance().font.width(buttonMessage+": "+volume) + ((getHeight() - 8) / 2);
        return size > getWidth()-((getHeight() - 8) / 2)*2;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(Boolean volume);
    }

}