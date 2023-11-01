package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import ru.kelcuprum.alinlib.AlinLib;

import java.awt.*;
import java.util.function.Consumer;

import static java.lang.Math.max;

public class StringEditBox extends net.minecraft.client.gui.components.EditBox {
    private int displayPos;
    public StringEditBox(int x, int y, int width, int height, Component label) {
        super(Minecraft.getInstance().font, x, y, width, height, label);
        this.setMaxLength(Integer.MAX_VALUE);
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
    public void setContent(String content){
        this.setValue(content);
    }
    public void setResponse(Consumer<String> consumer){
        this.setResponder(consumer);
    }
    @Override
    public void onClick(double d, double e) {
//        super.onClick(d, e);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = !active ? 3 : isHoveredOrFocused() ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);

            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
            guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), new Color(isFocused() ? 0xFFFFEE31 : 0xFF31FF83, true).getRGB());

            guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            Component volume = Component.literal(this.getValue());
            if(isFocused()){
                if(this.getValue().length() != this.getCursorPosition()){
                    int position = Minecraft.getInstance().font.width(this.getValue().substring(0, this.getCursorPosition()));
                    int symbolSize = Minecraft.getInstance().font.width(this.getValue().split("(?!^)")[this.getCursorPosition()]);
                    int renderPosition = (getX() + getWidth()-Minecraft.getInstance().font.width(volume)-((getHeight() - 8) / 2)) + position;
                    int y = (getY() + (getHeight() - 8) / 2)+Minecraft.getInstance().font.lineHeight+1;

                    guiGraphics.fill(renderPosition-1, y, renderPosition+symbolSize, y+1, new Color(0xFFFFFFFF, true).getRGB());
                }

            }
            guiGraphics.drawString(Minecraft.getInstance().font, volume, getX() + getWidth()-Minecraft.getInstance().font.width(volume.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }
}
