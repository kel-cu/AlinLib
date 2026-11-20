package ru.kelcuprum.alinlib.config.parser;

import net.minecraft.client.Minecraft;
import org.meteordev.starscript.Script;
import org.meteordev.starscript.Section;
import org.meteordev.starscript.StandardLib;
import org.meteordev.starscript.Starscript;
import org.meteordev.starscript.compiler.Compiler;
import org.meteordev.starscript.compiler.Parser;
import org.meteordev.starscript.utils.Error;
import org.meteordev.starscript.utils.StarscriptError;
import org.meteordev.starscript.value.Value;
import org.meteordev.starscript.value.ValueMap;
import net.minecraft.SharedConstants;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.info.Player;
import ru.kelcuprum.alinlib.info.World;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class StarScript {
    public Starscript ss = new Starscript();

    public StarScript() {
        StandardLib.init(ss);
        // General
        ss.set("minecraft", new ValueMap()
                //#if MC <= 12105
                //$$ .set("version", SharedConstants.getCurrentVersion().getName())
                //#else
                .set("version", SharedConstants.getCurrentVersion().name())
                //#endif
                .set("loader", Minecraft.getInstance().getLaunchedVersion())
                .set("fps", () -> Value.number(Minecraft.getInstance().getFps()))
                .set("window", new ValueMap()
                        .set("width", () -> Value.number(Minecraft.getInstance().getWindow().getWidth()))
                        .set("height", () -> Value.number(Minecraft.getInstance().getWindow().getHeight()))
                        .set("scaled_width", () -> Value.number(Minecraft.getInstance().getWindow().getGuiScaledWidth()))
                        .set("scaled_height", () -> Value.number(Minecraft.getInstance().getWindow().getGuiScaledHeight()))
                        .set("fullscreen", () -> Value.bool(Minecraft.getInstance().getWindow()
                                //#if MC < 260200
                                //$$ .isFullscreen()
                                //#else
                                        .isExclusiveFullscreen()
                                //#endif
                        ))
                        .set("vsync", () -> Value.bool(Minecraft.getInstance().options.enableVsync().get()))
                )
        );
        ss.set("time", () -> Value.string(getTime()));
        // Player
        ss.set("player", new ValueMap()
                .set("name", () -> Value.string(Player.getName()))
                .set("uuid", () -> Value.string(Player.getUUID()))
                .set("license", () -> Value.string(""+Player.isLicenseAccount()))
                .set("health", () -> Value.number(Minecraft.getInstance().player != null ? Player.getHealth() : 0))
                .set("health_max", () -> Value.number(Minecraft.getInstance().player != null ? Player.getMaxHealth() : 0))
                .set("health_percent", () -> Value.number(Minecraft.getInstance().player != null ? Player.getPercentHealth() : 0))
                .set("armor", () -> Value.number(Minecraft.getInstance().player != null ? Player.getArmor() : 0))
                .set("direction", () -> Value.string(Minecraft.getInstance().player != null ? Player.getDirection(false) : ""))
                .set("direction_symbol", () -> Value.string(Minecraft.getInstance().player != null ? Player.getDirection(true) : ""))
                .set("debug.direction", () -> Value.string(Minecraft.getInstance().player != null ? Player.getDebugDirection(false) : ""))
                .set("debug.direction_symbol", () -> Value.string(Minecraft.getInstance().player != null ? Player.getDebugDirection(true) : ""))
                .set("ping", () -> Value.number(Minecraft.getInstance().player != null ? Player.getPing() : -1))
                .set("hunger", () -> Value.number(Minecraft.getInstance().player != null ? Player.getHunger() : 0))
                .set("pos", new ValueMap()
                        .set("x", () -> Value.number(Minecraft.getInstance().player != null ? Player.getX() : 0))
                        .set("y", () -> Value.number(Minecraft.getInstance().player != null ? Player.getY() : 0))
                        .set("z", () -> Value.number(Minecraft.getInstance().player != null ? Player.getZ() : 0))
                )

                .set("item", () -> Value.string(Minecraft.getInstance().player != null ? Player.getItemName() : ""))
                .set("item_count", () -> Value.number(Minecraft.getInstance().player != null ? Player.getItemCount() : 0))

                .set("xp", new ValueMap()
                        .set("level", () -> Value.number(Minecraft.getInstance().player != null ? Minecraft.getInstance().player.experienceLevel : 0))
                        .set("progress", () -> Value.number(Minecraft.getInstance().player != null ? Minecraft.getInstance().player.experienceProgress : 0))
                        .set("total", () -> Value.number(Minecraft.getInstance().player != null ? Minecraft.getInstance().player.totalExperience : 0))
                )
        );
        // World
        ss.set("world", new ValueMap()
                .set("name", () -> Value.string(Minecraft.getInstance().level != null ? World.getName() : ""))
                .set("time_type", () -> Value.string(Minecraft.getInstance().level != null ? World.getTimeType() : ""))
                .set("time_formatted", () -> Value.string(Minecraft.getInstance().level != null ? World.getFormattedTime() : ""))
                .set("time", () -> Value.number(Minecraft.getInstance().level != null ? World.getTime() : -1))
                .set("day_time", () -> Value.number(Minecraft.getInstance().level != null ? World.getTime() : -1))
                .set("days", () -> Value.number(Minecraft.getInstance().level != null ? World.getDays() : -1))
                .set("difficulty", () -> Value.string(Minecraft.getInstance().level != null ? Minecraft.getInstance().level.getDifficulty().getDisplayName().getString() : ""))
        );
    }
    // Helpers
    public String lastExceptionRunSection;
    public String lastExceptionRun;
    public String lastError;
    public long lastExceptionRunSectionCheck = 0;
    public long lastExceptionRunCheck = 0;
    public long lastErrorCheck = 0;

    public String getTime(){
        String clock;
        try {
            String strDateFormat = AlinLib.localization.getLocalization("date.time", false, false);
            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
            clock = dateFormat.format(System.currentTimeMillis());
            if(strDateFormat.contains("hh")) clock = clock+(new Date().getHours() >= 12 ? "pm" : "am");
        } catch (IllegalArgumentException ex) {
            clock = "[It's not correct format for Java SimpleDateFormat]";
        }
        return clock;
    }

    public Script compile(String source) {
        Parser.Result result = Parser.parse(source);

        if (result.hasErrors()) {
            for (Error error : result.errors) {
                if(!Objects.equals(lastError, error.message) && System.currentTimeMillis()-lastErrorCheck > 500){
                    lastError = error.message;
                    AlinLib.LOG.log("[StarScript/Errors] "+error.message, Level.ERROR);
                }
                lastErrorCheck = System.currentTimeMillis();
            }
            return null;
        }
        if(lastError != null) lastError = null;
        return Compiler.compile(result);
    }


    public Section runSection(Script script, StringBuilder sb) {
        try {
            if(lastExceptionRunSection != null) lastExceptionRunSection = null;
            return ss.run(script, sb);
        }
        catch (StarscriptError error) {
            if(!Objects.equals(lastExceptionRunSection, error.getLocalizedMessage()) && System.currentTimeMillis()-lastExceptionRunSectionCheck > 500){
                lastExceptionRunSection = error.getLocalizedMessage();
                AlinLib.LOG.log("[StarScript/runSection] "+error.getLocalizedMessage(), Level.ERROR);
            }
            lastExceptionRunSectionCheck = System.currentTimeMillis();
            return null;
        }
    }
    public String run(Script script, StringBuilder sb) {
        try {
            Section section = runSection(script, sb);
            if(lastExceptionRun != null) lastExceptionRun = null;
            return section == null ? "" : section.toString();
        } catch (Exception error){
            if(!Objects.equals(lastExceptionRun, error.getLocalizedMessage()) && System.currentTimeMillis()-lastExceptionRunCheck > 500){
                lastExceptionRun = error.getLocalizedMessage();
                AlinLib.LOG.log("[StarScript/run] "+error.getLocalizedMessage(), Level.ERROR);
            }
            lastExceptionRunCheck = System.currentTimeMillis();
            return "[AlinLib] StarScript error occurred, please check the console";
        }
    }


    public String run(Script script) {
        return run(script, new StringBuilder());
    }
}
