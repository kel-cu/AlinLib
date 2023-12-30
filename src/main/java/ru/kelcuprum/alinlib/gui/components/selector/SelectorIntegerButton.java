package ru.kelcuprum.alinlib.gui.components.selector;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.selector.base.SelectorButton;

public class SelectorIntegerButton extends SelectorButton implements Resetable {
    public final int defaultVolume;
    public Config config;
    public String typeConfig;
    public SelectorIntegerButton(int x, int y, int width, int height, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, list, config, typeConfig, defaultVolume, label);
    }
    public SelectorIntegerButton(int x, int y, int width, int height, InterfaceUtils.DesignType type, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
        this(x, y, width, height, type, InterfaceUtils.Colors.SEADRIVE, list, config, typeConfig, defaultVolume, label);
    }
    public SelectorIntegerButton(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, String[] list, Config config, String typeConfig, int defaultVolume, Component label) {
        super(x, y, width, height, type, color, list, 0, label, null);

        this.defaultVolume = defaultVolume;
        this.typeConfig = typeConfig;
        this.config = config;
        setOnPress((OnPress) (onPress) -> {
            this.config.setNumber(this.typeConfig, onPress.getPosition());
        });
        setPosition(this.config.getNumber(this.typeConfig, this.defaultVolume).intValue());
    }

    @Override
    public void resetValue() {
        setPosition(defaultVolume);
        this.config.setNumber(this.typeConfig, getPosition());
    }
}