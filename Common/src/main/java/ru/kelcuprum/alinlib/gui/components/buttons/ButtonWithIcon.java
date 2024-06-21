package ru.kelcuprum.alinlib.gui.components.buttons;


import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class ButtonWithIcon extends Button {
    protected ResourceLocation icon;
    private OnPress onPress;
    private boolean isCenter = true;

    public ButtonWithIcon(Component leftLabel, ResourceLocation icon) {
        this(0,0, leftLabel, icon);
    }
    public ButtonWithIcon(int x, int y, Component leftLabel, ResourceLocation icon) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, leftLabel, icon);
    }
    public ButtonWithIcon(int x, int y, int width, int height, Component leftLabel, ResourceLocation icon) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), leftLabel, icon);
    }
    public ButtonWithIcon(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component leftLabel,ResourceLocation icon) {
        this(x, y, width, height, type, leftLabel, icon, true);
    }
    public ButtonWithIcon(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component leftLabel,ResourceLocation icon, boolean isCenter) {
        this(x, y, width, height, type, leftLabel, icon, isCenter, null);
    }
    public ButtonWithIcon(int x, int y, int width, int height, InterfaceUtils.DesignType type, Component label, ResourceLocation icon, boolean isCenter, OnPress onPress) {
        super(x, y, width, height, type, false, label, null);
        this.isCenter = isCenter;
        this.onPress = onPress;
        this.icon = icon;
    }

    @Override
    public void onPress() {
        if(!active) return;
        if(this.onPress != null) this.onPress.onPress(this);
    }

    @Override
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if(GuiUtils.isDoesNotFit(getMessage(), getWidth()-getHeight()-2, getHeight())){
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, getHeight(), 0xFFFFFF);
        } else {
            if(isCenter) guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, getMessage(), getX() + (getWidth()/2)+(getHeight()/2)+2, getY() + (getHeight() - 8) / 2, 0xffffff);
            else guiGraphics.drawString(AlinLib.MINECRAFT.font, getMessage(), getX() + getHeight() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
        }
        guiGraphics.blit(icon, getX(), getY(), 0.0f, 0.0f, getHeight(), getHeight(), getHeight(), getHeight());
    }
    @Override
    protected void renderScrollingString(GuiGraphics guiGraphics, Font font, int i, int j) {
        int k = this.getX() + i + 2;
        int l = this.getX() + this.getWidth() - 2;
        renderScrollingString(guiGraphics, font, this.getMessage(), k, this.getY(), l, this.getY() + this.getHeight(), j);
    }

    //

    public ButtonWithIcon setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }

    public interface OnPress {
        void onPress(ButtonWithIcon volume);
    }

    //
    protected Component description;
    public ButtonWithIcon setDescription(Component description){
        this.description = description;
        return this;
    }

    public Component getDescription(){
        return this.description;
    }
}
