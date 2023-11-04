package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;

import java.awt.*;
import java.text.Format;
import java.util.function.Consumer;

import static java.lang.Math.max;

public class StringEditBox extends net.minecraft.client.gui.components.EditBox {
    private Font font = Minecraft.getInstance().font;
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
    private int getPositionContent(String content){
        int pos = getX() + getWidth()-Minecraft.getInstance().font.width(content)-((getHeight() - 8) / 2);
        if(getX() + Minecraft.getInstance().font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2 > pos) pos = getX() + this.font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return pos;
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
                    int position = this.font.width(this.getValue().substring(0, this.getCursorPosition()));
                    int symbolSize = this.font.width(this.getValue().split("(?!^)")[this.getCursorPosition()]);
                    int renderPosition = getPositionContent(volume.getString()) + position;
                    int y = (getY() + (getHeight() - 8) / 2)+Minecraft.getInstance().font.lineHeight+1;

                    guiGraphics.fill(renderPosition-1, y, renderPosition+symbolSize, y+1, new Color(0xFFFFFFFF, true).getRGB());
                }

            }
//            font.split(volume, getX()+getWidth()-(getPositionContent(volume.getString())- (getHeight() - 8) / 2));
//            guiGraphics.drawString(this.font, volume, getPositionContent(volume.getString()), getY() + (getHeight() - 8) / 2, 0xffffff);
            FormattedText volume1 = font.substrByWidth(volume, getX()+getWidth()-(getPositionContent(volume.getString())));
            int peepohuy = volume.getString().length() - volume1.getString().length();
            int peepohuy1 = volume.getString().length()-getCursorPosition();
            String volume2 = getValue().substring(Math.max(0, peepohuy-peepohuy1), volume.getString().length());
            String[] symbols = this.getValue().split("(?!^)");
            FormattedText volume3;
            if(symbols.length >=2) volume3 = font.substrByWidth(FormattedText.of(volume2), getX()+getWidth()-(getPositionContent(volume.getString()))+font.width(symbols[symbols.length-1]));
            else volume3 = font.substrByWidth(FormattedText.of(volume2), getX()+getWidth()-(getPositionContent(volume.getString())));
            guiGraphics.drawString(this.font, volume3.getString(), getPositionContent(volume.getString()), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }
}
