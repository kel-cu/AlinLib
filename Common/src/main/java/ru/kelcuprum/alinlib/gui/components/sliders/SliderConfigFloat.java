package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderFloat;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SliderConfigFloat extends SliderFloat implements Resetable {
    public final float defaultConfig;
    public final Config config;
    public final String typeConfig;

    public SliderConfigFloat(int x, int y, Config config, String typeConfig, float defaultConfig, float min, float max, Component component) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, min, max, component);
    }
    public SliderConfigFloat(int x, int y, InterfaceUtils.DesignType type, Config config, String typeConfig, float defaultConfig, float min, float max, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, config, typeConfig, defaultConfig, min, max, label);
    }
    ///
    public SliderConfigFloat(int x, int y, int width, int height, Config config, String typeConfig, float defaultConfig, float min, float max, Component component) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, min, max, component);
    }
    public SliderConfigFloat(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, float defaultConfig, float min, float max, Component label) {
        super(x, y, width, height, type, config.getNumber(typeConfig, defaultConfig).floatValue(), min, max, label);
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
            this.setValue(((config.getNumber(typeConfig, defaultConfig).floatValue() - min) /(max-min)));
        }
    }

    protected Component description;
    public SliderConfigFloat setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
