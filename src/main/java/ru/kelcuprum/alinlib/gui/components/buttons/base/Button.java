package ru.kelcuprum.alinlib.gui.components.buttons.base;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.RESET;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class Button extends AbstractButton implements Description {
    protected AbstractStyle style;
    private final boolean isCentred;
    public final String buttonMessage;
    private OnPress onPress;


    public Button(int x, int y, Component label){
        this( x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, null);
    }
    public Button(int x, int y, Component label, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, GuiUtils.getSelected(), label, onPress);
    }
    public Button(int x, int y, AbstractStyle style, Component label, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, style, true, label, onPress);
    }
    public Button(int x, int y, AbstractStyle style, boolean isCentred, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, style, isCentred, label, onPress);
    }
    //////////
    public Button(int x, int y, int width, int height, Component label){
        this(x, y, width, height, label, null);
    }
    public Button(int x, int y, int width, int height, Component label, OnPress onPress){
        this(x, y, width, height, GuiUtils.getSelected(), label, onPress);
    }
    public Button(int x, int y, int width, int height, AbstractStyle style, Component label, OnPress onPress){
        this(x, y, width, height, style, true, label, onPress);
    }
    public Button(int x, int y, int width, int height, AbstractStyle style, boolean isCentred, Component label, OnPress onPress) {
        super(x, y, width, height, label);
        this.buttonMessage = label.getString();
        this.style = style;
        this.onPress = onPress;
        this.isCentred = isCentred;
    }

    // Изменение элементов в кнопке
    // С возвращением класса
    public Button setStyle(AbstractStyle style) {
        this.style = style;
        return this;
    }
    public Button setActive(boolean active){
        this.active = active;
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
            if(style != null) this.style.renderBackground$widget(guiGraphics, getX(), getY(), getHeight(), getHeight(), this.active, this.isHoveredOrFocused(true, guiGraphics, mouseX, mouseY));
            guiGraphics.blit(RESET, getX()+2, getY()+2, 0f, 0f, getHeight()-4, getHeight()-4, getHeight()-4, getHeight()-4);
            if(style != null) this.style.renderBackground$widget(guiGraphics, getXComponent(), getY(), getWidthComponent(), getHeight(), this.active, this.isHoveredOrFocused(false, guiGraphics, mouseX, mouseY));
        }
        else if(style != null) this.style.renderBackground$widget(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused());
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        if(GuiUtils.isDoesNotFit(getMessage(), getWidthComponent(), getHeight())) this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        else if(isCentred) GuiUtils.drawCenteredString(guiGraphics, AlinLib.MINECRAFT.font, getMessage(), getXComponent() + getWidthComponent() / 2, getY() + (getHeight() - 8) / 2, 0xffffff, true);
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
        boolean isHovered = mouseX >= x && mouseY >= this.getY() && mouseX < x + width && mouseY < this.getY() + this.height;
        return isHovered || (!isReset && isFocused());
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
