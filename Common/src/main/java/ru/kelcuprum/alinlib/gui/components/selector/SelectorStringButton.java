package ru.kelcuprum.alinlib.gui.components.selector;

import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.selector.base.SelectorButton;

import java.util.Arrays;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SelectorStringButton extends SelectorButton implements Resetable {
    public final String defaultVolume;
    public Config config;
    public String typeConfig;

    public SelectorStringButton(int x, int y, String[] list, Config config, String typeConfig, String defaultVolume, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), list, config, typeConfig, defaultVolume, label);
    }
    public SelectorStringButton(int x, int y, InterfaceUtils.DesignType type, String[] list, Config config, String typeConfig, String defaultVolume, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, list, config, typeConfig, defaultVolume, label);
    }
    ///
    public SelectorStringButton(int x, int y, int width, int height, String[] list, Config config, String typeConfig, String defaultVolume, Component label) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), list, config, typeConfig, defaultVolume, label);
    }
    public SelectorStringButton(int x, int y, int width, int height, InterfaceUtils.DesignType type, String[] list, Config config, String typeConfig, String defaultVolume, Component label) {
        super(x, y, width, height, type, list, 0, label, null);

        this.defaultVolume = defaultVolume;
        this.typeConfig = typeConfig;
        this.config = config;
        setOnPress((OnPress) (onPress) -> {
            if(this.config != null) this.config.setString(this.typeConfig, getList()[this.position]);
        });
        try {
            if(this.config != null) setPosition(Arrays.stream(getList()).toList().indexOf(this.config.getString(typeConfig, defaultVolume)));
        } catch(Exception ex){
            AlinLib.log(ex.getLocalizedMessage(), Level.ERROR);
            setPosition(0);
            if(this.config != null) this.config.setString(this.typeConfig, getList()[getPosition()]);
        }
    }

    @Override
    public void resetValue() {
        try {
            setPosition(Arrays.stream(getList()).toList().indexOf(defaultVolume));
        } catch(Exception ex){
            AlinLib.log(ex.getLocalizedMessage(), Level.ERROR);
            setPosition(0);
        }
        if(this.config != null) this.config.setString(this.typeConfig, getList()[getPosition()]);
    }

    protected Component description;
    public SelectorStringButton setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}