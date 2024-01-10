package ru.kelcuprum.alinlib.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import ru.kelcuprum.alinlib.AlinLibTest;
import ru.kelcuprum.alinlib.gui.screens.AlinaDemoScreen;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AlinLibCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(literal("alinlib")
                .then(literal("demo_screen")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();
                            client.tell(() -> client.setScreen(new AlinaDemoScreen().build(client.screen)));
                            return 1;
                        })
                )
                .then(literal("test_toast")
                        .executes(context -> {
                            Minecraft client = context.getSource().getClient();

                            client.tell(() -> {
                                ToastBuilder tm = new ToastBuilder()
                                        .setTitle(Component.literal("AlibLib (TM)"))
                                        .setMessage(Component.literal("Title, msg"));

                                tm.show(client.getToasts());
                                tm.setType(ToastBuilder.Type.WARN)
                                        .show(client.getToasts());

                                ToastBuilder tmi = new ToastBuilder()
                                        .setTitle(Component.literal("AlibLib (TMI)"))
                                        .setMessage(Component.literal("Title, msg, item"));

                                tmi.setIcon(Items.CRAFTING_TABLE)
                                        .show(client.getToasts());
                                tmi.setMessage(Component.literal("Title, msg, item"))
                                        .setIcon(Items.BEACON)
                                        .setType(ToastBuilder.Type.WARN)
                                        .show(client.getToasts());

                                ToastBuilder tmrl = new ToastBuilder()
                                        .setTitle(Component.literal("AlibLib (TMRl)"))
                                        .setMessage(Component.literal("Title, msg, resource location"))
                                        .setIcon(new ResourceLocation(AlinLibTest.MODID, "textures/gui/widget/test/well.png"));

                                tmrl.show(client.getToasts());
                                tmrl.setType(ToastBuilder.Type.WARN)
                                        .show(client.getToasts());

                                ToastBuilder tmp = new ToastBuilder()
                                        .setTitle(Component.literal("AlibLib (TMP)"))
                                        .setMessage(Component.literal("Title, msg, player"))
                                        .setIcon(context.getSource().getPlayer());

                                tmp.show(client.getToasts());
                                tmp.setType(ToastBuilder.Type.WARN)
                                        .show(client.getToasts());
                            });

                            return 1;
                        }))
                .executes(context -> {
                    new ToastBuilder()
                            .setTitle(Component.literal("AlinLib"))
                            .setMessage(Component.literal("Hello, world :3"))
                            .setIcon(AlinLibTest.MODID, "textures/gui/widget/test/well.png")
                            .show(context.getSource().getClient().getToasts());
                    return 1;
                })
        );
    }
}
