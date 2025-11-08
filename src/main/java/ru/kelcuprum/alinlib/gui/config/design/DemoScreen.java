package ru.kelcuprum.alinlib.gui.config.design;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.components.ImageWidget;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.MultilineEditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.slider.SliderBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.HorizontalRuleBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.editbox.MultilineEditBox;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.DescriptionBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.alinlib.gui.screens.ConfirmScreen;
import ru.kelcuprum.alinlib.gui.screens.DialogScreen;

import java.net.URI;

import static ru.kelcuprum.alinlib.gui.Colors.SEADRIVE;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;
import static ru.kelcuprum.alinlib.gui.Icons.*;
import static ru.kelcuprum.alinlib.gui.config.DesignScreen.getPanelWidgets;

public class DemoScreen {
    public static Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("alinlib"));
        builder.addPanelWidgets(getPanelWidgets(parent));
        builder
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.design.demo.button")))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.design.demo.button.left"), Component.translatable("alinlib.design.demo.button.right")))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.design.demo.button.with_icon")).setIcon(CLOWNFISH))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.design.demo.button.left.with_icon"), Component.translatable("alinlib.design.demo.button.right.with_icon")).setIcon(WIKI))
                .addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.design.demo.button.sprite")).setSprite(CLOWNFISH));
        if (AlinLib.isNotReleaseVersion()) {
            builder.addPanelWidget(new ButtonBuilder(Component.translatable("alinlib.title.not_release"),
                    (s) -> AlinLib.MINECRAFT.setScreen(new ConfirmScreen(builder.build(), Component.translatable("alinlib"),
                            Component.translatable("alinlib.title.not_release.description"), "https://github.com/kel-cu/alinlib/issues"))
            ).setIcon(SEARCH).setCentered(false));
        }
        builder.setIcon(WIKI);
        builder.setCategoryTitle(Component.translatable("alinlib.config.design.demo"));
        builder.addWidget(new CategoryBox(Component.translatable("alinlib.design.demo.buttons"))
                .addValue(new ButtonBuilder(Component.translatable("alinlib.design.demo.button")))
                .addValue(new ButtonBuilder(Component.translatable("alinlib.design.demo.button.left"), Component.translatable("alinlib.design.demo.button.right")))
                .addValue(new ButtonBuilder(Component.translatable("alinlib.design.demo.button.with_icon")).setIcon(CLOWNFISH))
                .addValue(new ButtonBuilder(Component.translatable("alinlib.design.demo.button.left.with_icon"), Component.translatable("alinlib.design.demo.button.right.with_icon")).setIcon(WIKI))
                .addValue(new ButtonBuilder(Component.translatable("alinlib.design.demo.button.sprite")).setSprite(CLOWNFISH))
                .changeState(false)
        );

        builder.addWidget(new CategoryBox(Component.translatable("alinlib.design.demo.edit_box"))
                .addValue(new EditBoxBuilder(Component.translatable("alinlib.design.demo.edit_box")))
                .addValue(new EditBoxBuilder(Component.translatable("alinlib.design.demo.edit_box.color")).setColor(SEADRIVE))
                .addValue(new EditBoxBuilder(Component.translatable("alinlib.design.demo.edit_box.secret")).setSecret(true))
                .addValue(new MultilineEditBoxBuilder(Component.literal("TEST SUBJECT")).setValue("hell, yeah!\nThis multiline EditBox, but WritableTextBox").setHeight(100))
                .changeState(false)
        );
        builder.addWidget(new CategoryBox(Component.translatable("alinlib.design.demo.selector"))
                .addValue(new SelectorBuilder(Component.translatable("alinlib.design.demo.selector")).setList(new String[]{
                        "Hello",
                        ",",
                        "world",
                        "!"
                }))
                .changeState(false)
        );
        builder.addWidget(new CategoryBox(Component.translatable("alinlib.design.demo.slider"))
                .addValue(new SliderBuilder(Component.translatable("alinlib.design.demo.slider.int")).setMin(0).setMax(100).setDefaultValue(0))
                .addValue(new SliderBuilder(Component.translatable("alinlib.design.demo.slider.double")).setMin(0.0).setMax(100.0).setDefaultValue(0.0, false))
                .addValue(new SliderBuilder(Component.translatable("alinlib.design.demo.slider.percent")).setMin(0.0).setMax(100.0).setDefaultValue(0.0, true))
                .addValue(new SliderBuilder(Component.translatable("alinlib.design.demo.slider.float")).setMin(0.0f).setMax(100.0f).setDefaultValue(0.0f))
                .changeState(false)
        );
        builder.addWidget(new CategoryBox(Component.translatable("alinlib.design.demo.text"))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.left")).setAlign(TextBuilder.ALIGN.LEFT))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.center")))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.right")).setAlign(TextBuilder.ALIGN.RIGHT))
                .addValue(new HorizontalRuleBuilder(Component.translatable("alinlib.design.demo.text.message")))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.left")).setType(TextBuilder.TYPE.MESSAGE))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.center")).setType(TextBuilder.TYPE.MESSAGE).setAlign(TextBuilder.ALIGN.CENTER))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.right")).setType(TextBuilder.TYPE.MESSAGE).setAlign(TextBuilder.ALIGN.RIGHT))
                .addValue(new HorizontalRuleBuilder(Component.translatable("alinlib.design.demo.text.blockquote")))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.left")).setType(TextBuilder.TYPE.BLOCKQUOTE))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.center")).setType(TextBuilder.TYPE.BLOCKQUOTE).setAlign(TextBuilder.ALIGN.CENTER))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.right")).setType(TextBuilder.TYPE.BLOCKQUOTE).setAlign(TextBuilder.ALIGN.RIGHT))
                .addValue(new HorizontalRuleBuilder(Component.translatable("alinlib.design.demo.text.blockquote.colors")))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.blockquote.colors.def")).setType(TextBuilder.TYPE.BLOCKQUOTE))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.blockquote.colors.col1")).setType(TextBuilder.TYPE.BLOCKQUOTE).setColor(Colors.CLOWNFISH))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.blockquote.colors.col2")).setType(TextBuilder.TYPE.BLOCKQUOTE).setColor(Colors.SPECKLE[0], Colors.TETRA))
                //#if MC >= 12105
                .addValue(new TextBuilder(Component.empty().withStyle(Style.EMPTY.withClickEvent(new ClickEvent.OpenUrl(URI.create("https://wfu.kelcu.ru/jSgLdwH")))).append("hehehehe")).setType(TextBuilder.TYPE.MESSAGE))

                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.url", Component.empty().withStyle(Style.EMPTY.withClickEvent(new ClickEvent.OpenUrl(URI.create("https://wfu.kelcu.ru/jSgLdwH"))).withColor(SEADRIVE)).append("click please\nhehehehe"))).setType(TextBuilder.TYPE.MESSAGE))
                //#else
                //$$ .addValue(new TextBuilder(Component.empty().withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://wfu.kelcu.ru/jSgLdwH"))).append("hehehehe")).setType(TextBuilder.TYPE.MESSAGE))
                //$$ .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.text.url", Component.empty().withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://wfu.kelcu.ru/jSgLdwH")).withColor(SEADRIVE)).append("click please\nhehehehe"))).setType(TextBuilder.TYPE.MESSAGE))
                //#endif
                .addValue(new HorizontalRuleBuilder(Component.translatable("alinlib.design.demo.text.hr")))
                .addValue(new HorizontalRuleBuilder())
                .changeState(false)
        );
        DescriptionBox dbox = new DescriptionBox(0, 0, DEFAULT_WIDTH(), 80, Component.translatable("alinlib.design.demo.description"));
        dbox.setDescription(Component.translatable("alinlib.design.demo.description"));
        builder.addWidget(new CategoryBox(Component.translatable("alinlib.design.demo.other"))
                .addValue(new TextBuilder(Component.translatable("alinlib.design.demo.other.scroller")).setAlign(TextBuilder.ALIGN.RIGHT))
                .addValue(new HorizontalRuleBuilder(Component.translatable("alinlib.design.demo.other.description")))
                .addValue(dbox)
                .addValue(new HorizontalRuleBuilder(Component.translatable("alinlib.design.demo.other.image")))
                .addValue(new ImageWidget(0,0,512,512, CLOWNFISH, 512, 512, true, Component.empty()))
                .addValue(new ImageWidget(0,0,32,32, CLOWNFISH, 32, 32, false, Component.empty()))
                .changeState(false)
        )
                .addWidget(new ButtonBuilder(Component.translatable("..."),
                        (s) -> AlinLib.MINECRAFT.setScreen(new DialogScreen(AlinLib.MINECRAFT.screen, new String[]{}, null))));


        return builder.build();
    }
}
