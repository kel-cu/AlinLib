package ru.kelcuprum.alinlib.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.*;

public class LocalizationScreen {
    public static Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config.design"), (s) -> AlinLib.MINECRAFT.setScreen(DesignScreen.build(parent))).setIcon(OPTIONS).setCentered(false))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config.stealth"), (s) -> AlinLib.MINECRAFT.setScreen(StealthScreen.build(parent))).setIcon(INVISIBILITY).setCentered(false))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.localization"), (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).setIcon(LIST).setCentered(false));

        builder.addWidget(new TextBox(Component.translatable("alinlib.localization"), true))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("alinlib.config.localization.extended_coordinates"), false).setConfig(AlinLib.bariumConfig, "LOCALIZATION.EXTENDED_COORDINATES"))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("alinlib.config.view.item_off_hand"), false).setConfig(AlinLib.bariumConfig, "VIEW.ITEM_OFF_HAND"))
                .addWidget(new CategoryBox(Component.translatable("alinlib.localization.sides"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.north")).setLocalization(AlinLib.localization, "north"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.south")).setLocalization(AlinLib.localization, "south"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.west")).setLocalization(AlinLib.localization, "west"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.east")).setLocalization(AlinLib.localization, "east"))
                )
                .addWidget(new CategoryBox(Component.translatable("alinlib.localization.time"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.time.morning")).setLocalization(AlinLib.localization, "time.morning"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.time.day")).setLocalization(AlinLib.localization, "time.day"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.time.evening")).setLocalization(AlinLib.localization, "time.evening"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.time.night")).setLocalization(AlinLib.localization, "time.night"))
                )
                .addWidget(new CategoryBox(Component.translatable("alinlib.localization.world"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.world.overworld")).setLocalization(AlinLib.localization, "world.overworld"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.world.nether")).setLocalization(AlinLib.localization, "world.nether"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.world.the_end")).setLocalization(AlinLib.localization, "world.the_end"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.localization.world.moon")).setLocalization(AlinLib.localization, "world.moon"))
                )
                .addWidget(new EditBoxBuilder(Component.translatable("alinlib.localization.date.time")).setLocalization(AlinLib.localization, "date.time"))
                .addWidget(new EditBoxBuilder(Component.translatable("alinlib.localization.unknown")).setLocalization(AlinLib.localization, "unknown"));
        return builder.build();
    }
}
