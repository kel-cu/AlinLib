package ru.kelcuprum.alinlib.gui.toast;

import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.GuiUtils;

import java.util.Objects;
import java.util.function.Function;

public class ToastBuilder {
    protected Component title = Component.empty();
    protected Component message = Component.empty();
    protected ItemStack itemIcon;
    protected ResourceLocation icon;
    protected Type type = Type.INFO;
    protected Number color;
    protected int displayTime = 5000;
    protected Function<Toast.Visibility, Toast.Visibility> visibilityVisitor;

    public ToastBuilder setTitle(Component component) {
        this.title = component;
        return this;
    }

    public ToastBuilder setMessage(Component component) {
        this.message = component;
        return this;
    }

    public ToastBuilder setIcon(Item item) {
        return setIcon(new ItemStack(item));
    }

    public ToastBuilder setIcon(ItemStack itemStack) {
        this.itemIcon = itemStack;
        return this;
    }

    public ToastBuilder setIcon(String namespace, String path) {
        return setIcon(GuiUtils.getResourceLocation(namespace, path));
    }

    public ToastBuilder setIcon(ResourceLocation icon) {
        this.icon = icon;
        return this;
    }

    public boolean hasIcon() {
        return this.itemIcon != null || this.icon != null;
    }

    public ToastBuilder setType(Type type) {
        this.type = type;
        return this;
    }

    public ToastBuilder setType(int type) {
        this.color = type;
        return this;
    }

    @SuppressWarnings("unused")
    public ToastBuilder setDisplayTime(int time) {
        this.displayTime = time;
        return this;
    }

    @SuppressWarnings("unused")
    public ToastBuilder setVisibilityVisitor(Function<Toast.Visibility, Toast.Visibility> visibilityVisitor) {
        this.visibilityVisitor = visibilityVisitor;
        return this;
    }

    public AlinaToast build() {
        Objects.requireNonNull(this.title, "title == null");

        return new AlinaToast(this);
    }

    @SuppressWarnings("UnusedReturnValue")
    public AlinaToast buildAndShow(ToastComponent toasts) {
        AlinaToast toast = build();
        toasts.addToast(toast);

        return toast;
    }

    public void show(ToastComponent toasts) {
        buildAndShow(toasts);
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
