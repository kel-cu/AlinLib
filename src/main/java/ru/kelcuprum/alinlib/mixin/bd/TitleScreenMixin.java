package ru.kelcuprum.alinlib.mixin.bd;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method="render", at=@At("RETURN"))
    void render(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci){
        if(AlinLib.isHBKel()) {
            guiGraphics.renderFakeItem(Items.CAKE.getDefaultInstance(), guiGraphics.guiWidth()-30, guiGraphics.guiHeight()-30);
            guiGraphics.renderFakeItem(Items.GREEN_CANDLE.getDefaultInstance(), guiGraphics.guiWidth()-30, guiGraphics.guiHeight()-39);
            guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, "🔥", guiGraphics.guiWidth()-22, guiGraphics.guiHeight()-36-AlinLib.MINECRAFT.font.lineHeight, Colors.CPM_YELLOW);
        }
    }
}
