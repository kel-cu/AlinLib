package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

public class EditBoxString extends EditBox {
    private final InterfaceUtils.DesignType type;
    private final boolean secret;
    private boolean tweakBorder;
    protected boolean isError;

    public EditBoxString(int i, int j, int k, int l, Component component) {
        this(Minecraft.getInstance().font, i, j, k, l, component);
    }

    public EditBoxString(Font font, int i, int j, int k, int l, Component component) {
        this(font, i, j, k, l, false, component, InterfaceUtils.DesignType.ALINA);
    }


    public EditBoxString(int i, int j, int k, int l, boolean secret, Component component) {
        this(Minecraft.getInstance().font, i, j, k, l, secret, component);
    }

    public EditBoxString(Font font, int i, int j, int k, int l, boolean secret, Component component) {
        this(font, i, j, k, l, secret, component, InterfaceUtils.DesignType.ALINA);
    }

    public EditBoxString(Font font, int i, int j, int k, int l, boolean secret, Component component, InterfaceUtils.DesignType type) {
        super(font, i, j, k, l, null, component);
        this.secret = secret;
        this.type = type;
    }

    private int getPositionContent(String content) {
        int pos = getX() + getWidth() - font.width(content) - ((getHeight() - 8) / 2);

        if (getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2 > pos)
            pos = getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2;

        return pos;
    }

    protected int getColor() {
        return (isFocused() ? Colors.CLOWNFISH : Colors.SEADRIVE);
    }

    public void setError(boolean error) {
        this.isError = error;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (isVisible()) {

            this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.getColor());

            if (isFocused()) {
                this.tweakBorder = true;
                super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);
                this.tweakBorder = false;
            } else {
                guiGraphics.drawString(font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, isError ? Colors.GROUPIE : -1);

                String volume1 = font.plainSubstrByWidth(secret ? Component.translatable("alinlib.editbox.secret").getString() : getValue(), getX() + getWidth() - (getPositionContent(getValue())));

                guiGraphics.drawString(font, formatter.apply(volume1, displayPos), getPositionContent(volume1), getY() + (getHeight() - 8) / 2, isError ? Colors.GROUPIE : -1);
            }
        }
    }

    @Override
    public boolean isBordered() {
        if (tweakBorder) {
            return false;
        }

        return super.isBordered();
    }
}