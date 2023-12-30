package ru.kelcuprum.alinlib.gui.components.sliders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.sliders.base.Slider;

public class SliderPercent extends Slider implements Resetable {
    public final double defaultConfig;
    public final Config config;
    public final String typeConfig;
    public SliderPercent(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, Component component) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, component);
    }
    public SliderPercent(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, double defaultConfig, Component component) {
        super(x, y, width, height, type, config.getNumber(typeConfig, defaultConfig).doubleValue(), component);
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
    }
    @Override
    protected void applyValue() {
        this.config.setNumber(this.typeConfig, this.value);
    }

    @Override
    public void resetValue() {
        this.config.setNumber(this.typeConfig, this.defaultConfig);
        this.setValue(this.defaultConfig);
    }
}
