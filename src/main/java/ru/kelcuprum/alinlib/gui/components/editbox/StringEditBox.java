package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.function.Consumer;

public class StringEditBox extends net.minecraft.client.gui.components.EditBox {
    public final int posX;
    public StringEditBox(int x, int y, int width, int height, Component label, String volume, Consumer<String> consumer) {
        super(Minecraft.getInstance().font, x+(Minecraft.getInstance().font.width(label)+12), y, width-(Minecraft.getInstance().font.width(label)+12), height, label);
        this.posX = x;
        setMaxLength(256);
        this.setValue(volume);
        this.setResponder(consumer);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = 2;
            if (!active) {
                state = 5;
            } else if (isHovered) {
                state = 4;
            }
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);

            guiGraphics.fill(this.posX, getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
            // YELLOW - 0xFFFFEE31
            // GREEN  - 0xFF31FF83
            guiGraphics.fill(getX()-1, getY()+((getHeight() - 8) / 2), getX(), getY() + getHeight()-1-((getHeight() - 8) / 2), isFocused() ? 0xFFFFEE31 : 0xFF31FF83); // |
            guiGraphics.fill(this.posX, getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), new Color(isFocused() ? 0xFFFFEE31 : 0xFF31FF83, true).getRGB()); // _
//            guiGraphics.drawCenteredString(Minecraft.getInstance().font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff);

            guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), this.posX + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            Component volume = Component.literal(this.getValue());
            guiGraphics.drawString(Minecraft.getInstance().font, volume, this.getX() + 4, getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }
}
