package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;
import ru.kelcuprum.alinlib.gui.styles.AbstractStyle;

import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.GuiUtils.DEFAULT_WIDTH;

public class EditBoxConfigString extends EditBoxString implements Resetable {
    public String volume;
    public String defaultConfig;
    public Config config;
    public String typeConfig;


    public EditBoxConfigString(int x, int y, Config config, String typeConfig, String defaultConfig, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, false, GuiUtils.getSelected(), config, typeConfig, defaultConfig, label);
    }

    public EditBoxConfigString(int x, int y, boolean secret, Config config, String typeConfig, String defaultConfig, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, GuiUtils.getSelected(), config, typeConfig, defaultConfig, label);
    }

    public EditBoxConfigString(int x, int y, boolean secret, AbstractStyle style, Config config, String typeConfig, String defaultConfig, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, secret, style, config, typeConfig, defaultConfig, label);

    }
    //
    public EditBoxConfigString(int x, int y, int width, int height, Config config, String typeConfig, String defaultConfig, Component label) {
        this(x, y, width, height, false, GuiUtils.getSelected(), config, typeConfig, defaultConfig, label);
    }

    public EditBoxConfigString(int x, int y, int width, int height, boolean secret, Config config, String typeConfig, String defaultConfig, Component label) {
        this(x, y, width, height, secret, GuiUtils.getSelected(), config, typeConfig, defaultConfig, label);
    }

    public EditBoxConfigString(int x, int y, int width, int height, boolean secret, AbstractStyle style, Config config, String typeConfig, String defaultConfig, Component label) {
        super(x, y, width, height, secret, style, label);

        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.volume = config.getString(typeConfig, defaultConfig);

        setValue(this.volume);
        setResponder(string -> {
            this.config.setString(this.typeConfig, string);
        });
    }

    @Override
    public void resetValue() {
        setValue(defaultConfig);
    }


    protected Component description;
    public EditBoxConfigString setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
