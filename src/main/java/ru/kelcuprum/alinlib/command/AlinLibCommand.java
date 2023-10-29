package ru.kelcuprum.alinlib.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.DemoBariumScreen;
import ru.kelcuprum.alinlib.gui.toast.AlinLibToast;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AlinLibCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(literal("alinlib")
                .then(literal("demo_screen")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();
                            client.tell(() -> client.setScreen(new DemoBariumScreen(client.screen)));
                            return 1;
                        })
                ).executes(context -> {
                    context.getSource().getClient().getToasts().addToast(new AlinLibToast(Component.literal("AlinLib"), Component.literal("Hello, world :3"), false));
                    return 1;
                })
        );
    }
}
