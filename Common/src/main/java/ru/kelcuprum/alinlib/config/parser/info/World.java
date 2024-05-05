package ru.kelcuprum.alinlib.config.parser.info;

import ru.kelcuprum.alinlib.AlinLib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class World {
    public static String getTimeType(){
        if(AlinLib.MINECRAFT.level == null) return "";
        long currentTime = AlinLib.MINECRAFT.level.getDayTime() % 24000;
        if (currentTime < 6000 && currentTime > 0) {
            return AlinLib.localization.getLocalization("time.morning", false, false);
        } else if (currentTime < 12000 && currentTime > 6000) {
            return AlinLib.localization.getLocalization("time.day", false, false);
        } else if (currentTime < 16500 && currentTime > 12000) {
            return AlinLib.localization.getLocalization("time.evening", false, false);
        } else if (currentTime > 16500) {
            return AlinLib.localization.getLocalization("time.night", false, false);
        } else {
            return "";
        }
    }
    public static String getTime(){
        if(AlinLib.MINECRAFT.level == null) return "";
        long daytime = AlinLib.MINECRAFT.level.getDayTime()+6000;
        int hours=(int) (daytime / 1000)%24;
        int minutes = (int) ((daytime % 1000)*60/1000);
        int day = (int) daytime / 1000 / 24;
        String clock;
        try {
            String strDateFormat = AlinLib.localization.getLocalization("date.time", false, false);
            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
            Calendar calendar = new GregorianCalendar();
            calendar.set(2000, Calendar.JANUARY, day+1, hours, minutes, 0);

            clock = dateFormat.format(calendar.getTimeInMillis());
        } catch (IllegalArgumentException ex) {
            clock = "[It's not correct format for Java SimpleDateFormat]";
        }
        return clock;

    }
    public static String getCodeName(){
        return AlinLib.MINECRAFT.level == null ? "abi:unknown" : AlinLib.MINECRAFT.level.dimension().location().toString();
    }
    public static String getName(){
        String world = getCodeName();
        if(world.equals("minecraft:the_moon")) return AlinLib.localization.getLocalization("world.moon", false, false);
        if(world.equals("minecraft:the_end")) return AlinLib.localization.getLocalization("world.the_end", false, false);
        if(world.equals("minecraft:the_nether")) return AlinLib.localization.getLocalization("world.nether", false, false);
        if(world.equals("minecraft:overworld")) return AlinLib.localization.getLocalization("world.overworld", false, false);
        return AlinLib.localization.getLocalization("world.unknown", false, false);
    }
}
