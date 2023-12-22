package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;

import java.awt.*;

public class EditBoxColor extends EditBoxString {
    public int volume;
    public int defaultConfig;
    public Config config;
    public String typeConfig;


    public EditBoxColor(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, Component label) {
        this(x, y, width, height, InterfaceUtils.DesignType.ALINA, config, typeConfig, defaultConfig, label);
    }

    public EditBoxColor(int x, int y, int width, int height, InterfaceUtils.DesignType type, Config config, String typeConfig, int defaultConfig, Component label) {
        super(Minecraft.getInstance().font, x, y, width, height, false, label, type);

        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.volume = config.getInt(typeConfig, defaultConfig);

        setMaxLength(20);
        setFormatter((string, integer) -> FormattedCharSequence.forward(string.toUpperCase(), Style.EMPTY.withColor(volume)));
        setValue(Integer.toHexString(volume));
//        setFilter(((ConfigValueDuck) this.value).getValueSpec()::test);
        setResponder(string -> {
            try {
                this.volume = (int) Long.parseLong(string, 16);
                config.setInt(typeConfig, volume);

                setError(false);
            } catch (Exception ex) {
                setError(true);
            }
        });
    }

    @Override
    public int getColor() {
        return this.volume;
    }
}
