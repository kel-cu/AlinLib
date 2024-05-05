package ru.kelcuprum.alinlib.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforgespi.language.IModInfo;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.config.ConfigScreen;
import ru.kelcuprum.alinlib.neoforge.api.keybinding.KeyBindingRegistryImplNeoForge;
import ru.kelcuprum.alinlib.neoforge.api.render.GuiRenderEventsForge;

@Mod(AlinLib.MODID)
public class AlinLibNeoForge {
    public AlinLibNeoForge(){
        AlinLib.onInitializeClient();
        for(IModInfo mod : ModList.get().getMods()){
            if(mod.getModId().equals(AlinLib.MODID)){
                AlinLib.VERSION = mod.getVersion().getQualifier();
            }
        }
        if (FMLLoader.getDist() == Dist.CLIENT) {
            ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraftClient, screen) -> ConfigScreen.build(screen)));
            final IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
            bus.addListener(KeyBindingRegistryImplNeoForge::onRegisterKeyMappings);
            NeoForge.EVENT_BUS.addListener(GuiRenderEventsForge::onPostRenderGui);
        }
    }
}
