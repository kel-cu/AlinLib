package ru.kelcuprum.alinlib.gui.components.buttons;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.buttons.base.ButtonBoolean;

public class ButtonConfigBoolean extends ButtonBoolean implements Resetable {
    public final boolean defaultConfig;
    public final Config config;
    public final String typeConfig;
    public ButtonConfigBoolean(int x, int y, int width, int height, Config config, String typeConfig, boolean defaultConfig, Component label) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, label);
    }
    public ButtonConfigBoolean(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, boolean defaultConfig, Component label) {
        super(x, y, width, height, type, label, null);
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.setValue(this.config.getBoolean(this.typeConfig, this.defaultConfig));
        this.setOnPress((OnPress) (value) ->  config.setBoolean(typeConfig, value));
    }

    @Override
    public void resetValue() {
        this.setValue(defaultConfig);
        config.setBoolean(typeConfig, this.getValue());
    }
}