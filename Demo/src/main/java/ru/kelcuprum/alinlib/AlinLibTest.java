package ru.kelcuprum.alinlib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.api.events.client.ClientLifecycleEvents;
import ru.kelcuprum.alinlib.api.events.client.ClientTickEvents;
import ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents;
import ru.kelcuprum.alinlib.command.AlinLibCommand;

public class AlinLibTest implements ClientModInitializer {
    public static final String MODID = "alinlibtest";

    @Override
    public void onInitializeClient() {
        AlinLib.VERSION = FabricLoader.getInstance().getModContainer(MODID).get().getMetadata().getVersion().getFriendlyString();
        AlinLib.onInitializeClient();
        ClientCommandRegistrationCallback.EVENT.register(AlinLibCommand::register);

        KeyMapping key = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "alinlibtest.resetCoords",
                GLFW.GLFW_KEY_0,
                "alinlib.name"
        ));

        KeyMapping tkey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "alinlibtest.toggleCoords",
                GLFW.GLFW_KEY_0,
                "alinlib.name"
        ));
        ClientTickEvents.END_CLIENT_TICK.register((s) -> {
            while (key.consumeClick()) {
                AlinLib.funnyCoordinatesX.clear();
                AlinLib.funnyCoordinatesZ.clear();
            }
            while (tkey.consumeClick()) {
                AlinLib.bariumConfig.setBoolean("STREAMER.STEALTH", !AlinLib.bariumConfig.getBoolean("STREAMER.STEALTH", false));
                AlinLib.bariumConfig.save();
            }
        });

        ClientLifecycleEvents.CLIENT_STARTED.register((client) -> AlinLib.log(String.format("Client started. MC Version: %s", client.getLaunchedVersion())));
        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> AlinLib.log(String.format("Client stopped. MC Version: %s", client.getLaunchedVersion())));
        GuiRenderEvents.RENDER.register((guiGraphics, partialTick) -> guiGraphics.drawCenteredString(AlinLib.MINECRAFT.font, AlinLib.localization.getParsedText("{minecraft.version}, Test coords: {player.pos.x} {player.pos.y} {player.pos.z}"), guiGraphics.guiWidth()/2, guiGraphics.guiHeight()-70, -1));
    }
}
