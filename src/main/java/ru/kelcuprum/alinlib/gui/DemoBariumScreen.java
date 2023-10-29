package ru.kelcuprum.alinlib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.TextBox;
import ru.kelcuprum.alinlib.gui.components.buttons.BooleanButton;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonWithColor;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;

import java.awt.*;

public class DemoBariumScreen extends Screen {
    private final Screen parent;
    private static final Component TITLE = Component.literal("AlinLib");
    private static final Component CATEGORY = Component.literal("Example page");
    private static final Component EDIT_BOX = Component.literal("Edit Box");
    private static final Component EXIT = Component.literal("Exit");

    public DemoBariumScreen(Screen parent) {
        super(CATEGORY);
        this.parent = parent;
    }
    public void tick() {
        super.tick();
    }

    @Override
    public void init() {
        initButton();
        initButtonsCategory();
    }
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f){
        if(this.minecraft.level != null){
            guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            renderDirtBackground(guiGraphics);
        }
        guiGraphics.fill(0, 0, 130, this.height, new Color(0x80000000, true).getRGB());
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.drawCenteredString(this.minecraft.font, this.title, width / 2 + 60, 15, -1);
        guiGraphics.drawCenteredString(this.minecraft.font, TITLE, 120 / 2+5, 15, -1);
        new TextBox(guiGraphics, 140, 40+25, this.font.width(EDIT_BOX)+16, 20, Component.literal("Edit Box"), false);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

    }
    private void initButtonsCategory(){

        addRenderableWidget(new BooleanButton(140, 40, this.width - 150, 20, Component.literal("Boolean"), "Boolean", true, AlinLib.bariumConfig));


        EditBox editBox = new EditBox(Minecraft.getInstance().font, 140+this.font.width(EDIT_BOX)+16, 40+(25), width-150-(this.font.width(EDIT_BOX)+16), 20, Component.literal("Hell"));
        editBox.setValue(AlinLib.bariumConfig.getString("HELLO", "Hello, world!"));
        editBox.setResponder((string) -> {
                    AlinLib.bariumConfig.setString("HELLO", string);
                });
        addRenderableWidget(editBox);
        //
        String[] hell = {
                "Hello",
                ",",
                "World",
                "!",
                "No...",
                "Welcome to Hell :)"
        };
        addRenderableWidget(
                new SelectorStringButton(140, 40+(25*2), this.width - 150, 20, hell, AlinLib.bariumConfig, "selector", "Hello", Component.literal("Selector"))
        );
        //
    }
    private void initButton(){
        // line 0
        addRenderableWidget(new Button(10, 40, 110, 20, CATEGORY, (OnPress) -> {
            this.minecraft.setScreen(this);
        }));

        addRenderableWidget(new ButtonWithColor(10, height - 30, 110, 20, EXIT, 0xB6FF3131, (OnPress) -> {
            AlinLib.bariumConfig.save();
            this.minecraft.setScreen(parent);
        }));
    }
}
