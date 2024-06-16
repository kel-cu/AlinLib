package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.CommonInputs;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Description;

import java.util.List;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class MessageBox extends AbstractWidget implements Description {
    private final boolean isCentred;
    private final MessageBox.OnPress onPress;
    private final InterfaceUtils.DesignType type;

    public MessageBox(Component label){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, false, AlinLib.getDefaultDesignType(), null);
    }
    public MessageBox(Component label, OnPress onPress){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, false, AlinLib.getDefaultDesignType(), onPress);
    }
    public MessageBox(Component label, InterfaceUtils.DesignType type, OnPress onPress){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, false, type, onPress);
    }
    ///
    public MessageBox(Component label, boolean isCenter){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, AlinLib.getDefaultDesignType(), null);
    }
    public MessageBox(Component label, boolean isCenter, OnPress onPress){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, AlinLib.getDefaultDesignType(), onPress);
    }
    public MessageBox(Component label, boolean isCenter, InterfaceUtils.DesignType type, OnPress onPress){
        this(0, 0, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, type, onPress);
    }
    ///
    public MessageBox(int x, int y, Component label, boolean isCenter){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, AlinLib.getDefaultDesignType(), null);
    }
    public MessageBox(int x, int y, Component label, boolean isCenter, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, AlinLib.getDefaultDesignType(), onPress);
    }
    public MessageBox(int x, int y, Component label, boolean isCenter, InterfaceUtils.DesignType type, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, isCenter, type, onPress);
    }
    ///
    public MessageBox(int x, int y, int width, int height, Component label, boolean isCenter){
        this(x, y, width, height, label, isCenter, AlinLib.getDefaultDesignType(), null);
    }
    public MessageBox(int x, int y, int width, int height, Component label, boolean isCenter, OnPress onPress) {
        this(x, y, width, height, label, isCenter, AlinLib.getDefaultDesignType(), onPress);
    }
    public MessageBox(int x, int y, int width, int height, Component label, InterfaceUtils.DesignType type, boolean isCenter){
        this(x, y, width, height, label, isCenter, type, null);
    }

    public MessageBox(int x, int y, int width, int height, Component label, boolean isCenter, InterfaceUtils.DesignType type, OnPress onPress){
        super(x, y, width, height, label);
        this.isCentred = isCenter;
        this.onPress = onPress;
        this.type = type;
        this.setActive(this.onPress != null);
    }

    public void setActive(boolean active){
        this.active = active;
    }


    @Override
    public void setX(int x) {
        super.setX(x);
    }
    @Override
    public void setY(int y) {
        super.setY(y);
    }
    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
    }
    public void onPress() {
        if(onPress != null) this.onPress.onPress(this);
    }

    @Override
    public void setMessage(Component component) {
        super.setMessage(component);
    }
        @Override
    public int getHeight(){
        this.height = 8+(AlinLib.MINECRAFT.font.lineHeight+3)*(AlinLib.MINECRAFT.font.split(getMessage(), width-12).size());
        return this.height;
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            if(onPress != null) renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
            renderText(guiGraphics, mouseX, mouseY, partialTicks);
        }
    }
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        if(type != null) this.type.renderBackground(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused());
    }
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks){
        List<FormattedCharSequence> list = AlinLib.MINECRAFT.font.split(getMessage(), width-12);
        int l = 0;
        for(FormattedCharSequence text : list){
            if(isCentred) guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, text, getX()+(getWidth()/2), getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight+3) * l), -1);
            else guiGraphics.drawString(AlinLib.MINECRAFT.font, text, getX()+6, getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight+3) * l), -1);
            l++;
        }
    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }
    @Override
    public void onClick(double d, double e) {
        this.onPress();
    }

    @Override
    public boolean keyPressed(int i, int j, int k) {
        if (this.active && this.visible) {
            if (CommonInputs.selected(i)) {
                this.playDownSound(AlinLib.MINECRAFT.getSoundManager());
                this.onPress();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    protected Component description;
    public MessageBox setDescription(Component description){
        this.description = description;
        return this;
    }
    @Override
    public Component getDescription(){
        return this.description;
    }

    public interface OnPress {
        void onPress(MessageBox button);
    }
}
