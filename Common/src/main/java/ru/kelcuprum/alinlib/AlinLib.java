package ru.kelcuprum.alinlib;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.api.events.client.ClientLifecycleEvents;
import ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents;
import ru.kelcuprum.alinlib.api.keybinding.KeyBindingHelper;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import java.time.LocalDate;
import java.util.Random;

public class AlinLib {
    public static boolean isFabricLoader = false;
    public static final String MODID = "alinlib";
    public static final Logger LOG = LogManager.getLogger("AlinaLib");
    public static Config bariumConfig = new Config("config/AlinLib/config.json");
    public static Minecraft MINECRAFT = Minecraft.getInstance();
    
    public static void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register((client) -> AlinLib.log(String.format("Client started. MC Version: %s", client.getLaunchedVersion())));
        ClientLifecycleEvents.CLIENT_FULL_STARTED.register((client) -> AlinLib.log(String.format("Client full started. MC Version: %s", client.getLaunchedVersion())));
        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            log("This world goes round and round like a carousel in a circus.");
            log("Maybe the world is a circus?)");
        });
    }
    public static void isAprilFool(){
        if(LocalDate.now().getMonthValue() == 4 && LocalDate.now().getDayOfMonth() == 1){
            String[] types = {
                    "white",
                    "welcome",
                    "clownfish"
            };
            String type = types[new Random().nextInt(types.length)];
            new ToastBuilder()
                    .setIcon(new ResourceLocation("alinlib", "textures/gui/widget/toast/clownfish.png"))
                    .setTitle(Component.literal("AlinLib"))
                    .setMessage(Component.translatable("alinlib.april_fools."+type))
                    .setType(ToastBuilder.Type.WARN)
                    .show(MINECRAFT.getToasts());
        }
    }
    public static void log(Component message) { log(message, Level.INFO);}
    public static void log(Component message, Level level) { log(message.getString(), level);}
    public static void log(String message) { log(message, Level.INFO);}
    public static void log(String message, Level level) { LOG.log(level, "[" + LOG.getName() + "] " + message); }
}
