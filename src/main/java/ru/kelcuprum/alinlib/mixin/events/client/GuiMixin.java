package ru.kelcuprum.alinlib.mixin.events.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents;

@Mixin(value = Gui.class)
public class GuiMixin {
    @Inject(method = "render", at = @At("RETURN"))
    private void render(GuiGraphics guiGraphics,
                        //#if MC >= 12100
                        net.minecraft.client.DeltaTracker deltaTracker
                        //#elseif MC < 12100
                        //$$ float tick
                        //#endif
            , CallbackInfo ci) {
        if(AlinLib.MINECRAFT.options.hideGui) return;
        GuiRenderEvents.RENDER.invoker().onRender(guiGraphics,
                //#if MC >= 12100
                deltaTracker.getGameTimeDeltaTicks()
                //#elseif MC < 12100
                //$$ tick
                //#endif
        );
    }
}
