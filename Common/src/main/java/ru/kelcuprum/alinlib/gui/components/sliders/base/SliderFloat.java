package ru.kelcuprum.alinlib.gui.components.sliders.base;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class SliderFloat extends SliderPercent {
    public final float min;
    public final float max;
    public float displayValue;
    public String typeInteger = "";
    public OnPress onPress;
    public SliderFloat(int x, int y, float position, float min, float max, Component component) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), position, min, max, component);
    }
    public SliderFloat(int x, int y, InterfaceUtils.DesignType type, float position, float min, float max, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, min, max, label, null);

    }

    public SliderFloat(int x, int y, float position, float min, float max, Component component, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), position, min, max, component, onPress);
    }
    public SliderFloat(int x, int y, InterfaceUtils.DesignType type, float position, float min, float max, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, position, min, max, label, onPress);
    }
    ////
    public SliderFloat(int x, int y, int width, int height, float position, float min, float max, Component component) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), position, min, max, component);
    }
    public SliderFloat(int x, int y, int width, int height, InterfaceUtils.DesignType type, float position, float min, float max, Component label) {
        this(x, y, width, height, type, position, min, max, label, null);

    }

    public SliderFloat(int x, int y, int width, int height, float position, float min, float max, Component component, OnPress onPress) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), position, min, max, component, onPress);
    }
    public SliderFloat(int x, int y, int width, int height, InterfaceUtils.DesignType type, float position, float min, float max, Component label, OnPress onPress) {
        super(x, y, width, height, type, label);
        setValue(((double) (position - min) /(max-min)));
        this.onPress = onPress;
        this.displayValue = position;
        this.min = min;
        this.max = max;
    }
    // Получить
    // Заменить
    public SliderFloat setTypeInteger(String type){
        this.typeInteger = type;
        return this;
    }
    public SliderFloat setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    @Override
    public Component getComponentValue(){
        return Component.literal(Localization.getDoubleRounding(displayValue) + typeInteger);
    }

    @Override
    protected void applyValue() {
        float selValue = (float) ((this.max-this.min)*this.value);
        this.displayValue = this.min+selValue;
        if(this.onPress != null) this.onPress.onPress(this.displayValue);

    }

    public interface OnPress {
        void onPress(double value);
    }


    protected Component description;
    public SliderFloat setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
