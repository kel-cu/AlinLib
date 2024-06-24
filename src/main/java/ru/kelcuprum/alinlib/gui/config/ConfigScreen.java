package ru.kelcuprum.alinlib.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.slider.SliderBuilder;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.*;

public class ConfigScreen {
    public static Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config"), (s) -> AlinLib.MINECRAFT.setScreen(ConfigScreen.build(parent))).setIcon(OPTIONS).setCentered(false).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.localization"), (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).setIcon(LIST).setCentered(false).build())
                .addPanelWidget(new ButtonBuilder(Component.literal("Hello,"), Component.literal("world!"), (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).setIcon(WIKI).setCentered(false).build())
                .addPanelWidget(new SliderBuilder(Component.literal("1")).setConfig(AlinLib.bariumConfig, "huy1").setMax(2.0).setDefaultValue(0.5, true).build())
                .addPanelWidget(new SliderBuilder(Component.literal("2")).setConfig(AlinLib.bariumConfig, "huy2").setMax(2.0).setDefaultValue(0.5, false).build())
                .addPanelWidget(new SliderBuilder(Component.literal("3")).setConfig(AlinLib.bariumConfig, "huy3").setMax(2.0f).setDefaultValue(0.5f).build())
                .addPanelWidget(new SliderBuilder(Component.literal("4")).setConfig(AlinLib.bariumConfig, "huy4").setMax(10).setDefaultValue(0).build());

        builder.addWidget(new TextBox(Component.translatable("alinlib.config"), true))
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.design"))
                        .addValue(new SelectorBuilder(Component.translatable("alinlib.config.default_design_type"), selectorButton -> AlinLib.bariumConfig.setString("DEFAULT_DESIGN_TYPE", GuiUtils.getStyleByName(selectorButton.getList()[selectorButton.getPosition()]).id))
                                .setList(GuiUtils.getStylesName())
                                .setValue(GuiUtils.getPositionOnStylesID(GuiUtils.getSelected().name.getString()))
                                .build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.config_screen.small_panel_size"), false).setConfig(AlinLib.bariumConfig, "CONFIG_SCREEN.SMALL_PANEL_SIZE").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.toast.timeline"), true).setConfig(AlinLib.bariumConfig, "TOAST.TIMELINE").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.button.enable_reset_button"), true).setConfig(AlinLib.bariumConfig, "BUTTON.ENABLE_RESET_BUTTON").build())
                )
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.localization"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.localization.extended_coordinates"), false).setConfig(AlinLib.bariumConfig, "LOCALIZATION.EXTENDED_COORDINATES").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.view.item_off_hand"), false).setConfig(AlinLib.bariumConfig, "VIEW.ITEM_OFF_HAND").build())
                )
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.stealth"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.streamer.stealth"), false).setConfig(AlinLib.bariumConfig, "STREAMER.STEALTH").build())
                        .addValue(new SelectorBuilder(Component.translatable("alinlib.config.streamer.stealth.type")).setValue(0).setList(new String[]{"ImGRUI Version", "AlinLib"}).setConfig(AlinLib.bariumConfig, "STREAMER.STEALTH.TYPE").build())
                );
        return builder.build();
    }
    //if FORGE && MC >= 12002
    //$$ public static Screen build(net.minecraft.client.Minecraft minecraft, Screen screen) {
    //$$     return build(screen);
    //$$ }
    //endif
}
