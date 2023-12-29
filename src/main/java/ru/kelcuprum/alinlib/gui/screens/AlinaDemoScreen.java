package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.Util;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderPercent;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxColor;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.toast.AlinaToast;

public class AlinaDemoScreen extends AbstractConfigScreen {
    private static final Component TITLE = Component.literal("AlinLib");
    private static final Component CATEGORY = Component.literal("Example page");
    private static final Component EDIT_BOX = Component.literal("Edit Box");
    private static final Component SECRET_EDIT_BOX = Component.literal("Secret Edit Box");
    private static final Component COLOR_EDIT_BOX = Component.literal("Color Edit Box");
    private static final Component SLIDER_PERCENT = Component.literal("Slider Percent");
    private static final Component SLIDER_INTEGER = Component.literal("Slider Integer");
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

    private boolean isFollow = false;


    public AlinaDemoScreen(Screen parent) {
        super(parent, TITLE);
    }

    @Override
    protected void initCategory(){
        super.initCategory();
        //
        CategoryBox category = new CategoryBox(140, 5, this.width - 150, 20, CATEGORY, true);
        category.setTooltip(Localization.toText("Hello, world!"));
        widgetList.add(category);
        //
        ButtonBoolean booleanButton = new ButtonBoolean(140, 30, this.width - 150, 20, type, AlinLib.bariumConfig, "Boolean", true, Component.literal("Boolean"));
        category.addValue(booleanButton);
        widgetList.add(booleanButton);
        //
        EditBoxString stringEditBox = new EditBoxString(140, 55, width - 150, 20, AlinLib.bariumConfig.getString("HELLO", "Hello, world!"), type, EDIT_BOX, (string) -> AlinLib.bariumConfig.setString("HELLO", string));
        category.addValue(stringEditBox);
        widgetList.add(stringEditBox);
        //
        EditBoxString stringEditBoxSecret = new EditBoxString(140, 80, width - 150, 20, true, AlinLib.bariumConfig.getString("SECRET", "Alina doesn't have a boyfriend"), type, SECRET_EDIT_BOX, (string) -> AlinLib.bariumConfig.setString("SECRET", string));
        category.addValue(stringEditBoxSecret);
        widgetList.add(stringEditBoxSecret);
        //
        SelectorStringButton selectorStringButton = new SelectorStringButton(140, 105, this.width - 150, 20, type, hell, AlinLib.bariumConfig, "selector", hell[0], Component.literal("Selector"));
        category.addValue(selectorStringButton);
        widgetList.add(selectorStringButton);
        //
        EditBoxColor colorEditBox = new EditBoxColor(140, 130, width - 150, 20, type, AlinLib.bariumConfig, "Color", Colors.TETRA, COLOR_EDIT_BOX);
        category.addValue(colorEditBox);
        widgetList.add(colorEditBox);
        //
        SliderPercent sliderPercent = new SliderPercent(140, 155, width - 150, 20, type, AlinLib.bariumConfig, "Slider_percent", 0, SLIDER_PERCENT);
        category.addValue(sliderPercent);
        widgetList.add(sliderPercent);
        //
        SliderInteger sliderInt = new SliderInteger(140, 180, width - 150, 20, type, AlinLib.bariumConfig, "Slider_int", 30, 30, 110, SLIDER_INTEGER);
        sliderInt.setTypeInteger(" Coffee");
        category.addValue(sliderInt);
        widgetList.add(sliderInt);
        //
        TextBox something = new TextBox(140, 205, this.width - 150, 20, SOMETHING, true, (OnPress) -> {
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
        widgetList.add(something);
        addRenderableWidgets(widgetList);
    }

    @Override
    protected void initPanelButtons(){
        super.initPanelButtons();
        // line 0
        addRenderableWidget(new Button(10, 40, 110, 20, InterfaceUtils.DesignType.VANILLA, Colors.KENNY, Component.literal("DesignType.VANILLA"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.VANILLA"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.VANILLA;
            this.rebuildWidgets();
        }));
        addRenderableWidget(new Button(10, 65, 110, 20, InterfaceUtils.DesignType.ALINA, Colors.KENNY, Component.literal("DesignType.ALINA"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.ALINA"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.ALINA;
            this.rebuildWidgets();
        }));
        addRenderableWidget(new Button(10, 90, 110, 20, InterfaceUtils.DesignType.FLAT, Colors.KENNY, Component.literal("DesignType.FLAT"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.FLAT"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.FLAT;
            this.rebuildWidgets();
        }));

        addRenderableWidget(new Button(10, height - 55, 110, 20, InterfaceUtils.DesignType.VANILLA, GITHUB, (OnPress) -> {
            Util.getPlatform().openUri("https://github.com/simply-kel/AlinLib/");
        }));
    }
}
