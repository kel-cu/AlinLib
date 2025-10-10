package ru.kelcuprum.alinlib.gui.components.builder.text;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;

import static ru.kelcuprum.alinlib.gui.Colors.GROUPIE;
import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.ALIGN.CENTER;
import static ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder.ALIGN.LEFT;

public class TextBuilder extends AbstractBuilder {
    public TextBox.OnPress onPress;
    public TYPE type = TYPE.TEXT;
    public ALIGN align = CENTER;
    public int[] color;
    public TextBuilder(){
        this(Component.empty());
    }
    public TextBuilder(Component title){
        this(title, null);
    }
    public TextBuilder(Component title, TextBox.OnPress onPress){
        super(title);
        this.onPress = onPress;
    }
    // OnPress
    public TextBuilder setOnPress(TextBox.OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public TextBox.OnPress getOnPress(){
        return this.onPress;
    }
    // TYPE
    public TextBuilder setType(TYPE type){
        this.type = type;
        if(type != TYPE.TEXT && type != TYPE.TITLE && align == CENTER) this.align = LEFT;
        return this;
    }
    public TYPE getType(){
        return this.type;
    }
    // ALIGN
    public TextBuilder setAlign(ALIGN align){
        this.align = align;
        return this;
    }
    public ALIGN getAlign(){
        return this.align;
    }
    // Color
    public TextBuilder setColor(int color){
        return setColor(color, color-0xE1000000);
    }
    public TextBuilder setColor(int line, int background){
        this.color = new int[]{line, background-0xE1000000};
        return this;
    }

    public TextBox build(){
        return new TextBox(this);
    }

    public enum TYPE{
        TEXT(),
        TITLE(),
        MESSAGE(),
        BLOCKQUOTE()
    }
    public enum ALIGN{
        LEFT(),
        CENTER(),
        RIGHT()
    }
}
