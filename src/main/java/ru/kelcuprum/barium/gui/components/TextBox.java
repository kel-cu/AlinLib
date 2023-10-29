package ru.kelcuprum.barium.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class TextBox {
    public TextBox(GuiGraphics guiGraphics, int x, int y, int width, int height, Component label, boolean isCenter){

        if(isCenter) guiGraphics.drawString(Minecraft.getInstance().font, label, x + (height - 8) / 2 + ((width/2) - (Minecraft.getInstance().font.width(label)/2)), y + (height - 8) / 2, 0xffffff);
        else guiGraphics.drawString(Minecraft.getInstance().font, label, x + (height - 8) / 2, y + (height - 8) / 2, 0xffffff);
    }
}
