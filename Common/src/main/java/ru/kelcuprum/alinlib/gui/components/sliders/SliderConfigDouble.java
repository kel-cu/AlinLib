package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderDouble;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class SliderConfigDouble extends SliderDouble implements Resetable {
    public final double defaultConfig;
    public final Config config;
    public final String typeConfig;

    public SliderConfigDouble(int x, int y, Config config, String typeConfig, double defaultConfig, double min, double max, Component component) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, GuiUtils.getSelected(), config, typeConfig, defaultConfig, min, max, component);
    }
    public SliderConfigDouble(int x, int y, AbstractStyle style, Config config, String typeConfig, double defaultConfig, double min, double max, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, style, config, typeConfig, defaultConfig, min, max, label);
    }
    ///
    public SliderConfigDouble(int x, int y, int width, int height, Config config, String typeConfig, double defaultConfig, double min, double max, Component component) {
        this(x, y, width, height, GuiUtils.getSelected(), config, typeConfig, defaultConfig, min, max, component);
    }
    public SliderConfigDouble(int x, int y, int width, int height, AbstractStyle style, Config config, String typeConfig, double defaultConfig, double min, double max, Component label) {
        super(x, y, width, height, style, config.getNumber(typeConfig, defaultConfig).doubleValue(), min, max, label);
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

    protected Component description;
    public SliderConfigDouble setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
