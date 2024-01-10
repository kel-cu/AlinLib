package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.toast.AlinaToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ConfigScreenBuilder {
    protected Component title;
    protected InterfaceUtils.DesignType type;
    protected List<AbstractWidget> panelWidgets = new ArrayList<>();
    protected List<AbstractWidget> widgets = new ArrayList<>();
    protected Screen parent;

    public ConfigScreenBuilder(Screen parent) {
        this(parent, Component.translatable("alinlib.change_title_pls"));
    }
    public ConfigScreenBuilder(Screen parent, Component title) {
        this(parent, title, InterfaceUtils.DesignType.ALINA);
    }
    public ConfigScreenBuilder(Screen parent, Component title, InterfaceUtils.DesignType type){
        this.parent = parent;
        this.title = title;
        this.type = type;
    }
    //
    public ConfigScreenBuilder setTitle(String string){
        setTitle(Component.literal(string));
        return this;
    }
    public ConfigScreenBuilder setTitle(Component component) {
        this.title = component;
        return this;
    }
    //
    public ConfigScreenBuilder setType(InterfaceUtils.DesignType type) {
        this.type = type;
        return this;
    }
    //
    public ConfigScreenBuilder addPanelWidget(AbstractWidget widget){
        widget.setWidth(110);
        this.panelWidgets.add(widget);
        return this;
    }

    public ConfigScreenBuilder addWidget(AbstractWidget widget){
        if(widget instanceof CategoryBox){
            this.widgets.add(widget);
            this.widgets.addAll(((CategoryBox) widget).getValues());
        } else this.widgets.add(widget);
        return this;
    }
    //
    public ConfigScreenBuilder setParent(Screen parent){
        this.parent = parent;
        return this;
    }

    public AbstractConfigScreen build() {
        Objects.requireNonNull(this.title, "title == null");
        return new AbstractConfigScreen(this);
    }
}
