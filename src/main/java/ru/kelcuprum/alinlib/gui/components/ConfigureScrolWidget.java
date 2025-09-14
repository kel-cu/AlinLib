package ru.kelcuprum.alinlib.gui.components;
//#if MC < 12105
//$$ import com.mojang.blaze3d.systems.RenderSystem;
//#endif
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
//#if MC >= 12109
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
//#endif
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigureScrolWidget extends AbstractWidget {
    private double scrollAmount;
    private boolean scrolling;

    public final Consumer<ConfigureScrolWidget> onScroll;
    public int innerHeight;
    public List<AbstractWidget> widgets = new ArrayList<>();

    public ConfigureScrolWidget(int x, int y, int width, int height, Component message, Consumer<ConfigureScrolWidget> onScroll) {
        super(x, y, width, height, message);
        this.onScroll = onScroll;
    }
    protected int getInnerHeight() {
        return innerHeight;
    }

    protected double scrollRate() {
        return 9.0;
    }

    public double scrollAmount() {
        return scrollAmount;
    }

    public void setScrollAmount(double amount) {
        this.scrollAmount = Mth.clamp(amount, 0.0, this.getMaxScrollAmount());
        this.onScroll.accept(this);
    }

    protected void renderBackground(GuiGraphics guiGraphics) {
        if (this.scrollbarVisible()) guiGraphics.fill(getX(), getY(), getX()+this.width, getY()+getHeight(), 0x75000000);
    }

    private int getContentHeight() {
        return this.getInnerHeight() + 4;
    }

    private int getScrollBarHeight() {
        return Mth.clamp((int)((float)(this.height * this.height) / (float)this.getContentHeight()), 16, this.height);
    }
    protected void renderDecorations(GuiGraphics guiGraphics) {
        if (this.scrollbarVisible()) {
            int i = this.getScrollBarHeight();
            int k = Math.max(this.getY(), (int)this.scrollAmount() * (this.height - i) / this.getMaxScrollAmount()+ this.getY());
            //#if MC < 12105
            //$$ RenderSystem.enableBlend();
            //#endif
            guiGraphics.fill(getX(), k, getX()+getWidth(), k+i, GuiUtils.getSelected().getScrollerColor());
            //#if MC < 12105
            //$$ RenderSystem.disableBlend();
            //#endif
        }
    }

    public void resetWidgets(){
        widgets.clear();
        setScrollAmount(0);
    }
    public void addWidget(AbstractWidget widget) {
        widgets.add(widget);
    }
    public void addWidgets(List<AbstractWidget> widgets) {
        for(AbstractWidget widget : widgets) addWidget(widget);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, getMessage());
    }

    // Original code: https://modrinth.com/mod/smooth-scrolling-refurbished
    private double animationTimer = 0;
    private double scrollStartVelocity = 0;

    public static double scrollSpeed = 0.5;
    public static double scrollbarDrag= 0.025;
    public static double animationDuration = 1.0;
    public static double pushBackStrength = 1.0;
    // ---
    @Override
    public boolean mouseScrolled(double d, double e, double f, double g) {
        if (!this.visible) {
            return false;
        } else {
            double amount = this.scrollAmount - g * this.scrollRate();
            if(AlinLib.bariumConfig.getBoolean("SCROLLER.SMOOTH", false) && scrollbarVisible()){
                double diff = amount - this.scrollAmount;
                diff = Math.signum(diff) * Math.min(Math.abs(diff), 10);
                diff *= scrollSpeed;
                if (Math.signum(diff) != Math.signum(scrollStartVelocity)) diff *= 2.5d;
                animationTimer *= 0.5;
                scrollStartVelocity = scrollbarVelocity(animationTimer, scrollStartVelocity) + diff;
                animationTimer = 0;
            } else this.setScrollAmount(amount);
            return true;
        }
    }

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
        if (!this.visible) {
            return false;
        } else {
            boolean bl = this.withinContentAreaPoint(d, e);
            boolean bl2 = this.scrollbarVisible() && d >= (double)(this.getX()) && d <= (double)(this.getX() + this.width) && e >= (double)this.getY() && e < (double)(this.getY() + this.height);
            if (bl2 && i == 0) {
                this.scrolling = true;
                return true;
            } else {
                return bl || bl2;
            }
        }
    }

    public boolean mouseReleased(
            //#if MC >= 12109
            MouseButtonEvent mouseButtonEvent
            //#else
            //$$ double d, double e, int i
            //#endif
    ) {
        //#if MC >= 12109
        int i = mouseButtonEvent.button();
        //#endif
        if (i == 0) {
            this.scrolling = false;
        }

        return super.mouseReleased(
                //#if MC >= 12109
        mouseButtonEvent
        //#else
        //$$ d, e, i
        //#endif
        );
    }

    public boolean mouseDragged(
            //#if MC >= 12109
            MouseButtonEvent mouseButtonEvent, double f, double g
            //#else
            //$$ double d, double e, int i, double f, double g
            //#endif
    ) {
        //#if MC >= 12109
        double e = mouseButtonEvent.y();
        //#endif
        if (this.visible && this.isFocused() && this.scrolling) {
            if (e < (double)this.getY()) {
                this.setScrollAmount(0.0);
            } else if (e > (double)(this.getY() + this.height)) {
                this.setScrollAmount(this.getMaxScrollAmount());
            } else {
                int j = this.getScrollBarHeight();
                double h = Math.max(1, this.getMaxScrollAmount() / (this.height - j));
                this.setScrollAmount(this.scrollAmount + g * h);
            }

            return true;
        } else {
            return false;
        }
    }

    protected boolean withinContentAreaPoint(double d, double e) {
        return d >= (double)this.getX() && d < (double)(this.getX() + this.width) && e >= (double)this.getY() && e < (double)(this.getY() + this.height);
    }

    public boolean keyPressed(
            //#if MC < 12109
            //$$ int i, int j, int k
            //#else
            KeyEvent keyEvent
            //#endif
    ) {
        //#if MC >= 12109
        int i = keyEvent.key();
        //#endif
        boolean bl = i == 265;
        boolean bl2 = i == 264;
        if (bl || bl2) {
            double d = this.scrollAmount;
            this.setScrollAmount(this.scrollAmount + (double)(bl ? -1 : 1) * this.scrollRate());
            if (d != this.scrollAmount) {
                return true;
            }
        }

        return super.keyPressed(
                //#if MC < 12109
                //$$ i, j, k
                //#else
                keyEvent
                //#endif
        );
    }
    // ---

    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if(AlinLib.bariumConfig.getBoolean("SCROLLER.SMOOTH", false)) {
            checkOutOfBounds(delta);
            if (Math.abs(scrollbarVelocity(animationTimer, scrollStartVelocity)) > 1.0) applyMotion(delta);
        }
        renderBackground(guiGraphics);
        renderDecorations(guiGraphics);
    }
    private void applyMotion(float delta) {
        this.scrollAmount += scrollbarVelocity(animationTimer, scrollStartVelocity) * delta;
        animationTimer += delta * 10 / animationDuration;
        this.onScroll.accept(this);
    }

    private void checkOutOfBounds(float delta) {
        if (this.scrollAmount < 0) {
            this.scrollAmount += pushBackStrength(Math.abs(this.scrollAmount), delta);
            if (this.scrollAmount > -0.2) this.scrollAmount = 0;
        }
        if (this.scrollAmount > getMaxScrollAmount()) {
            this.scrollAmount -= pushBackStrength(this.scrollAmount - getMaxScrollAmount(), delta);
            if (this.scrollAmount < getMaxScrollAmount() + 0.2) this.scrollAmount = getMaxScrollAmount();
        }
    }
    // MC
    protected boolean scrollbarVisible() {
        return this.getInnerHeight() > this.getHeight();
    }

    protected int getMaxScrollAmount() {
        return Math.max(0, this.getContentHeight() - (this.height - 4));
    }

    // ScrollMath
    public static double scrollbarVelocity(double timer, double factor) {
        return Math.pow(1 - scrollbarDrag, timer) * factor;
    }

    public static double pushBackStrength(double distance, float delta) {
        return ((distance + 4d) * delta / 0.3d) / (3.2d/pushBackStrength);
    }
}