package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
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
        if(GuiUtils.isDoesNotFit(getMessage(), getWidthComponent(), getHeight())){
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            guiGraphics.drawString(AlinLib.MINECRAFT.font, volumeState, getX() + getWidth()-AlinLib.MINECRAFT.font.width(volumeState.getString())-((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
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

    @Override
    public void resetValue() {
        if(this.builder.hasConfigurable()){
            this.builder.config.setBoolean(this.builder.configType, this.builder.defaultValue);
        } else setValue(this.builder.defaultValue);
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