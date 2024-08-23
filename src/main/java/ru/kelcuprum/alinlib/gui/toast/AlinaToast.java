package ru.kelcuprum.alinlib.gui.toast;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
//#if MC >= 12102
import net.minecraft.client.gui.components.toasts.ToastManager;
//#elseif MC < 12102
//$$ import net.minecraft.client.gui.components.toasts.ToastComponent;
//#endif
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AlinaToast implements Toast {
    private final ToastBuilder builder;

    protected AlinaToast(ToastBuilder builder) {
        this.builder = builder;
    }

    @Override
    public int width() {
        return Math.min(AlinLib.MINECRAFT.getWindow().getGuiScaledWidth()/2 , Math.max(165, (builder.hasIcon() ? 38 : 16) + Math.max(AlinLib.MINECRAFT.font.width(this.builder.title), AlinLib.MINECRAFT.font.width(this.builder.message))));
    }
    public int textWidth(){
        int toastSize = width();
        toastSize -= builder.hasIcon() ? 38 : 16;
        return toastSize;
    }
    @Override
    public int height(){
        List<FormattedCharSequence> texts = new ObjectArrayList<>();
        texts.addAll(AlinLib.MINECRAFT.font.split(builder.title, textWidth()));
        texts.addAll(AlinLib.MINECRAFT.font.split(builder.message, textWidth()));
        return texts.size() == 2 ? 32 : 12+(11*(texts.size()));
    }

    long l = 0;
    @Override
    //#if MC >= 12102
    public void render(GuiGraphics guiGraphics, Font font, long lon) {
        l = lon;
        //#elseif MC < 12102
        //$$ public @NotNull Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long lon) {
        //#endif
        guiGraphics.fill(0, 0, width(), height() - 1, 0xB3000000);
        if(builder.color != null) guiGraphics.fill(0, height() - 1, width(), height(), builder.color.intValue());
        else if (builder.type != ToastBuilder.Type.FLAT) {
            guiGraphics.fill(0, height() - 1, width(), height(), 0xB3000000);
            if(AlinLib.bariumConfig.getBoolean("TOAST.TIMELINE")) guiGraphics.fill(0, height() - 1, (int) (width()*(lon/(double)builder.displayTime)), height(), builder.type.color);
            else guiGraphics.fill(0, height() - 1, width(), height(), builder.type.color);
        } else guiGraphics.fill(0, height() - 1, width(), height(), 0xB3000000);

        List<FormattedCharSequence> texts = new ObjectArrayList<>();
        texts.addAll(AlinLib.MINECRAFT.font.split(builder.title, textWidth()));
        texts.addAll(AlinLib.MINECRAFT.font.split(builder.message, textWidth()));
        int y = builder.hasIcon() ? 7 : 8;
        for(FormattedCharSequence text : texts){
            guiGraphics.drawString(AlinLib.MINECRAFT.font, text, builder.hasIcon() ? 30 : 8, y, -1, false);
            y+=11;
        }
        if (builder.hasIcon()) {
            if (builder.icon != null) guiGraphics.blit(
                    //#if MC >= 12102
                    RenderType::guiTextured,
                    //#endif
                    builder.icon, 8, 8, 0.0F, 0.0F, 16, 16, 16, 16);
            else if (builder.itemIcon != null) guiGraphics.renderFakeItem(builder.itemIcon, 8, 8);
        }
        //#if MC < 12102
        //$$ Visibility visibility = (double) l >= builder.displayTime * toastComponent.getNotificationDisplayTimeMultiplier() ? Visibility.HIDE : Visibility.SHOW;
        //$$ if (builder.visibilityVisitor != null) visibility = builder.visibilityVisitor.apply(visibility);
        //$$ return visibility;
        //#endif
    }

    //#if MC >= 12102
    Visibility visibility = Visibility.SHOW;
        @Override
    public void update(ToastManager toastManager, long l) {
            visibility = (double) l >= builder.displayTime * toastManager.getNotificationDisplayTimeMultiplier() ? Visibility.HIDE : Visibility.SHOW;
            if (builder.visibilityVisitor != null) visibility = builder.visibilityVisitor.apply(visibility);
    }

    @Override
    public @NotNull Visibility getWantedVisibility() {
        return visibility;
    }
    //#endif
}