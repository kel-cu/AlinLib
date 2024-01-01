package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;

public class EditBoxConfigString extends EditBoxString implements Resetable {
    public String volume;
    public String defaultConfig;
    public Config config;
    public String typeConfig;

    public EditBoxConfigString(int x, int y, int width, int height, Config config, String typeConfig, String defaultConfig, Component label) {
        this(x, y, width, height, false, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, label);
    }

    public EditBoxConfigString(int x, int y, int width, int height, boolean secret, Config config, String typeConfig, String defaultConfig, Component label) {
        this(x, y, width, height, secret, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, label);
    }

    public EditBoxConfigString(int x, int y, int width, int height, boolean secret, InterfaceUtils.DesignType type, Config config, String typeConfig, String defaultConfig, Component label) {
        super(x, y, width, height, secret, type, label);

        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.volume = config.getString(typeConfig, defaultConfig);

        setValue(this.volume);
        setResponder(string -> {
            this.config.setString(this.typeConfig, string);
        });
    }

    @Override
    public void resetValue() {
        setValue(defaultConfig);
    }
}
