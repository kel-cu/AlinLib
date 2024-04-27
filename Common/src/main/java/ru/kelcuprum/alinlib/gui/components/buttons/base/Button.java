package ru.kelcuprum.alinlib.gui.components.buttons.base;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Description;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class Button extends AbstractButton implements Description {
    protected InterfaceUtils.DesignType type;
    int color;
    private final boolean isCentred;
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
        if(type != null) this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.color);
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        if(InterfaceUtils.isDoesNotFit(getMessage(), getWidth(), getHeight())) this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        else if(isCentred) InterfaceUtils.drawCenteredString(guiGraphics, AlinLib.MINECRAFT.font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff, true);
        else guiGraphics.drawString(AlinLib.MINECRAFT.font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff, true);
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
