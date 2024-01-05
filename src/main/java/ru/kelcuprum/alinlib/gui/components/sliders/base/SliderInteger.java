package ru.kelcuprum.alinlib.gui.components.sliders.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;

public class SliderInteger extends SliderPercent {
    public final int min;
    public final int max;
    public int displayValue;
    public String typeInteger = "";
    public OnPress onPress;
    public SliderInteger(int x, int y, int width, int height, int position, int min, int max, Component component) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, position, min, max, component);
    }
    public SliderInteger(int x, int y, int width, int height, InterfaceUtils.DesignType type, int position, int min, int max, Component label) {
        this(x, y, width, height, type, position, min, max, label, null);

    }

    public SliderInteger(int x, int y, int width, int height, int position, int min, int max, Component component, OnPress onPress) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, position, min, max, component, onPress);
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
    public void setTypeInteger(String type){
        this.typeInteger = type;
    }
    public void setOnPress(OnPress onPress){
        this.onPress = onPress;
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

    @Environment(EnvType.CLIENT)
    public interface OnPress {
        void onPress(int value);
    }
}
