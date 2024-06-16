package ru.kelcuprum.alinlib.mixin.events.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.kelcuprum.alinlib.api.events.client.ClientLifecycleEvents;
import ru.kelcuprum.alinlib.api.events.client.ClientTickEvents;

@Mixin(value = Minecraft.class)
public class MinecraftMixin {

    @Shadow @Nullable private Overlay overlay;

    @Inject(at = @At(value = "HEAD"), method = "tick")
    private void onStartTick(CallbackInfo ci) {
        if(this.overlay == null && !ClientLifecycleEvents.isClientFullStarted){
            ClientLifecycleEvents.isClientFullStarted = true;
            ClientLifecycleEvents.CLIENT_FULL_STARTED.invoker().onClientFullStarted((Minecraft) (Object) this);
        }
        ClientTickEvents.START_CLIENT_TICK.invoker().onStartTick((Minecraft) (Object) this);
    }
    @Inject(at = @At(value = "RETURN"), method = "tick")
    private void onEndTick(CallbackInfo ci) {
        ClientTickEvents.END_CLIENT_TICK.invoker().onEndTick((Minecraft) (Object) this);
    }
    @Inject(at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;info(Ljava/lang/String;)V", shift = At.Shift.AFTER, remap = false), method = "destroy")
    private void onStopping(CallbackInfo ci) {
        ClientLifecycleEvents.CLIENT_STOPPING.invoker().onClientStopping((Minecraft) (Object) this);
    }
    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;gameThread:Ljava/lang/Thread;", shift = At.Shift.AFTER, ordinal = 0), method = "run")
    private void onStart(CallbackInfo ci) {
        ClientLifecycleEvents.CLIENT_STARTED.invoker().onClientStarted((Minecraft) (Object) this);
    }
}
