package ru.kelcuprum.alinlib.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.kelcuprum.alinlib.gui.screens.AlinaDemoScreen;
//import ru.kelcuprum.alinlib.gui.screens.AlinaDemoScreen;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return new AlinaDemoScreen()::build;
//        return null;
    }
}