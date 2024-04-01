package ru.kelcuprum.alinlib.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;

public class ConfigScreen {
    public static Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config"), (s) -> AlinLib.MINECRAFT.setScreen(ConfigScreen.build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.localization"), (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).build());

        builder.addWidget(new TextBox(Component.translatable("alinlib.config"), true))
                .addWidget(new SelectorBuilder(Component.translatable("alinlib.config.default_design_type")).setValue(1).setList(new String[]{"Alina", "Flat", "Vanilla"}).setConfig(AlinLib.bariumConfig, "DEFAULT_DESIGN_TYPE").build());
        return builder.build();
    }
}
