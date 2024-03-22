package ru.kelcuprum.alinlib.gui.toast;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import ru.kelcuprum.alinlib.AlinLib;

import org.jetbrains.annotations.NotNull;

public class AlinaToast implements Toast {
    private final ToastBuilder builder;

    protected AlinaToast(ToastBuilder builder) {
        this.builder = builder;
    }

    @Override
    public int width() {
        return Math.max(165, (builder.hasIcon() ? 38 : 16) + Math.max(AlinLib.MINECRAFT.font.width(this.builder.title), AlinLib.MINECRAFT.font.width(this.builder.message)));
    }

    @Override
    public @NotNull Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long l) {
        final float fc = 1.5F * 0.9F + 0.1F;
        final int colorBackground = (int) (255.0F * fc);

        guiGraphics.fill(0, 0, width(), height() - 1, colorBackground / 2 << 24);
        if (builder.type != ToastBuilder.Type.FLAT)
            guiGraphics.fill(0, height() - 1, width(), height(), builder.type.color);
        else guiGraphics.fill(0, height() - 1, width(), height(), colorBackground / 2 << 24);

        Font font = AlinLib.MINECRAFT.font;
        guiGraphics.drawString(font, builder.title, builder.hasIcon() ? 30 : 8, builder.hasIcon() ? 7 : 8, -1, false);
        guiGraphics.drawString(font, builder.message, builder.hasIcon() ? 30 : 8, builder.hasIcon() ? 18 : 19, -1, false);
        if (builder.hasIcon()) {
            if (builder.icon != null) guiGraphics.blit(builder.icon, 8, 8, 0.0F, 0.0F, 16, 16, 16, 16);
            else if (builder.itemIcon != null) guiGraphics.renderFakeItem(builder.itemIcon, 8, 8);
            else if (builder.playerIcon != null) PlayerFaceRenderer.draw(guiGraphics, builder.playerIcon, 8, 8, 16);
        }

        Visibility visibility = (double) l >= builder.displayTime * toastComponent.getNotificationDisplayTimeMultiplier() ? Visibility.HIDE : Visibility.SHOW;

        if (builder.visibilityVisitor != null) visibility = builder.visibilityVisitor.apply(visibility);

        return visibility;
    }
}