package ru.kelcuprum.alinlib.gui.toast;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class AlinaFlatToast implements Toast {
    public static final int DISPLAY_TIME = 5000;
    private final Component title;
    private final Component message;
    private final Item itemIcon;
    private final ResourceLocation icon;
    private boolean playedSound;

    /**
     * Alina not flat. And the design is flat. Heh.
     */
    public AlinaFlatToast(Component title, Component message) {
        this(title, message, Items.CRAFTING_TABLE, null);
    }

    /**
     * Alina not flat. And the design is flat. Heh.
     */
    public AlinaFlatToast(Component title, Component message, boolean isFail) {
        this(title, message, isFail ? Items.BARRIER : Items.CRAFTING_TABLE, null);
    }


    /**
     * Alina not flat. And the design is flat. Heh.
     */
    public AlinaFlatToast(Component title, Component message, Item itemIcon) {
        this(title, message, itemIcon, null);
    }
    /**
     * Alina not flat. And the design is flat. Heh.
     */
    public AlinaFlatToast(Component title, Component message, ResourceLocation icon) {
        this(title, message, null, icon);
    }

    /**
     * Alina not flat. And the design is flat. Heh.
     */
    public AlinaFlatToast(Component title, Component message, Item itemIcon, ResourceLocation icon){
        this.title = title;
        this.message = message;
        this.itemIcon = itemIcon;
        this.icon = icon;
    }

    public Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long l) {
        final float fc = 1.5F * 0.9F + 0.1F;
        final int colorBackground = (int) (255.0F * fc);

        guiGraphics.fill(0, 0, this.width(), this.height(), colorBackground / 2 << 24);

        List<FormattedCharSequence> list = toastComponent.getMinecraft().font.split(this.message, 125);
        int i = 16777215;
        if (list.size() == 1) {
            guiGraphics.drawString(toastComponent.getMinecraft().font, title, 30, 7, i, false);
            guiGraphics.drawString(toastComponent.getMinecraft().font, list.get(0), 30, 18, i, false);
        } else {
            float f = 300.0F;
            int k;
            if (l < 1000L) {
                k = Mth.floor(Mth.clamp((float) (1000L - l) / f, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
                guiGraphics.drawString(toastComponent.getMinecraft().font, title, 30, 11, i | k, false);
            } else {
                k = Mth.floor(Mth.clamp((float) (l - 1000L) / f, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
                int var10000 = this.height() / 2;
                int var10001 = list.size();
                Objects.requireNonNull(toastComponent.getMinecraft().font);
                int m = var10000 - var10001 * 9 / 2;

                for (Iterator var12 = list.iterator(); var12.hasNext(); m += 9) {
                    FormattedCharSequence formattedCharSequence = (FormattedCharSequence) var12.next();
                    guiGraphics.drawString(toastComponent.getMinecraft().font, formattedCharSequence, 30, m, i | k, false);
                    Objects.requireNonNull(toastComponent.getMinecraft().font);
                }
            }
        }

        if (!this.playedSound && l > 0L) {
            this.playedSound = true;
        }
        if(this.icon != null) guiGraphics.blit(this.icon, 8, 8, 0.0F, 0.0F, 16, 16, 16, 16);
        else if(this.itemIcon != null) guiGraphics.renderFakeItem(new ItemStack(this.itemIcon), 8, 8);
        return (double) l >= DISPLAY_TIME * toastComponent.getNotificationDisplayTimeMultiplier() ? Visibility.HIDE : Visibility.SHOW;
    }
}
