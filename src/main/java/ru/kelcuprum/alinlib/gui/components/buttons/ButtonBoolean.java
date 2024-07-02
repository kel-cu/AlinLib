package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;

public class ButtonBoolean extends Button implements Resetable {
    protected final ButtonBooleanBuilder builder;
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
    public void onPress() {
        if(!active) return;
        this.setValue(!this.value);
        if(builder.hasConfigurable()) this.builder.config.setBoolean(this.builder.configType, value);
        if(this.builder.getOnPress() != null) this.builder.getOnPress().onPress(this.value);
    }

    @Override
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if(GuiUtils.isDoesNotFit(builder.isCheckBox ? builder.getTitle() : getMessage(), getWidthComponent(), getHeight())){
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, builder.isCheckBox ? builder.getTitle() : getMessage(), (getHeight() - 8) / 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getXTextComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            if(!builder.isCheckBox) guiGraphics.drawString(AlinLib.MINECRAFT.font, volumeState, getX() + getWidth()-AlinLib.MINECRAFT.font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
        if(builder.isCheckBox) {
            int boxHeight = getHeight() - 10;
            int boxX = 5;
            int color = value ? getCheckBoxColor() : 0xFFFFFFFF;
            guiGraphics.fill(getXComponent()+boxX, getY()+boxX, getXComponent()+boxX + boxHeight, getY()+boxX+1, color);
            guiGraphics.fill(getXComponent()+boxX, getY()+boxHeight+boxX-1, getXComponent()+boxX + boxHeight, getY()+boxHeight+boxX, color);

            guiGraphics.fill(getXComponent()+boxX, getY()+boxX+1, getXComponent()+boxX+1, getY()+boxX+boxHeight, color);
            guiGraphics.fill(getXComponent()+boxX+boxHeight-1, getY()+boxX+1, getXComponent()+boxX+boxHeight, getY()+boxX+boxHeight, color);
            if(value){
                guiGraphics.fill(getXComponent()+boxX+2, getY()+boxX+2, getXComponent()+boxX+boxHeight-2, getY()+boxX+boxHeight-2, color);
            }
        }
    }
    public int getCheckBoxColor(){
        return switch (AlinLib.bariumConfig.getNumber("CHECKBOX.COLOR", 0).intValue()){
            case 0 -> Colors.GROUPIE;
            case 1 -> Colors.SEADRIVE;
            case 2 -> Colors.TETRA;
            case 3 -> Colors.CONVICT;
            case 4 -> Colors.SEABIRD;
            default -> AlinLib.bariumConfig.getNumber("CHECKBOX.COLOR.CUSTOM", Colors.SPECKLE[0]).intValue();
        };
    }
    // Получить
    // Заменить
    public ButtonBoolean setValue(boolean value){
        this.value = value;
        this.volumeState = this.value ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
        this.setMessage(Component.literal(builder.getTitle().getString() +": ").append(volumeState));
        return this;
    }

    protected void renderScrollingString(GuiGraphics guiGraphics, Font font, Component message, int x, int color) {
        int k = this.getXTextComponent() + x;
        int l = this.getX() + this.getWidth() - x;
        renderScrollingString(guiGraphics, font, message, k, getY(), l, getY()+height, color);
    }

    protected int getXTextComponent() {
        return builder.isCheckBox ? getXComponent()+height : getXComponent();
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


    protected Component description;
    public ButtonBoolean setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}