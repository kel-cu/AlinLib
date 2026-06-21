package ru.kelcuprum.alinlib.mixin.events.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents;

@Mixin(value = Hud.class)
public class GuiMixin {

    @Shadow
    private boolean isHidden;

    @Inject(method = "extractRenderState", at = @At("RETURN"))
    private void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if(this.isHidden) return;
        GuiRenderEvents.RENDER.invoker().onRender(graphics, deltaTracker.getGameTimeDeltaTicks());
    }
}
