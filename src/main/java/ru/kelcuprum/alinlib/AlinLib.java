package ru.kelcuprum.alinlib;

import meteordevelopment.starscript.value.ValueMap;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.api.KeyMappingHelper;
import ru.kelcuprum.alinlib.api.events.alinlib.AlinLibEvents;
import ru.kelcuprum.alinlib.api.events.client.ClientLifecycleEvents;
import ru.kelcuprum.alinlib.api.events.alinlib.LocalizationEvents;
import ru.kelcuprum.alinlib.api.events.client.ClientTickEvents;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.config.parser.StarScript;
import ru.kelcuprum.alinlib.config.parser.info.Player;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.styles.FlatStyle;
import ru.kelcuprum.alinlib.gui.styles.ModernStyle;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;

import static ru.kelcuprum.alinlib.gui.Icons.CLOWNFISH;
//#if FORGE
//$$ @net.minecraftforge.fml.common.Mod("alinlib")
//$$ @net.minecraftforge.fml.common.Mod.EventBusSubscriber(bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD, value = net.minecraftforge.api.distmarker.Dist.CLIENT)
//#elseif NEOFORGE
//$$ @net.neoforged.fml.common.Mod("alinlib")
//#endif
public class AlinLib
    //#if FABRIC
        implements net.fabricmc.api.ClientModInitializer
    //#endif
{
    public static boolean isFabricLoader = false;
    public static final String MODID = "alinlib";
    public static String VERSION = "alinlib";
    public static final Logger LOG = LogManager.getLogger("AlinaLib");
    public static Config bariumConfig = new Config("config/AlinLib/config.json");
    public static Localization localization = new Localization("alinlib","config/AlinLib/lang");
    public static Minecraft MINECRAFT = Minecraft.getInstance();
    public static StarScript starScript;
    public static HashMap<String, Double> funnyCoordinatesX$alinLib = new HashMap<>();
    public static HashMap<String, Double> funnyCoordinatesZ$alinLib = new HashMap<>();
    ///
    public static HashMap<String, Double> funnyCoordinatesX$imGRUI = new HashMap<>();
    public static HashMap<String, Double> funnyCoordinatesZ$imGRUI = new HashMap<>();

    // Init
    public static void init() {
        starScript = new StarScript();
        GuiUtils.registerStyle(new FlatStyle());
        GuiUtils.registerStyle(new ModernStyle());
        KeyMapping toggleStealth = KeyMappingHelper.register(new KeyMapping(
                "alinlib.key.stealth",
                GLFW.GLFW_KEY_UNKNOWN,
                "alinlib"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            assert client.player != null;
            while (toggleStealth.consumeClick()) {
                bariumConfig.setBoolean("STREAMER.STEALTH", !bariumConfig.getBoolean("STREAMER.STEALTH", false));
                bariumConfig.save();
            }
        });

        ClientLifecycleEvents.CLIENT_STARTED.register((client) -> {
            LocalizationEvents.DEFAULT_PARSER_INIT.invoker().onParserInit(starScript);
            AlinLib.log(String.format("Client started. MC Version: %s", client.getLaunchedVersion()));
        });
        ClientLifecycleEvents.CLIENT_FULL_STARTED.register((client) -> {
            AlinLib.log(String.format("Client full started. MC Version: %s", client.getLaunchedVersion()));
            isAprilFool();
            isHBKel();
        });
        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            log(Component.translatable("alinlib.log.exit.first"));
            log(Component.translatable("alinlib.log.exit.two"));
        });
        LocalizationEvents.DEFAULT_PARSER_INIT.register(parser -> parser.ss.set("alinlib", new ValueMap()
                .set("id", MODID)
                .set("version", VERSION))
        );
        AlinLibEvents.INIT.invoker().onInit();
    }
    //#if FABRIC
    @Override
    public void onInitializeClient() {
        AlinLib.isFabricLoader = true;
        AlinLib.VERSION = net.fabricmc.loader.api.FabricLoader.getInstance().getModContainer(AlinLib.MODID).get().getMetadata().getVersion().getFriendlyString();
        KeyMappingHelper.onRegister = net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper::registerKeyBinding;
        init();
    }
    //#elseif FORGE
    //$$  public static final java.util.List<KeyMapping> EXAMPLE_MAPPING = new java.util.ArrayList<>();
    //$$  public AlinLib(){
    //$$      KeyMappingHelper.onRegister = (s) -> {
    //$$          EXAMPLE_MAPPING.add(s);
    //$$          return s;
    //$$      };
    //$$      init();
    //$$     for(net.minecraftforge.forgespi.language.IModInfo mod : net.minecraftforge.fml.ModList.get().getMods()){
    //$$          if(mod.getModId().equals(AlinLib.MODID)){
    //$$              AlinLib.VERSION = mod.getVersion().getQualifier();
    //$$          }
    //$$      }
    //$$      if (net.minecraftforge.fml.loading.FMLLoader.getDist() == net.minecraftforge.api.distmarker.Dist.CLIENT) {
    //$$          net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerKeymappings);
    //$$          registerScreen();
    //$$          net.minecraftforge.common.MinecraftForge.EVENT_BUS.addListener(this::onPostRenderGui);
    //$$      }
    //$$  }
    //$$  public void onPostRenderGui(net.minecraftforge.client.event.RenderGuiEvent.Post event) {
    //$$      ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents.RENDER.invoker().onRender(event.getGuiGraphics(), event.getPartialTick());
    //$$  }
    //$$  public void registerKeymappings(net.minecraftforge.client.event.RegisterKeyMappingsEvent event) {
    //$$      for(KeyMapping mapping : EXAMPLE_MAPPING) {
    //$$          event.register(mapping);
    //$$      }
    //$$  }
    //#elseif NEOFORGE
    //$$  public static final java.util.List<KeyMapping> EXAMPLE_MAPPING = new java.util.ArrayList<>();
    //$$  public AlinLib(){
    //$$      KeyMappingHelper.onRegister = (s) -> {
    //$$          EXAMPLE_MAPPING.add(s);
    //$$          return s;
    //$$      };
    //$$      init();
    //$$      for(net.neoforged.neoforgespi.language.IModInfo mod : net.neoforged.fml.ModList.get().getMods()){
    //$$          if(mod.getModId().equals(AlinLib.MODID)){
    //$$              AlinLib.VERSION = mod.getVersion().getQualifier();
    //$$          }
    //$$      }
    //$$      if (net.neoforged.fml.loading.FMLLoader.getDist() == net.neoforged.api.distmarker.Dist.CLIENT) {
    //$$          net.neoforged.fml.ModLoadingContext.get().registerExtensionPoint(
    //$$                  net.neoforged.neoforge.client.gui.IConfigScreenFactory.class,
    //$$                  () -> (minecraftClient, screen) -> ru.kelcuprum.alinlib.gui.config.DesignScreen.build(screen));
    //$$          final net.neoforged.bus.api.IEventBus bus = net.neoforged.fml.ModLoadingContext.get().getActiveContainer().getEventBus();
    //$$          bus.addListener(this::registerBindings);
    //$$          net.neoforged.neoforge.common.NeoForge.EVENT_BUS.addListener(this::onPostRenderGui);
    //$$      }
    //$$  }
    //$$  @net.neoforged.bus.api.SubscribeEvent
    //$$  public void registerBindings(net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent event) {
    //$$      for(KeyMapping mapping : EXAMPLE_MAPPING) {
    //$$          event.register(mapping);
    //$$      }
    //$$  }
    //#endif

    //#if NEOFORGE && MC >= 12100
    //$$  public void onPostRenderGui(net.neoforged.neoforge.client.event.RenderGuiEvent.Post event) {
    //$$      ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents.RENDER.invoker().onRender(event.getGuiGraphics(), event.getPartialTick().getGameTimeDeltaTicks());
    //$$  }
    //#elseif NEOFORGE && MC < 12100
    //$$  public void onPostRenderGui(net.neoforged.neoforge.client.event.RenderGuiEvent.Post event) {
    //$$      ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents.RENDER.invoker().onRender(event.getGuiGraphics(), event.getPartialTick());
    //$$  }
    //#endif

    //#if FORGE && MC < 12002
    //$$ public void registerScreen(){
    //$$          net.minecraftforge.fml.ModLoadingContext.get().registerExtensionPoint(
    //$$                  net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory.class,
    //$$                  () -> new net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory((java.util.function.Function<net.minecraft.client.gui.screens.Screen, net.minecraft.client.gui.screens.Screen>) ru.kelcuprum.alinlib.gui.config.DesignScreen::build));
    //$$ }
    //#elseif FORGE && MC >= 12002
    //$$ public void registerScreen(){
    //$$          net.minecraftforge.fml.ModLoadingContext.get().registerExtensionPoint(
    //$$                  net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory.class,
    //$$                  () -> new net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory(ru.kelcuprum.alinlib.gui.config.DesignScreen::build));
    //$$ }
    //#endif
    // Stealth
    public static double getFunnyValueCoordinate(double coordinate, String server, String world, boolean isX){
        return switch (bariumConfig.getNumber("STREAMER.STEALTH.TYPE", 0).intValue()){
            case 1 -> getFunnyValueCoordinate$kelVersion(coordinate, server, world, isX);
            default -> getFunnyValueCoordinate$ImGRUIVersion(coordinate, server, world, isX);
        };
    }
    public static double getFunnyValueCoordinate$kelVersion(double coordinate, String server, String world, boolean isX){
        String info =  server + "-" + world;
        double value;
        if(isX ? funnyCoordinatesX$alinLib.containsKey(info) : funnyCoordinatesZ$alinLib.containsKey(info)) value = isX ? funnyCoordinatesX$alinLib.get(info) : funnyCoordinatesZ$alinLib.get(info);
        else {
            while(true){
                double r = Math.random();
                int i = Math.random() < 0.5 ? -1 : 1;
                double m= Math.random() * 10;
                value = r*i*m;
                if((value > -1.25 && value < -0.75 ) || (value > 0.75 && value < 1.25)){
                    if(isX) funnyCoordinatesX$alinLib.put(info, value); else funnyCoordinatesZ$alinLib.put(info, value);
                    log(info+": "+value+(isX ? " x" : " z"));
                    break;
                }
            }
        }
        return coordinate * value;
    }
    public static double getFunnyValueCoordinate$ImGRUIVersion(double coordinate, String server, String world, boolean isX){
        String info =  server + "-" + world;
        double value;
        if(isX ? funnyCoordinatesX$imGRUI.containsKey(info) : funnyCoordinatesZ$imGRUI.containsKey(info)) value = isX ? funnyCoordinatesX$imGRUI.get(info) : funnyCoordinatesZ$imGRUI.get(info);
        else {
            while(true){
                int i = Math.random() < 0.5 ? -1 : 1;
                value = 2*Math.random()*i;
                if((value > -1.25 && value < -0.75 ) || (value > 0.75 && value < 1.25)){
                    if(isX) funnyCoordinatesX$imGRUI.put(info, value); else funnyCoordinatesZ$imGRUI.put(info, value);
                    log(info+": "+value+(isX ? " x" : " z"));
                    break;
                }
            }
        }
        if(!isX) {
            if (Player.getX() > 0 && coordinate > 0 && value > 0) value *= -1;
        }
        return coordinate*value;
    }
    // Funny
    public static void isAprilFool(){
        if(LocalDate.now().getMonthValue() == 4 && LocalDate.now().getDayOfMonth() == 1){
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
                    .show(MINECRAFT.getToasts());
        }
    }
    public static void isHBKel(){
        if(LocalDate.now().getMonthValue() == 4 && LocalDate.now().getDayOfMonth() == 8){
            if(bariumConfig.getBoolean("KEL_HB_"+LocalDate.now().getYear(), false)) return;
            bariumConfig.setBoolean("KEL_HB_"+LocalDate.now().getYear(), true);
            new ToastBuilder()
                    .setIcon(Items.CAKE)
                    .setTitle(Component.literal("AlinLib"))
                    .setMessage(Component.translatable("alinlib.hb"))
                    .show(MINECRAFT.getToasts());
        }
    }

    // Logs
    public static void log(Component message) { log(message, Level.INFO);}
    public static void log(Component message, Level level) { log(message.getString(), level);}
    public static void log(String message) { log(message, Level.INFO);}
    public static void log(String message, Level level) { LOG.log(level, "[" + LOG.getName() + "] " + message); }
}
