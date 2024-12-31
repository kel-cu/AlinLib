package ru.kelcuprum.alinlib.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.config.design.DemoScreen;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.alinlib.gui.screens.ConfirmScreen;

import static ru.kelcuprum.alinlib.gui.Colors.GROUPIE;
import static ru.kelcuprum.alinlib.gui.Icons.*;

public class DesignScreen {
    public static Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config.design"), (s) -> AlinLib.MINECRAFT.setScreen(DesignScreen.build(parent))).setIcon(OPTIONS).setCentered(false))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config.stealth"), (s) -> AlinLib.MINECRAFT.setScreen(StealthScreen.build(parent))).setIcon(INVISIBILITY).setCentered(false))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.localization"), (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).setIcon(LIST).setCentered(false));
        if (AlinLib.isNotReleaseVersion()) {
            builder.addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.title.not_release"),
                    (s) -> AlinLib.MINECRAFT.setScreen(new ConfirmScreen(builder.build(), Component.translatable("alinlib"),
                            Component.translatable("alinlib.title.not_release.description"), "https://github.com/kel-cu/alinlib/issues"))
            ).setIcon(SEARCH).setCentered(false));
        }
        builder.addWidget(new TextBuilder(Component.translatable("alinlib.config.design")))
                .addWidget(new SelectorBuilder(Component.translatable("alinlib.config.default_design_type"), selectorButton -> AlinLib.bariumConfig.setString("DEFAULT_DESIGN_TYPE", GuiUtils.getStyleByName(selectorButton.getList()[selectorButton.getPosition()]).id))
                        .setList(GuiUtils.getStylesName())
                        .setValue(GuiUtils.getPositionOnStylesID(GuiUtils.getSelected().name.getString())))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("alinlib.config.config_screen.small_panel_size"), false).setConfig(AlinLib.bariumConfig, "CONFIG_SCREEN.SMALL_PANEL_SIZE"))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("alinlib.config.toast.timeline"), true).setConfig(AlinLib.bariumConfig, "TOAST.TIMELINE"))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("alinlib.config.button.enable_reset_button"), true).setConfig(AlinLib.bariumConfig, "BUTTON.ENABLE_RESET_BUTTON"))
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.checkbox"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.checkbox.example"), true, true, null))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("alinlib.config.checkbox.enable"), true).setConfig(AlinLib.bariumConfig, "CHECKBOX.ENABLE"))
                        .addValue(new SelectorBuilder(Component.translatable("alinlib.config.checkbox.color")).setValue(0).setList(new String[]{
                                Component.translatable("alinlib.color.groupie").getString(),
                                Component.translatable("alinlib.color.alina").getString(),
                                Component.translatable("alinlib.color.seadrive").getString(),
                                Component.translatable("alinlib.color.tetra").getString(),
                                Component.translatable("alinlib.color.convict").getString(),
                                Component.translatable("alinlib.color.seabird").getString(),
                                Component.translatable("alinlib.color.sodium").getString(),
                                Component.translatable("alinlib.color.embeddium").getString(),
                                Component.translatable("alinlib.color.white").getString(),
                                Component.translatable("alinlib.color.waterplayer").getString(),
                                // CPM - Catppuccin Mocha
                                Component.translatable("alinlib.color.catppucin.rosewater").getString(),
                                Component.translatable("alinlib.color.catppucin.flamingo").getString(),
                                Component.translatable("alinlib.color.catppucin.pink").getString(),
                                Component.translatable("alinlib.color.catppucin.mauve").getString(),
                                Component.translatable("alinlib.color.catppucin.red").getString(),
                                Component.translatable("alinlib.color.catppucin.maroon").getString(),
                                Component.translatable("alinlib.color.catppucin.peach").getString(),
                                Component.translatable("alinlib.color.catppucin.yellow").getString(),
                                Component.translatable("alinlib.color.catppucin.green").getString(),
                                Component.translatable("alinlib.color.catppucin.teal").getString(),
                                Component.translatable("alinlib.color.catppucin.sky").getString(),
                                Component.translatable("alinlib.color.catppucin.sapphire").getString(),
                                Component.translatable("alinlib.color.catppucin.blue").getString(),
                                Component.translatable("alinlib.color.catppucin.lavender").getString(),

                                Component.translatable("alinlib.color.user").getString()
                        }).setConfig(AlinLib.bariumConfig, "CHECKBOX.COLOR"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.config.checkbox.color.custom")).setColor(Colors.CPM_LAVENDER).setConfig(AlinLib.bariumConfig, "CHECKBOX.COLOR.CUSTOM"))
                )
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.blockquote"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.config.blockquote.color")).setColor(GROUPIE).setConfig(AlinLib.bariumConfig, "BLOCKQUOTE.COLOR"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.config.blockquote.color.background")).setColor(GROUPIE - 0xE1000000).setConfig(AlinLib.bariumConfig, "BLOCKQUOTE.COLOR.BACKGROUND"))
                )
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.scroller"))
                        .addValue(new SelectorBuilder(Component.translatable("alinlib.config.scroller.color")).setValue(0).setList(new String[]{
                                Component.translatable("alinlib.color.white").getString(),
                                Component.translatable("alinlib.color.groupie").getString(),
                                Component.translatable("alinlib.color.alina").getString(),
                                Component.translatable("alinlib.color.seadrive").getString(),
                                Component.translatable("alinlib.color.tetra").getString(),
                                Component.translatable("alinlib.color.convict").getString(),
                                Component.translatable("alinlib.color.seabird").getString(),
                                Component.translatable("alinlib.color.sodium").getString(),
                                Component.translatable("alinlib.color.embeddium").getString(),
                                Component.translatable("alinlib.color.waterplayer").getString(),
                                // CPM - Catppuccin Mocha
                                Component.translatable("alinlib.color.catppucin.rosewater").getString(),
                                Component.translatable("alinlib.color.catppucin.flamingo").getString(),
                                Component.translatable("alinlib.color.catppucin.pink").getString(),
                                Component.translatable("alinlib.color.catppucin.mauve").getString(),
                                Component.translatable("alinlib.color.catppucin.red").getString(),
                                Component.translatable("alinlib.color.catppucin.maroon").getString(),
                                Component.translatable("alinlib.color.catppucin.peach").getString(),
                                Component.translatable("alinlib.color.catppucin.yellow").getString(),
                                Component.translatable("alinlib.color.catppucin.green").getString(),
                                Component.translatable("alinlib.color.catppucin.teal").getString(),
                                Component.translatable("alinlib.color.catppucin.sky").getString(),
                                Component.translatable("alinlib.color.catppucin.sapphire").getString(),
                                Component.translatable("alinlib.color.catppucin.blue").getString(),
                                Component.translatable("alinlib.color.catppucin.lavender").getString(),
                                Component.translatable("alinlib.color.user").getString()
                        }).setConfig(AlinLib.bariumConfig, "SCROLLER.COLOR"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.config.checkbox.color.custom")).setColor(Colors.CPM_LAVENDER).setConfig(AlinLib.bariumConfig, "SCROLLER.COLOR.CUSTOM")))
                .addWidget(new CategoryBox(Component.translatable("alinlib.config.horizontal_rule"))
                        .addValue(new SelectorBuilder(Component.translatable("alinlib.config.horizontal_rule.color")).setValue(0).setList(new String[]{
                                Component.translatable("alinlib.color.white").getString(),
                                Component.translatable("alinlib.color.groupie").getString(),
                                Component.translatable("alinlib.color.alina").getString(),
                                Component.translatable("alinlib.color.seadrive").getString(),
                                Component.translatable("alinlib.color.tetra").getString(),
                                Component.translatable("alinlib.color.convict").getString(),
                                Component.translatable("alinlib.color.seabird").getString(),
                                Component.translatable("alinlib.color.sodium").getString(),
                                Component.translatable("alinlib.color.embeddium").getString(),
                                Component.translatable("alinlib.color.waterplayer").getString(),
                                // CPM - Catppuccin Mocha
                                Component.translatable("alinlib.color.catppucin.rosewater").getString(),
                                Component.translatable("alinlib.color.catppucin.flamingo").getString(),
                                Component.translatable("alinlib.color.catppucin.pink").getString(),
                                Component.translatable("alinlib.color.catppucin.mauve").getString(),
                                Component.translatable("alinlib.color.catppucin.red").getString(),
                                Component.translatable("alinlib.color.catppucin.maroon").getString(),
                                Component.translatable("alinlib.color.catppucin.peach").getString(),
                                Component.translatable("alinlib.color.catppucin.yellow").getString(),
                                Component.translatable("alinlib.color.catppucin.green").getString(),
                                Component.translatable("alinlib.color.catppucin.teal").getString(),
                                Component.translatable("alinlib.color.catppucin.sky").getString(),
                                Component.translatable("alinlib.color.catppucin.sapphire").getString(),
                                Component.translatable("alinlib.color.catppucin.blue").getString(),
                                Component.translatable("alinlib.color.catppucin.lavender").getString(),
                                Component.translatable("alinlib.color.user").getString()
                        }).setConfig(AlinLib.bariumConfig, "HORIZONTAL_RULE.COLOR"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.config.checkbox.color.custom")).setColor(Colors.CPM_LAVENDER).setConfig(AlinLib.bariumConfig, "HORIZONTAL_RULE.COLOR.CUSTOM")))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("alinlib.config.scroller.smooth"), false).setConfig(AlinLib.bariumConfig, "SCROLLER.SMOOTH"))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("alinlib.config.modern"), true).setConfig(AlinLib.bariumConfig, "MODERN"))
                .addWidget(new ButtonBuilder(Component.translatable("alinlib.config.design.demo"), (s) -> AlinLib.MINECRAFT.setScreen(DemoScreen.build(parent))).setIcon(WIKI));
        return builder.build();
    }
}
