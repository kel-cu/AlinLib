package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class EditBoxColor extends EditBoxString implements Resetable {
    public int volume;
    public int defaultConfig;
    public Config config;
    public String typeConfig;


    public EditBoxColor(int x, int y, Config config, String typeConfig, int defaultConfig, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, AlinLib.getDefaultDesignType(), config, typeConfig, defaultConfig, label);
    }

    public EditBoxColor(int x, int y, InterfaceUtils.DesignType type, Config config, String typeConfig, int defaultConfig, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, config, typeConfig, defaultConfig, label);
    }

    ///


    public EditBoxColor(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, Component label) {
        this(x, y, width, height, AlinLib.getDefaultDesignType(), config, typeConfig, defaultConfig, label);
    }

    public EditBoxColor(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, int defaultConfig, Component label) {
        super(x, y, width, height, type, label);

        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.volume = config.getNumber(typeConfig, defaultConfig).intValue();

        setMaxLength(16);
        setFormatter((string, integer) -> FormattedCharSequence.forward(string.toUpperCase(), Style.EMPTY.withColor(getColor())));
        setValue(Integer.toHexString(volume));
        setResponder(string -> {
            try {
                this.volume = (int) Long.parseLong(string.toUpperCase(), 16);
                config.setNumber(typeConfig, volume);

                setError(false);
            } catch (Exception ex) {
                setError(true);
            }
        });
    }

    @Override
    protected int getPositionContent(String content) {
        int pos = getX() + getWidth() - font.width(content.toUpperCase()) - ((getHeight() - 8) / 2);

        if (getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2 > pos)
            pos = getX() + font.width(getMessage()) + ((getHeight() - 8) / 2) * 2;

        return pos;
    }
    @Override
    public int getColor() {
        return getError() ? InterfaceUtils.Colors.GROUPIE : this.volume;
    }

    @Override
    public void resetValue() {
        setValue(Integer.toHexString(this.defaultConfig));
    }


    protected Component description;
    public EditBoxColor setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
