package ru.kelcuprum.alinlib.gui.components.editbox.vanilla;

import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class VanillaEditBoxString extends AbstractWidget implements Renderable {
    private static final WidgetSprites SPRITES = new WidgetSprites(new ResourceLocation("widget/text_field"), new ResourceLocation("widget/text_field_highlighted"));
    public static final int BACKWARDS = -1;
    public static final int FORWARDS = 1;
    private static final int CURSOR_INSERT_WIDTH = 1;
    private static final int CURSOR_INSERT_COLOR = -3092272;
    private static final String CURSOR_APPEND_CHARACTER = "_";
    public static final int DEFAULT_TEXT_COLOR = 14737632;
    private static final int CURSOR_BLINK_INTERVAL_MS = 300;
    private final Font font;
    private String value;
    private int maxLength;
    private boolean bordered;
    private boolean canLoseFocus;
    private boolean isEditable;
    private int displayPos;
    private int cursorPos;
    private int highlightPos;
    private int textColor;
    private int textColorUneditable;
    @Nullable
    private String suggestion;
    @Nullable
    private Consumer<String> responder;
    private Predicate<String> filter;
    private BiFunction<String, Integer, FormattedCharSequence> formatter;
    @Nullable
    private Component hint;
    private long focusedTime;

    public VanillaEditBoxString(Font font, int i, int j, Component component) {
        this(font, 0, 0, i, j, component);
    }

    public VanillaEditBoxString(Font font, int i, int j, int k, int l, Component component) {
        this(font, i, j, k, l, (EditBox)null, component);
    }
    public VanillaEditBoxString(int i, int j, int k, int l, Component component) {
        this(Minecraft.getInstance().font, i, j, k, l, (EditBox)null, component);
    }

    public VanillaEditBoxString(Font font, int i, int j, int k, int l, @Nullable EditBox editBox, Component component) {
        super(i, j, k, l, component);
        this.value = "";
        this.maxLength = Integer.MAX_VALUE;
        this.bordered = true;
        this.canLoseFocus = true;
        this.isEditable = true;
        this.textColor = 14737632;
        this.textColorUneditable = 7368816;
        this.filter = Objects::nonNull;
        this.formatter = (string, integer) -> {
            return FormattedCharSequence.forward(string, Style.EMPTY);
        };
        this.focusedTime = Util.getMillis();
        this.font = font;
        if (editBox != null) {
            this.setValue(editBox.getValue());
        }

    }
    //


    public void setActive(boolean active){
        this.active = active;
    }

    public void setYPos(int i) {
        this.setY(i);
    }

    public void setXPos(int i) {
        this.setX(i);
    }


    public void setPos(int i, int j) {
        this.setPosition(i, j);
    }
    public void setContent(String content){
        this.setValue(content);
    }
    public void setResponse(Consumer<String> consumer){
        this.setResponder(consumer);
    }
    //

    public void setResponder(Consumer<String> consumer) {
        this.responder = consumer;
    }

    public void setFormatter(BiFunction<String, Integer, FormattedCharSequence> biFunction) {
        this.formatter = biFunction;
    }

    protected MutableComponent createNarrationMessage() {
        Component component = this.getMessage();
        return Component.translatable("gui.narrate.editBox", new Object[]{component, this.value});
    }

    public void setValue(String string) {
        if (this.filter.test(string)) {
            if (string.length() > this.maxLength) {
                this.value = string.substring(0, this.maxLength);
            } else {
                this.value = string;
            }

            this.moveCursorToEnd(false);
            this.setHighlightPos(this.cursorPos);
            this.onValueChange(string);
        }
    }

    public String getValue() {
        return this.value;
    }

    public String getHighlighted() {
        int i = Math.min(this.cursorPos, this.highlightPos);
        int j = Math.max(this.cursorPos, this.highlightPos);
        return this.value.substring(i, j);
    }

    public void setFilter(Predicate<String> predicate) {
        this.filter = predicate;
    }

    public void insertText(String string) {
        int i = Math.min(this.cursorPos, this.highlightPos);
        int j = Math.max(this.cursorPos, this.highlightPos);
        int k = this.maxLength - this.value.length() - (i - j);
        String string2 = SharedConstants.filterText(string);
        int l = string2.length();
        if (k < l) {
            string2 = string2.substring(0, k);
            l = k;
        }

        String string3 = (new StringBuilder(this.value)).replace(i, j, string2).toString();
        if (this.filter.test(string3)) {
            this.value = string3;
            this.setCursorPosition(i + l);
            this.setHighlightPos(this.cursorPos);
            this.onValueChange(this.value);
        }
    }

    private void onValueChange(String string) {
        if (this.responder != null) {
            this.responder.accept(string);
        }

    }

    private void deleteText(int i) {
        if (Screen.hasControlDown()) {
            this.deleteWords(i);
        } else {
            this.deleteChars(i);
        }

    }

    public void deleteWords(int i) {
        if (!this.value.isEmpty()) {
            if (this.highlightPos != this.cursorPos) {
                this.insertText("");
            } else {
                this.deleteChars(this.getWordPosition(i) - this.cursorPos);
            }
        }
    }

    public void deleteChars(int i) {
        if (!this.value.isEmpty()) {
            if (this.highlightPos != this.cursorPos) {
                this.insertText("");
            } else {
                int j = this.getCursorPos(i);
                int k = Math.min(j, this.cursorPos);
                int l = Math.max(j, this.cursorPos);
                if (k != l) {
                    String string = (new StringBuilder(this.value)).delete(k, l).toString();
                    if (this.filter.test(string)) {
                        this.value = string;
                        this.moveCursorTo(k, false);
                    }
                }
            }
        }
    }

    public int getWordPosition(int i) {
        return this.getWordPosition(i, this.getCursorPosition());
    }

    private int getWordPosition(int i, int j) {
        return this.getWordPosition(i, j, true);
    }

    private int getWordPosition(int i, int j, boolean bl) {
        int k = j;
        boolean bl2 = i < 0;
        int l = Math.abs(i);

        for(int m = 0; m < l; ++m) {
            if (!bl2) {
                int n = this.value.length();
                k = this.value.indexOf(32, k);
                if (k == -1) {
                    k = n;
                } else {
                    while(bl && k < n && this.value.charAt(k) == ' ') {
                        ++k;
                    }
                }
            } else {
                while(bl && k > 0 && this.value.charAt(k - 1) == ' ') {
                    --k;
                }

                while(k > 0 && this.value.charAt(k - 1) != ' ') {
                    --k;
                }
            }
        }

        return k;
    }

    public void moveCursor(int i, boolean bl) {
        this.moveCursorTo(this.getCursorPos(i), bl);
    }

    private int getCursorPos(int i) {
        return Util.offsetByCodepoints(this.value, this.cursorPos, i);
    }

    public void moveCursorTo(int i, boolean bl) {
        this.setCursorPosition(i);
        if (!bl) {
            this.setHighlightPos(this.cursorPos);
        }

        this.onValueChange(this.value);
    }

    public void setCursorPosition(int i) {
        this.cursorPos = Mth.clamp(i, 0, this.value.length());
        this.scrollTo(this.cursorPos);
    }

    public void moveCursorToStart(boolean bl) {
        this.moveCursorTo(0, bl);
    }

    public void moveCursorToEnd(boolean bl) {
        this.moveCursorTo(this.value.length(), bl);
    }

    public boolean keyPressed(int i, int j, int k) {
        if (!this.canConsumeInput()) {
            return false;
        } else if (Screen.isSelectAll(i)) {
            this.moveCursorToEnd(false);
            this.setHighlightPos(0);
            return true;
        } else if (Screen.isCopy(i)) {
            Minecraft.getInstance().keyboardHandler.setClipboard(this.getHighlighted());
            return true;
        } else if (Screen.isPaste(i)) {
            if (this.isEditable) {
                this.insertText(Minecraft.getInstance().keyboardHandler.getClipboard());
            }

            return true;
        } else if (Screen.isCut(i)) {
            Minecraft.getInstance().keyboardHandler.setClipboard(this.getHighlighted());
            if (this.isEditable) {
                this.insertText("");
            }

            return true;
        } else {
            switch (i) {
                case 259:
                    if (this.isEditable) {
                        this.deleteText(-1);
                    }

                    return true;
                case 260:
                case 264:
                case 265:
                case 266:
                case 267:
                default:
                    return false;
                case 261:
                    if (this.isEditable) {
                        this.deleteText(1);
                    }

                    return true;
                case 262:
                    if (Screen.hasControlDown()) {
                        this.moveCursorTo(this.getWordPosition(1), Screen.hasShiftDown());
                    } else {
                        this.moveCursor(1, Screen.hasShiftDown());
                    }

                    return true;
                case 263:
                    if (Screen.hasControlDown()) {
                        this.moveCursorTo(this.getWordPosition(-1), Screen.hasShiftDown());
                    } else {
                        this.moveCursor(-1, Screen.hasShiftDown());
                    }

                    return true;
                case 268:
                    this.moveCursorToStart(Screen.hasShiftDown());
                    return true;
                case 269:
                    this.moveCursorToEnd(Screen.hasShiftDown());
                    return true;
            }
        }
    }

    public boolean canConsumeInput() {
        return this.isVisible() && this.isFocused() && this.isEditable();
    }

    public boolean charTyped(char c, int i) {
        if (!this.canConsumeInput()) {
            return false;
        } else if (SharedConstants.isAllowedChatCharacter(c)) {
            if (this.isEditable) {
                this.insertText(Character.toString(c));
            }

            return true;
        } else {
            return false;
        }
    }

    public void onClick(double d, double e) {
        int i = Mth.floor(d) - this.getX();
        if (this.bordered) {
            i -= 4;
        }

        String string = this.font.plainSubstrByWidth(this.value.substring(this.displayPos), this.getInnerWidth());
        this.moveCursorTo(this.font.plainSubstrByWidth(string, i).length() + this.displayPos, Screen.hasShiftDown());
    }

    public void playDownSound(SoundManager soundManager) {
    }

    private int getPositionContent(String content){
        int pos = getX() + getWidth()-Minecraft.getInstance().font.width(content)-((getHeight() - 8) / 2);
        if(getX() + Minecraft.getInstance().font.width(getMessage().getString()+":") + ((getHeight() - 8) / 2)*2 > pos) pos = getX() + this.font.width(getMessage().getString()+":") + ((getHeight() - 8) / 2)*2;
        return pos;
    }

    public void renderWidget(GuiGraphics guiGraphics, int i, int j, float tick) {
        if (this.isVisible()) {
            if (this.isBordered()) {
                ResourceLocation resourceLocation = SPRITES.get(this.isActive(), this.isFocused());
                guiGraphics.blitSprite(resourceLocation, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            }

            if(isFocused()){
                int k = this.isEditable ? this.textColor : this.textColorUneditable;
                int l = this.cursorPos - this.displayPos;
                String string = this.font.plainSubstrByWidth(this.value.substring(this.displayPos), this.getInnerWidth());
                boolean bl = l >= 0 && l <= string.length();
                boolean bl2 = this.isFocused() && (Util.getMillis() - this.focusedTime) / 300L % 2L == 0L && bl;
                int m = this.bordered ? this.getX() + 4 : this.getX();
                int n = this.bordered ? this.getY() + (this.height - 8) / 2 : this.getY();
                int o = m;
                int p = Mth.clamp(this.highlightPos - this.displayPos, 0, string.length());
                if (!string.isEmpty()) {
                    String string2 = bl ? string.substring(0, l) : string;
                    o = guiGraphics.drawString(this.font, (FormattedCharSequence)this.formatter.apply(string2, this.displayPos), m, n, k);
                }

                boolean bl3 = this.cursorPos < this.value.length() || this.value.length() >= this.getMaxLength();
                int q = o;
                if (!bl) {
                    q = l > 0 ? m + this.width : m;
                } else if (bl3) {
                    q = o - 1;
                    --o;
                }

                if (!string.isEmpty() && bl && l < string.length()) {
                    guiGraphics.drawString(this.font, (FormattedCharSequence)this.formatter.apply(string.substring(l), this.cursorPos), o, n, k);
                }

                if (this.hint != null && string.isEmpty() && !this.isFocused()) {
                    guiGraphics.drawString(this.font, this.hint, o, n, k);
                }

                if (!bl3 && this.suggestion != null) {
                    guiGraphics.drawString(this.font, this.suggestion, q - 1, n, -8355712);
                }

                int var10003;
                int var10004;
                int var10005;
                if (bl2) {
                    if (bl3) {
                        RenderType var10001 = RenderType.guiOverlay();
                        var10003 = n - 1;
                        var10004 = q + 1;
                        var10005 = n + 1;
                        Objects.requireNonNull(this.font);
                        guiGraphics.fill(var10001, q, var10003, var10004, var10005 + 9, -3092272);
                    } else {
                        guiGraphics.drawString(this.font, "_", q, n, k);
                    }
                }

                if (p != l) {
                    int r = m + this.font.width(string.substring(0, p));
                    var10003 = n - 1;
                    var10004 = r - 1;
                    var10005 = n + 1;
                    Objects.requireNonNull(this.font);
                    this.renderHighlight(guiGraphics, q, var10003, var10004, var10005 + 9);
                }
            } else {
                if (isDoesNotFit()) {
                    this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
                } else {
                    guiGraphics.drawString(Minecraft.getInstance().font, getMessage().getString() + ":", getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
                    FormattedText volume1 = font.substrByWidth(FormattedText.of(value), getX() + getWidth() - (getPositionContent(value)));
                    guiGraphics.drawString(this.font, volume1.getString(), getPositionContent(volume1.getString()), getY() + (getHeight() - 8) / 2, 0xffffff);
                }
            }
        }
    }
    public boolean isDoesNotFit(){
        int size = Minecraft.getInstance().font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return size > getWidth();
    }

    private void renderHighlight(GuiGraphics guiGraphics, int i, int j, int k, int l) {
        int m;
        if (i < k) {
            m = i;
            i = k;
            k = m;
        }

        if (j < l) {
            m = j;
            j = l;
            l = m;
        }

        if (k > this.getX() + this.width) {
            k = this.getX() + this.width;
        }

        if (i > this.getX() + this.width) {
            i = this.getX() + this.width;
        }

        guiGraphics.fill(RenderType.guiTextHighlight(), i, j, k, l, -16776961);
    }

    public void setMaxLength(int i) {
        this.maxLength = i;
        if (this.value.length() > i) {
            this.value = this.value.substring(0, i);
            this.onValueChange(this.value);
        }

    }

    private int getMaxLength() {
        return this.maxLength;
    }

    public int getCursorPosition() {
        return this.cursorPos;
    }

    public boolean isBordered() {
        return this.bordered;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public void setTextColorUneditable(int i) {
        this.textColorUneditable = i;
    }

    @Nullable
    public ComponentPath nextFocusPath(FocusNavigationEvent focusNavigationEvent) {
        return this.visible && this.isEditable ? super.nextFocusPath(focusNavigationEvent) : null;
    }

    public boolean isMouseOver(double d, double e) {
        return this.visible && d >= (double)this.getX() && d < (double)(this.getX() + this.width) && e >= (double)this.getY() && e < (double)(this.getY() + this.height);
    }

    public void setFocused(boolean bl) {
        if (this.canLoseFocus || bl) {
            super.setFocused(bl);
            if (bl) {
                this.focusedTime = Util.getMillis();
            }

        }
    }

    private boolean isEditable() {
        return this.isEditable;
    }

    public void setEditable(boolean bl) {
        this.isEditable = bl;
    }

    public int getInnerWidth() {
        return this.isBordered() ? this.width - 8 : this.width;
    }

    public void setHighlightPos(int i) {
        this.highlightPos = Mth.clamp(i, 0, this.value.length());
        this.scrollTo(this.highlightPos);
    }

    private void scrollTo(int i) {
        if (this.font != null) {
            this.displayPos = Math.min(this.displayPos, this.value.length());
            int j = this.getInnerWidth();
            String string = this.font.plainSubstrByWidth(this.value.substring(this.displayPos), j);
            int k = string.length() + this.displayPos;
            if (i == this.displayPos) {
                this.displayPos -= this.font.plainSubstrByWidth(this.value, j, true).length();
            }

            if (i > k) {
                this.displayPos += i - k;
            } else if (i <= this.displayPos) {
                this.displayPos -= this.displayPos - i;
            }

            this.displayPos = Mth.clamp(this.displayPos, 0, this.value.length());
        }
    }

    public void setCanLoseFocus(boolean bl) {
        this.canLoseFocus = bl;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean bl) {
        this.visible = bl;
    }

    public void setSuggestion(@Nullable String string) {
        this.suggestion = string;
    }

    public int getScreenX(int i) {
        return i > this.value.length() ? this.getX() : this.getX() + this.font.width(this.value.substring(0, i));
    }

    public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, this.createNarrationMessage());
    }

    public void setHint(Component component) {
        this.hint = component;
    }
}
