package ru.kelcuprum.alinlib.gui.components.selector;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;

import java.util.Arrays;

public class SelectorButton extends Button implements Resetable {
    public final SelectorBuilder builder;
    protected int position;

    public SelectorButton(AbstractBuilder builder) {
        super(builder);
        this.builder = (SelectorBuilder) builder;
        if(((SelectorBuilder) builder).hasConfigurable()){
            if(((SelectorBuilder) builder).defaultString == null) setPosition(this.builder.config.getNumber(this.builder.configType, this.builder.defaultInt).intValue());
            else setPosition(Arrays.stream(getList()).toList().indexOf(this.builder.config.getString(((SelectorBuilder) builder).configType, ((SelectorBuilder) builder).defaultString)));
        } else setPosition(((SelectorBuilder) builder).defaultInt);
    }


    // Получить
    public Component getValue(){
        assert this.builder.list !=null;
        if(this.position >= this.builder.list.length) {
            setPosition(-1);
            onPress();
        }
        try {
            return Component.literal(this.builder.list[this.position]);
        } catch (Exception ex){
            AlinLib.log(ex.getLocalizedMessage(), Level.ERROR);
            return Component.literal(this.builder.list[0]);
        }
    }
    public int getPosition(){
        return position;
    }
    public String[] getList(){
        return builder.list;
    }

    // Заменить
    public SelectorButton setList(String[] list){
        this.builder.list = list;
        return this;
    }
    public SelectorButton setOnPress(OnPress onPress){
        this.builder.setOnPress(onPress);
        return this;
    }
    public SelectorButton setPosition(int position){
        this.position = position;
        return this;
    }

    // Рендер
    @Override
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if(GuiUtils.isDoesNotFit(Component.empty().append(builder.getTitle()).append(": ").append(getValue()), getWidthComponent(), getHeight())){
            if(isHoveredOrFocused()){
                this.setMessage(getValue());
            } else {
                this.setMessage(Component.empty().append(builder.getTitle()).append(": ").append(getValue()));
            }
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            // VOLUME
            guiGraphics.drawString(AlinLib.MINECRAFT.font, getValue(), getX() + getWidth() - AlinLib.MINECRAFT.font.width(getValue()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

    // Мелочь
    @Override
    public void onPress() {
        this.position++;
        if(this.builder.list.length == this.position) this.position = 0;
        if(builder.hasConfigurable()){
            if(builder.defaultString == null) builder.config.setNumber(builder.configType, this.position);
            else builder.config.setString(builder.configType, builder.list[this.position]);
        }
        if(this.builder.getOnPress() != null) this.builder.getOnPress().onPress(this);
    }

    @Override
    public void resetValue() {
        if(resettable()){
            if(builder.defaultString == null) builder.config.setNumber(builder.configType, builder.defaultInt);
            else builder.config.setString(builder.configType, builder.defaultString);
        }
        setPosition(builder.defaultString == null ? builder.defaultInt : Arrays.stream(getList()).toList().indexOf(builder.defaultString));
    }

    @Override
    public boolean resettable() {
        return this.builder.hasConfigurable();
    }

    public interface OnPress {
        void onPress(SelectorButton onPress);
    }

    public SelectorButton setDescription(Component description){
        this.builder.setDescription(description);
        return this;
    }
    public Component getDescription(){
        return this.builder.getDescription();
    }
}
