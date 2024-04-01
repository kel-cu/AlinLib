package ru.kelcuprum.alinlib.gui.components.editbox.base;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Description;

import java.util.function.Consumer;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class EditBoxString extends EditBox implements Description {
    private final InterfaceUtils.DesignType type;
    private final boolean secret;
    private boolean tweakBorder;
    protected boolean isError;


    ///
    public EditBoxString(int i, int j, boolean secret, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, "", AlinLib.getDefaultDesignType(), component, null);
    }
    public EditBoxString(int i, int j, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, "", AlinLib.getDefaultDesignType(), component, null);
    }
    public EditBoxString(int i, int j, boolean secret, InterfaceUtils.DesignType type, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, "", type, component, null);
    }
    public EditBoxString(int i, int j, InterfaceUtils.DesignType type, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, "", type, component, null);
    }

    // NO FONT
    public EditBoxString(int i, int j, boolean secret, String value, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, value, AlinLib.getDefaultDesignType(), component, responder);
    }


    public EditBoxString(int i, int j, String value, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, value, AlinLib.getDefaultDesignType(), component, responder);
    }

    public EditBoxString(int i, int j, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, value, type, component, responder);
    }
    public EditBoxString(int i, int j, boolean secret, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, value, type, component, responder);

    }
    // FONT
    public EditBoxString(Font font, int i, int j, boolean secret, String value, Component component, Consumer<String> responder) {
        this(font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, value, AlinLib.getDefaultDesignType(), component, responder);
    }


    public EditBoxString(Font font, int i, int j, String value, Component component, Consumer<String> responder) {
        this(font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, value, AlinLib.getDefaultDesignType(), component, responder);
    }

    public EditBoxString(Font font, int i, int j, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, value, type, component, responder);
    }

    public EditBoxString(Font font, int i, int j, boolean secret, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, value, type, component, responder);
    }

    ////


    public EditBoxString(int i, int j, int k, int l, boolean secret, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, secret, "", AlinLib.getDefaultDesignType(), component, null);
    }
    public EditBoxString(int i, int j, int k, int l, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, false, "", AlinLib.getDefaultDesignType(), component, null);
    }
    public EditBoxString(int i, int j, int k, int l, boolean secret, InterfaceUtils.DesignType type, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, secret, "", type, component, null);
    }
    public EditBoxString(int i, int j, int k, int l, InterfaceUtils.DesignType type, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, false, "", type, component, null);
    }

    // NO FONT
    public EditBoxString(int i, int j, int k, int l, boolean secret, String value, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, secret, value, AlinLib.getDefaultDesignType(), component, responder);
    }


    public EditBoxString(int i, int j, int k, int l, String value, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, false, value, AlinLib.getDefaultDesignType(), component, responder);
    }

    public EditBoxString(int i, int j, int k, int l, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, false, value, type, component, responder);
    }
    public EditBoxString(int i, int j, int k, int l, boolean secret, String value, InterfaceUtils.DesignType type, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, secret, value, type, component, responder);

    }
    // FONT
    public EditBoxString(Font font, int i, int j, int k, int l, boolean secret, String value, Component component, Consumer<String> responder) {
        this(font, i, j, k, l, secret, value, AlinLib.getDefaultDesignType(), component, responder);
    }


    public EditBoxString(Font font, int i, int j, int k, int l, String value, Component component, Consumer<String> responder) {
        this(font, i, j, k, l, false, value, AlinLib.getDefaultDesignType(), component, responder);
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

    protected int getPositionContent(String content) {
        int pos = getX() + getWidth() - font.width(content) - ((getHeight() - 8) / 2);

        if (getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2 > pos)
            pos = getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2;

        return pos;
    }

    protected int getColor() {
        return (getError() ? InterfaceUtils.Colors.GROUPIE : isFocused() ? InterfaceUtils.Colors.CLOWNFISH : InterfaceUtils.Colors.SEADRIVE);
    }

    public EditBoxString setError(boolean error) {
        this.isError = error;
        return this;
    }
    public boolean getError() { return this.isError; }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (isVisible()) {
            renderBackground(guiGraphics, mouseX, mouseY, partialTick);
            if (isFocused()) {
                this.tweakBorder = true;
                super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);
                this.tweakBorder = false;
            } else {
                renderText(guiGraphics, mouseX, mouseY, partialTick);
            }
        }
    }
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick){
        this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.getColor());
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick){
        guiGraphics.drawString(font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, isError ? InterfaceUtils.Colors.GROUPIE : -1);
        String volume1 = font.plainSubstrByWidth(secret ? Component.translatable("alinlib.editbox.secret").getString() : getValue(), getX() + getWidth() - (getPositionContent(secret ? Component.translatable("alinlib.editbox.secret").getString() : getValue())));
        guiGraphics.drawString(font, formatter.apply(volume1, displayPos), getPositionContent(volume1), getY() + (getHeight() - 8) / 2, isError ? InterfaceUtils.Colors.GROUPIE : -1);
    }

    @Override
    public boolean isBordered() {
        if (tweakBorder) {
            return false;
        }
        return super.isBordered();
    }
    protected Component description;
    public EditBoxString setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}