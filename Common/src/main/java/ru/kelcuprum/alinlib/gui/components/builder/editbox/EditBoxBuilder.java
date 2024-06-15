package ru.kelcuprum.alinlib.gui.components.builder.editbox;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxColor;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxConfigString;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxLocalization;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;

import java.util.function.Consumer;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;

public class EditBoxBuilder extends AbstractBuilder {
    public boolean secret = false;
    public String value = "";
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
    //
    // Title
    public EditBoxBuilder setTitle(String title){
        return (EditBoxBuilder) super.setTitle(title);
    }
    public EditBoxBuilder setTitle(Component title){
        return (EditBoxBuilder) super.setTitle(title);
    }
    // Description
    public EditBoxBuilder setDescription(String description){
        return (EditBoxBuilder) super.setDescription(description);
    }
    public EditBoxBuilder setDescription(Component description){
        return (EditBoxBuilder) super.setDescription(description);
    }
    //
    public EditBoxBuilder setDesignType(InterfaceUtils.DesignType designType){
        return (EditBoxBuilder) super.setDesignType(designType);
    }
    // Position
    public EditBoxBuilder setPosition(int x, int y){
        return (EditBoxBuilder) super.setPosition(x, y);
    }
    public EditBoxBuilder setX(int x){
        return (EditBoxBuilder) super.setX(x);
    }
    public EditBoxBuilder setY(int y){
        return (EditBoxBuilder) super.setY(y);
    }
    // Size
    public EditBoxBuilder setSize(int width, int height){
        return (EditBoxBuilder) super.setSize(width, height);
    }
    public EditBoxBuilder setWidth(int width){
        return (EditBoxBuilder) super.setWidth(width);
    }
    public EditBoxBuilder setHeight(int height){
        return (EditBoxBuilder) super.setHeight(height);
    }
    //
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
    @Override
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

    public EditBoxString build(){
        if(this.config != null && this.configType != null) {
            if(this.isColor) return new EditBoxColor(x, y, width, height, designType, config, configType, color, title);
            else return new EditBoxConfigString(x, y, width, height, secret, designType, config, configType, value, title);
        } else if(this.localization != null && this.configType != null) return new EditBoxLocalization(x, y, width, height, designType, localization, configType, title);
        else return new EditBoxString(font, x, y, width, height, secret, value, designType, title, responder);
    }
}
