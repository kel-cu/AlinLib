package ru.kelcuprum.alinlib.gui.components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class ImageWidget extends AbstractWidget implements Description {
    protected final ResourceLocation image;
    protected final int imageWidth, imageHeight;
    protected final boolean isScale;

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
        this(x, y, width, height, image, imageWidth, imageHeight, false, message);
    }

    public ImageWidget(int x, int y, int width, int height, ResourceLocation image, int imageWidth, int imageHeight, boolean isScale, Component message) {
        super(x, y, width, height, message);
        this.isScale = isScale;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.active = false;
    }

    @Override
    public int getHeight() {
        double scale = (double) width /imageWidth;
        int imHeight = (int) (imageHeight*scale);
        return isScale ? imHeight : super.getHeight();
    }

    @Override
    public int getWidth() {
        double scale = (double) width /imageWidth;
        int imWidth = (int) (imageWidth*scale);
        return isScale ? imWidth : super.getWidth();
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if(isScale) {
            double scale = (double) width / imageWidth;
            int imWidth = (int) (imageWidth * scale);
            int imHeight = (int) (imageHeight * scale);
            //#if MC >= 12102
            guiGraphics.blit(RenderType::guiTextured, this.image, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), imWidth, imHeight);
        } else guiGraphics.blit(RenderType::guiTextured, this.image, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), imageWidth, imageHeight);
            //#elseif MC < 12102
            //$$    guiGraphics.blit(this.image, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), imWidth, imHeight);
            //$$} else guiGraphics.blit(this.image, getX(), getY(), 0.0F, 0.0F, getWidth(), getHeight(), imageWidth, imageHeight);
            //#endif

    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }


    protected Component description;
    public ImageWidget setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
