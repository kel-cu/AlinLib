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

public class Button extends AbstractButton {
    final InterfaceUtils.DesignType type;
    int color;
    private final boolean isCentred;
    private OnPress onPress;


    public Button(int x, int y, int width, int height, Component label, OnPress onPress){
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, Colors.SEADRIVE, true, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, OnPress onPress){
        this(x, y, width, height, type, Colors.SEADRIVE, true, label, onPress);
    }
    //
    public Button(int x, int y, int width, int height, int color, Component label, OnPress onPress){
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, color, true, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, Component label, OnPress onPress){
        this(x, y, width, height, type, color, true, label, onPress);
    }
    //
    public Button(int x, int y, int width, int height, boolean isCentred, Component label, OnPress onPress){
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, Colors.SEADRIVE, isCentred, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, boolean isCentred, Component label, OnPress onPress){
        this(x, y, width, height, type, Colors.SEADRIVE, isCentred, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, boolean isCentred, Component label, OnPress onPress) {
        super(x, y, width, height, label);
        this.type = type;
        this.color = color;
        this.onPress = onPress;
        this.isCentred = isCentred;
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
    public void setActive(boolean active){
        this.active = active;
    }
    public void setColor(int color) { this.color = color; }
    public void setOnPress(OnPress onPress) { this.onPress = onPress; }
    @Override
    public void onPress() {
        if(this.onPress != null) this.onPress.onPress(this);
    }
    @Override
    public void setMessage(Component component) {
        super.setMessage(component);
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.color);
            if(isDoesNotFit()) this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
            else if(isCentred) InterfaceUtils.drawCenteredString(guiGraphics, Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff, type == InterfaceUtils.DesignType.VANILLA);
            else guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff, type == InterfaceUtils.DesignType.VANILLA);
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
