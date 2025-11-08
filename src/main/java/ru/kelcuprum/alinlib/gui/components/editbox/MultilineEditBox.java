package ru.kelcuprum.alinlib.gui.components.editbox;

//#if MC >= 12109
import net.minecraft.client.input.KeyEvent;
import com.mojang.blaze3d.platform.cursor.CursorTypes;
//#endif
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.MultilineEditBoxBuilder;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.RESET;

public class MultilineEditBox extends net.minecraft.client.gui.components.MultiLineEditBox implements Description, Resetable {
    protected boolean isError;

    public final MultilineEditBoxBuilder builder;
    public int volume;

    public MultilineEditBox(AbstractBuilder builder) {
        super(((MultilineEditBoxBuilder) builder).getFont(), builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight(), builder.getTitle(), Component.empty(),
                builder.getStyle().getEditBoxColor(builder.getActive()), builder.getStyle().editBoxShadow(), builder.getStyle().getCursorColor(),
                false, false);
        this.builder = (MultilineEditBoxBuilder) builder;
        this.active = builder.getActive();
        this.visible = builder.getVisible();
        setCharacterLimit(Integer.MAX_VALUE);
        if (this.builder.hasConfigurable()){
            setValue(this.builder.config.getString(this.builder.configType, this.builder.value));
            setValueListener(string -> this.builder.config.setString(this.builder.configType, string));
        } else if (this.builder.hasLocalization()) {
            setValue(this.builder.localization.getLocalization(this.builder.configType, false, false, false));
            setValueListener(string -> this.builder.localization.setLocalization(this.builder.configType, string));
        } else {
            setValue(((MultilineEditBoxBuilder) builder).value);
            if(((MultilineEditBoxBuilder) builder).responder != null) setValueListener(((MultilineEditBoxBuilder) builder).responder);
        }
    }

    @Override
    public boolean isActive() {
        return builder.getActive();
    }

    protected int getPositionContent(String content) {
        int pos = getX() + getWidth() - AlinLib.MINECRAFT.font.width(builder.isColor ? content.toUpperCase() : content) - ((getHeight() - 8) / 2);

        if (getX() + AlinLib.MINECRAFT.font.width(getMessage()) + ((getHeight() - 8) / 2) * 2 > pos)
            pos = getX() + AlinLib.MINECRAFT.font.width(getMessage()) + ((getHeight() - 8) / 2) * 2;

        return pos;
    }

    protected int getColor() {
        return (getError() ? Colors.GROUPIE : builder.isColor ? volume : isFocused() ? Colors.CLOWNFISH : Colors.SEADRIVE);
    }

    public MultilineEditBox setError(boolean error) {
        this.isError = error;
        return this;
    }

    public boolean getError() {
        return this.isError;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (visible) {
            renderBackground(guiGraphics, mouseX, mouseY, partialTick);
            if (isFocused()) {
                super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);
            } else {
                renderText(guiGraphics, mouseX, mouseY, partialTick);
            }

            //#if MC >= 12109
            if (this.isHovered()) {
                guiGraphics.requestCursor(this.isActive() ? isFocused() ? CursorTypes.IBEAM : CursorTypes.POINTING_HAND : CursorTypes.NOT_ALLOWED);
            }
            //#endif
        }
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

    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.builder.getStyle().renderBackground$editbox(guiGraphics, getX(), getY(), getWidth(), getHeight(), this.active, this.isHoveredOrFocused());
    }

    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int y = 4;
        int x = 4;
        if(!builder.getTitle().equals(Component.empty())){
            guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getX() + 5, getY() + 5, isError ? Colors.GROUPIE : builder.getStyle().getEditBoxColor(active), builder.getStyle().editBoxShadow());
            y+=AlinLib.MINECRAFT.font.lineHeight+4;
            guiGraphics.fill(getX()+3, getY()+y, getRight()-3, getY()+y+1, builder.getStyle().getHorizontalRuleColor());
            y+=5;
        }
        for(FormattedCharSequence formattedCharSequence : AlinLib.MINECRAFT.font.split(this.builder.secret ? Component.translatable("alinlib.editbox.secret"): Component.literal(getValue()), width-12)){
            if(y+AlinLib.MINECRAFT.font.lineHeight+3 > height) break;
            guiGraphics.drawString(AlinLib.MINECRAFT.font, formattedCharSequence, getX() + x, getY() + y, isError ? Colors.GROUPIE : builder.getStyle().getEditBoxColor(active), builder.getStyle().editBoxShadow());
            y+=AlinLib.MINECRAFT.font.lineHeight;
        }
    }

    public MultilineEditBox setDescription(Component description) {
        this.builder.setDescription(description);
        return this;
    }
    public Component getDescription(){
        return this.builder.getDescription();
    }

    @Override
    public void resetValue() {
        if(builder.isColor) {
            if(builder.hasConfigurable()) builder.config.setNumber(builder.configType, builder.color);
            setValue(Integer.toHexString(builder.color));
        } else if(builder.hasConfigurable()){
            builder.config.setString(builder.configType, builder.value);
            setValue(builder.value);
        } else if(builder.hasLocalization()){
            builder.localization.resetLocalization(builder.configType);
            setValue(builder.localization.getLocalization(builder.configType, false, false, false));
        } else setValue(builder.value);
    }

    @Override
    public boolean resettable() {
        return builder.hasConfigurable() || builder.hasLocalization();
    }
}