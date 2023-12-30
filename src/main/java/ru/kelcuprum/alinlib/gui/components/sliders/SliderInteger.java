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

public class SliderInteger extends Slider implements Resetable {
    public final int defaultConfig;
    public final Config config;
    public final String typeConfig;
    public final int min;
    public final int max;
    public int displayValue;
    public String typeInteger = "";
    public SliderInteger(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, int min, int max, Component component) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, min, max, component);
    }
    public SliderInteger(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, int defaultConfig, int min, int max, Component label) {
        super(x, y, width, height, type, label);
        setValue(((config.getNumber(typeConfig, defaultConfig).doubleValue() - min) /(max-min)));
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.displayValue = this.config.getNumber(this.typeConfig, this.defaultConfig).intValue();
        this.min = min;
        this.max = max;
    }

    public void setTypeInteger(String type){
        this.typeInteger = type;
    }
    @Override
    public Component getComponentValue(){
        return Component.literal(displayValue + typeInteger);
    }

    @Override
    protected void applyValue() {
        int selValue = (int) ((this.max-this.min)*this.value);
        this.displayValue = this.min+selValue;
        this.config.setNumber(this.typeConfig, this.displayValue);

    }

    @Override
    public void resetValue() {
        this.displayValue = defaultConfig;
        this.config.setNumber(this.typeConfig, this.displayValue);
        this.setValue(((config.getNumber(typeConfig, defaultConfig).doubleValue() - min) /(max-min)));
    }
}
