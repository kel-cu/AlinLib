package ru.kelcuprum.alinlib.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonWithIconBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.Icons.*;

public class ConfigScreen {
    public static Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"))
                .addPanelWidget(new ButtonWithIconBuilder(Component.translatable("alinlib.config"), OPTIONS, (s) -> AlinLib.MINECRAFT.setScreen(ConfigScreen.build(parent))).setCentered(false).build())
                .addPanelWidget(new ButtonWithIconBuilder(Component.translatable("alinlib.localization"), LIST, (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).setCentered(false).build());

        builder.addWidget(new TextBox(Component.translatable("alinlib.config"), true))
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.design"))
                        .addValue(new SelectorBuilder(Component.translatable("alinlib.config.default_design_type")).setValue(0).setList(new String[]{"Flat", "Modern", "Vanilla"}).setConfig(AlinLib.bariumConfig, "DEFAULT_DESIGN_TYPE").build())
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
}
