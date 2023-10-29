package ru.kelcuprum.barium.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.barium.gui.DemoBariumScreen;
import ru.kelcuprum.barium.gui.toast.BariumToast;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class BariumCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(literal("barium")
                .then(literal("test_screen")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();
                            client.tell(() -> client.setScreen(new DemoBariumScreen(client.screen)));
                            return 1;
                        })
                ).executes(context -> {
                    context.getSource().getClient().getToasts().addToast(new BariumToast(Component.literal("Barial"), Component.literal("Hello, world!"), false));
                    return 1;
                })
        );
    }
}
