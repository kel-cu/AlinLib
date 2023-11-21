package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class TextBox extends AbstractWidget {
    private boolean isCentred;
    private Component tooltipMessage;
    public TextBox(int x, int y, int width, int height, Component label, boolean isCenter){
        super(x, y, width, height, label);
        this.isCentred = isCenter;
        this.setActive(false);
    }
    public void setTooltip(Component tooltipMessage){
        this.tooltipMessage = tooltipMessage;
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
    protected void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        if(isHovered() && tooltipMessage != null){
            guiGraphics.renderTooltip(Minecraft.getInstance().font, tooltipMessage, i, j);
        }
        if(isDoesNotFit()) this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
        else if(this.isCentred) guiGraphics.drawCenteredString(Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        else guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
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
