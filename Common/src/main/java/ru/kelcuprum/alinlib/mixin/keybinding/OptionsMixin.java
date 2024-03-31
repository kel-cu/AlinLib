package ru.kelcuprum.alinlib.mixin.keybinding;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.kelcuprum.alinlib.api.keybinding.KeyBindingRegistryImpl;

@Mixin(value = Options.class, priority = -1)
public class OptionsMixin {
    @Mutable
    @Final
    @Shadow
    public KeyMapping[] keyMappings;
    @Inject(at = @At("HEAD"), method = "load()V")
    public void loadHook(CallbackInfo info) {
        keyMappings = KeyBindingRegistryImpl.process(keyMappings);
    }
}
