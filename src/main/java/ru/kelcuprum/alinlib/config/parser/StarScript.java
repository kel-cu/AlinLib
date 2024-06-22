package ru.kelcuprum.alinlib.config.parser;

import meteordevelopment.starscript.Script;
import meteordevelopment.starscript.Section;
import meteordevelopment.starscript.StandardLib;
import meteordevelopment.starscript.Starscript;
import meteordevelopment.starscript.compiler.Compiler;
import meteordevelopment.starscript.compiler.Parser;
import meteordevelopment.starscript.utils.Error;
import meteordevelopment.starscript.utils.StarscriptError;
import meteordevelopment.starscript.value.Value;
import meteordevelopment.starscript.value.ValueMap;
import net.minecraft.SharedConstants;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.parser.info.Player;
import ru.kelcuprum.alinlib.config.parser.info.World;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class StarScript {
    public Starscript ss = new Starscript();

    public StarScript() {
        StandardLib.init(ss);
        // General
        ss.set("minecraft", new ValueMap()
                .set("version", SharedConstants.getCurrentVersion().getName())
                .set("loader", AlinLib.MINECRAFT.getVersionType())
                .set("fps", () -> Value.number(AlinLib.MINECRAFT.getFps()))
                .set("window", new ValueMap()
                        .set("width", () -> Value.number(AlinLib.MINECRAFT.getWindow().getWidth()))
                        .set("height", () -> Value.number(AlinLib.MINECRAFT.getWindow().getHeight()))
                        .set("scaled_width", () -> Value.number(AlinLib.MINECRAFT.getWindow().getGuiScaledWidth()))
                        .set("scaled_height", () -> Value.number(AlinLib.MINECRAFT.getWindow().getGuiScaledHeight()))
                        .set("fullscreen", () -> Value.bool(AlinLib.MINECRAFT.getWindow().isFullscreen()))
                        .set("vsync", () -> Value.bool(AlinLib.MINECRAFT.options.enableVsync().get()))
                )
        );
        ss.set("time", () -> Value.string(new SimpleDateFormat(AlinLib.localization.getLocalization("date.time")).format(System.currentTimeMillis())));
        // Player
        ss.set("player", new ValueMap()
                .set("name", () -> Value.string(Player.getName()))
                .set("uuid", () -> Value.string(Player.getUUID()))
                .set("profile_type", () -> Value.string(Player.getProfileType()))
                .set("health", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getHealth() : 0))
                .set("health_max", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getMaxHealth() : 0))
                .set("health_percent", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getPercentHealth() : 0))
                .set("armor", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getArmor() : 0))
                .set("direction", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getDirection(false) : ""))
                .set("direction_symbol", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getDirection(true) : ""))
                .set("ping", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getPing() : -1))
                .set("hunger", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getHunger() : 0))
                .set("pos", new ValueMap()
                        .set("x", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getX() : 0))
                        .set("y", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getY() : 0))
                        .set("z", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getZ() : 0))
                )

                .set("item", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getItemName() : ""))
                .set("item_count", () -> Value.number(AlinLib.MINECRAFT.player != null ? Player.getItemCount() : 0))

                .set("xp", new ValueMap()
                        .set("level", () -> Value.number(AlinLib.MINECRAFT.player != null ? AlinLib.MINECRAFT.player.experienceLevel : 0))
                        .set("progress", () -> Value.number(AlinLib.MINECRAFT.player != null ? AlinLib.MINECRAFT.player.experienceProgress : 0))
                        .set("total", () -> Value.number(AlinLib.MINECRAFT.player != null ? AlinLib.MINECRAFT.player.totalExperience : 0))
                )
        );
        // World
        ss.set("world", new ValueMap()
                .set("name", () -> Value.string(AlinLib.MINECRAFT.level != null ? World.getName() : ""))
                .set("time_type", () -> Value.string(AlinLib.MINECRAFT.level != null ? World.getTimeType() : ""))
                .set("time_formatted", () -> Value.string(AlinLib.MINECRAFT.level != null ? World.getFormattedTime() : ""))
                .set("time", () -> Value.number(AlinLib.MINECRAFT.level != null ? World.getTime() : -1))
                .set("day_time", () -> Value.number(AlinLib.MINECRAFT.level != null ? World.getTime() : -1))
                .set("days", () -> Value.number(AlinLib.MINECRAFT.level != null ? World.getDays() : -1))
                .set("difficulty", () -> Value.string(AlinLib.MINECRAFT.level != null ? AlinLib.MINECRAFT.level.getDifficulty().getDisplayName().getString() : ""))
        );
    }
    // Helpers
    public String lastExceptionRunSection;
    public String lastExceptionRun;
    public String lastError;
    public long lastExceptionRunSectionCheck = 0;
    public long lastExceptionRunCheck = 0;
    public long lastErrorCheck = 0;
    public Script compile(String source) {
        Parser.Result result = Parser.parse(source);

        if (result.hasErrors()) {
            for (Error error : result.errors) {
                if(!Objects.equals(lastError, error.message) && System.currentTimeMillis()-lastErrorCheck > 500){
                    lastError = error.message;
                    AlinLib.log("[StarScript/Errors] "+error.message, Level.ERROR);
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
                AlinLib.log("[StarScript/runSection] "+error.getLocalizedMessage(), Level.ERROR);
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
                AlinLib.log("[StarScript/run] "+error.getLocalizedMessage(), Level.ERROR);
            }
            lastExceptionRunCheck = System.currentTimeMillis();
            return "[AlinLib] StarScript error occurred, please check the console";
        }
    }


    public String run(Script script) {
        return run(script, new StringBuilder());
    }
}
