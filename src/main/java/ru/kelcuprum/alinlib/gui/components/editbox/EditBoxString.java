package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import java.util.function.Consumer;

public class EditBoxString extends EditBox {
    private final InterfaceUtils.DesignType type;
    private final boolean secret;
    private boolean tweakBorder;
    protected boolean isError;


    public EditBoxString(int i, int j, int k, int l, boolean secret, Component component) {
        this(Minecraft.getInstance().font, i, j, k, l, secret, "", InterfaceUtils.DesignType.ALINA, component, null);
    }
    public EditBoxString(int i, int j, int k, int l, Component component) {
        this(Minecraft.getInstance().font, i, j, k, l, false, "", InterfaceUtils.DesignType.ALINA, component, null);
    }
    public EditBoxString(int i, int j, int k, int l, boolean secret, InterfaceUtils.DesignType type, Component component) {
        this(Minecraft.getInstance().font, i, j, k, l, secret, "", type, component, null);
    }
    public EditBoxString(int i, int j, int k, int l, InterfaceUtils.DesignType type, Component component) {
        this(Minecraft.getInstance().font, i, j, k, l, false, "", type, component, null);
    }

    // NO FONT
    public EditBoxString(int i, int j, int k, int l, boolean secret, String value, Component component, Consumer<String> responder) {
        this(Minecraft.getInstance().font, i, j, k, l, secret, value, InterfaceUtils.DesignType.ALINA, component, responder);
    }


    public EditBoxString(int i, int j, int k, int l, String value, Component component, Consumer<String> responder) {
        this(Minecraft.getInstance().font, i, j, k, l, false, value, InterfaceUtils.DesignType.ALINA, component, responder);
    }

    public EditBoxString(int i, int j, int k, int l, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(Minecraft.getInstance().font, i, j, k, l, false, value, type, component, responder);
    }
    public EditBoxString(int i, int j, int k, int l, boolean secret, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(Minecraft.getInstance().font, i, j, k, l, secret, value, type, component, responder);

    }
    // FONT
    public EditBoxString(Font font, int i, int j, int k, int l, boolean secret, String value, Component component, Consumer<String> responder) {
        this(font, i, j, k, l, secret, value, InterfaceUtils.DesignType.ALINA, component, responder);
    }


    public EditBoxString(Font font, int i, int j, int k, int l, String value, Component component, Consumer<String> responder) {
        this(font, i, j, k, l, false, value, InterfaceUtils.DesignType.ALINA, component, responder);
    }

    public EditBoxString(Font font, int i, int j, int k, int l, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(font, i, j, k, l, false, value, type, component, responder);
    }

    public EditBoxString(Font font, int i, int j, int k, int l, boolean secret, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        super(font, i, j, k, l, component);
        setMaxLength(Integer.MAX_VALUE);
        setValue(value);
        setResponder(responder);
        this.secret = secret;
        this.type = type;
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
    @Override
    public void setValue(String content) { super.setValue(content); }
    @Override
    public void setResponder(Consumer<String> responder) { super.setResponder(responder); }

    protected int getPositionContent(String content) {
        int pos = getX() + getWidth() - font.width(content) - ((getHeight() - 8) / 2);

        if (getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2 > pos)
            pos = getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2;

        return pos;
    }

    protected int getColor() {
        return (isFocused() ? InterfaceUtils.Colors.CLOWNFISH : InterfaceUtils.Colors.SEADRIVE);
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
                guiGraphics.drawString(font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, isError ? InterfaceUtils.Colors.GROUPIE : -1);

                String volume1 = font.plainSubstrByWidth(secret ? Component.translatable("alinlib.editbox.secret").getString() : getValue(), getX() + getWidth() - (getPositionContent(getValue())));

//                guiGraphics.drawString(Minecraft.getInstance().font, volume1, getX() + getWidth() - Minecraft.getInstance().font.width(volume1) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
                guiGraphics.drawString(font, formatter.apply(volume1, displayPos), getPositionContent(volume1), getY() + (getHeight() - 8) / 2, isError ? InterfaceUtils.Colors.GROUPIE : -1);
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