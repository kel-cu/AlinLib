package ru.kelcuprum.alinlib;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.gui.GuiUtils;

import java.io.File;
import java.util.HashMap;

public class CommonUtils {
    public static long parseSeconds(long mills){
        return (mills-(mills % 1000)) /1000;
    }
    public static long getFileSize(File file){
        long length = 0;
        if(!file.exists()) return 0;
        if(file.isFile()) length = file.length();
        else for (File dirFile : file.listFiles()) {
            if (dirFile.isFile()) length += dirFile.length();
            else length += getFileSize(file);
        }
        return length;
    }

    public static boolean isValidFileName(String string){
        String refactor = string.replaceAll("[<>:\"?|\\\\/*]", "");
        return refactor.length() == string.length();
    }

    static long kilo = 1024;
    static long mega = kilo * kilo;
    static long giga = mega * kilo;
    static long tera = giga * kilo;

    public static String getParsedFileSize(File file) {
        return getParsedFileSize(getFileSize(file));
    }

    public static String getParsedFileSize(long size) {
        String s;
        double kb = (double)size / kilo;
        double mb = kb / kilo;
        double gb = mb / kilo;
        double tb = gb / kilo;
        if(size < kilo)  s = size + " Bytes";
        else if(size < mega) s =  String.format("%.2f", kb) + " KB";
        else if(size < giga) s = String.format("%.2f", mb) + " MB";
        else if(size < tera) s = String.format("%.2f", gb) + " GB";
        else s = String.format("%.2f", tb) + " TB";
        return s;
    }
    //#if MC >= 12109
    public static HashMap<String, KeyMapping.Category> categories = new HashMap<>();
    //#endif
    public static KeyMapping getKeyMapping(String path, int bind, String category){
        //#if MC >= 12109
        String[] args = category.split(":");
        KeyMapping.Category category1;
        if(categories.containsKey(category)) category1 = categories.get(category);
        else{
            category1 = args.length == 1 ? KeyMapping.Category.register(Identifier.parse(category)) : KeyMapping.Category.register(GuiUtils.getResourceLocation(args[0], args[1]));
            categories.put(category, category1);
        }
        //#endif
        return new KeyMapping(
                path,
                bind,
                //#if MC >= 12109
                category1
                //#else
                //$$ category
                //#endif
        );
    }
}
