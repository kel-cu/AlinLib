package ru.kelcuprum.alinlib.gui.components.builder.text;

import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.text.HorizontalRule;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;

public class HorizontalRuleBuilder extends AbstractBuilder {
    public int[] color;
    public HorizontalRuleBuilder(){
        this(Component.empty());
    }
    public HorizontalRuleBuilder(Component title){
        super(title);
        this.height = 1;
    }
    public HorizontalRuleBuilder setColor(int color){
        this.color = new int[]{color};
        return this;
    }
    public HorizontalRule build(){
        return new HorizontalRule(this);
    }
}
