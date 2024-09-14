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
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.*;

public class DesignScreen {
    public static Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config.design"), (s) -> AlinLib.MINECRAFT.setScreen(DesignScreen.build(parent))).setIcon(OPTIONS).setCentered(false))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.config.stealth"), (s) -> AlinLib.MINECRAFT.setScreen(StealthScreen.build(parent))).setIcon(INVISIBILITY).setCentered(false))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.localization"), (s) -> AlinLib.MINECRAFT.setScreen(LocalizationScreen.build(parent))).setIcon(LIST).setCentered(false));

        builder.addWidget(new TextBox(Component.translatable("alinlib.config.design"), true))
                .addWidget(new SelectorBuilder(Component.translatable("alinlib.config.default_design_type"), selectorButton -> AlinLib.bariumConfig.setString("DEFAULT_DESIGN_TYPE", GuiUtils.getStyleByName(selectorButton.getList()[selectorButton.getPosition()]).id))
                        .setList(GuiUtils.getStylesName())
                        .setValue(GuiUtils.getPositionOnStylesID(GuiUtils.getSelected().name.getString())).setDescription("test"))
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
                                Component.translatable("alinlib.color.user").getString()
                        }).setConfig(AlinLib.bariumConfig, "CHECKBOX.COLOR"))
                        .addValue(new EditBoxBuilder(Component.translatable("alinlib.config.checkbox.color.custom")).setColor(Colors.SPECKLE[0]).setConfig(AlinLib.bariumConfig, "CHECKBOX.COLOR.CUSTOM"))
                )
                .addWidget(new SelectorBuilder(Component.translatable("alinlib.config.scroller.color")).setValue(0).setList(new String[]{
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
                        Component.translatable("alinlib.color.user").getString()
                }).setConfig(AlinLib.bariumConfig, "SCROLLER.COLOR"));
        return builder.build();
    }
    //if FORGE && MC >= 12002
    //$$ public static Screen build(net.minecraft.client.Minecraft minecraft, Screen screen) {
    //$$     return build(screen);
    //$$ }
    //endif
}
