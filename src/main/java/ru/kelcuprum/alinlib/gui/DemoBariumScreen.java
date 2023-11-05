package ru.kelcuprum.alinlib.gui;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.buttons.BooleanButton;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonWithColor;
import ru.kelcuprum.alinlib.gui.components.editbox.ColorEditBox;
import ru.kelcuprum.alinlib.gui.components.editbox.StringEditBox;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;

public class DemoBariumScreen extends Screen {
    private final Screen parent;
    private static final Component TITLE = Component.literal("AlinLib");
    private static final Component CATEGORY = Component.literal("Example page");
    private static final Component EDIT_BOX = Component.literal("Edit Box");
    private static final Component COLOR_EDIT_BOX = Component.literal("Color Edit Box");
    private static final Component GITHUB = Component.literal("GitHub");
    private static final Component EXIT = Component.literal("Exit");
    //
    private int scrolled = 0;
    private int size = 900;
    //
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
        //


    public DemoBariumScreen(Screen parent) {
        super(CATEGORY);
        this.parent = parent;
    }
    public void tick() {
        booleanButton.setY(40-scrolled);
        stringEditBox.setY(40+(25)-scrolled);
        selectorStringButton.setY(40+(25*2)-scrolled);
        super.tick();
    }

    @Override
    public void init() {
        scrolled = 0;
        initButton();
        initButtonsCategory();
    }

    private void initButtonsCategory(){
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
        guiGraphics.drawCenteredString(this.minecraft.font, this.title, width / 2 + 60, 15-scrolled, -1);
        guiGraphics.drawCenteredString(this.minecraft.font, TITLE, 120 / 2+5, 15, -1);
        //
        guiGraphics.drawCenteredString(this.minecraft.font, this.title, width / 2 + 60, 875-scrolled, -1);
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
