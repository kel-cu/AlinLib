package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
//#if MC >= 12109
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import com.mojang.blaze3d.platform.cursor.CursorTypes;
//#endif
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import org.jetbrains.annotations.Nullable;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.ALIGN.CENTER;
import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.ALIGN.LEFT;
import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.TYPE.*;

public class TextBox extends AbstractWidget implements Description {
    public final TextBuilder builder;

    public TextBox(TextBuilder builder) {
        super(builder.getX(), builder.getY(), builder.getWidth(), builder.getHeight(), builder.getTitle());
        this.builder = builder;
        this.setActive(builder.onPress != null);
    }

    public void setActive(boolean active) {
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
        if (builder.getOnPress() != null) builder.getOnPress().onPress(this);
    }

    @Override
    public void setMessage(Component component) {
        this.builder.setTitle(component);
        super.setMessage(component);
    }

    @Override
    public int getHeight() {
        if (builder.type == TEXT) {
            this.height = builder.getHeight();
            return super.getHeight();
        } else {
            this.height = 8 + (AlinLib.MINECRAFT.font.lineHeight + 3) * (AlinLib.MINECRAFT.font.split(getMessage(), width - 12).size());
            return this.height;
        }
    }

    @Override
    public void setHeight(int i) {
        builder.setHeight(i);
        super.setHeight(i);
    }

    public List<FormattedCharSequence> getArrayTexts(int border) {
        return AlinLib.MINECRAFT.font.split(getMessage(), width - border);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        renderBackground(guiGraphics);
        if (builder.type == TEXT || builder.type == TITLE) {
            if (isDoesNotFit()) this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2,  builder.getStyle().getTextColor(builder.type), builder.getStyle().textShadow(builder.type));
            else if (builder.align == CENTER)
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getMessage(), getX() + getWidth() / 2 - (AlinLib.MINECRAFT.font.width(getMessage())/2), getY() + (getHeight() - 8) / 2, builder.getStyle().getTextColor(builder.type), builder.getStyle().textShadow(builder.type));
            else
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getMessage(), (builder.align == LEFT ? (getX() + (getHeight() - 8) / 2) : (getX() + getWidth() - (getHeight() - 8) / 2) - AlinLib.MINECRAFT.font.width(getMessage())), getY() + (getHeight() - 8) / 2, builder.getStyle().getTextColor(builder.type), builder.getStyle().textShadow(builder.type));
        } else renderMessageText(guiGraphics);

        //#if MC >= 12109
        if (this.isHovered() && builder.onPress != null) {
            guiGraphics.requestCursor(this.isActive() ? CursorTypes.POINTING_HAND : CursorTypes.NOT_ALLOWED);
        }
        //#endif
    }

    public void renderBackground(GuiGraphics guiGraphics) {
        if (builder.type != BLOCKQUOTE && builder.onPress != null)
            builder.getStyle().renderBackground$widget(guiGraphics, getX(), getY(), getWidth(), getHeight(), true, isHoveredOrFocused());
        else if (builder.type == BLOCKQUOTE) {
            builder.getStyle().renderBlockquoteBackground(builder, guiGraphics, this.getX(), this.getY(), this.getWidth(), this.getHeight(), getBlockquoteColor());
        }
    }

    public int[] getBlockquoteColor() {
        return builder.color == null ? builder.getStyle().getBlockquoteColors() : builder.color;
    }

    public void renderMessageText(GuiGraphics guiGraphics) {
        List<FormattedCharSequence> list = getArrayTexts(this.builder.type == BLOCKQUOTE && this.builder.align != CENTER ? 13 : 12);
        int l = 0;
        for (FormattedCharSequence text : list) {
            if (builder.align == CENTER)
                guiGraphics.drawString(AlinLib.MINECRAFT.font, text, getX() + (getWidth() / 2) - (AlinLib.MINECRAFT.font.width(text)/2), getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight + 3) * l), builder.getStyle().getTextColor(builder.type), builder.getStyle().textShadow(builder.type));
            else
                guiGraphics.drawString(AlinLib.MINECRAFT.font, text, (builder.align == LEFT ? getX() + (this.builder.type == BLOCKQUOTE ? 7 : 6) : getX() + getWidth() - 6 - AlinLib.MINECRAFT.font.width(text)), getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight + 3) * l), builder.getStyle().getTextColor(builder.type), builder.getStyle().textShadow(builder.type));
            l++;
        }
    }

    public static void renderScrollingString(GuiGraphics guiGraphics, Font font, Component component, int i, int j, int k, int l, int m, boolean shadow) {
        renderScrollingString(guiGraphics, font, component, (i + k) / 2, i, j, k, l, m, shadow);
    }

    public static void renderScrollingString(GuiGraphics guiGraphics, Font font, Component component, int i, int j, int k, int l, int m, int n, boolean shadow) {
        int o = font.width(component);
        int var10000 = k + m;
        Objects.requireNonNull(font);
        int p = (var10000 - 9) / 2 + 1;
        int q = l - j;
        if (o > q) {
            int r = o - q;
            double d = (double)Util.getMillis() / (double)1000.0F;
            double e = Math.max((double)r * (double)0.5F, (double)3.0F);
            double f = Math.sin((Math.PI / 2D) * Math.cos((Math.PI * 2D) * d / e)) / (double)2.0F + (double)0.5F;
            double g = Mth.lerp(f, (double)0.0F, (double)r);
            guiGraphics.enableScissor(j, k, l, m);
            guiGraphics.drawString(font, component, j - (int)g, p, n, shadow);
            guiGraphics.disableScissor();
        } else {
            int r = Mth.clamp(i, j + o / 2, l - o / 2);
            guiGraphics.drawString(font, component, r-(font.width(component)/2), p, n, shadow);
        }

    }

    protected void renderScrollingString(GuiGraphics guiGraphics, Font font, int i, int j, boolean shadow) {
        int k = this.getX() + i;
        int l = this.getX() + this.getWidth() - i;
        renderScrollingString(guiGraphics, font, this.getMessage(), k, this.getY(), l, this.getY() + this.getHeight(), j, shadow);
    }

    private boolean isDoesNotFit() {
        int size = AlinLib.MINECRAFT.font.width(this.getMessage()) + ((getHeight() - 8) / 2) * 2;
        return size > getWidth();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    @Override
    public void onClick(
                        //#if MC >= 12109
                        MouseButtonEvent mouseButtonEvent, boolean bl
                        //#else
                        //$$ double d, double e
                        //#endif
    ) {
        this.onPress();
    }

    @Override
    public boolean mouseClicked(
                                //#if MC >= 12109
                                MouseButtonEvent mouseButtonEvent, boolean bl
                                //#else
                                //$$ double d, double e, int i
                                //#endif
    ) {
        //#if MC >= 12109
        double d = mouseButtonEvent.x();
        double e = mouseButtonEvent.y();
        //#endif
        if (this.builder.type == BLOCKQUOTE || this.builder.type == MESSAGE) {
            List<FormattedCharSequence> list = getArrayTexts(this.builder.type == BLOCKQUOTE && this.builder.align != CENTER ? 13 : 12);
            int l = 0;
            int x = getX() + (this.builder.type == BLOCKQUOTE ? 7 : 6);
            for (FormattedCharSequence chars : list) {
                int y = getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight + 3) * l);
                if (x <= d && d <= x + AlinLib.MINECRAFT.font.width(chars)) {
                    if (y <= e && e <= y + AlinLib.MINECRAFT.font.lineHeight) {
                        Style style = AlinLib.MINECRAFT.font.getSplitter().componentStyleAtWidth(chars, Mth.floor(screenToChatX(x, d)));
                        if (style != null && this.handleComponentClicked(style)) {
                            return true;
                        }
                    }
                }
                l++;
            }
            return super.mouseClicked(
                    //#if MC >= 12109
                    mouseButtonEvent, bl
                    //#else
                    //$$ d, e, i
                    //#endif
            );
        } else return super.mouseClicked(
                //#if MC >= 12109
                mouseButtonEvent, bl
                //#else
                //$$ d, e, i
                //#endif
        );
    }


    public boolean handleComponentClicked(@Nullable Style style) {
        if (style == null) {
            return false;
        } else {
            ClickEvent clickEvent = style.getClickEvent();
            if (clickEvent != null) {
                //#if MC >= 12105
                if (clickEvent.action() == ClickEvent.Action.OPEN_URL) {
                    //#else
                    //$$ if (clickEvent.getAction() == ClickEvent.Action.OPEN_URL) {
                    //#endif
                    if (!(Boolean) AlinLib.MINECRAFT.options.chatLinks().get()) {
                        return false;
                    }

                    //#if MC >= 12105
                    URI uRI = ((ClickEvent.OpenUrl) clickEvent).uri();
                    if (AlinLib.MINECRAFT.options.chatLinksPrompt().get()) {
                        Screen current = AlinLib.MINECRAFT.screen;
                        AlinLib.MINECRAFT.setScreen(new ConfirmLinkScreen((bl) -> {
                            if (bl) {
                                Util.getPlatform().openUri(uRI);
                            }

                            AlinLib.MINECRAFT.setScreen(current);
                        }, uRI.toString(), false));
                    } else {
                        Util.getPlatform().openUri(uRI);
                    }
                    //#else
                    //$$ try {
                    //$$       URI uRI = Util.parseAndValidateUntrustedUri(clickEvent.getValue());
                    //$$       if (AlinLib.MINECRAFT.options.chatLinksPrompt().get()) {
                    //$$                        Screen current = AlinLib.MINECRAFT.screen;
                    //$$                        AlinLib.MINECRAFT.setScreen(new ConfirmLinkScreen((bl) -> {
                    //$$                            if (bl) {
                    //$$                                Util.getPlatform().openUri(uRI);
                    //$$                            }
                    //$$
                    //$$                            AlinLib.MINECRAFT.setScreen(current);
                    //$$                        }, uRI.toString(), false));
                    //$$                    } else {
                    //$$                        Util.getPlatform().openUri(uRI);
                    //$$                    }
                    //$$ } catch(Exception ex) {}
                    //#endif

                    //#if MC >= 12105
                } else if (clickEvent.action() == ClickEvent.Action.OPEN_FILE) {
                    Util.getPlatform().openFile(((ClickEvent.OpenFile) clickEvent).file());
                } else if (clickEvent.action() == ClickEvent.Action.RUN_COMMAND) {
                    String string = StringUtil.filterText(((ClickEvent.RunCommand) clickEvent).command());
                    //#else
                    //$$ } else if (clickEvent.getAction() == ClickEvent.Action.OPEN_FILE) {
                    //$$                    Util.getPlatform().openFile(new File(clickEvent.getValue()));
                    //$$                } else if (clickEvent.getAction() == ClickEvent.Action.RUN_COMMAND) {
                    //$$                    String string = StringUtil.filterText(clickEvent.getValue());
                    //#endif
                    if (string.startsWith("/")) {
                        assert AlinLib.MINECRAFT.player != null;
                        try {
                            AlinLib.MINECRAFT.player.connection.sendCommand(string.substring(1));
                        } catch (Exception ignored) {
                            AlinLib.LOG.error("Not allowed to run command with signed argument from click event: '{}'", string);
                        }
                    } else {
                        AlinLib.LOG.error("Failed to run command without '/' prefix from click event: '{}'", string);
                    }
                    //#if MC >= 12105
                } else if (clickEvent.action() == ClickEvent.Action.COPY_TO_CLIPBOARD) {
                    AlinLib.MINECRAFT.keyboardHandler.setClipboard(((ClickEvent.CopyToClipboard) clickEvent).value());
                    //#else
                    //$$ } else if (clickEvent.getAction() == ClickEvent.Action.COPY_TO_CLIPBOARD) {
                    //$$    AlinLib.MINECRAFT.keyboardHandler.setClipboard(clickEvent.getValue());
                    //#endif
                } else {
                    AlinLib.LOG.error("Don't know how to handle {}", clickEvent);
                }

                return true;
            }

            return false;
        }
    }

    private double screenToChatX(int x, double d) {
        return d - (double) x;
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
        if (this.active && this.visible) {
            if (i == 257 || i == 32 || i == 335) {
                this.playDownSound(AlinLib.MINECRAFT.getSoundManager());
                this.onPress();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public interface OnPress {
        void onPress(TextBox button);
    }

    protected Component description;

    public TextBox setDescription(Component description) {
        this.description = description;
        return this;
    }

    public Component getDescription() {
        return this.description;
    }
}
