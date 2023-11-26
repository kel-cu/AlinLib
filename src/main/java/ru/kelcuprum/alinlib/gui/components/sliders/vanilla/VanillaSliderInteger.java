package ru.kelcuprum.alinlib.gui.components.sliders.vanilla;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.config.Config;

import java.awt.*;

public class VanillaSliderInteger extends AbstractSliderButton {

    private static final ResourceLocation SLIDER_SPRITE = new ResourceLocation("widget/slider");
    private static final ResourceLocation HIGHLIGHTED_SPRITE = new ResourceLocation("widget/slider_highlighted");
    private static final ResourceLocation SLIDER_HANDLE_SPRITE = new ResourceLocation("widget/slider_handle");
    private static final ResourceLocation SLIDER_HANDLE_HIGHLIGHTED_SPRITE = new ResourceLocation("widget/slider_handle_highlighted");
    //
    public final int defaultConfig;
    public final Config config;
    public final String typeConfig;
    public final int min;
    public final int max;
    public int displayValue;
    public String typeInteger = "";
    public final String buttonMessage;
    Component volumeState;
    public VanillaSliderInteger(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, int min, int max, Component component) {
        super(x, y, width, height, component, ((double) (config.getInt(typeConfig, defaultConfig) - min) /(max-min)));
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.displayValue = this.config.getInt(this.typeConfig, this.defaultConfig);
        this.min = min;
        this.max = max;
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

    public void setTypeInteger(String type){
        this.typeInteger = type;
    }

    private ResourceLocation getSprite() {
        return this.isFocused() ? HIGHLIGHTED_SPRITE : SLIDER_SPRITE;
    }

    private ResourceLocation getHandleSprite() {
        return this.isHoveredOrFocused() ? SLIDER_HANDLE_SPRITE : SLIDER_HANDLE_HIGHLIGHTED_SPRITE;
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float tick) {
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        guiGraphics.blitSprite(this.getSprite(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        if(isHoveredOrFocused()){
            guiGraphics.blitSprite(this.getHandleSprite(), this.getX() + (int)(this.value * (double)(this.width - 8)), this.getY(), 8, this.getHeight());
        }

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
        this.config.setInt(this.typeConfig, this.displayValue);

    }
}
