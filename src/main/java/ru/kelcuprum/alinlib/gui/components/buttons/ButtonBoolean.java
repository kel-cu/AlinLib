package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

public class ButtonBoolean extends AbstractButton {
    private final InterfaceUtils.DesignType type;
    public boolean volume;
    public Component volumeState;
    public final boolean defaultConfig;
    public final Config config;
    public final String typeConfig;
    public final String buttonMessage;
    public ButtonBoolean(int x, int y, int width, int height, Config config, String typeConfig, boolean defaultConfig, Component label) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, label);
    }
    public ButtonBoolean(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, boolean defaultConfig, Component label) {
        super(x, y, width, height, label);
        this.type = type;
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.buttonMessage = label.getString();
        this.volume = config.getBoolean(typeConfig, defaultConfig);
        this.setMessage(Component.literal(buttonMessage +": ").append(Component.translatable("alinlib.boolean." + (this.volume ? "true" : "false"))));
    }
    public void setXPos(int x) {
        this.setX(x);
    }
    public void setYPos(int y) {
        this.setY(y);
    }
    public void setPos(int x, int y) {
        this.setPosition(x, y);
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public void resetVolume(){
        this.volume = defaultConfig;
        this.config.setBoolean(typeConfig, this.volume);
    }

    @Override
    public void onPress() {
        if(!active) return;
        this.volume = !this.volume;
        this.setMessage(Component.literal(buttonMessage +": ").append(Component.translatable("alinlib.boolean." + (this.volume ? "true" : "false"))));
        config.setBoolean(typeConfig, this.volume);
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

}