package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.Util;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.AlinLibTest;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.ImageWidget;
import ru.kelcuprum.alinlib.gui.components.buttons.base.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxConfigString;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxLocalization;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorIntegerButton;
import ru.kelcuprum.alinlib.gui.components.selector.base.SelectorButton;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderConfigPercent;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderPercent;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxColor;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.AlinLibTest.MINECRAFT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class AlinaDemoScreen {
    private static final ResourceLocation icon = new ResourceLocation(AlinLibTest.MODID, "textures/gui/widget/test/well.png");
    private static final Component CONFIGURE_CATEGORY = Component.literal("Configuration widgets");
    private static final Component BASE_CATEGORY = Component.literal("Base widgets");
    private static final Component BOOLEAN = Component.literal("Boolean");
    private static final Component BUTTON = Component.literal("Button");
    private static final Component EDIT_BOX = Component.literal("Edit Box");
    private static final Component LOCALIZATION_EDIT_BOX = Component.literal("Localization Edit Box");
    private static final Component SECRET_EDIT_BOX = Component.literal("Secret Edit Box");
    private static final Component COLOR_EDIT_BOX = Component.literal("Color Edit Box");
    private static final Component STRING_SELECTOR = Component.literal("String Selector");
    private static final Component INTEGER_SELECTOR = Component.literal("Integer Selector");
    private static final Component SLIDER_PERCENT = Component.literal("Slider Percent");
    private static final Component SLIDER_INTEGER = Component.literal("Slider Integer");
    private static final Component SLIDER = Component.literal("Slider");
    private static final Component SOMETHING = Component.translatable("alinlib.something");
    private static final Component GITHUB = Component.literal("GitHub");
    //
    String[] hell = {
            "Hello",
            ",",
            "World",
            "!",
            "No...",
            "Welcome to Hell :)"
    };
    String[] alina = {
            "Hi",
            ",",
            "I'm",
            "Alina",
            "!"
    };

    private boolean isFollow = false;
    public Screen build(Screen parent){
        return build(parent, InterfaceUtils.DesignType.ALINA);
    }
    public Screen build(Screen parent, InterfaceUtils.DesignType type){
        Component description = Component.literal("FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "FIRE IN THE HOLE! "+
                "something");
        Component config = Component.translatable("alinlibtest.description.configure_widgets");
        Component base = Component.translatable("alinlibtest.description.base_widgets");
        ConfigScreenBuilder screen = new ConfigScreenBuilder(parent)
                .setTitle(Component.translatable("alinlibtest.name"))
                .setType(InterfaceUtils.DesignType.VANILLA)
                .setOnTick((onTick) -> AlinLib.log(String.format("Tick on %s, count panel components: %s, count category components: %s", onTick.title, onTick.panelWidgets.size(), onTick.widgets.size())))
                ///
                .addPanelWidget(
                        new Button(10, 40, InterfaceUtils.DesignType.VANILLA, Component.literal("DesignType.VANILLA"), (OnPress) -> {
                            new ToastBuilder()
                                    .setTitle(Component.literal("AlinLib"))
                                    .setMessage(Component.literal("DesignType.VANILLA"))
                                    .setType(ToastBuilder.Type.DEBUG)
                                    .show(MINECRAFT.getToasts());
                            MINECRAFT.setScreen(this.build(parent, InterfaceUtils.DesignType.VANILLA));
                        })
                )
                .addPanelWidget(
                        new Button(10, 65, InterfaceUtils.DesignType.ALINA, Component.literal("DesignType.ALINA"), (OnPress) -> {
                            new ToastBuilder()
                                    .setTitle(Component.literal("AlinLib"))
                                    .setMessage(Component.literal("DesignType.ALINA"))
                                    .setType(ToastBuilder.Type.DEBUG)
                                    .show(MINECRAFT.getToasts());
                            MINECRAFT.setScreen(this.build(parent, InterfaceUtils.DesignType.ALINA));
                        })
                )
                .addPanelWidget(
                        new Button(10, 90, InterfaceUtils.DesignType.FLAT, Component.literal("DesignType.FLAT"), (OnPress) -> {
                            new ToastBuilder()
                                    .setTitle(Component.literal("AlinLib"))
                                    .setMessage(Component.literal("DesignType.FLAT"))
                                    .setType(ToastBuilder.Type.DEBUG)
                                    .show(MINECRAFT.getToasts());
                            MINECRAFT.setScreen(this.build(parent, InterfaceUtils.DesignType.FLAT));
                        })
                )
                .addPanelWidget(
                        new Button(10, 115, type, GITHUB, (OnPress) -> Util.getPlatform().openUri("https://github.com/simply-kel/AlinLib/"))
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal("AAAAAAAA"), (OnPress) -> new ToastBuilder().setTitle(Component.literal("AAAAAAAAAAAA")).setMessage(Component.literal(":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)")).show(AlinLib.MINECRAFT.getToasts()))
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal("AAAAAAAA v2"), (OnPress) -> new ToastBuilder().setIcon(Items.BARRIER).setTitle(Component.literal("AAAAAAAAAAAA")).setMessage(Component.literal(":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)")).show(AlinLib.MINECRAFT.getToasts()))
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                .addPanelWidget(
                        new Button(10, 115, type, Component.literal(":)"), (OnPress) -> AlinLib.MINECRAFT.stop())
                )
                ///
                .addWidget(
                        new CategoryBox(140, 5, CONFIGURE_CATEGORY)
                                .setDescription(config)
                                .addValue(new ButtonConfigBoolean(140, 30, type, AlinLib.bariumConfig, "Boolean", true, BOOLEAN))
                                .addValue(new EditBoxConfigString(140, 55, false, type, AlinLib.bariumConfig, "HELLO", "Hello, world", EDIT_BOX))
                                .addValue(new EditBoxConfigString(140, 80, true, type, AlinLib.bariumConfig, "SECRET", "Alina doesn't have a boyfriend", SECRET_EDIT_BOX))
                                .addValue(new EditBoxLocalization(140, 105, type, AlinLibTest.localization, "hi", LOCALIZATION_EDIT_BOX))
                                .addValue(new EditBoxColor(140, 130, type, AlinLib.bariumConfig, "Color", InterfaceUtils.Colors.TETRA, COLOR_EDIT_BOX))
                                .addValue(new SelectorStringButton(140, 155, type, hell, AlinLib.bariumConfig, "string_selector", hell[0], STRING_SELECTOR))
                                .addValue(new SelectorIntegerButton(140, 180, type, alina, AlinLib.bariumConfig, "integer_selector", 0, INTEGER_SELECTOR))
                                .addValue(new SliderConfigPercent(140, 205, type, AlinLib.bariumConfig, "Slider_percent", 0, SLIDER_PERCENT))
                                .addValue(new SliderConfigInteger(140, 230, type, AlinLib.bariumConfig, "Slider_int", 30, 30, 110, SLIDER_INTEGER)
                                        .setTypeInteger(" Coffee"))
                )
                .addWidget(
                        new CategoryBox(140, 280, BASE_CATEGORY)
                                .setDescription(base)
                                .addValue(new Button(140, 280, type, BUTTON, (s) ->
                                        new ToastBuilder()
                                                .setTitle(Component.literal("AlinLib"))
                                                .setMessage(Component.literal("Click!"))
                                                .setIcon(icon)
                                                .setType(ToastBuilder.Type.DEBUG)
                                                .show(MINECRAFT.getToasts())
                                ))
                                .addValue(new ButtonBoolean(140, 305, type, BOOLEAN, (s)->
                                        new ToastBuilder()
                                                .setTitle(Component.literal("AlinLib"))
                                                .setMessage(Component.literal(String.format("State: %b", s)))
                                                .setIcon(icon)
                                                .setType(ToastBuilder.Type.DEBUG)
                                                .show(MINECRAFT.getToasts())
                                ))
                                .addValue(new EditBoxString(140, 330, "Change me", type, EDIT_BOX, (s) -> {
                                    AlinLib.log(String.format("Not Secret: %s", s), Level.WARN);
                                }))
                                .addValue(new EditBoxString(140, 355, true, "Change me", type, EDIT_BOX, (s) -> {
                                    AlinLib.log(String.format("Secret: %s", s), Level.WARN);
                                }))
                                .addValue(new SelectorButton(140, 380, type, InterfaceUtils.Colors.GROUPIE, alina, 0, STRING_SELECTOR, (s) ->
                                        new ToastBuilder()
                                                .setTitle(Component.literal("AlinLib"))
                                                .setMessage(Component.literal(String.format("Select: %s", s.getValue().getString())))
                                                .setIcon(icon)
                                                .setType(ToastBuilder.Type.DEBUG)
                                                .show(MINECRAFT.getToasts())
                                ))
                                .addValue(new SliderPercent(140, 405, type, Math.random(), SLIDER))
                                .addValue(new SliderInteger(140, 430, type, 50, 0, 100, SLIDER))
                                .addValue(new TextBox(140, 455, Component.literal("Not center"), false))
                                .addValue(new TextBox(140, 455, Component.literal("Center"), true))
                                .changeState(false)
                )
                .addWidget(new ImageWidget(140, 505, DEFAULT_WIDTH(), 60, new ResourceLocation(AlinLibTest.MODID, "textures/gui/widget/test/normal.png"), 20, 20, Component.empty()).setDescription(description))
                .addWidget(new TextBox(140, 570, DEFAULT_WIDTH(), 20, SOMETHING, true, (OnPress) -> {
                    if (!this.isFollow) {
                        this.isFollow = true;
                        new ToastBuilder()
                                .setTitle(Component.literal("fire in the hole"))
                                .setMessage(Component.literal("fire in the hole"))
                                .setIcon(AlinLibTest.MODID, "textures/gui/widget/test/normal.png")
                                .setType(ToastBuilder.Type.INFO)
                                .show(MINECRAFT.getToasts());
                    } else {
                        OnPress.setActive(false);
                        Util.getPlatform().openUri("https://cdn.discordapp.com/emojis/1190682055273619627.png?size=512&quality=lossless");
                        AlinLib.log(Component.translatable("alinlib.something.oops"));
                        MINECRAFT.stop();
                    }
                }).setDescription(description));
        return screen.build();
    }
}
