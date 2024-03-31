package ru.kelcuprum.alinlib.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.neoforge.api.keybinding.KeyBindingRegistryImplNeoForge;

@Mod("alinlib")
public class AlinLibNeoForge {
    public AlinLibNeoForge(){
        AlinLib.onInitializeClient();
        if (FMLLoader.getDist() == Dist.CLIENT) {
            final IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
            bus.addListener(KeyBindingRegistryImplNeoForge::onRegisterKeyMappings);
        }
    }
}
