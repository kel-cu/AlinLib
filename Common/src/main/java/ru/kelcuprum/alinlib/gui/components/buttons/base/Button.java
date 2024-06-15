package ru.kelcuprum.alinlib.gui.components.buttons.base;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.Icons.RESET;

public class Button extends AbstractButton implements Description {
    protected InterfaceUtils.DesignType type;
    int color;
    private final boolean isCentred;
    public final String buttonMessage;
    private OnPress onPress;


    public Button(int x, int y, Component label){
        this( x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, null);
    }
    public Button(int x, int y, Component label, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, InterfaceUtils.DesignType.ALINA, label, onPress);
    }
    public Button(int x, int y, InterfaceUtils.DesignType type, Component label, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, InterfaceUtils.Colors.SEADRIVE, label, onPress);
    }
    public Button(int x, int y, InterfaceUtils.DesignType type, int color, Component label, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, color, true, label, onPress);
    }
    public Button(int x, int y, InterfaceUtils.DesignType type, int color, boolean isCentred, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, color, isCentred, label, onPress);
    }
    //////////
    public Button(int x, int y, int width, int height, Component label){
        this(x, y, width, height, label, null);
    }
    public Button(int x, int y, int width, int height, Component label, OnPress onPress){
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, OnPress onPress){
        this(x, y, width, height, type, InterfaceUtils.Colors.SEADRIVE, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, Component label, OnPress onPress){
        this(x, y, width, height, type, color, true, label, onPress);
    }
    public Button(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, boolean isCentred, Component label, OnPress onPress) {
        super(x, y, width, height, label);
        this.buttonMessage = label.getString();
        this.type = type;
        this.color = color;
        this.onPress = onPress;
        this.isCentred = isCentred;
    }

    // Изменение элементов в кнопке
    // С возвращением класса
    public Button setType(InterfaceUtils.DesignType type) {
        this.type = type;
        return this;
    }
    public Button setActive(boolean active){
        this.active = active;
        return this;
    }
    public Button setColor(int color) {
        this.color = color;
        return this;
    }
    public Button setOnPress(OnPress onPress) {
        this.onPress = onPress;
        return this;
    }


    // Рендер
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
            renderText(guiGraphics, mouseX, mouseY, partialTicks);
        }
    }
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        if(isResetable()){
            if(type != null) this.type.renderBackground(guiGraphics, getX(), getY(), getHeight(), getHeight(), this.active, this.isHoveredOrFocused(true, guiGraphics, mouseX, mouseY), this.color);
            guiGraphics.blit(RESET, getX()+2, getY()+2, 0f, 0f, getHeight()-4, getHeight()-4, getHeight()-4, getHeight()-4);
            if(type != null) this.type.renderBackground(guiGraphics, getXComponent(), getY(), getWidthComponent(), getHeight(), this.active, this.isHoveredOrFocused(false, guiGraphics, mouseX, mouseY), this.color);
        }
        else if(type != null) this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.color);
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        if(InterfaceUtils.isDoesNotFit(getMessage(), getWidthComponent(), getHeight())) this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        else if(isCentred) InterfaceUtils.drawCenteredString(guiGraphics, AlinLib.MINECRAFT.font, getMessage(), getXComponent() + getWidthComponent() / 2, getY() + (getHeight() - 8) / 2, 0xffffff, true);
        else guiGraphics.drawString(AlinLib.MINECRAFT.font, getMessage(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff, true);
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
        if(isResetable() && (getX() < d && d < getX()+getHeight())) ((Resetable) this).resetValue();
        else super.onClick(d, e);
    }

    public boolean isHoveredOrFocused(boolean isReset, GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int x = isReset ? getX() : getX()+22;
        int width = isReset ? 20 : getWidth()-22;
        boolean isHovered = guiGraphics.containsPointInScissor(mouseX, mouseY) && mouseX >= x && mouseY >= this.getY() && mouseX < x + width && mouseY < this.getY() + this.height;
        return isHovered || isFocused();
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

    // Мелочи
    @Override
    public void onPress() {
        if(this.onPress != null) {
            this.onPress.onPress(this);
            setFocused(false);
        }
    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    public interface OnPress {
        void onPress(Button button);
    }

    protected Component description;
    public Button setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
