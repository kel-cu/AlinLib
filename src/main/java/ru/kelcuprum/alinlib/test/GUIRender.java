package ru.kelcuprum.alinlib.test;

import net.minecraft.client.gui.GuiGraphics;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents;
import ru.kelcuprum.alinlib.config.Localization;

public class GUIRender implements GuiRenderEvents {
    @Override
    public void onRender(GuiGraphics guiGraphics, float partialTick) {
        if(AlinLib.bariumConfig.getBoolean("DISABLE_TEST_TEXT_RENDER", true)) return;
        guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font,
                Localization.fixFormatCodes(AlinLib.localization.getParsedText("&6XYZ:&r {player.pos.x} {player.pos.y} {player.pos.z} &6{player.direction_symbol} {world.name} {world.time_formatted} {player.license}&r")),
                guiGraphics.guiWidth()/2, 5, -1);
    }
}
