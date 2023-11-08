package ru.kelcuprum.alinlib.gui;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.editbox.StringEditBox;
import ru.kelcuprum.alinlib.gui.components.editbox.experiment.StringEditBoxExp;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderPercent;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.components.buttons.BooleanButton;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonWithColor;
import ru.kelcuprum.alinlib.gui.components.editbox.ColorEditBox;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;

public class DemoBariumScreen extends Screen {
    private final Screen parent;
    private static final Component TITLE = Component.literal("AlinLib");
    private static final Component CATEGORY = Component.literal("Example page");
    private static final Component EDIT_BOX = Component.literal("Edit Box");
    private static final Component COLOR_EDIT_BOX = Component.literal("Color Edit Box");
    private static final Component SLIDER_PERCENT = Component.literal("Slider Percent");
    private static final Component SLIDER_INTEGER = Component.literal("Slider Integer");
    private static final Component SOMETHING = Component.translatable("alinlib.something");
    private static final Component GITHUB = Component.literal("GitHub");
    private static final Component EXIT = Component.literal("Exit");
    //
    private int scrolled = 0;
    private int size = 900;
    //
    private TextBox titleBox;
    private StringEditBox stringEditBox;
    private BooleanButton booleanButton;
    String[] hell = {
            "Hello",
            ",",
            "World",
            "!",
            "No...",
            "Welcome to Hell :)"
    };
    private SelectorStringButton selectorStringButton;
    private ColorEditBox colorEditBox;
    private SliderPercent sliderPercent;
    private SliderInteger sliderInt;
    private StringEditBoxExp stringEditBoxExp;
    private TextBox something;
        //


    public DemoBariumScreen(Screen parent) {
        super(CATEGORY);
        this.parent = parent;
    }
    public void tick() {
        titleBox.setY(15-scrolled);
        booleanButton.setY(40-scrolled);
        stringEditBox.setY(40+(25)-scrolled);
        selectorStringButton.setY(40+(25*2)-scrolled);
        colorEditBox.setY(40+(25*3)-scrolled);
        sliderPercent.setY(40+(25*4)-scrolled);
        sliderInt.setY(40+(25*5)-scrolled);
        stringEditBoxExp.setY(40+(25*6)-scrolled);
        something.setY(875-scrolled);
        super.tick();
    }

    @Override
    public void init() {
        scrolled = 0;
        initButton();
        initButtonsCategory();
    }

    private void initButtonsCategory(){
        titleBox = addRenderableWidget(new TextBox(140, 15, this.width - 150, this.font.lineHeight, this.title, true));
        booleanButton = new BooleanButton(140, 40, this.width - 150, 20, AlinLib.bariumConfig, "Boolean", true, Component.literal("Boolean"));
        addRenderableWidget(booleanButton);
        stringEditBox = new StringEditBox(140, 40+(25), width-150, 20, EDIT_BOX);
        stringEditBox.setValue(AlinLib.bariumConfig.getString("HELLO", "Hello, world!"));
        stringEditBox.setResponder((string) -> {
            AlinLib.bariumConfig.setString("HELLO", string);
        });

        addRenderableWidget(stringEditBox);
        //
        selectorStringButton = new SelectorStringButton(140, 40+(25*2), this.width - 150, 20, hell, AlinLib.bariumConfig, "selector", hell[0], Component.literal("Selector"));
        addRenderableWidget(selectorStringButton);
        //
        colorEditBox = new ColorEditBox(140, 40+(25*3), width-150, 20, AlinLib.bariumConfig, "Color", 0xFFFFFF, COLOR_EDIT_BOX);
        addRenderableWidget(colorEditBox);
        //
        sliderPercent = new SliderPercent(140, 40+(25*4), width-150, 20, AlinLib.bariumConfig, "Slider_percent", 0, SLIDER_PERCENT);
        addRenderableWidget(sliderPercent);
        sliderInt = new SliderInteger(140, 40+(25*5), width-150, 20, AlinLib.bariumConfig, "Slider_int", 30, 30, 110, SLIDER_PERCENT);
        sliderInt.setTypeInteger(" Coffee");
        addRenderableWidget(sliderInt);
        //
        stringEditBoxExp = new StringEditBoxExp(140, 40+(25*6), width-150, 20, EDIT_BOX);
        stringEditBoxExp.setValue(AlinLib.bariumConfig.getString("HELLO_ALINA", "Hello, Alina! How are you?"));
        stringEditBoxExp.setResponder((string) -> {
            AlinLib.bariumConfig.setString("HELLO_ALINA", string);
        });

        addRenderableWidget(stringEditBoxExp);
        //
        something = addRenderableWidget(new TextBox(140, 875, this.width - 150, this.font.lineHeight, SOMETHING, true));
    }
    private void initButton(){
        // line 0
        addRenderableWidget(new Button(10, 40, 110, 20, CATEGORY, (OnPress) -> {
            this.minecraft.setScreen(this);
        }));

        addRenderableWidget(new Button(10, height - 55, 110, 20, GITHUB, (OnPress) -> {
            Util.getPlatform().openUri("https://github.com/simply-kel/AlinLib/");
        }));

        addRenderableWidget(new ButtonWithColor(10, height - 30, 110, 20, EXIT, 0xB6FF3131, (OnPress) -> {
            AlinLib.bariumConfig.save();
            this.minecraft.setScreen(parent);
        }));
    }
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f){
        if(this.minecraft.level != null){
            guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            renderDirtBackground(guiGraphics);
        }
        GUIUtils.renderLeftPanel(guiGraphics, 130, this.height);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.drawCenteredString(this.minecraft.font, TITLE, 120 / 2+5, 15, -1);
        //
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

    }

    @Override
    public boolean mouseScrolled(double d, double e, double f, double g) {
        scrolled = (int) (scrolled + (g*10.0*-1.0));
        if(scrolled <= 0) scrolled = 0;
        else if(scrolled >= size-height) scrolled = size-height;

        return super.mouseScrolled(d, e, f, g);
    }
}
