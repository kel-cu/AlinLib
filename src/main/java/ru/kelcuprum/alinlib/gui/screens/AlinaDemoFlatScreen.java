package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.flat.FlatButton;
import ru.kelcuprum.alinlib.gui.components.buttons.flat.FlatColoredButton;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;

public class AlinaDemoFlatScreen extends Screen {
    private final Screen parent;
    private static final Component TITLE = Component.literal("AlinLib");
    private static final Component CATEGORY = Component.literal("It's YOU!");
    private static final Component CUPRUM = Component.literal("It's me (Developer)");
    private static final Component EXIT = Component.literal("Exit");

    public AlinaDemoFlatScreen(Screen parent) {
        super(CATEGORY);
        this.parent = parent;
    }
    public void tick() {
        super.tick();
    }

    @Override
    public void init() {
        initButton();
    }
    private void initButton(){
        // line 0
        addRenderableWidget(new FlatButton(10, this.height/2-10, 110, 20, CUPRUM, (OnPress) -> {
            Util.getPlatform().openUri("https://kelcuprum.ru/r/mr");
        }));
        addRenderableWidget(new TextBox(width/2-100, height/2-120, 200, 20, CATEGORY, true));

        addRenderableWidget(new FlatColoredButton(10, height - 30, 110, 20, 0xFFFF3131, EXIT, (OnPress) -> {
            AlinLib.bariumConfig.save();
            this.minecraft.setScreen(parent);
        }));
    }
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f){
        if(this.minecraft.level != null){
            guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            guiGraphics.fillGradient(0, 0, this.width, this.height, 0x893168, 0x2e1c2b);
        }
        InterfaceUtils.renderLeftPanel(guiGraphics, 130, this.height);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.drawCenteredString(this.minecraft.font, TITLE, 120 / 2+5, 15, -1);
        //
        int k = width/2-100;
        int l = height/2-100;
        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, k, l, k + 200, l + 200, 75, 0.0625F, mouseX, mouseY, this.minecraft.player);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }
}
