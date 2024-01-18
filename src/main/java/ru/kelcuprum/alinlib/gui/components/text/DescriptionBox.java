package ru.kelcuprum.alinlib.gui.components.text;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.CommonInputs;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.gui.components.Description;

import java.util.List;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class DescriptionBox extends AbstractWidget{
    protected Component description;

    public DescriptionBox(int x, int y, Component label){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, null);
    }
    public DescriptionBox(int x, int y, Component label, OnPress onPress){
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, label, onPress);
    }
    ///
    public DescriptionBox(int x, int y, int width, int height, Component label){
        this(x, y, width, height, label, null);
    }
    public DescriptionBox(int x, int y, int width, int height, Component label, OnPress onPress){
        super(x, y, width, height, label);
        this.setActive(false);
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

    }

    @Override
    public void setMessage(Component component) {
        super.setMessage(component);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        List<FormattedCharSequence> list = Minecraft.getInstance().font.split(this.description, width);
        int l = 0;
        for(FormattedCharSequence text : list){
            if(getHeight() > ((Minecraft.getInstance().font.lineHeight+3)*(l+2))) {
                guiGraphics.drawString(Minecraft.getInstance().font, text, getX(), getY() + ((Minecraft.getInstance().font.lineHeight+3) * l), -1);
                l++;
            } else {
                guiGraphics.drawString(Minecraft.getInstance().font, "...", getX(), getY() + ((Minecraft.getInstance().font.lineHeight+3) * l), -1);
                break;
            };
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
                this.playDownSound(Minecraft.getInstance().getSoundManager());
                this.onPress();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(DescriptionBox button);
    }

    public DescriptionBox setDescription(Component description){
        this.description = description;
        return this;
    }
}
