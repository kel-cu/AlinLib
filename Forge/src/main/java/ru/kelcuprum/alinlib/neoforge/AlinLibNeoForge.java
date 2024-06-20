package ru.kelcuprum.alinlib.neoforge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.forgespi.language.IModInfo;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.api.KeyMappingHelper;
import ru.kelcuprum.alinlib.gui.config.ConfigScreen;
import ru.kelcuprum.alinlib.neoforge.api.render.GuiRenderEventsForge;
import ru.kelcuprum.alinlib.neoforge.api.render.KeyMappingRegister;

@Mod(AlinLib.MODID)
public class AlinLibNeoForge {
    public AlinLibNeoForge(){
        KeyMappingHelper.onRegister = (s) -> {
            KeyMappingRegister.EXAMPLE_MAPPING.add(s);
            return s;
        };
        AlinLib.onInitializeClient();
        for(IModInfo mod : ModList.get().getMods()){
            if(mod.getModId().equals(AlinLib.MODID)){
                AlinLib.VERSION = mod.getVersion().getQualifier();
            }
        }
        if (FMLLoader.getDist() == Dist.CLIENT) {
            ModLoadingContext.get().registerExtensionPoint(
                    ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory(ConfigScreen::build));
            MinecraftForge.EVENT_BUS.addListener(KeyMappingRegister::registerBindings);
            MinecraftForge.EVENT_BUS.addListener(GuiRenderEventsForge::onPostRenderGui);
        }
    }
}
