package ru.kelcuprum.alinlib.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
//#if MC >= 12109
import net.minecraft.client.input.MouseButtonEvent;
import com.mojang.blaze3d.platform.cursor.CursorTypes;
//#endif
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;

public class PageControlWidget extends AbstractWidget {
    public int position;
    public int size;
    public final OnPress onPress;

    public PageControlWidget(int x, int y, int width, int height, int position, int size, OnPress onPress) {
        super(x, y, width, height, Component.empty());
        this.position = position;
        this.size = size;
        this.onPress = onPress;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        int p = (this.getHeight() - 8) / 2;
        guiGraphics.drawString(AlinLib.MINECRAFT.font, "◀", getX() + p, getY() + p, GuiUtils.getSelected().getTextColor(availableLeftScroll()), GuiUtils.getSelected().textShadow());
        guiGraphics.drawString(AlinLib.MINECRAFT.font, String.format("%s / %s", position + 1, size),
                getX() + (getWidth() / 2) - (AlinLib.MINECRAFT.font.width(String.format("%s / %s", position + 1, size))/2), getY() + p, GuiUtils.getSelected().getTextColor(availableLeftScroll()), GuiUtils.getSelected().textShadow());
        guiGraphics.drawString(AlinLib.MINECRAFT.font, "▶", getRight() - p - AlinLib.MINECRAFT.font.width("▶"), getY() + p, GuiUtils.getSelected().getTextColor(availableLeftScroll()), GuiUtils.getSelected().textShadow());

        //#if MC >= 12109
        if (i < getX() + getHeight() || i > getRight() - getHeight()) {
            boolean enable;
            if(i < getX() + getHeight()){
                enable = availableLeftScroll();
            } else enable = availableRightScroll();
            guiGraphics.requestCursor(this.isActive() && enable ? CursorTypes.POINTING_HAND : CursorTypes.NOT_ALLOWED);
        }
        //#endif
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }

    @Override
    public boolean mouseClicked(
            //#if MC >= 12109
            MouseButtonEvent mouseButtonEvent, boolean bl1
            //#else
            //$$ double d, double e, int i
            //#endif
    ) {
        //#if MC >= 12109
        double d = mouseButtonEvent.x();
        double e = mouseButtonEvent.y();
        int i = mouseButtonEvent.button();
        //#endif
        //#if MC < 12104
        //$$ if (clicked(d, e)) {
        //#else
        if(isMouseOver(d, e)){
        //#endif
            if (d < getX() + getHeight() && availableLeftScroll()) {
                leftScroll();
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
                onPress.onPress(this);
                return true;
            } else if (d > getRight() - getHeight() && availableRightScroll()) {
                rightScroll();
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
                onPress.onPress(this);
                return true;
            }
        }
        return false;
    }

    public boolean availableLeftScroll() {
        return position > 0;
    }

    public boolean availableRightScroll() {
        return position + 1 != size;
    }

    public void leftScroll() {
        if (position - 1 != -1) position--;
    }

    public void rightScroll() {
        if (position + 1 != size) position++;
    }

    public interface OnPress {
        void onPress(PageControlWidget var1);
    }
}
