package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.HorizontalRuleBuilder;

import java.util.Objects;

import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.ALIGN.CENTER;

public class HorizontalRule extends AbstractWidget {
    public final HorizontalRuleBuilder builder;
    public final boolean hasText;
    public HorizontalRule(HorizontalRuleBuilder builder) {
        super(builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight(), builder.title);
        this.active = false;
        this.builder = builder;
        this.hasText = !Objects.equals(builder.getTitle(), Component.empty());
    }

    @Override
    public int getHeight() {
        if(hasText){
            return 2 + AlinLib.MINECRAFT.font.lineHeight + builder.height;
        } else return builder.height;
    }

    @Override
    protected void extractWidgetRenderState(GuiGraphicsExtractor GuiGraphicsExtractor, int i, int j, float f) {
        if(hasText){
            int titleWidth = Math.min(AlinLib.MINECRAFT.font.width(builder.title), getWidth()-(getHeight()-8));
            int width = (getWidth() / 2) - (titleWidth / 2) - 4;
            int y = getY()+(getHeight()/2);

            if(isDoesNotFit()) this.renderScrollingString(GuiGraphicsExtractor, AlinLib.MINECRAFT.font, 2, builder.getStyle().getTextColor(true), builder.getStyle().textShadow());
            else GuiGraphicsExtractor.text(AlinLib.MINECRAFT.font, getMessage(), getX() + getWidth() / 2-(AlinLib.MINECRAFT.font.width(getMessage())/2), getY() + (getHeight() - 8) / 2, builder.getStyle().getTextColor(true), builder.getStyle().textShadow());

            GuiGraphicsExtractor.fill(getX(), y-builder.height, getX()+width, y, getColor());
            GuiGraphicsExtractor.fill(getX()+getWidth()-width, y-builder.height, getRight(), y, getColor());
        } else GuiGraphicsExtractor.fill(getX(), getY(), getRight(), getBottom(), getColor());
    }

    protected void renderScrollingString(GuiGraphicsExtractor GuiGraphicsExtractor, Font font, int i, int j, boolean shadow) {
        int k = this.getX() + i;
        int l = this.getX() + this.getWidth() - i;
        TextBox.renderScrollingString(GuiGraphicsExtractor, font, this.getMessage(), k, this.getY(), l, this.getY() + this.getHeight(), j, shadow);
    }

    private boolean isDoesNotFit(){
        int size = AlinLib.MINECRAFT.font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return size > getWidth();
    }

    public int getColor(){
        return builder.color != null ? builder.color[0] : GuiUtils.getSelected().getHorizontalRuleColor();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}
