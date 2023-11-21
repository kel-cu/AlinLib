package ru.kelcuprum.alinlib.gui.components.selector.flat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;

public class FlatSelectorStringWithIntButton extends AbstractButton {
    public int currentPosition = 0;
    public String[] list;
    public Config config;
    public String typeConfig;
    public final String buttonMessage;
    public FlatSelectorStringWithIntButton(int x, int y, int width, int height, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
        super(x, y, width, height, label);

        this.typeConfig = typeConfig;
        this.config = config;
        this.list = list;
        this.buttonMessage = label.getString();

        this.currentPosition = this.config.getInt(typeConfig, defaultVolume);
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
        this.config.setInt(this.typeConfig, this.currentPosition);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = !active ? 3 : isHovered ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);
            // BASE
            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), color / 2 << 24);
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