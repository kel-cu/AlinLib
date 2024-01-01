package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.Util;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.ImageWidget;
import ru.kelcuprum.alinlib.gui.components.buttons.base.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxConfigString;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxLocalization;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorIntegerButton;
import ru.kelcuprum.alinlib.gui.components.selector.base.SelectorButton;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderPercent;
import ru.kelcuprum.alinlib.gui.components.sliders.base.Slider;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxColor;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.toast.AlinaToast;

public class AlinaDemoScreen extends AbstractConfigScreen {
    private static final ResourceLocation icon = new ResourceLocation("alinlib", "textures/gui/widget/test/well.png");
    private static final Component TITLE = Component.literal("AlinLib");
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
    private InterfaceUtils.DesignType type = InterfaceUtils.DesignType.ALINA;
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


    public AlinaDemoScreen(Screen parent) {
        super(parent, TITLE);
    }

    @Override
    protected void initCategory(){
        super.initCategory();
        int width = this.width - 150;
        //
        CategoryBox configure = new CategoryBox(140, 5, this.width - 150, 20, CONFIGURE_CATEGORY);
        configure.setTooltip(Localization.toText("Hello, world!"));
        //
        ButtonConfigBoolean booleanButton = new ButtonConfigBoolean(140, 30, width, 20, type, AlinLib.bariumConfig, "Boolean", true, BOOLEAN);
        configure.addValue(booleanButton);
        // -=-=-=-=-=-=-=-=-=-=-=-
        EditBoxConfigString stringEditBox = new EditBoxConfigString(140, 55, width, 20, false, type, AlinLib.bariumConfig, "HELLO", "Hello, world", EDIT_BOX);
        configure.addValue(stringEditBox);
        //
        EditBoxConfigString stringEditBoxSecret = new EditBoxConfigString(140, 80, width, 20, true, type, AlinLib.bariumConfig, "SECRET", "Alina doesn't have a boyfriend", SECRET_EDIT_BOX);
        configure.addValue(stringEditBoxSecret);
        //
        EditBoxLocalization localizationEditBox = new EditBoxLocalization(140, 80, width, 20, type, AlinLib.localization, "hi", LOCALIZATION_EDIT_BOX);
        configure.addValue(localizationEditBox);
        //
        EditBoxColor colorEditBox = new EditBoxColor(140, 105, width, 20, type, AlinLib.bariumConfig, "Color", InterfaceUtils.Colors.TETRA, COLOR_EDIT_BOX);
        configure.addValue(colorEditBox);
        // -=-=-=-=-=-=-=-=-=-=-=-
        SelectorStringButton selectorStringButton = new SelectorStringButton(140, 130, width, 20, type, hell, AlinLib.bariumConfig, "string_selector", hell[0], STRING_SELECTOR);
        configure.addValue(selectorStringButton);
        //
        SelectorIntegerButton selectorIntegerButton = new SelectorIntegerButton(140, 155, width, 20, type, alina, AlinLib.bariumConfig, "integer_selector", 0, INTEGER_SELECTOR);
        configure.addValue(selectorIntegerButton);
        // -=-=-=-=-=-=-=-=-=-=-=-
        SliderPercent sliderPercent = new SliderPercent(140, 180, width, 20, type, AlinLib.bariumConfig, "Slider_percent", 0, SLIDER_PERCENT);
        configure.addValue(sliderPercent);
        //
        SliderInteger sliderInt = new SliderInteger(140, 205, width, 20, type, AlinLib.bariumConfig, "Slider_int", 30, 30, 110, SLIDER_INTEGER);
        sliderInt.setTypeInteger(" Coffee");
        configure.addValue(sliderInt);
        addCategory(configure);
        // -=-=-=-=-=-=-=-=-=-=-=-

        CategoryBox notConfigure = new CategoryBox(140, 230, width, 20, BASE_CATEGORY);
        Button button = new Button(140, 255, width, 20, type, BUTTON, (s)->{
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Click!"), icon, AlinaToast.Type.DEBUG));
        });
        notConfigure.addValue(button);
        //
        ButtonBoolean buttonBoolean = new ButtonBoolean(140, 255, width, 20, type, BOOLEAN, (s)->{
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal(String.format("State: %b", s)), icon, AlinaToast.Type.DEBUG));
        });
        notConfigure.addValue(buttonBoolean);
        //
        EditBoxString editBoxString = new EditBoxString(140, 280, width, 20, "Change me", type, EDIT_BOX, (s) -> {
            AlinLib.log(String.format("Not Secret: %s", s), Level.WARN);
        });
        notConfigure.addValue(editBoxString);
        //
        EditBoxString editBoxStringSecret = new EditBoxString(140, 305, width, 20, true, "Change me", type, EDIT_BOX, (s) -> {
            AlinLib.log(String.format("Secret: %s", s), Level.WARN);
        });
        notConfigure.addValue(editBoxStringSecret);
        //
        SelectorButton selectorButton = new SelectorButton(140, 330, width, 20, type, InterfaceUtils.Colors.GROUPIE, alina, 0, STRING_SELECTOR, (s) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal(String.format("Select: %b", s.getValue().getString())), icon, AlinaToast.Type.DEBUG));
        });
        notConfigure.addValue(selectorButton);
        //
        Slider slider = new Slider(140, 355, width, 20, type, Math.random(), SLIDER);
        notConfigure.addValue(slider);

        notConfigure.addValue(new TextBox(140, 405, width, 20, Component.literal("Not center"), false, (s)->{
            Util.getPlatform().openUri("https://cdn.kelcuprum.ru/other/monke.gif");
        }));
        notConfigure.addValue(new TextBox(140, 380, width, 20, Component.literal("Center"), true, (s)->{
            Util.getPlatform().openUri("https://cdn.kelcuprum.ru/other/monke.gif");
        }));
        addCategory(notConfigure);

        // -=-=-=-=-=-=-=-=-=-=-=-
        addCategoryWidget(new ImageWidget(140, 430, width, 20, icon, 20, 20, Component.empty()));
        //
        TextBox something = new TextBox(140, 455, width, 20, SOMETHING, true, (OnPress) -> {
            if (!this.isFollow) {
                this.isFollow = true;
                this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Click me again :)"), new ResourceLocation("alinlib", "textures/gui/widget/test/well.png"), AlinaToast.Type.ERROR));
            } else {
                OnPress.setActive(false);
                Util.getPlatform().openUri("https://cdn.kelcuprum.ru/other/monke.gif");
                AlinLib.log(Component.translatable("alinlib.something.oops"));
                this.minecraft.stop();
            }
        });
        addCategoryWidget(something);
    }

    @Override
    protected void initPanelButtons(){
        super.initPanelButtons();
        // line 0
        addRenderableWidget(new Button(10, 40, 110, 20, InterfaceUtils.DesignType.VANILLA, InterfaceUtils.Colors.KENNY, Component.literal("DesignType.VANILLA"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.VANILLA"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.VANILLA;
            this.rebuildWidgets();
        }));
        addRenderableWidget(new Button(10, 65, 110, 20, InterfaceUtils.DesignType.ALINA, InterfaceUtils.Colors.KENNY, Component.literal("DesignType.ALINA"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.ALINA"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.ALINA;
            this.rebuildWidgets();
        }));
        addRenderableWidget(new Button(10, 90, 110, 20, InterfaceUtils.DesignType.FLAT, InterfaceUtils.Colors.KENNY, Component.literal("DesignType.FLAT"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.FLAT"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.FLAT;
            this.rebuildWidgets();
        }));

        addRenderableWidget(new Button(10, height - 55, 110, 20, InterfaceUtils.DesignType.VANILLA, GITHUB, (OnPress) -> {
            Util.getPlatform().openUri("https://github.com/simply-kel/AlinLib/");
        }));
    }
}
