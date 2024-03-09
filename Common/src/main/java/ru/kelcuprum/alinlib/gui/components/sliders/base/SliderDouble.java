package ru.kelcuprum.alinlib.gui.components.sliders.base;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SliderDouble extends SliderPercent {
    public final double min;
    public final double max;
    public double displayValue;
    public String typeInteger = "";
    public OnPress onPress;
    public SliderDouble(int x, int y, double position, double min, double max, Component component) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, InterfaceUtils.DesignType.ALINA, position, min, max, component);
    }
    public SliderDouble(int x, int y, InterfaceUtils.DesignType type, double position, double min, double max, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, min, max, label, null);

    }

    public SliderDouble(int x, int y, double position, double min, double max, Component component, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, InterfaceUtils.DesignType.ALINA, position, min, max, component, onPress);
    }
    public SliderDouble(int x, int y, InterfaceUtils.DesignType type, double position, double min, double max, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, min, max, label, onPress);
    }
    ////
    public SliderDouble(int x, int y, int width, int height, double position, double min, double max, Component component) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, position, min, max, component);
    }
    public SliderDouble(int x, int y, int width, int height, InterfaceUtils.DesignType type, double position, double min, double max, Component label) {
        this(x, y, width, height, type, position, min, max, label, null);

    }

    public SliderDouble(int x, int y, int width, int height, double position, double min, double max, Component component, OnPress onPress) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, position, min, max, component, onPress);
    }
    public SliderDouble(int x, int y, int width, int height, InterfaceUtils.DesignType type, double position, double min, double max, Component label, OnPress onPress) {
        super(x, y, width, height, type, label);
        setValue(((double) (position - min) /(max-min)));
        this.onPress = onPress;
        this.displayValue = position;
        this.min = min;
        this.max = max;
    }
    // Получить
    // Заменить
    public SliderDouble setTypeInteger(String type){
        this.typeInteger = type;
        return this;
    }
    public SliderDouble setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    @Override
    public Component getComponentValue(){
        return Component.literal(displayValue + typeInteger);
    }

    @Override
    protected void applyValue() {
        double selValue = ((this.max-this.min)*this.value);
        this.displayValue = this.min+selValue;
        if(this.onPress != null) this.onPress.onPress(this.displayValue);

    }

    public interface OnPress {
        void onPress(double value);
    }


    protected Component description;
    public SliderDouble setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
