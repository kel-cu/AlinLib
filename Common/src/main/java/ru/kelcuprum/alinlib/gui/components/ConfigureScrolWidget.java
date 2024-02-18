package ru.kelcuprum.alinlib.gui.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractScrollWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class ConfigureScrolWidget extends AbstractScrollWidget {
    public final Consumer<ConfigureScrolWidget> onScroll;
    public int innerHeight;

    public ConfigureScrolWidget(int x, int y, int width, int height, Component message, Consumer<ConfigureScrolWidget> onScroll) {
        super(x, y, width, height, message);

        this.onScroll = onScroll;
    }

    @Override
    protected int getInnerHeight() {
        return innerHeight;
    }

    @Override
    protected double scrollRate() {
        return 9.0;
    }

    @Override
    public double scrollAmount() {
        return super.scrollAmount();
    }

    @Override
    protected void setScrollAmount(double scrollAmount) {
        super.setScrollAmount(scrollAmount);
        this.onScroll.accept(this);
    }

    @Override
    protected void renderContents(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

    }

    @Override
    protected void renderBorder(GuiGraphics guiGraphics, int x, int y, int width, int height) {
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, getMessage());
    }
}