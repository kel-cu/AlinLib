package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class TextBox extends AbstractWidget {
    private boolean isCentred;
    public TextBox(int x, int y, int width, int height, Component label, boolean isCenter){
        super(x, y, width, height, label);
        this.isCentred = isCenter;
//        if(isCenter) guiGraphics.drawString(Minecraft.getInstance().font, label, x + (height - 8) / 2 + ((width/2) - (Minecraft.getInstance().font.width(label)/2)), y + (height - 8) / 2, 0xffffff);
//        else guiGraphics.drawString(Minecraft.getInstance().font, label, x + (height - 8) / 2, y + (height - 8) / 2, 0xffffff);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        if(this.isCentred) guiGraphics.drawCenteredString(Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        else guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }
}
