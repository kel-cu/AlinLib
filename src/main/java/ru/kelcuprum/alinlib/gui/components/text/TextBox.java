package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.CommonInputs;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;

import java.util.List;

import static ru.kelcuprum.alinlib.gui.Colors.CPM_BLUE;
import static ru.kelcuprum.alinlib.gui.Colors.GROUPIE;
import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.ALIGN.CENTER;
import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.ALIGN.LEFT;
import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.TYPE.*;

public class TextBox extends AbstractWidget implements Description {
    public final TextBuilder builder;
    public TextBox(TextBuilder builder){
        super(builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight(), builder.getTitle());
        this.builder = builder;
        this.setActive(builder.onPress != null);
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
        if(builder.getOnPress() != null) builder.getOnPress().onPress(this);
    }

    @Override
    public void setMessage(Component component) {
        this.builder.setTitle(component);
        super.setMessage(component);
    }

    @Override
    public int getHeight(){
        if(builder.type == TEXT) {
            this.height = builder.getHeight();
            return super.getHeight();
        }
        else {
            this.height = 8+(AlinLib.MINECRAFT.font.lineHeight+3)*(AlinLib.MINECRAFT.font.split(getMessage(), width-12).size());
            return this.height;
        }
    }

    @Override
    public void setHeight(int i) {
        builder.setHeight(i);
        super.setHeight(i);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        renderBackground(guiGraphics);
        if(builder.type == TEXT){
            if(isDoesNotFit()) this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
            else if(builder.align == CENTER) guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            else guiGraphics.drawString(AlinLib.MINECRAFT.font, getMessage(), (builder.align == LEFT ? (getX() + (getHeight() - 8) / 2) : (getX() + getWidth() - (getHeight() - 8) / 2) - AlinLib.MINECRAFT.font.width(getMessage())), getY() + (getHeight() - 8) / 2, 0xffffff);
        } else renderMessageText(guiGraphics);
    }
    public void renderBackground(GuiGraphics guiGraphics){
       if(builder.type != BLOCKQUOTE && builder.onPress != null) builder.getStyle().renderBackground$widget(guiGraphics, getX(), getY(), getWidth(), getHeight(), true, isHoveredOrFocused());
       else if(builder.type == BLOCKQUOTE){
           guiGraphics.fill(this.getX(), this.getY(), this.getX()+1, this.getY()+this.getHeight(), getBlockquoteColor()[0]);
           guiGraphics.fill(this.getX()+1, this.getY(), this.getX()+this.getWidth(), this.getY()+this.getHeight(), getBlockquoteColor()[1]);
       }
    }
    public int[] getBlockquoteColor(){
        return builder.color == null ? new int[]{AlinLib.bariumConfig.getNumber("BLOCKQUOTE.COLOR", CPM_BLUE).intValue(), AlinLib.bariumConfig.getNumber("BLOCKQUOTE.COLOR.BACKGROUND", CPM_BLUE-0xE1000000).intValue()} : builder.color;
    }
    public void renderMessageText(GuiGraphics guiGraphics){
        List<FormattedCharSequence> list = AlinLib.MINECRAFT.font.split(getMessage(), width-(this.builder.type == BLOCKQUOTE && this.builder.align != CENTER ? 13 : 12));
        int l = 0;
        for(FormattedCharSequence text : list){
            if(builder.align == CENTER) guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, text, getX()+(getWidth()/2), getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight+3) * l), -1);
            else guiGraphics.drawString(AlinLib.MINECRAFT.font, text, (builder.align == LEFT ? getX()+(this.builder.type == BLOCKQUOTE ? 7 : 6) : getX()+getWidth()-6-AlinLib.MINECRAFT.font.width(text)), getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight+3) * l), -1);
            l++;
        }
    }
    private boolean isDoesNotFit(){
        int size = AlinLib.MINECRAFT.font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return size > getWidth();
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

    public interface OnPress {
        void onPress(TextBox button);
    }

    protected Component description;
    public TextBox setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
