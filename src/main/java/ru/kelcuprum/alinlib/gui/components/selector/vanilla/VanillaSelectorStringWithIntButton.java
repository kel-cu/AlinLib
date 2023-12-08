package ru.kelcuprum.alinlib.gui.components.selector.vanilla;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.config.Config;

public class VanillaSelectorStringWithIntButton extends AbstractButton {
    private static final WidgetSprites SPRITES = new WidgetSprites(new ResourceLocation("widget/button"), new ResourceLocation("widget/button_disabled"), new ResourceLocation("widget/button_highlighted"));
    public int currentPosition = 0;
    public String[] list;
    public Config config;
    public String typeConfig;
    public final String buttonMessage;
    public VanillaSelectorStringWithIntButton(int x, int y, int width, int height, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
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
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.enableBlend();
            RenderSystem.enableDepthTest();
            guiGraphics.blitSprite(SPRITES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
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