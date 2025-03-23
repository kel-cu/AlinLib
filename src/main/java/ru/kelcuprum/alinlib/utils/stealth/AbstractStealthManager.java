package ru.kelcuprum.alinlib.utils.stealth;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

public abstract class AbstractStealthManager {
    public String id;
    public Component name;
    // данные
    public AbstractStealthManager(String id, String name){
        this(id, Component.literal(name));
    }

    public AbstractStealthManager(String id, Component name){
        this.id = id;
        this.name = name;
    }
    // get данные
    public abstract double getX(Entity player);
    public abstract double getY(Entity player);
    public abstract double getZ(Entity player);
    public abstract String getName(String string);
    public abstract Direction getDirection(Direction direction);
}
