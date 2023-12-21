package ru.kelcuprum.alinlib.gui.components.selector;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import java.util.Arrays;

public class SelectorStringButton extends AbstractButton {
    private final InterfaceUtils.DesignType type;
    public int currentPosition = 0;
    public String[] list;
    public Config config;
    public String typeConfig;
    public final String buttonMessage;
    public SelectorStringButton(int x, int y, int width, int height, String[] list, Config config, String typeConfig, String defaultVolume, Component label) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, list, config, typeConfig, defaultVolume, label);
    }
    public SelectorStringButton(int x, int y, int width, int height, InterfaceUtils.DesignType type, String[] list, Config config, String typeConfig, String defaultVolume, Component label) {
        super(x, y, width, height, label);

        this.type = type;
        this.typeConfig = typeConfig;
        this.config = config;
        this.list = list;
        this.buttonMessage = label.getString();

        this.currentPosition = Arrays.stream(this.list).toList().indexOf(this.config.getString(typeConfig, defaultVolume));
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
    public void onPress() {
        this.currentPosition++;
        if(this.list.length == this.currentPosition) this.currentPosition = 0;
        this.config.setString(this.typeConfig, this.list[this.currentPosition]);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), Colors.SEADRIVE);
            Component volumeState = Component.literal(this.list[this.currentPosition]);
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
    }
    public boolean isDoesNotFit(){
        int size = Minecraft.getInstance().font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return size > getWidth();
    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

}