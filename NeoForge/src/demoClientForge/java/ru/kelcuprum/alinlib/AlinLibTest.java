package ru.kelcuprum.alinlib;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
//import ru.kelcuprum.alinlib.command.AlinLibCommand;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.screens.AlinaDemoScreen;

@Mod(AlinLibTest.MODID)
public class AlinLibTest {
    public static final String MODID = "alinlibtest";
    public static Localization localization = new Localization(MODID, "config/AlinLib/lang/");
    public static Minecraft MINECRAFT = Minecraft.getInstance();
    public AlinLibTest(){
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraftClient, screen) -> new AlinaDemoScreen().build(screen)));
    }
//    @Override
//    public void onInitializeClient() {
//        ClientCommandRegistrationCallback.EVENT.register(AlinLibCommand::register);
//    }
}
