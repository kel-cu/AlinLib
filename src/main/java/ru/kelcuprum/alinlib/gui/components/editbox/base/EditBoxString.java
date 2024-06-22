package ru.kelcuprum.alinlib.gui.components.editbox.base;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import java.util.function.Consumer;

import static ru.kelcuprum.alinlib.gui.Icons.RESET;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class EditBoxString extends EditBox implements Description {
    private final AbstractStyle style;
    private final boolean secret;
    private boolean tweakBorder;
    protected boolean isError;


    ///
    public EditBoxString(int i, int j, boolean secret, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, "", GuiUtils.getSelected(), component, null);
    }
    public EditBoxString(int i, int j, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, "", GuiUtils.getSelected(), component, null);
    }
    public EditBoxString(int i, int j, boolean secret, AbstractStyle style, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, "", style, component, null);
    }
    public EditBoxString(int i, int j, AbstractStyle style, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, "", style, component, null);
    }

    // NO FONT
    public EditBoxString(int i, int j, boolean secret, String value, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, value, GuiUtils.getSelected(), component, responder);
    }


    public EditBoxString(int i, int j, String value, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, value, GuiUtils.getSelected(), component, responder);
    }

    public EditBoxString(int i, int j, String value, AbstractStyle style, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, value, style, component, responder);
    }
    public EditBoxString(int i, int j, boolean secret, String value, AbstractStyle style, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, value, style, component, responder);

    }
    // FONT
    public EditBoxString(Font font, int i, int j, boolean secret, String value, Component component, Consumer<String> responder) {
        this(font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, value, GuiUtils.getSelected(), component, responder);
    }


    public EditBoxString(Font font, int i, int j, String value, Component component, Consumer<String> responder) {
        this(font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, value, GuiUtils.getSelected(), component, responder);
    }

    public EditBoxString(Font font, int i, int j, String value, AbstractStyle style, Component component, Consumer<String> responder) {
        this(font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, value, style, component, responder);
    }

    public EditBoxString(Font font, int i, int j, boolean secret, String value, AbstractStyle style, Component component, Consumer<String> responder) {
        this(font, i, j, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, value, style, component, responder);
    }

    ////


    public EditBoxString(int i, int j, int k, int l, boolean secret, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, secret, "", GuiUtils.getSelected(), component, null);
    }
    public EditBoxString(int i, int j, int k, int l, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, false, "", GuiUtils.getSelected(), component, null);
    }
    public EditBoxString(int i, int j, int k, int l, boolean secret, AbstractStyle style, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, secret, "", style, component, null);
    }
    public EditBoxString(int i, int j, int k, int l, AbstractStyle style, Component component) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, false, "", style, component, null);
    }

    // NO FONT
    public EditBoxString(int i, int j, int k, int l, boolean secret, String value, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, secret, value, GuiUtils.getSelected(), component, responder);
    }


    public EditBoxString(int i, int j, int k, int l, String value, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, false, value, GuiUtils.getSelected(), component, responder);
    }

    public EditBoxString(int i, int j, int k, int l, String value, AbstractStyle style, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, false, value, style, component, responder);
    }
    public EditBoxString(int i, int j, int k, int l, boolean secret, String value, AbstractStyle style, Component component, Consumer<String> responder) {
        this(AlinLib.MINECRAFT.font, i, j, k, l, secret, value, style, component, responder);

    }
    // FONT
    public EditBoxString(Font font, int i, int j, int k, int l, boolean secret, String value, Component component, Consumer<String> responder) {
        this(font, i, j, k, l, secret, value, GuiUtils.getSelected(), component, responder);
    }


    public EditBoxString(Font font, int i, int j, int k, int l, String value, Component component, Consumer<String> responder) {
        this(font, i, j, k, l, false, value, GuiUtils.getSelected(), component, responder);
    }

    public EditBoxString(Font font, int i, int j, int k, int l, String value, AbstractStyle style, Component component, Consumer<String> responder) {
        this(font, i, j, k, l, false, value, style, component, responder);
    }

    public EditBoxString(Font font, int i, int j, int k, int l, boolean secret, String value, AbstractStyle style, Component component, Consumer<String> responder) {
        super(font, i, j, k, l, component);
        setMaxLength(Integer.MAX_VALUE);
        setValue(value);
        setResponder(responder);
        this.secret = secret;
        this.style = style;
    }

    protected int getPositionContent(String content) {
        int pos = getX() + getWidth() - font.width(content) - ((getHeight() - 8) / 2);

        if (getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2 > pos)
            pos = getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2;

        return pos;
    }

    protected int getColor() {
        return (getError() ? Colors.GROUPIE : isFocused() ? Colors.CLOWNFISH : Colors.SEADRIVE);
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

    @Override
    public boolean keyPressed(int i, int j, int k) {
        if(i == GLFW.GLFW_KEY_DELETE && (this instanceof Resetable)) {
            ((Resetable) this).resetValue();
            assert AlinLib.MINECRAFT != null;
            new ToastBuilder()
                    .setTitle(getMessage())
                    .setMessage(Component.translatable("alinlib.component.value_reset.toast"))
                    .setIcon(RESET)
                    .show(AlinLib.MINECRAFT.getToasts());
            AlinLib.log(Component.translatable("alinlib.component.reset.toast"));
            return true;
        }
        return super.keyPressed(i, j, k);
    }

    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick){
        this.style.renderBackground$widget(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused());
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick){
        guiGraphics.drawString(font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, isError ? Colors.GROUPIE : -1);
        String volume1 = font.plainSubstrByWidth(secret ? Component.translatable("alinlib.editbox.secret").getString() : getValue(), getX() + getWidth() - (getPositionContent(secret ? Component.translatable("alinlib.editbox.secret").getString() : getValue())));
        guiGraphics.drawString(font, formatter.apply(volume1, displayPos), getPositionContent(volume1), getY() + (getHeight() - 8) / 2, isError ? Colors.GROUPIE : -1);
    }
    //#if MC >=12020
    @Override
    public boolean isBordered() {
        if (tweakBorder) {
            return false;
        }
        return super.isBordered();
    }
    //#endif
    protected Component description;
    public EditBoxString setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}