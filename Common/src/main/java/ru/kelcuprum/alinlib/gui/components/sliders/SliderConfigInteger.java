package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderInteger;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SliderConfigInteger extends SliderInteger implements Resetable {
    public final int defaultConfig;
    public final Config config;
    public final String typeConfig;

    public SliderConfigInteger(int x, int y, Config config, String typeConfig, int defaultConfig, int min, int max, Component component) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), config, typeConfig, defaultConfig, min, max, component);
    }
    public SliderConfigInteger(int x, int y, InterfaceUtils.DesignType type, Config config, String typeConfig, int defaultConfig, int min, int max, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, config, typeConfig, defaultConfig, min, max, label);
    }
    ///
    public SliderConfigInteger(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, int min, int max, Component component) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), config, typeConfig, defaultConfig, min, max, component);
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
            this.setValue(((double) (config.getNumber(typeConfig, defaultConfig).intValue() - min) /(max-min)));
        }
    }

    protected Component description;
    public SliderConfigInteger setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
