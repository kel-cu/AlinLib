package ru.kelcuprum.alinlib.gui.components.sliders.base;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.Icons.RESET;

public class SliderPercent extends AbstractSliderButton implements Description {
    public final InterfaceUtils.DesignType type;
    public final String buttonMessage;
    public OnPress onPress;

    public SliderPercent(int x, int y, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), 0, label, null);
    }
    public SliderPercent(int x, int y, InterfaceUtils.DesignType type, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, 0, label, null);
    }
    public SliderPercent(int x, int y, InterfaceUtils.DesignType type, double position, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, label, null);
    }
    //
    public SliderPercent(int x, int y, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), 0, label, onPress);
    }
    public SliderPercent(int x, int y, InterfaceUtils.DesignType type, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, 0, label, onPress);
    }
    public SliderPercent(int x, int y, InterfaceUtils.DesignType type, double position, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, label, onPress);
    }
    //
    public SliderPercent(int x, int y, int width, int height, Component label) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), 0, label, null);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label) {
        this(x, y, width, height, type, 0, label, null);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, double position, Component label) {
        this(x, y, width, height, type, position, label, null);
    }
    //
    public SliderPercent(int x, int y, int width, int height, Component label, OnPress onPress) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), 0, label, onPress);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, OnPress onPress) {
        this(x, y, width, height, type, 0, label, onPress);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, double position, Component label, OnPress onPress) {
        super(x, y, width, height, label, position);
        this.type = type;
        this.onPress = onPress;
        this.buttonMessage = label.getString();
        this.setMessage(Component.literal(buttonMessage).append(": ").append(getComponentValue()));
    }
    // Получить
    public Component getComponentValue(){
        return Component.literal(Localization.getRounding(getValue() * 100,   true)+"%");
    }
    public double getValue(){
        return this.value;
    }

    // Заменить
    public SliderPercent setActive(boolean active){
        this.active = active;
        return this;
    }
    public SliderPercent setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }

    // Рендер
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        if(this.visible){
            renderBackground(guiGraphics, mouseX, mouseY, tick);
            renderText(guiGraphics, mouseX, mouseY, tick);
        }
    }
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        if(isResetable()){
            if(type != null) this.type.renderBackground(guiGraphics, getX(), getY(), getHeight(), getHeight(), this.active, this.isHoveredOrFocused(true, guiGraphics, mouseX, mouseY));
            guiGraphics.blit(RESET, getX()+2, getY()+2, 0f, 0f, getHeight()-4, getHeight()-4, getHeight()-4, getHeight()-4);
            if(type != null) this.type.renderSliderBackground(guiGraphics, getXComponent(), getY(), getWidthComponent(), getHeight(), this.active, this.isHoveredOrFocused(false, guiGraphics, mouseX, mouseY), this.value, this);
        } else this.type.renderSliderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.value, this);
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        if(InterfaceUtils.isDoesNotFit(Component.literal(buttonMessage).append(": ").append(getComponentValue()), getWidth(), getHeight())){
            if(isHoveredOrFocused()){
                this.setMessage(getComponentValue());
            } else {
                this.setMessage(Component.literal(buttonMessage).append(": ").append(getComponentValue()));
            }
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        } else {
            if(isHovered()){
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getComponentValue(), getXComponent() + (getWidthComponent()/2) - (AlinLib.MINECRAFT.font.width(getComponentValue().getString())/2), getY() + (getHeight() - 8) / 2, 0xffffff);
            } else {
                guiGraphics.drawString(AlinLib.MINECRAFT.font, buttonMessage, getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
                // VOLUME
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getComponentValue(), getX() + getWidth() - AlinLib.MINECRAFT.font.width(getComponentValue().getString()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
            }
        }
    }



    // BASE
    @Override
    protected void updateMessage() {

    }
    // Мелочи V2
    protected int getWidthComponent(){
        return isResetable() ? getWidth()-getHeight()-2 : getWidth();
    }
    protected int getXComponent(){
        return isResetable() ? getX()+getHeight()+2 : getX();
    }
    protected boolean isResetable(){
        return this instanceof Resetable && AlinLib.bariumConfig.getBoolean("BUTTON.ENABLE_RESET_BUTTON", true);
    }

    @Override
    protected void renderScrollingString(GuiGraphics guiGraphics, Font font, int i, int j) {
        int k = this.getX() + i;
        int l = this.getX() + this.getWidth() - i;
        if(isResetable()) k+=22;
        renderScrollingString(guiGraphics, font, this.getMessage(), k, this.getY(), l, this.getY() + this.getHeight(), j);
    }

    @Override
    public void onClick(double d, double e) {
        if(isResetable()){
            if(getX() < d && d < getX()+getHeight()){
                ((Resetable) this).resetValue();
            } else {
                this.setValueFromMouse(d);
            }
        } else super.onClick(d, e);
    }

    public boolean isHoveredOrFocused(boolean isReset, GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int x = isReset ? getX() : getX()+22;
        int width = isReset ? 20 : getWidth()-22;
        boolean isHovered = guiGraphics.containsPointInScissor(mouseX, mouseY) && mouseX >= x && mouseY >= this.getY() && mouseX < x + width && mouseY < this.getY() + this.height;
        return isHovered || (!isReset && isFocused());
    }
    // Мелочи v2 Slider
    private void setValueFromMouse(double d) {
        this.setValue((d - (double)(this.getXComponent() + 4)) / (double)(getWidthComponent() - 8));
    }


    @Override
    protected void onDrag(double d, double e, double f, double g) {
        if(isResetable()) {
            if(d > getXComponent()) this.setValueFromMouse(d);
        }
        else super.onDrag(d, e, f, g);
    }

    @Override
    public boolean keyPressed(int i, int j, int k) {
        if(i == GLFW.GLFW_KEY_DELETE && (this instanceof Resetable)) {
            ((Resetable) this).resetValue();
            assert AlinLib.MINECRAFT != null;
            new ToastBuilder()
                    .setTitle(Component.literal(buttonMessage))
                    .setMessage(Component.translatable("alinlib.component.value_reset.toast"))
                    .setIcon(RESET)
                    .show(AlinLib.MINECRAFT.getToasts());
            AlinLib.log(Component.translatable("alinlib.component.reset.toast"));
            return true;
        }
        return super.keyPressed(i, j, k);
    }
    //

    @Override
    protected void applyValue(){
        if(this.onPress != null) this.onPress.onPress(this.value);
    }
    // Мелочи

    public interface OnPress {
        void onPress(double value);
    }

    protected Component description;
    public SliderPercent setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
