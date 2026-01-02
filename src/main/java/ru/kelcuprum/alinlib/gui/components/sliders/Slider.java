package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
//#if MC >= 12109
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import com.mojang.blaze3d.platform.cursor.CursorTypes;
//#endif
//#if MC >= 12106
import net.minecraft.client.renderer.RenderPipelines;
//#else
//$$ import net.minecraft.client.renderer.RenderType;
//#endif
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.slider.SliderBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.RESET;

public class Slider extends AbstractSliderButton implements Description, Resetable {
    public final SliderBuilder builder;
    public Number displayValue = 0;

    public Slider(AbstractBuilder builder) {
        super(builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight(), builder.getTitle(), 0);
        this.builder = (SliderBuilder) builder;
        this.active = builder.getActive();
        this.visible = builder.getVisible();
        if(this.builder.hasConfigurable()){
            if (this.builder.type == SliderBuilder.NUMBER_TYPE.INTEGER) {
                int selValue = this.builder.config.getNumber(this.builder.configType, this.builder.defaultValue).intValue() - this.builder.min.intValue();
                this.displayValue = this.builder.min.intValue() + selValue;
                this.setValue(((double) selValue / (this.builder.max.intValue() - this.builder.min.intValue())));
            } else if (this.builder.type == SliderBuilder.NUMBER_TYPE.FLOAT) {
                float selValue = this.builder.config.getNumber(this.builder.configType, this.builder.defaultValue).floatValue() - this.builder.min.floatValue();
                this.displayValue = this.builder.min.floatValue() + selValue;
                this.setValue(((double) selValue / (this.builder.max.floatValue() - this.builder.min.floatValue())));
            } else if (this.builder.type == SliderBuilder.NUMBER_TYPE.DOUBLE || this.builder.type == SliderBuilder.NUMBER_TYPE.PERCENT) {
                double selValue = this.builder.config.getNumber(this.builder.configType, this.builder.defaultValue).doubleValue() - this.builder.min.doubleValue();
                this.displayValue = this.builder.min.doubleValue() + selValue;
                this.setValue(selValue / (this.builder.max.doubleValue() - this.builder.min.doubleValue()));
            }
        } else {
            if (this.builder.type == SliderBuilder.NUMBER_TYPE.INTEGER) {
                int selValue = (this.builder.defaultValue.intValue() - this.builder.min.intValue());
                this.displayValue = this.builder.min.intValue() + selValue;
                setValue(((double) selValue / (this.builder.max.intValue() - this.builder.min.intValue())));
            } else if (this.builder.type == SliderBuilder.NUMBER_TYPE.FLOAT) {
                float selValue = (this.builder.defaultValue.floatValue() - this.builder.min.floatValue());
                this.displayValue = this.builder.min.floatValue() + selValue;
                setValue(selValue / (this.builder.max.floatValue() - this.builder.min.floatValue()));
            } else if (this.builder.type == SliderBuilder.NUMBER_TYPE.DOUBLE || this.builder.type == SliderBuilder.NUMBER_TYPE.PERCENT) {
                double selValue = (this.builder.defaultValue.doubleValue() - this.builder.min.doubleValue());
                this.displayValue = this.builder.min.doubleValue() + selValue;
                setValue((selValue / (this.builder.max.doubleValue() - this.builder.min.doubleValue())));
            }
        }
        this.setMessage(Component.literal(this.builder.getTitle().getString()).append(": ").append(getComponentValue()));
    }

    @Override
    public boolean isActive() {
        return builder.getActive();
    }

    // Получить
    public Component getComponentValue() {
        if (this.builder.type == SliderBuilder.NUMBER_TYPE.PERCENT)
            return Component.literal(Localization.getRounding(displayValue.doubleValue(), true) + "%");
        else if (this.builder.type == SliderBuilder.NUMBER_TYPE.DOUBLE || this.builder.type == SliderBuilder.NUMBER_TYPE.FLOAT)
            return Component.literal(Localization.getRounding(displayValue.doubleValue()) + builder.getValueType());
        else return Component.literal(displayValue.intValue() + builder.getValueType());
    }

    public double getValue() {
        return this.value;
    }

    // Заменить
    public Slider setActive(boolean active) {
        this.active = active;
        return this;
    }

    // Рендер
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        if (this.visible) {
            renderBackground(guiGraphics, mouseX, mouseY, tick);
            renderText(guiGraphics, mouseX, mouseY, tick);
            //#if MC >= 12109
            if (this.isHovered()) {
                guiGraphics.requestCursor(this.isActive() ? isFocused() ? CursorTypes.RESIZE_EW : CursorTypes.POINTING_HAND : CursorTypes.NOT_ALLOWED);
            }
            //#endif
        }
    }

    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        if (isResetable()) {
            if (builder.getStyle() != null)
                builder.getStyle().renderBackground$widget(guiGraphics, getX(), getY(), getHeight(), getHeight(), this.active, this.isHoveredOrFocused(true, mouseX, mouseY));
            guiGraphics.blit(
                    //#if MC >= 12106
                    RenderPipelines.GUI_TEXTURED,
                    //#elseif MC >= 12102
                    //$$ RenderType::guiTextured,
                    //#endif
                    RESET, getX() + 2, getY() + 2, 0f, 0f, getHeight() - 4, getHeight() - 4, getHeight() - 4, getHeight() - 4);
            if (builder.getStyle() != null)
                builder.getStyle().renderBackground$slider(guiGraphics, getXComponent(), getY(), getWidthComponent(), getHeight(), this.active, this.isHoveredOrFocused(false, mouseX, mouseY), this.value);
        } else
            builder.getStyle().renderBackground$slider(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused(), this.value);
    }

    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        if (GuiUtils.isDoesNotFit(Component.literal(builder.getTitle().getString()).append(": ").append(getComponentValue()), getWidth(), getHeight())) {
            if (isHoveredOrFocused()) {
                this.setMessage(getComponentValue());
            } else {
                this.setMessage(Component.literal(builder.getTitle().getString()).append(": ").append(getComponentValue()));
            }
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, builder.getStyle().getTextSliderColor(active), builder.getStyle().sliderShadow());
        } else {
            if (isHovered()) {
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getComponentValue(), getXComponent() + (getWidthComponent() / 2) - (AlinLib.MINECRAFT.font.width(getComponentValue().getString()) / 2), getY() + (getHeight() - 8) / 2, builder.getStyle().getTextSliderColor(active), builder.getStyle().sliderShadow());
            } else {
                guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, builder.getStyle().getTextSliderColor(active), builder.getStyle().sliderShadow());
                // VOLUME
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getComponentValue(), getX() + getWidth() - AlinLib.MINECRAFT.font.width(getComponentValue().getString()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, builder.getStyle().getTextSliderColor(active), builder.getStyle().sliderShadow());
            }
        }
    }


    protected void renderScrollingString(GuiGraphics guiGraphics, Font font, int i, int j, boolean shadow) {
        int k = this.getX() + i;
        int l = this.getX() + this.getWidth() - i;
        TextBox.renderScrollingString(guiGraphics, font, this.getMessage(), k, this.getY(), l, this.getY() + this.getHeight(), j, shadow);
    }


    // BASE
    @Override
    protected void updateMessage() {

    }

    // Мелочи V2
    protected int getWidthComponent() {
        return isResetable() ? getWidth() - getHeight() - 2 : getWidth();
    }

    protected int getXComponent() {
        return isResetable() ? getX() + getHeight() + 2 : getX();
    }

    protected boolean isResetable() {
        return this.resettable() && AlinLib.bariumConfig.getBoolean("BUTTON.ENABLE_RESET_BUTTON", true);
    }

    @Override
    public void onClick(
                        //#if MC >= 12109
                        MouseButtonEvent event, boolean bl
                        //#else
                        //$$ double d, double e
                        //#endif
    ) {
        //#if MC >= 12109
        double d = event.x();
        //#endif
        if (isResetable()) {
            if (getX() < d && d < getX() + getHeight()) {
                ((Resetable) this).resetValue();
            } else {
                this.setValueFromMouse(d);
            }
        } else super.onClick(
                //#if MC >= 12109
                event, bl
                //#else
                //$$ d, e
                //#endif
        );
    }

    public boolean isHoveredOrFocused(boolean isReset, int mouseX, int mouseY) {
        int x = isReset ? getX() : getX() + 22;
        int width = isReset ? 20 : getWidth() - 22;
        boolean isHovered = mouseX >= x && mouseY >= this.getY() && mouseX < x + width && mouseY < this.getY() + this.height;
        return isHovered || (!isReset && isFocused());
    }

    // Мелочи v2 Slider
    private void setValueFromMouse(double d) {
        this.setValue((d - (double) (this.getXComponent() + 4)) / (double) (getWidthComponent() - 8));
    }


    @Override
    protected void onDrag(
            //#if MC < 12109
            //$$ double d, double e
            //#else
            MouseButtonEvent mouseButtonEvent
            //#endif
            , double f, double g) {

        //#if MC >= 12109
        double d = mouseButtonEvent.x();
        //#endif
        if (isResetable()) {
            if (d > getXComponent()) this.setValueFromMouse(d);
        } else super.onDrag(
                //#if MC < 12109
                //$$ d, e
                //#else
                mouseButtonEvent
                //#endif
                , f, g);
    }

    @Override
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
        if (i == GLFW.GLFW_KEY_DELETE && this.resettable()) {
            ((Resetable) this).resetValue();
            assert AlinLib.MINECRAFT != null;
            new ToastBuilder()
                    .setTitle(builder.getTitle())
                    .setMessage(Component.translatable("alinlib.component.value_reset.toast"))
                    .setIcon(RESET)
                    .setIsWhiteIcon(true)
                    .buildAndShow();
            AlinLib.LOG.log(Component.translatable("alinlib.component.reset.toast"));
            return true;
        }
        return super.keyPressed(
                //#if MC < 12109
                //$$ i, j, k
                //#else
                keyEvent
                //#endif
        );
    }
    //

    @Override
    protected void applyValue() {
        if (builder.type == SliderBuilder.NUMBER_TYPE.INTEGER) {
            int selValue = (int) ((this.builder.max.intValue() - this.builder.min.intValue()) * this.value);
            this.displayValue = this.builder.min.intValue() + selValue;
            if (builder.hasConfigurable()) this.builder.config.setNumber(this.builder.configType, this.displayValue.intValue());
        } else if (builder.type == SliderBuilder.NUMBER_TYPE.DOUBLE) {
            double selValue = (this.builder.max.doubleValue() - this.builder.min.doubleValue()) * getValue();
            this.displayValue = this.builder.min.doubleValue() + selValue;
            if (builder.hasConfigurable()) this.builder.config.setNumber(this.builder.configType, displayValue.doubleValue());
        } else if (builder.type == SliderBuilder.NUMBER_TYPE.FLOAT) {
            float selValue = (float) ((this.builder.max.floatValue() - this.builder.min.floatValue()) * getValue());
            this.displayValue = this.builder.min.floatValue() + selValue;
            if (builder.hasConfigurable()) this.builder.config.setNumber(this.builder.configType, displayValue.floatValue());
        } else if (builder.type == SliderBuilder.NUMBER_TYPE.PERCENT) {
            double selValue = (this.builder.max.doubleValue() - this.builder.min.doubleValue()) * getValue();
            this.displayValue = (this.builder.min.doubleValue() + selValue) * 100;
            if (builder.hasConfigurable())
                this.builder.config.setNumber(this.builder.configType, (this.builder.min.doubleValue() + selValue));
        }
        if (this.builder.getOnPress() != null) this.builder.getOnPress().onPress(this.value);
    }

    @Override
    public void resetValue() {
        if (this.builder.hasConfigurable()) {
            this.builder.config.setNumber(this.builder.configType, this.builder.defaultValue);
            if (this.builder.type == SliderBuilder.NUMBER_TYPE.INTEGER) {
                this.setValue(((double) (builder.config.getNumber(builder.configType, builder.defaultValue).intValue() - builder.min.intValue()) / (builder.max.intValue() - builder.min.intValue())));
            } else if (this.builder.type == SliderBuilder.NUMBER_TYPE.FLOAT) {
                this.setValue(((double) (builder.config.getNumber(builder.configType, builder.defaultValue).floatValue() - builder.min.floatValue()) / (builder.max.floatValue() - builder.min.floatValue())));
            } else if (this.builder.type == SliderBuilder.NUMBER_TYPE.DOUBLE || this.builder.type == SliderBuilder.NUMBER_TYPE.PERCENT) {
                this.setValue(((builder.config.getNumber(builder.configType, builder.defaultValue).doubleValue() - builder.min.doubleValue()) / (builder.max.doubleValue() - builder.min.doubleValue())));
            }
        } else {
            if (this.builder.type == SliderBuilder.NUMBER_TYPE.INTEGER) {
                this.setValue(((double) (builder.defaultValue.intValue() - builder.min.intValue()) / (builder.max.intValue() - builder.min.intValue())));
            } else if (this.builder.type == SliderBuilder.NUMBER_TYPE.FLOAT) {
                this.setValue(((double) (builder.defaultValue.floatValue() - builder.min.floatValue()) / (builder.max.floatValue() - builder.min.floatValue())));
            } else if (this.builder.type == SliderBuilder.NUMBER_TYPE.DOUBLE || this.builder.type == SliderBuilder.NUMBER_TYPE.PERCENT) {
                this.setValue(((builder.defaultValue.doubleValue() - builder.min.doubleValue()) / (builder.max.doubleValue() - builder.min.doubleValue())));
            }
        }
    }

    @Override
    public boolean resettable() {
        return builder.hasConfigurable();
    }
    // Мелочи

    public interface OnPress {
        void onPress(double value);
    }

    public Slider setDescription(Component description) {
        this.builder.setDescription(description);
        return this;
    }
    public Component getDescription(){
        return this.builder.getDescription();
    }
}
