package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
//#if MC >= 12109
import net.minecraft.client.input.InputWithModifiers;
//#endif
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;

public class ButtonBoolean extends Button implements Resetable {
    public final ButtonBooleanBuilder builder;
    protected Component volumeState;
    public boolean value;

    public ButtonBoolean(AbstractBuilder builder) {
        super(builder);
        this.builder = (ButtonBooleanBuilder) builder;
        if(((ButtonBooleanBuilder) builder).hasConfigurable()){
            this.value = ((ButtonBooleanBuilder) builder).config.getBoolean(((ButtonBooleanBuilder) builder).configType, ((ButtonBooleanBuilder) builder).defaultValue);
        } else this.value = ((ButtonBooleanBuilder) builder).defaultValue;
        volumeState = this.value ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
        this.setMessage(Component.literal(builder.getTitle().getString() +": ").append(volumeState));
    }
    @Override
    public void onPress(
            //#if MC >= 12109
            InputWithModifiers inputWithModifiers
            //#endif
    ) {
        if(!active) return;
        this.setValue(!this.value);
        if(builder.hasConfigurable()) this.builder.config.setBoolean(this.builder.configType, value);
        if(this.builder.getOnPress() != null) this.builder.getOnPress().onPress(this.value);
    }

    @Override
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if(GuiUtils.isDoesNotFit(builder.isCheckBox ? builder.getTitle() : getMessage(), getWidthComponent() - (builder.isCheckBox ? (10+builder.getStyle().getCheckBoxSizes(getWidth(), getHeight())[0]) : 0), getHeight())){
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, builder.isCheckBox ? builder.getTitle() : getMessage(), (getHeight() - 8) / 2, builder.getStyle().getTextColor(active), builder.getStyle().textShadow());
        } else {
            guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, builder.getStyle().getTextColor(active), builder.getStyle().textShadow());
            if(!builder.isCheckBox) guiGraphics.drawString(AlinLib.MINECRAFT.font, volumeState, getX() + getWidth()-AlinLib.MINECRAFT.font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, builder.getStyle().getTextColor(active), builder.getStyle().textShadow());
        }
        if(builder.isCheckBox) {
            int boxX = getXComponent() + getWidthComponent()-5-builder.getStyle().getCheckBoxSizes(getWidth(), getHeight())[0];
            int boxY = getY() + (height-builder.getStyle().getCheckBoxSizes(getWidth(), getHeight())[1]) / 2;
            builder.getStyle().renderCheckBox(guiGraphics, boxX, boxY, getWidth(), getHeight(), value);
        }
    }


    // Получить
    // Заменить
    public ButtonBoolean setValue(boolean value){
        this.value = value;
        this.volumeState = this.value ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
        this.setMessage(Component.literal(builder.getTitle().getString() +": ").append(volumeState));
        return this;
    }

    protected void renderScrollingString(GuiGraphics guiGraphics, Font font, Component message, int x, int color, boolean shadow) {
        int k = this.getXComponent() + x;
        int l = this.getX() + this.getWidth() - x;
        if(builder.isCheckBox) l-=(10+builder.getStyle().getCheckBoxSizes(getWidth(), getHeight())[0]);
        TextBox.renderScrollingString(guiGraphics, font, message, k, getY(), l, getY()+height, color, shadow);
    }

    @Override
    public void resetValue() {
        if(this.builder.hasConfigurable()){
            this.builder.config.setBoolean(this.builder.configType, this.builder.defaultValue);
        }
        setValue(this.builder.defaultValue);
    }

    @Override
    protected boolean isResetable() {
        return this.builder.hasConfigurable();
    }

    public interface OnPress {
        void onPress(Boolean volume);
    }

    public ButtonBoolean setDescription(Component description){
        this.builder.setDescription(description);
        return this;
    }
    public Component getDescription(){
        return this.builder.getDescription();
    }
}