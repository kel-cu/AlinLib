package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.AlinLibTest;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.ImageWidget;
import ru.kelcuprum.alinlib.gui.components.builder.button.*;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.slider.*;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

public class AlinaDemoScreen {
    private static final ResourceLocation icon = new ResourceLocation(AlinLibTest.MODID, "textures/gui/widget/test/normal.png");
    private static final String[] list = {
        "Welcome", "to", "The", "Amazing", "Digital", "Interface", "™"
    };
    public Screen build(Screen parent){
        ConfigScreenBuilder screen = new ConfigScreenBuilder(parent)
                .setTitle(Component.translatable("alinlibtest.name"))
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #0"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #1"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #2"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #3"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #4"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #5"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #6"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #7"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #8"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Button #9"), (s) -> AlinLib.MINECRAFT.setScreen(this.build(parent))).build())

                .addWidget(new TextBox(Component.literal("TextBox"), true))
                .addWidget(new CategoryBox(Component.literal("CategoryBox"))
                        .addValue(new ImageWidget(0,0, InterfaceUtils.DEFAULT_WIDTH(), InterfaceUtils.DEFAULT_WIDTH(), icon, Component.empty())))
                .addWidget(new ButtonBuilder(Component.literal("ButtonBuilder"), (s) -> new ToastBuilder().setIcon(Items.SALMON).setTitle(Component.literal("AlinLib")).setMessage(Component.literal("Good morning, Mr. Sunfish!")).show(AlinLib.MINECRAFT.getToasts())).build())
                .addWidget(new ButtonBooleanBuilder(Component.literal("ButtonBooleanBuilder"), true).build())
                .addWidget(new ButtonSpriteBuilder(icon).setSize(20, 20).build())
                .addWidget(new EditBoxBuilder(Component.literal("EditBoxBuilder")).build())
                .addWidget(new SelectorBuilder(Component.literal("SelectorBuilder")).setList(list).build())
                .addWidget(new SliderDoubleBuilder(Component.literal("SliderDoubleBuilder")).build())
                .addWidget(new SliderFloatBuilder(Component.literal("SliderFloatBuilder")).build())
                .addWidget(new SliderIntegerBuilder(Component.literal("SliderIntegerBuilder")).build())
                .addWidget(new SliderPercentBuilder(Component.literal("SliderPercentBuilder")).build());
        return screen.build();
    }
}
