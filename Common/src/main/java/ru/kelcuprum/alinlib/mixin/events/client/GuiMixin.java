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
    @Inject(method = "render", at = @At("HEAD"))
    private void render(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        if(AlinLib.MINECRAFT.options.hideGui) return;
        GuiRenderEvents.RENDER.invoker().onRender(guiGraphics, partialTick);
    }
}
