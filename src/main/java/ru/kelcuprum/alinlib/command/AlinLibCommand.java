package ru.kelcuprum.alinlib.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.gui.screens.AlinaDemoScreen;
import ru.kelcuprum.alinlib.gui.screens.AlinaDemoFlatScreen;
import ru.kelcuprum.alinlib.gui.toast.AlinaFlatToast;
import ru.kelcuprum.alinlib.gui.toast.AlinaToast;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AlinLibCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(literal("alinlib")
                .then(literal("demo_screen")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();
                            client.tell(() -> client.setScreen(new AlinaDemoScreen(client.screen)));
                            return 1;
                        })
                )
                .then(literal("demo_flat_screen")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();
                            client.tell(() -> client.setScreen(new AlinaDemoFlatScreen(client.screen)));
                            return 1;
                        })
                )
                .then(literal("test_toast")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMFf)"), Component.literal("Title, msg, fail false"), false)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMFt)"), Component.literal("Title, msg, fail true"), true)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMI)"), Component.literal("Title, msg, item"), Items.BEACON)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMC)"), Component.literal("Title, msg, color"), Colors.TETRA)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMIC)"), Component.literal("Title, msg, item, color"), Items.BEACON, Colors.SEABIRD)));
                            ResourceLocation icon = new ResourceLocation("alinlib", "icon.png");
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMRl)"), Component.literal("Title, msg, resource location"), icon)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMRlFf)"), Component.literal("Title, msg, resource location, fail true"), icon, true)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMRlFf)"), Component.literal("Title, msg, resource location, fail false"), icon, false)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMRlC)"), Component.literal("Title, msg, resource location, color"), icon, Colors.CONVICT)));

                            return 1;
                        }))
                .then(literal("test_flat_toast")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();
                            ResourceLocation icon = new ResourceLocation("alinlib", "icon.png");
                            client.tell(() -> client.getToasts().addToast(new AlinaFlatToast(Component.literal("AlibLib (TM)"), Component.literal("Title, msg"))));
                            client.tell(() -> client.getToasts().addToast(new AlinaFlatToast(Component.literal("AlibLib (TMF)"), Component.literal("Title, msg, fail"), false)));
                            client.tell(() -> client.getToasts().addToast(new AlinaFlatToast(Component.literal("AlibLib (TMI)"), Component.literal("Title, msg, item"), Items.BEACON)));
                            client.tell(() -> client.getToasts().addToast(new AlinaFlatToast(Component.literal("AlibLib (TMRl)"), Component.literal("Title, msg, resource location"), icon)));
                            return 1;
                        }))
                .executes(context -> {
                    context.getSource().getClient().getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Hello, world :3"), false));
                    return 1;
                })
        );
    }
}
