package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderInteger;

public class SliderConfigInteger extends SliderInteger implements Resetable {
    public final int defaultConfig;
    public final Config config;
    public final String typeConfig;
    public SliderConfigInteger(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, int min, int max, Component component) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, min, max, component);
    }
    public SliderConfigInteger(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, int defaultConfig, int min, int max, Component label) {
        super(x, y, width, height, type, config.getNumber(typeConfig, defaultConfig).intValue(), min, max, label);
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.setOnPress((OnPress) (value) -> {
            if(this.config != null) this.config.setNumber(this.typeConfig, this.displayValue);
        });
    }

    @Override
    public void resetValue() {
        this.displayValue = defaultConfig;
        if(this.config != null) {
            this.config.setNumber(this.typeConfig, this.displayValue);
            this.setValue(((config.getNumber(typeConfig, defaultConfig).doubleValue() - min) /(max-min)));
        }
    }
}
