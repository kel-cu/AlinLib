package ru.kelcuprum.alinlib.mixin.events.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
//#if MC >= 12109
import net.minecraft.client.input.KeyEvent;
//#endif
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.kelcuprum.alinlib.api.events.client.ScreenEvents;

@Mixin(value = Screen.class)
public class ScreenMixin {
    @Inject(method = "extractRenderState", at = @At("HEAD"))
    private void render(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        ScreenEvents.SCREEN_RENDER.invoker().onScreenRender((Screen) (Object) this, guiGraphics, mouseX, mouseY, partialTick);
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void keyPressed(
            //#if MC >= 12109
            KeyEvent keyEvent
            //#else
            //$$int keycode, int scanCode, int modifiers
            //#endif
            , CallbackInfoReturnable<Boolean> cir) {
        ScreenEvents.KEY_PRESS.invoker().onKeyPressed((Screen) (Object) this,
                //#if MC >= 12109
                keyEvent.key(), keyEvent.scancode(), keyEvent.modifiers()
                //#else
                //$$keycode, scanCode, modifiers
                //#endif
                , cir);
    }
}
