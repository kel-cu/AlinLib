package ru.kelcuprum.alinlib.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
//#if MC >= 12109
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
//#endif
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;

import java.util.List;

public class DialogScreen extends Screen {
    public final Screen screen;
    public final String[] dialog;
    public final Runnable runnable;
    private final int timeShow;
    private long startTime = System.currentTimeMillis();
    private long startTimeText;

    public DialogScreen(Screen screen, Component[] dialog, Runnable runnable) {
        this(screen, dialog, runnable, 1000);
    }
    public DialogScreen(Screen screen, Component[] dialog, Runnable runnable, int timeShot) {
        super(Component.empty());
        this.screen = screen;
        String[] convertedDialog = new String[dialog.length];
        int i = 0;
        for(Component component : dialog){
            convertedDialog[i] = component.getString(); i++;
        }
        this.dialog = convertedDialog.length == 0 ? new String[]{"Истинное счастие невозможно без одиночества", "- Антон Чехов"} : convertedDialog;
        this.runnable = runnable;
        this.timeShow = timeShot;
        startTimeText = startTime + timeShow;
    }
    public DialogScreen(Screen screen, String[] dialog, Runnable runnable) {
        this(screen, dialog, runnable, 1000);
    }
    public DialogScreen(Screen screen, String[] dialog, Runnable runnable, int timeShot) {
        super(Component.empty());
        this.screen = screen;
        this.dialog = dialog.length == 0 ? new String[]{"Истинное счастие невозможно без одиночества", "- Антон Чехов"} : dialog;
        this.runnable = runnable;
        this.timeShow = timeShot;
        startTimeText = startTime + timeShow;
    }

    int pos = 0;
    boolean isClose = false;
    boolean isRevertText = false;

    @Override
    //#if MC >= 12002
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        super.renderBackground(guiGraphics, i, j, f);
        //#elseif MC < 12002
        //$$ public void renderBackground(GuiGraphics guiGraphics) {
        //$$         super.renderBackground(guiGraphics);
        //#endif
        long cur = System.currentTimeMillis();
        int back = (int) (127.5F * (Math.clamp((double) (cur - startTime) / timeShow, 0.0, 1.0))) << 24;
        if (isClose) back = (int) (127.5F - (127.5F * (Math.clamp((double) (cur - startTime) / timeShow, 0.0, 1.0)))) << 24;
        guiGraphics.fillGradient(0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight(), back, back);
        if (startTimeText+100 < cur && !isRevertText ) {
            int backT = replaceAlpha(0xFFFFFFFF, (int) (255 * Math.clamp((double) (cur - startTimeText) / timeShow, 0.0, 1.0)));
            List<FormattedCharSequence> texts = minecraft.font.split(FormattedText.of(dialog[pos]), (int) (width * 0.75));
            int heightText = (minecraft.font.lineHeight * texts.size()) + (3 * (texts.size()-1));
            int y = height / 2 - heightText / 2;
            for(FormattedCharSequence text : texts) {
                guiGraphics.drawCenteredString(minecraft.font, text, width / 2, y, backT);
                y+=minecraft.font.lineHeight+3;
            }
        }
    }

    public static int replaceAlpha(int i, int j) {
        return i & 16777215 | j << 24;
    }


    @Override
    public boolean keyPressed(
            //#if MC >= 12109
            KeyEvent keyEvent
            //#else
            //$$ int i, int j, int k
            //#endif
    ) {

        //#if MC >= 12109
        int i = keyEvent.key();
        //#endif

        if(i == GLFW.GLFW_KEY_SPACE || i == GLFW.GLFW_KEY_Z){
            changePosition();
            return false;
        } else return super.keyPressed(
                //#if MC >= 12109
                keyEvent
                //#else
                //$$ i, j, k
                //#endif
                );
    }

    @Override
    public boolean mouseClicked(
    //#if MC >= 12109
    MouseButtonEvent mouseButtonEvent, boolean bl
    //#else
    //$$ double d, double e, int i
    //#endif
    ) {
        changePosition();
        return false;
    }

    public void changePosition(){
        if (startTime + 1000 < System.currentTimeMillis()) {
            if (pos + 1 == dialog.length) {
                isClose = isRevertText = true;
                startTimeText = startTime = System.currentTimeMillis();
            } else pos++;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (System.currentTimeMillis() - startTime >= timeShow && isClose) onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public void onClose() {
        if (isClose) {
            if (runnable != null) runnable.run();
            else AlinLib.MINECRAFT.setScreen(screen);
        }
    }
}