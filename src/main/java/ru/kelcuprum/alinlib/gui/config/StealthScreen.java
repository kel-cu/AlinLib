package ru.kelcuprum.alinlib.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.*;

public class StealthScreen {
    public static Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config.design"), (s) -> AlinLib.MINECRAFT.setScreen(DesignScreen.build(parent))).setIcon(OPTIONS).setCentered(false))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config.stealth"), (s) -> AlinLib.MINECRAFT.setScreen(StealthScreen.build(parent))).setIcon(INVISIBILITY).setCentered(false))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.localization"), (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).setIcon(LIST).setCentered(false));

        builder.addWidget(new TextBox(Component.translatable("alinlib.config.stealth"), true))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("alinlib.config.streamer.stealth"), false).setConfig(AlinLib.bariumConfig, "STREAMER.STEALTH"))
                .addWidget(new SelectorBuilder(Component.translatable("alinlib.config.streamer.stealth.type")).setValue(0).setList(new String[]{"ImGRUI Version", "AlinLib"}).setConfig(AlinLib.bariumConfig, "STREAMER.STEALTH.TYPE"));
        return builder.build();
    }
}
