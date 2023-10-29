package ru.kelcuprum.barium.config;

import net.minecraft.client.Minecraft;

import java.io.File;

public class Localization {
    public String modID;
    public File filePath;
    public Localization(String modID, String filePath){
        this.modID = modID;
        this.filePath = new File(Minecraft.getInstance().gameDirectory + filePath);
    }

}
