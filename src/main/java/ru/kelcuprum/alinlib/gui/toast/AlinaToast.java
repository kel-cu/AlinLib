package ru.kelcuprum.alinlib.gui.toast;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.kelcuprum.alinlib.Colors;

import java.util.Iterator;
import java.util.List;

@Environment(EnvType.CLIENT)
public class AlinaToast implements Toast {
    public static final int DISPLAY_TIME = 5000;
    private final Component title;
    private final Component message;
    private final Item itemIcon;
    private final ResourceLocation icon;
    private final Type type;

    // ITEM
    public AlinaToast(Component title, Component message, Item itemIcon) {
        this(title, message, itemIcon, null, Type.INFO);
    }

    // ITEM
    public AlinaToast(Component title, Component message, Item itemIcon, Type type) {
        this(title, message, itemIcon, null, type);
    }

    // RESOURCE LOCATION
    public AlinaToast(Component title, Component message, ResourceLocation icon) {
        this(title, message, icon, Type.INFO);
    }

    // RESOURCE LOCATION
    public AlinaToast(Component title, Component message, ResourceLocation icon, Type type) {
        this(title, message, null, icon, type);
    }

    // GLOBAL
    public AlinaToast(Component title, Component message) {
        this(title, message, Type.INFO);
    }

    // GLOBAL
    public AlinaToast(Component title, Component message, Type type) {
        this(title, message, null, null, type);
    }

    // GLOBAL
    public AlinaToast(Component title, Component message, Item itemIcon, ResourceLocation icon, Type type) {
        this.title = title;
        this.message = message;
        this.itemIcon = itemIcon;
        this.icon = icon;
        this.type = type;
    }

    @Override
    public @NotNull Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long l) {
        final float fc = 1.5F * 0.9F + 0.1F;
        final int colorBackground = (int) (255.0F * fc);

        boolean missingIcon = this.itemIcon == null && this.icon == null;

        guiGraphics.fill(0, 0, this.width(), this.height()-1, colorBackground / 2 << 24);
        if (this.type != Type.FLAT)
            guiGraphics.fill(0, this.height() - 1, this.width(), this.height(), this.type.color);

        Font font = toastComponent.getMinecraft().font;

        List<FormattedCharSequence> list = font.split(this.message, 125);
        int i = 16777215;
        if (list.size() == 1) {
            guiGraphics.drawString(font, title, missingIcon ? 8 : 30, missingIcon ? 8 : 7, i, false);
            guiGraphics.drawString(font, list.get(0), missingIcon ? 8 : 30, missingIcon ? 19 : 18, i, false);
        } else {
            float f = 300.0F;
            int k;
            if (l < 1000L) {
                k = Mth.floor(Mth.clamp((float) (1000L - l) / f, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
                guiGraphics.drawString(font, title, missingIcon ? 8 : 30, missingIcon ? 8 : 11, i | k, false);
            } else {
                k = Mth.floor(Mth.clamp((float) (l - 1000L) / f, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
                int var10001 = list.size();
                int m = (this.height() / 2) - var10001 * 9 / 2;

                for (Iterator<FormattedCharSequence> var12 = list.iterator(); var12.hasNext(); m += 9) {
                    guiGraphics.drawString(font, var12.next(), missingIcon ? 8 : 30, m, i | k, false);
                }
            }
        }

        if (this.icon != null) {
            guiGraphics.blit(this.icon, 8, 8, 0.0F, 0.0F, 16, 16, 16, 16);
        } else if(this.itemIcon != null) {
            guiGraphics.renderFakeItem(new ItemStack(this.itemIcon), 8, 8);
        }

        return (double) l >= DISPLAY_TIME * toastComponent.getNotificationDisplayTimeMultiplier() ? Visibility.HIDE : Visibility.SHOW;
    }

    public enum Type {
        FLAT(null),
        INFO(Colors.SEADRIVE),
        WARN(Colors.CLOWNFISH),
        DEBUG(Colors.TETRA),
        ERROR(Colors.GROUPIE);

        public final Integer color;

        Type(Integer color) {
            this.color = color;
        }
    }
}