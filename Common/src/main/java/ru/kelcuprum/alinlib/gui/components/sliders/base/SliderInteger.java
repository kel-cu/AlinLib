package ru.kelcuprum.alinlib.gui.components.sliders.base;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class SliderInteger extends SliderPercent {
    public final int min;
    public final int max;
    public int displayValue;
    public String typeInteger = "";
    public OnPress onPress;
    public SliderInteger(int x, int y, int position, int min, int max, Component component) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), position, min, max, component);
    }
    public SliderInteger(int x, int y, InterfaceUtils.DesignType type, int position, int min, int max, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, min, max, label, null);

    }

    public SliderInteger(int x, int y, int position, int min, int max, Component component, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), position, min, max, component, onPress);
    }
    public SliderInteger(int x, int y, InterfaceUtils.DesignType type, int position, int min, int max, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, min, max, label, onPress);
    }
    ////
    public SliderInteger(int x, int y, int width, int height, int position, int min, int max, Component component) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), position, min, max, component);
    }
    public SliderInteger(int x, int y, int width, int height, InterfaceUtils.DesignType type, int position, int min, int max, Component label) {
        this(x, y, width, height, type, position, min, max, label, null);

    }

    public SliderInteger(int x, int y, int width, int height, int position, int min, int max, Component component, OnPress onPress) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), position, min, max, component, onPress);
    }
    public SliderInteger(int x, int y, int width, int height, InterfaceUtils.DesignType type, int position, int min, int max, Component label, OnPress onPress) {
        super(x, y, width, height, type, label);
        setValue(((double) (position - min) /(max-min)));
        this.onPress = onPress;
        this.displayValue = position;
        this.min = min;
        this.max = max;
    }
    // Получить
    // Заменить
    public SliderInteger setTypeInteger(String type){
        this.typeInteger = type;
        return this;
    }
    public SliderInteger setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    @Override
    public Component getComponentValue(){
        return Component.literal(displayValue + typeInteger);
    }

    @Override
    protected void applyValue() {
        int selValue = (int) ((this.max-this.min)*this.value);
        this.displayValue = this.min+selValue;
        if(this.onPress != null) this.onPress.onPress(this.displayValue);

    }

    public interface OnPress {
        void onPress(int value);
    }


    protected Component description;
    public SliderInteger setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
