package ru.kelcuprum.alinlib.gui.components.selector;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.selector.base.SelectorButton;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class SelectorIntegerButton extends SelectorButton implements Resetable {
    public final int defaultVolume;
    public Config config;
    public String typeConfig;

    public SelectorIntegerButton(int x, int y, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, GuiUtils.getSelected(), list, config, typeConfig, defaultVolume, label);
    }
    public SelectorIntegerButton(int x, int y, AbstractStyle style, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, style, list, config, typeConfig, defaultVolume, label);
    }
    //
    public SelectorIntegerButton(int x, int y, int width, int height, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
        this(x, y, width, height, GuiUtils.getSelected(), list, config, typeConfig, defaultVolume, label);
    }
    public SelectorIntegerButton(int x, int y, int width, int height, AbstractStyle style, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
        super(x, y, width, height, style, list, 0, label, null);

        this.defaultVolume = defaultVolume;
        this.typeConfig = typeConfig;
        this.config = config;
        setOnPress((OnPress) (onPress) -> {
            if(this.config != null) this.config.setNumber(this.typeConfig, onPress.getPosition());
        });
        if(this.config != null) setPosition(this.config.getNumber(this.typeConfig, this.defaultVolume).intValue());
    }

    @Override
    public void resetValue() {
        setPosition(defaultVolume);
        if(this.config != null) this.config.setNumber(this.typeConfig, getPosition());
    }


    protected Component description;
    public SelectorIntegerButton setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}