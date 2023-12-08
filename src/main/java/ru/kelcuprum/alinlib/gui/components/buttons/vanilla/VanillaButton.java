package ru.kelcuprum.alinlib.gui.components.buttons.vanilla;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

public class VanillaButton extends AbstractButton {
    private static final WidgetSprites SPRITES = new WidgetSprites(new ResourceLocation("widget/button"), new ResourceLocation("widget/button_disabled"), new ResourceLocation("widget/button_highlighted"));
    private final boolean isCentred;
    private final OnPress onPress;

    public VanillaButton(int x, int y, int width, int height, Component label, OnPress onPress){
        this(x, y, width, height, label, true, onPress);
    }
    public VanillaButton(int x, int y, int width, int height, boolean isCentred, Component label, OnPress onPress){
        this(x, y, width, height, label, isCentred, onPress);
    }
    public VanillaButton(int x, int y, int width, int height, Component label, boolean isCentred, OnPress onPress) {
        super(x, y, width, height, label);
        this.onPress = onPress;
        this.isCentred = isCentred;
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

    @Override
    public void onPress() {
        this.onPress.onPress(this);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.enableBlend();
            RenderSystem.enableDepthTest();
            guiGraphics.blitSprite(SPRITES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            if(isDoesNotFit()) this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
            else if(isCentred) InterfaceUtils.drawCenteredString(guiGraphics, Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff, false);
            else guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff, false);
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

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(VanillaButton button);
    }
}