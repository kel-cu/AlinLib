package ru.kelcuprum.alinlib.gui.components.text;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.CommonInputs;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
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

import static ru.kelcuprum.alinlib.gui.Colors.CPM_BLUE;
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
        if (builder.type == TEXT) {
            if (isDoesNotFit()) this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
            else if (builder.align == CENTER)
                guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, getMessage(), getX() + getWidth() / 2, getY() + (getHeight() - 8) / 2, -1);
            else
                guiGraphics.drawString(AlinLib.MINECRAFT.font, getMessage(), (builder.align == LEFT ? (getX() + (getHeight() - 8) / 2) : (getX() + getWidth() - (getHeight() - 8) / 2) - AlinLib.MINECRAFT.font.width(getMessage())), getY() + (getHeight() - 8) / 2, -1);
        } else renderMessageText(guiGraphics);
    }

    public void renderBackground(GuiGraphics guiGraphics) {
        if (builder.type != BLOCKQUOTE && builder.onPress != null)
            builder.getStyle().renderBackground$widget(guiGraphics, getX(), getY(), getWidth(), getHeight(), true, isHoveredOrFocused());
        else if (builder.type == BLOCKQUOTE) {
            guiGraphics.fill(this.getX(), this.getY(), this.getX() + 1, this.getY() + this.getHeight(), getBlockquoteColor()[0]);
            guiGraphics.fill(this.getX() + 1, this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), getBlockquoteColor()[1]);
        }
    }

    public int[] getBlockquoteColor() {
        return builder.color == null ? new int[]{AlinLib.bariumConfig.getNumber("BLOCKQUOTE.COLOR", CPM_BLUE).intValue(), AlinLib.bariumConfig.getNumber("BLOCKQUOTE.COLOR.BACKGROUND", CPM_BLUE - 0xE1000000).intValue()} : builder.color;
    }

    public void renderMessageText(GuiGraphics guiGraphics) {
        List<FormattedCharSequence> list = getArrayTexts(this.builder.type == BLOCKQUOTE && this.builder.align != CENTER ? 13 : 12);
        int l = 0;
        for (FormattedCharSequence text : list) {
            if (builder.align == CENTER)
                guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, text, getX() + (getWidth() / 2), getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight + 3) * l), -1);
            else
                guiGraphics.drawString(AlinLib.MINECRAFT.font, text, (builder.align == LEFT ? getX() + (this.builder.type == BLOCKQUOTE ? 7 : 6) : getX() + getWidth() - 6 - AlinLib.MINECRAFT.font.width(text)), getY() + 6 + ((AlinLib.MINECRAFT.font.lineHeight + 3) * l), -1);
            l++;
        }
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
    public void onClick(double d, double e) {
        this.onPress();
    }

    @Override
    public boolean mouseClicked(double d, double e, int i) {
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
            return super.mouseClicked(d, e, i);
        } else return super.mouseClicked(d, e, i);
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
    public boolean keyPressed(int i, int j, int k) {
        if (this.active && this.visible) {
            if (CommonInputs.selected(i)) {
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
