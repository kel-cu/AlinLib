package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.RESET;

public class EditBox extends net.minecraft.client.gui.components.EditBox implements Description, Resetable {
    private boolean tweakBorder;
    protected boolean isError;

    public final EditBoxBuilder builder;
    public int volume;

    public EditBox(AbstractBuilder builder) {
        super(((EditBoxBuilder) builder).getFont(), builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight(), builder.getTitle());
        this.builder = (EditBoxBuilder) builder;
        this.active = builder.getActive();
        this.visible = builder.getVisible();
        setMaxLength(Integer.MAX_VALUE);
        if (this.builder.isColor) {
            setMaxLength(16);
            setFormatter((string, integer) -> FormattedCharSequence.forward(string.toUpperCase(), Style.EMPTY.withColor(getColor())));
            this.volume = this.builder.hasConfigurable() ? this.builder.config.getNumber(this.builder.configType, this.builder.color).intValue() : this.builder.color;
            setValue(Integer.toHexString(volume));
            setResponder(string -> {
                try {
                    this.volume = (int) Long.parseLong(string.toUpperCase(), 16);
                    this.builder.config.setNumber(this.builder.configType, volume);
                    setError(false);
                } catch (Exception ex) {
                    setError(true);
                }
            });
        } else if (this.builder.hasConfigurable()){
            setValue(this.builder.config.getString(this.builder.configType, this.builder.value));
            setResponder(string -> this.builder.config.setString(this.builder.configType, string));
        } else if (this.builder.hasLocalization()) {
            setValue(this.builder.localization.getLocalization(this.builder.configType, false, false, false));
            setResponder(string -> this.builder.localization.setLocalization(this.builder.configType, string));
        } else {
            setValue(((EditBoxBuilder) builder).value);
            setResponder(((EditBoxBuilder) builder).responder);
        }
    }

    @Override
    public boolean isActive() {
        return builder.getActive();
    }

    protected int getPositionContent(String content) {
        int pos = getX() + getWidth() - font.width(builder.isColor ? content.toUpperCase() : content) - ((getHeight() - 8) / 2);

        if (getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2 > pos)
            pos = getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2;

        return pos;
    }

    protected int getColor() {
        return (getError() ? Colors.GROUPIE : builder.isColor ? volume : isFocused() ? Colors.CLOWNFISH : Colors.SEADRIVE);
    }

    public EditBox setError(boolean error) {
        this.isError = error;
        return this;
    }

    public boolean getError() {
        return this.isError;
    }

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
        if (i == GLFW.GLFW_KEY_DELETE && this.resettable()) {
            ((Resetable) this).resetValue();
            assert AlinLib.MINECRAFT != null;
            new ToastBuilder()
                    .setTitle(builder.getTitle())
                    .setMessage(Component.translatable("alinlib.component.value_reset.toast"))
                    .setIcon(RESET)
                    //#if MC >= 12102
                    .show(AlinLib.MINECRAFT.getToastManager());
            //#elseif MC < 12102
            //$$ .show(AlinLib.MINECRAFT.getToasts());
            //#endif
            AlinLib.log(Component.translatable("alinlib.component.reset.toast"));
            return true;
        }
        return super.keyPressed(i, j, k);
    }

    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.builder.getStyle().renderBackground$widget(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused());
    }

    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.drawString(font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, isError ? Colors.GROUPIE : -1);
        String volume1 = font.plainSubstrByWidth(this.builder.secret ? Component.translatable("alinlib.editbox.secret").getString() : getValue(), getX() + getWidth() - (getPositionContent(this.builder.secret ? Component.translatable("alinlib.editbox.secret").getString() : getValue())));
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

    public EditBox setDescription(Component description) {
        this.builder.setDescription(description);
        return this;
    }
    public Component getDescription(){
        return this.builder.getDescription();
    }

    @Override
    public void resetValue() {
        if(builder.isColor) {
            if(builder.hasConfigurable()) builder.config.setNumber(builder.configType, builder.color);
            setValue(Integer.toHexString(builder.color));
        } else if(builder.hasConfigurable()){
            builder.config.setString(builder.configType, builder.value);
            setValue(builder.value);
        } else if(builder.hasLocalization()){
            builder.localization.resetLocalization(builder.configType);
            setValue(builder.localization.getLocalization(builder.configType, false, false, false));
        } else setValue(builder.value);
    }

    @Override
    public boolean resettable() {
        return builder.hasConfigurable() || builder.hasLocalization();
    }
}