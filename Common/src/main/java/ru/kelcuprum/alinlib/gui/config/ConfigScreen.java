package ru.kelcuprum.alinlib.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;

public class ConfigScreen {
    public static Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config"), (s) -> AlinLib.MINECRAFT.setScreen(ConfigScreen.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.localization"), (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).build());

        builder.addWidget(new TextBox(Component.translatable("alinlib.config"), true))
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.design"))
                        .addValue(new SelectorBuilder(Component.translatable("alinlib.config.default_design_type")).setValue(1).setList(new String[]{"Alina", "Flat", "Vanilla"}).setConfig(AlinLib.bariumConfig, "DEFAULT_DESIGN_TYPE").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.config_screen.small_panel_size"), false).setConfig(AlinLib.bariumConfig, "CONFIG_SCREEN.SMALL_PANEL_SIZE").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.toast.timeline"), true).setConfig(AlinLib.bariumConfig, "TOAST.TIMELINE").build())
                )
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.localization"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.localization.extended_coordinates"), false).setConfig(AlinLib.bariumConfig, "LOCALIZATION.EXTENDED_COORDINATES").build())

//                        .addWidget(new ButtonConfigBoolean(140, 80, designType, ActionBarInfo.config, "USE_EXTENDED_COORDINATES", false, useExtendedCoordinatesText))
                )
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.stealth"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.streamer.stealth"), false).setConfig(AlinLib.bariumConfig, "STREAMER.STEALTH").build())
                        .addValue(new SelectorBuilder(Component.translatable("alinlib.config.streamer.stealth.type")).setValue(0).setList(new String[]{"ImGRUI Version", "AlinLib"}).setConfig(AlinLib.bariumConfig, "STREAMER.STEALTH.TYPE").build())
                );
        return builder.build();
    }
}
