package ru.kelcuprum.alinlib.gui.components.builder.editbox;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxColor;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxConfigString;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxLocalization;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;

import java.util.function.Consumer;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class EditBoxBuilder {
    protected int x = 0;
    protected int y = 0;

    protected int width = DEFAULT_WIDTH();
    protected int height = DEFAULT_HEIGHT;
    protected boolean secret = false;
    protected String value = "";
    protected Component title = Component.empty();
    protected Font font = MINECRAFT.font;
    protected InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    protected Consumer<String> responder = null;
    protected Config config;
    protected String configType;
    protected Localization localization;
    protected boolean isColor = false;
    protected int color;

    public EditBoxBuilder(){
        this(Component.empty());
    }
    public EditBoxBuilder(Component title){
        this(title, null);
    }
    public EditBoxBuilder(Component title, Consumer<String> responder){
        this.title = title;
        this.responder = responder;
    }
    // DesignType
    public EditBoxBuilder setDesignType(InterfaceUtils.DesignType designType){
        this.designType = designType;
        return this;
    }
    // OnPress
    public EditBoxBuilder setResponder(Consumer<String> responder){
        this.responder = responder;
        return this;
    }
    // Title
    public EditBoxBuilder setTitle(Component title){
        this.title = title;
        return this;
    }
    // Position
    public EditBoxBuilder setPosition(int x, int y){
        setX(x).setY(y);
        return this;
    }
    public EditBoxBuilder setX(int x){
        this.x = x;
        return this;
    }
    public EditBoxBuilder setY(int y){
        this.y = y;
        return this;
    }
    // Size
    public EditBoxBuilder setSize(int width, int height){
        setWidth(width).setHeight(height);
        return this;
    }
    public EditBoxBuilder setWidth(int width){
        this.width = width;
        return this;
    }
    public EditBoxBuilder setHeight(int height){
        this.height = height;
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

    public EditBoxString build(){
        if(this.config != null && this.configType != null) {
            if(this.isColor) return new EditBoxColor(x, y, width, height, designType, config, configType, color, title);
            else return new EditBoxConfigString(x, y, width, height, secret, designType, config, configType, value, title);
        } else if(this.localization != null && this.configType != null) return new EditBoxLocalization(x, y, width, height, designType, localization, configType, title);
        else return new EditBoxString(font, x, y, width, height, secret, value, designType, title, responder);
    }
}
