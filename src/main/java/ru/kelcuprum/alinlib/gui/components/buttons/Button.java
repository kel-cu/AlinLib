package ru.kelcuprum.alinlib.gui.components.buttons;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.flat.FlatColoredButton;

import java.awt.*;

public class Button extends AbstractButton {
    private final int color;
    private final boolean isCentred;
    private final OnPress onPress;


    public Button(int x, int y, int width, int height, Component label, OnPress onPress){
        this(x, y, width, height, Colors.SEADRIVE, true, label, onPress);
    }
    public Button(int x, int y, int width, int height, int color, Component label, OnPress onPress){
        this(x, y, width, height, color, true, label, onPress);
    }
    public Button(int x, int y, int width, int height, boolean isCentred, Component label, OnPress onPress){
        this(x, y, width, height, Colors.SEADRIVE, isCentred, label, onPress);
    }
    public Button(int x, int y, int width, int height, int color, boolean isCentred, Component label, OnPress onPress) {
        super(x, y, width, height, label);
        this.color = color;
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
    public void setMessage(Component component) {
        super.setMessage(component);
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = !active ? 3 : isHovered ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);

            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
            guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), this.color);
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
        void onPress(Button button);
    }
}
