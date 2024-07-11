//#if FABRIC
package ru.kelcuprum.alinlib;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import ru.kelcuprum.alinlib.api.KeyMappingHelper;

public class AlinLibPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch(){
        KeyMappingHelper.onRegister = net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper::registerKeyBinding;
    }
}
//#endif
