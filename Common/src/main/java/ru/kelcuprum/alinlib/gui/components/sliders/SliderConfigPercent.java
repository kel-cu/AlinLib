package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.sliders.base.SliderPercent;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class SliderConfigPercent extends SliderPercent implements Resetable {
    public final double defaultConfig;
    public final Config config;
    public final String typeConfig;

    public SliderConfigPercent(int x, int y, Config config, String typeConfig, int defaultConfig, Component component) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), config, typeConfig, defaultConfig, component);
    }
    public SliderConfigPercent(int x, int y, InterfaceUtils.DesignType type, Config config, String typeConfig, double defaultConfig, Component component) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, config, typeConfig, defaultConfig, component);
    }
    ///
    public SliderConfigPercent(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, Component component) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), config, typeConfig, defaultConfig, component);
    }
    public SliderConfigPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, double defaultConfig, Component component) {
        super(x, y, width, height, type, config.getNumber(typeConfig, defaultConfig).doubleValue(), component);
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
    }
    @Override
    protected void applyValue() {
        if(this.config != null) this.config.setNumber(this.typeConfig, this.value);
    }

    @Override
    public void resetValue() {
        if(this.config != null) this.config.setNumber(this.typeConfig, this.defaultConfig);
        this.setValue(this.defaultConfig);
    }

    protected Component description;
    public SliderConfigPercent setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
