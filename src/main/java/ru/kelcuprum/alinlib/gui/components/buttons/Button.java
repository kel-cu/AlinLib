package ru.kelcuprum.alinlib.gui.components.buttons;

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
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.RESET;

public class Button extends AbstractButton implements Description {
    protected final AbstractBuilder builder;
    public Button(AbstractBuilder builder) {
        super(builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight(), builder.getTitle());
        this.builder =  builder;
        this.active = builder.getActive();
        this.visible = builder.getVisible();
    }

    @Override
    public boolean isActive() {
        return builder.getActive();
    }

    // Изменение элементов в кнопке
    // С возвращением класса
    public Button setStyle(AbstractStyle style) {
        this.builder.setStyle(style);
        return this;
    }
    public Button setActive(boolean active){
        this.active = active;
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
            if(builder.getStyle() != null) this.builder.getStyle().renderBackground$widget(guiGraphics, getX(), getY(), getHeight(), getHeight(), this.active, this.isHoveredOrFocused(true, mouseX, mouseY));
            guiGraphics.blit(RESET, getX()+2, getY()+2, 0f, 0f, getHeight()-4, getHeight()-4, getHeight()-4, getHeight()-4);
            if(builder.getStyle() != null) this.builder.getStyle().renderBackground$widget(guiGraphics, getXComponent(), getY(), getWidthComponent(), getHeight(), this.active, this.isHoveredOrFocused(false, mouseX, mouseY));
        }
        else if(builder.getStyle() != null) this.builder.getStyle().renderBackground$widget(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused());
    }
    public void renderSprite(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        guiGraphics.blit(((ButtonBuilder) builder).sprite, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), ((ButtonBuilder) builder).textureWidth, ((ButtonBuilder) builder).textureHeight);
        if(!builder.getTitle().getString().isEmpty() && isHovered()){
            guiGraphics.renderTooltip(AlinLib.MINECRAFT.font, builder.getTitle(), mouseX, mouseY);
        }
    }

    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        if(((ButtonBuilder) builder).sprite == null) {
            if (((ButtonBuilder) builder).rightTitle == null) {
                if (GuiUtils.isDoesNotFit(getMessage(), getWidthComponent(), getHeight()))
                    this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
                else if (((ButtonBuilder) builder).getCentered())
                    GuiUtils.drawCenteredString(guiGraphics, AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + getWidthComponent() / 2, getY() + (getHeight() - 8) / 2, 0xffffff, true);
                else
                    guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff, true);
            } else {
                if (GuiUtils.isDoesNotFit(Component.empty().append(builder.getTitle()).append(" ").append(((ButtonBuilder) builder).getRightTitle()), getWidthComponent(), getHeight())) {
                    this.setMessage(Component.empty().append(builder.getTitle()).append(" ").append(((ButtonBuilder) builder).rightTitle));
                    this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
                } else {
                    guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
                    guiGraphics.drawString(AlinLib.MINECRAFT.font, ((ButtonBuilder) builder).getRightTitle(), getX() + getWidth() - AlinLib.MINECRAFT.font.width(((ButtonBuilder) builder).getRightTitle().getString()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
                }
            }
            if (((ButtonBuilder) builder).icon != null)
                guiGraphics.blit(((ButtonBuilder) builder).icon, getX(), getY(), 0.0f, 0.0f, getHeight(), getHeight(), getHeight(), getHeight());
        } else renderSprite(guiGraphics, mouseX, mouseY, partialTicks);
    }
    // Мелочи V2
    protected int getWidthComponent(){
        int widthC = (isResetable() ? getWidth()-getHeight()-2 : getWidth());
        if(builder instanceof ButtonBuilder) {
            if(((ButtonBuilder) builder).icon != null) widthC-= getHeight() + (getHeight() - 8) / 2;
        }
        return  widthC;
    }
    protected int getXComponent(){
        int xC = isResetable() ? getX()+getHeight()+2 : getX();
        if(builder instanceof  ButtonBuilder){
            if(((ButtonBuilder) builder).icon != null) xC+=getHeight() + (getHeight() - 8) / 2;
        }
        return xC;
    }
    protected boolean isResetable(){
        return this instanceof Resetable && ((Resetable) this).resettable() && AlinLib.bariumConfig.getBoolean("BUTTON.ENABLE_RESET_BUTTON", true);
    }

    @Override
    protected void renderScrollingString(GuiGraphics guiGraphics, Font font, int i, int j) {
        int k = this.getX() + i;
        int l = this.getX() + this.getWidth() - i;
        if(builder instanceof ButtonBuilder){
            if(((ButtonBuilder) builder).icon != null) k+=height;
        }
        if(isResetable()) k+=(height+2);
        renderScrollingString(guiGraphics, font, this.getMessage(), k, this.getY(), l, this.getY() + this.getHeight(), j);
    }

    @Override
    public void onClick(double d, double e) {
        if(isResetable() && (getX() < d && d < getX()+getHeight())) ((Resetable) this).resetValue();
        else super.onClick(d, e);
    }

    public boolean isHoveredOrFocused(boolean isReset, int mouseX, int mouseY) {
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
                    .setTitle(builder.getTitle())
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
        if(((ButtonBuilder) builder).getOnPress() != null) {
            ((ButtonBuilder) builder).getOnPress().onPress(this);
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
