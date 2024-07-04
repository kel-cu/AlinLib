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
            guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            if(!builder.isCheckBox) guiGraphics.drawString(AlinLib.MINECRAFT.font, volumeState, getX() + getWidth()-AlinLib.MINECRAFT.font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
        if(builder.isCheckBox) {
            int boxHeight = getHeight() - 10;
            int boxX = getWidthComponent()+5-height;
            int boxY = 5;
            int color = value ? Colors.getCheckBoxColor() : 0xFFFFFFFF;
            guiGraphics.fill(getXComponent()+boxX, getY()+boxY, getXComponent()+boxX + boxHeight, getY()+boxY+1, color);
            guiGraphics.fill(getXComponent()+boxX, getY()+boxHeight+boxY-1, getXComponent()+boxX + boxHeight, getY()+boxHeight+boxY, color);

            guiGraphics.fill(getXComponent()+boxX, getY()+boxY+1, getXComponent()+boxX+1, getY()+boxY+boxHeight, color);
            guiGraphics.fill(getXComponent()+boxX+boxHeight-1, getY()+boxY+1, getXComponent()+boxX+boxHeight, getY()+boxY+boxHeight, color);
            if(value){
                guiGraphics.fill(getXComponent()+boxX+2, getY()+boxY+2, getXComponent()+boxX+boxHeight-2, getY()+boxY+boxHeight-2, color);
            }
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

    protected void renderScrollingString(GuiGraphics guiGraphics, Font font, Component message, int x, int color) {
        int k = this.getXComponent() + x;
        int l = this.getX() + this.getWidth() - x;
        if(builder.isCheckBox) l-=height;
        renderScrollingString(guiGraphics, font, message, k, getY(), l, getY()+height, color);
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