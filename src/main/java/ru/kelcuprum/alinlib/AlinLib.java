package ru.kelcuprum.alinlib;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.Screen;
import org.meteordev.starscript.value.ValueMap;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.api.KeyMappingHelper;
import ru.kelcuprum.alinlib.api.events.alinlib.AlinLibEvents;
import ru.kelcuprum.alinlib.api.events.client.ClientLifecycleEvents;
import ru.kelcuprum.alinlib.api.events.alinlib.LocalizationEvents;
import ru.kelcuprum.alinlib.api.events.client.ClientTickEvents;
import ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.config.parser.StarScript;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.config.DesignScreen;
import ru.kelcuprum.alinlib.gui.styles.*;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;
import ru.kelcuprum.alinlib.info.World;
import ru.kelcuprum.alinlib.test.GUIRender;
import ru.kelcuprum.alinlib.utils.StealthManager;

import java.time.LocalDate;
import java.util.Random;

import static ru.kelcuprum.alinlib.gui.Icons.CLOWNFISH;
public class AlinLib implements net.fabricmc.api.ClientModInitializer {
    public static final String MODID = "alinlib";
    public static String VERSION = "alinlib";
    public static final AlinLogger LOG = new AlinLogger("AlinaLib");
    public static Config bariumConfig = new Config("config/AlinLib/config.json");
    public static Localization localization = new Localization("alinlib","config/AlinLib/lang");
    public static Minecraft MINECRAFT = Minecraft.getInstance();
    public static StarScript starScript;

    public static boolean isNotReleaseVersion(){
        return (VERSION.split("-").length > 1) && (VERSION.contains("beta") || VERSION.contains("alpha") || VERSION.contains("dev") || VERSION.contains("rc"));
    }

    // Init
    public static void init() {
        starScript = new StarScript();
        GuiUtils.registerStyle(new FlatStyle());
        GuiUtils.registerStyle(new ModernStyle());
        GuiUtils.registerStyle(new WinStyle());
        GuiUtils.registerStyle(new WMStyle());
//        if(FabricLoader.getInstance().isModLoaded("reglass"))
//            GuiUtils.registerStyle(new ReGlassStyle());
        World.registerDefaultWorlds();
        StealthManager.registerDefault();
        KeyMapping toggleStealth = KeyMappingHelper.register(CommonUtils.getKeyMapping(
                "alinlib.key.stealth",
                GLFW.GLFW_KEY_UNKNOWN,
                "alinlib"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            assert client.player != null;
            while (toggleStealth.consumeClick())
                bariumConfig.setBoolean("STREAMER.STEALTH", !bariumConfig.getBoolean("STREAMER.STEALTH", false));
        });
        if(FabricLoader.getInstance().isDevelopmentEnvironment()){
            KeyMapping openConfig = KeyMappingHelper.register(CommonUtils.getKeyMapping(
                    "alinlib.key.config",
                    GLFW.GLFW_KEY_L,
                    "alinlib"
            ));
            GuiRenderEvents.RENDER.register(new GUIRender());
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                assert client.player != null;
                while (openConfig.consumeClick()) MINECRAFT.setScreenAndShow(DesignScreen.build(MINECRAFT.gui.screen()));
            });
        }

        ClientLifecycleEvents.CLIENT_STARTED.register((client) -> {
            LocalizationEvents.DEFAULT_PARSER_INIT.invoker().onParserInit(starScript);
            LOG.log(String.format("Client started. MC Version: %s", client.getLaunchedVersion()));
        });
        ClientLifecycleEvents.CLIENT_FULL_STARTED.register((client) -> {
            LOG.log(String.format("Client full started. MC Version: %s", client.getLaunchedVersion()));
            aprilFool();
            hbKel();
        });
        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            LOG.log(Component.translatable("alinlib.log.exit.first"));
            LOG.log(Component.translatable("alinlib.log.exit.two"));
        });
        LocalizationEvents.DEFAULT_PARSER_INIT.register(parser -> parser.ss.set("alinlib", new ValueMap()
                .set("id", MODID)
                .set("version", VERSION))
        );
        AlinLibEvents.INIT.invoker().onInit();
    }

    @Override
    public void onInitializeClient() {
        AlinLib.VERSION = net.fabricmc.loader.api.FabricLoader.getInstance().getModContainer(AlinLib.MODID).get().getMetadata().getVersion().getFriendlyString();
        init();
    }

    // Funny
    public static boolean isAprilFool(){
        return LocalDate.now().getMonthValue() == 4 && LocalDate.now().getDayOfMonth() == 1;
    }
    public static void aprilFool(){
        if(isAprilFool()){
            String[] types = {
                    "white",
                    "welcome",
                    "clownfish"
            };
            String type = types[new Random().nextInt(types.length)];
            new ToastBuilder()
                    .setIcon(CLOWNFISH)
                    .setTitle(Component.literal("AlinLib"))
                    .setMessage(Component.translatable("alinlib.april_fools."+type))
                    .setType(ToastBuilder.Type.WARN)
                    .buildAndShow();
        }
    }
    public static void hbKel(){
        if(isHBKel()){
            if(bariumConfig.getBoolean("KEL_HB_"+LocalDate.now().getYear(), false)) return;
            bariumConfig.setBoolean("KEL_HB_"+LocalDate.now().getYear(), true);
            new ToastBuilder()
                    .setIcon(Items.CAKE)
                    .setTitle(Component.literal("AlinLib"))
                    .setMessage(Component.translatable("alinlib.hb"))
                    .buildAndShow();
        }
    }
    public static boolean isHBKel(){
        return LocalDate.now().getMonthValue() == 4 && LocalDate.now().getDayOfMonth() == 8;
    }
}
