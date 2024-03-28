package ru.kelcuprum.alinlib.mixin.events.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.kelcuprum.alinlib.api.AbstractEvent;
import ru.kelcuprum.alinlib.api.EventRegisters;
import ru.kelcuprum.alinlib.api.clients.lifecycle.ClientStartEvent;
import ru.kelcuprum.alinlib.api.clients.lifecycle.ClientStartTickEvent;
import ru.kelcuprum.alinlib.api.clients.lifecycle.ClientStopEvent;
import ru.kelcuprum.alinlib.api.clients.lifecycle.ClientStopTickEvent;

@Mixin(value = Minecraft.class)
public class MinecraftMixin {

    @Inject(at = @At(value = "HEAD"), method = "tick")
    private void onStartTick(CallbackInfo ci) {
        for(AbstractEvent event : EventRegisters.events){
            if(event instanceof ClientStartTickEvent) event.execute( (Minecraft) (Object) this);
        }
    }
    @Inject(at = @At(value = "RETURN"), method = "tick")
    private void onEndTick(CallbackInfo ci) {
        for(AbstractEvent event : EventRegisters.events){
            if(event instanceof ClientStopTickEvent) event.execute( (Minecraft) (Object) this);
        }
    }
    @Inject(at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;info(Ljava/lang/String;)V", shift = At.Shift.AFTER, remap = false), method = "destroy")
    private void onStopping(CallbackInfo ci) {
        for(AbstractEvent event : EventRegisters.events){
            if(event instanceof ClientStopEvent) event.execute( (Minecraft) (Object) this);
        }
    }
    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;gameThread:Ljava/lang/Thread;", shift = At.Shift.AFTER, ordinal = 0), method = "run")
    private void onStart(CallbackInfo ci) {
        for(AbstractEvent event : EventRegisters.events){
            if(event instanceof ClientStartEvent) event.execute( (Minecraft) (Object) this);
        }
    }
}
