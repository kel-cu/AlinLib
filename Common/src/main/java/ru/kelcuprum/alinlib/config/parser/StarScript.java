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

public class StarScript {
    public Starscript ss = new Starscript();

    public StarScript() {
        StandardLib.init(ss);
        // General
        ss.set("minecraft", new ValueMap()
                .set("version", SharedConstants.getCurrentVersion().getName())
                .set("loader", AlinLib.MINECRAFT.getVersionType())
                .set("fps", () -> Value.number(AlinLib.MINECRAFT.getFps()))
        );
        ss.set("time", () -> Value.string(new SimpleDateFormat(AlinLib.localization.getLocalization("date.time")).format(System.currentTimeMillis())));
        // Player
        ss.set("player", new ValueMap()
                .set("name", () -> Value.string(AlinLib.MINECRAFT.getUser().getName()))
                .set("health", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getHealth() : ""))
                .set("health_max", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getMaxHealth() : ""))
                .set("health_percent", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getPercentHealth() : ""))
                .set("armor", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getArmor() : ""))
                .set("direction", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getDirection(false) : ""))
                .set("direction_symbol", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getDirection(true) : ""))
                .set("hunger", () -> Value.number(AlinLib.MINECRAFT.player != null ? AlinLib.MINECRAFT.player.getFoodData().getFoodLevel() : 0))
                .set("pos", new ValueMap()
                        .set("x", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getX() : ""))
                        .set("y", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getY() : ""))
                        .set("z", () -> Value.string(AlinLib.MINECRAFT.player != null ? Player.getZ() : ""))
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
                .set("time", () -> Value.string(AlinLib.MINECRAFT.level != null ? World.getTime() : ""))
                .set("difficulty", () -> Value.string(AlinLib.MINECRAFT.level != null ? AlinLib.MINECRAFT.level.getDifficulty().getDisplayName().getString() : ""))
        );
    }
    // Helpers

    public Script compile(String source) {
        Parser.Result result = Parser.parse(source);

        if (result.hasErrors()) {
            for (Error error : result.errors) AlinLib.log(error.message, Level.ERROR);
            return null;
        }

        return Compiler.compile(result);
    }

    public Section runSection(Script script, StringBuilder sb) {
        try {
            return ss.run(script, sb);
        }
        catch (StarscriptError error) {
            AlinLib.log(error.getLocalizedMessage(), Level.ERROR);
            return null;
        }
    }
    public String run(Script script, StringBuilder sb) {
        try {
            Section section = runSection(script, sb);
            return section == null ? "" : section.toString();
        } catch (Exception e){
            AlinLib.log(e.getLocalizedMessage(), Level.ERROR);
            return e.getLocalizedMessage();
        }
    }

    public String run(Script script) {
        return run(script, new StringBuilder());
    }
}
