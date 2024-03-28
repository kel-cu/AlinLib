package ru.kelcuprum.alinlib.api;

import net.minecraft.client.Minecraft;
import ru.kelcuprum.alinlib.AlinLib;

public abstract class AbstractEvent {
    public String name = "AbstractName";
    public String description = "The abstract event";
    public AbstractEvent(String name, String description){
        this.name = name;
        this.description = description;
    }
    public void execute(Object object){
        AlinLib.log("Abstract event executed");
    }

    public abstract void execute(Minecraft minecraft);
}
