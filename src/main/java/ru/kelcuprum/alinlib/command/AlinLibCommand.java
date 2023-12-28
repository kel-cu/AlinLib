package ru.kelcuprum.alinlib.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import ru.kelcuprum.alinlib.gui.screens.AlinaDemoScreen;
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
                .then(literal("test_toast")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();
                            ResourceLocation icon = new ResourceLocation("alinlib", "textures/gui/widget/test/well.png");
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TM)"), Component.literal("Title, msg"))));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TM)"), Component.literal("Title, msg"), AlinaToast.Type.WARN)));

                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMI)"), Component.literal("Title, msg, item"), Items.CRAFTING_TABLE)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMI)"), Component.literal("Title, msg, item"), Items.BEACON, AlinaToast.Type.WARN)));

                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMRl)"), Component.literal("Title, msg, resource location"), icon)));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMRl)"), Component.literal("Title, msg, resource location"), icon, AlinaToast.Type.WARN)));

                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMP)"), Component.literal("Title, msg, player"), context.getSource().getPlayer().getSkin())));
                            client.tell(() -> client.getToasts().addToast(new AlinaToast(Component.literal("AlibLib (TMP)"), Component.literal("Title, msg, player"), context.getSource().getPlayer().getSkin(), AlinaToast.Type.WARN)));

                            return 1;
                        }))
                .executes(context -> {
                    context.getSource().getClient().getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Hello, world :3"), new ResourceLocation("alinlib", "textures/gui/widget/test/well.png")));
                    return 1;
                })
        );
    }
}
