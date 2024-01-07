package ru.kelcuprum.alinlib.gui.toast;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

@Environment(EnvType.CLIENT)
public class AlinaToast implements Toast {
    private final ToastBuilder builder;

    protected AlinaToast(ToastBuilder builder) {
        this.builder = builder;
    }

    @Override
    public @NotNull Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long l) {
        final float fc = 1.5F * 0.9F + 0.1F;
        final int colorBackground = (int) (255.0F * fc);

        guiGraphics.fill(0, 0, width(), height() - 1, colorBackground / 2 << 24);
        if (builder.type != ToastBuilder.Type.FLAT)
            guiGraphics.fill(0, height() - 1, width(), height(), builder.type.color);

        Font font = toastComponent.getMinecraft().font;

        List<FormattedCharSequence> list = font.split(builder.message, 125);
        int i = 16777215;
        if (list.size() == 1) {
            guiGraphics.drawString(font, builder.title, builder.hasIcon() ? 30 : 8, builder.hasIcon() ? 7 : 8, i, false);
            guiGraphics.drawString(font, list.get(0), builder.hasIcon() ? 30 : 8, builder.hasIcon() ? 18 : 19, i, false);
        } else {
            float f = 300.0F;
            int k;
            if (l < 1000L) {
                k = Mth.floor(Mth.clamp((float) (1000L - l) / f, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
                guiGraphics.drawString(font, builder.title, builder.hasIcon() ? 30 : 8, builder.hasIcon() ? 11 : 8, i | k, false);
            } else {
                k = Mth.floor(Mth.clamp((float) (l - 1000L) / f, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
                int var10001 = list.size();
                int m = (this.height() / 2) - var10001 * 9 / 2;

                for (Iterator<FormattedCharSequence> var12 = list.iterator(); var12.hasNext(); m += 9) {
                    guiGraphics.drawString(font, var12.next(), builder.hasIcon() ? 30 : 8, m, i | k, false);
                }
            }
        }

        if (builder.hasIcon()) {
            if (builder.icon != null) {
                guiGraphics.blit(builder.icon, 8, 8, 0.0F, 0.0F, 16, 16, 16, 16);

            } else if (builder.itemIcon != null) {
                guiGraphics.renderFakeItem(builder.itemIcon, 8, 8);

            } else if (builder.playerIcon != null) {
                PlayerFaceRenderer.draw(guiGraphics, builder.playerIcon, 8, 8, 16);
            }
        }

        Visibility visibility = (double) l >= builder.displayTime * toastComponent.getNotificationDisplayTimeMultiplier() ? Visibility.HIDE : Visibility.SHOW;

        if (builder.visibilityVisitor != null) {
            visibility = builder.visibilityVisitor.apply(visibility);
        }

        return visibility;
    }
}