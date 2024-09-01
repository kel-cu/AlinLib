package ru.kelcuprum.alinlib;

import java.io.File;

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
}
