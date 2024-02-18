package ru.kelcuprum.alinlib.neoforge;

import net.neoforged.fml.common.Mod;
import ru.kelcuprum.alinlib.AlinLib;

@Mod("alinlib")
public class AlinLibNeoForge {
    public AlinLibNeoForge(){
        AlinLib.onInitializeClient();
    }
}
