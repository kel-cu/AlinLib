package ru.kelcuprum.alinlib.gui.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class ImageWidget extends AbstractWidget {
    protected final ResourceLocation image;
    protected final int imageWidth, imageHeight;

    public ImageWidget(int x, int y, ResourceLocation image, Component message) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, image, DEFAULT_WIDTH(), DEFAULT_HEIGHT, message);
    }
    public ImageWidget(int x, int y, ResourceLocation image, int imageWidth, int imageHeight, Component message) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, image, imageWidth, imageHeight, message);
    }
    ///
    public ImageWidget(int x, int y, int width, int height, ResourceLocation image, Component message) {
        this(x, y, width, height, image, width, height, message);
    }
    public ImageWidget(int x, int y, int width, int height, ResourceLocation image, int imageWidth, int imageHeight, Component message) {
        super(x, y, width, height, message);
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.active = false;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(this.image, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), imageWidth, imageHeight);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }
}
