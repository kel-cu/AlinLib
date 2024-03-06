package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class EditBoxLocalization extends EditBoxString implements Resetable {
    public Localization localization;
    public String key;


    public EditBoxLocalization(int x, int y, Localization config, String key, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, InterfaceUtils.DesignType.ALINA, config, key, label);
    }

    public EditBoxLocalization(int x, int y, InterfaceUtils.DesignType type, Localization localization, String key, Component label) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, localization, key, label);

    }
    //
    public EditBoxLocalization(int x, int y, int width, int height, Localization config, String key, Component label) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, config, key, label);
    }

    public EditBoxLocalization(int x, int y, int width, int height, InterfaceUtils.DesignType type, Localization localization, String key, Component label) {
        super(x, y, width, height, type, label);

        this.localization = localization;
        this.key = key;

        setValue(this.localization.getLocalization(this.key, false, false, false));
        setResponder(string -> {
            this.localization.setLocalization(this.key, string);
        });
    }

    @Override
    public void resetValue() {
        this.localization.resetLocalization(this.key);
        this.setValue(this.localization.getLocalization(key, false, false, false));
    }


    protected Component description;
    public EditBoxLocalization setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
