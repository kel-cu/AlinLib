package ru.kelcuprum.alinlib.gui.components.builder.editbox;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBox;

import java.util.function.Consumer;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;

public class EditBoxBuilder extends AbstractBuilder {
    public boolean secret = false;
    public String value = "";
    public int color;
    public Font font = MINECRAFT.font;
    public Consumer<String> responder;
    public Config config;
    public String configType;
    public Localization localization;
    public boolean isColor = false;

    public EditBoxBuilder(){
        this(Component.empty());
    }
    public EditBoxBuilder(Component title){
        this(title, null);
    }
    public EditBoxBuilder(Component title, Consumer<String> responder){
        super(title);
        this.responder = responder;
    }
    // OnPress
    public EditBoxBuilder setResponder(Consumer<String> responder){
        this.responder = responder;
        return this;
    }
    // Config
    public EditBoxBuilder setConfig(Config config, String configType){
        this.config = config;
        this.configType = configType;
        return this;
    }
    // Localization
    public EditBoxBuilder setLocalization(Localization localization, String configType){
        this.localization = localization;
        this.configType = configType;
        return this;
    }
    // Color
    public EditBoxBuilder setColor(int color){
        this.color = color;
        this.isColor = true;
        return this;
    }
    // Value
    public EditBoxBuilder setValue(String value){
        this.value = value;
        return this;
    }
    // Secret
    public EditBoxBuilder setSecret(boolean secret){
        this.secret = secret;
        return this;
    }
    public boolean hasConfigurable(){
        return this.config != null && this.configType != null;
    }
    public boolean hasLocalization(){
        return this.localization != null && this.configType != null;
    }
    //
    public EditBoxBuilder setFont(Font font){
        this.font = font;
        return this;
    }

    public Font getFont() {
        return font;
    }

    public EditBox build(){
        return new EditBox(this);
    }
}
