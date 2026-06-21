package ru.kelcuprum.alinlib.gui.components.selector;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
//#if MC >= 12109
import net.minecraft.client.input.InputWithModifiers;
//#endif
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.GuiUtils;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.builder.AbstractBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;

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
            onPress(
                    //#if MC >= 12109
                    null
                    //#endif
            );
        }
        try {
            return Component.literal(this.builder.list[this.position]);
        } catch (Exception ex){
            AlinLib.LOG.log(ex.getLocalizedMessage(), Level.ERROR);
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
    public void renderText(GuiGraphicsExtractor GuiGraphicsExtractor, int mouseX, int mouseY, float partialTicks) {
        if(GuiUtils.isDoesNotFit(Component.empty().append(builder.getTitle()).append(": ").append(getValue()), getWidthComponent(), getHeight())){
            if(isHoveredOrFocused()){
                this.setMessage(getValue());
            } else {
                this.setMessage(Component.empty().append(builder.getTitle()).append(": ").append(getValue()));
            }
            this.renderScrollingString(GuiGraphicsExtractor, AlinLib.MINECRAFT.font, 2, builder.getStyle().getTextColor(active),builder.getStyle().textShadow());
        } else {
            GuiGraphicsExtractor.text(AlinLib.MINECRAFT.font, builder.getTitle(), getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, builder.getStyle().getTextColor(active),builder.getStyle().textShadow());
            // VOLUME
            GuiGraphicsExtractor.text(AlinLib.MINECRAFT.font, getValue(), getX() + getWidth() - AlinLib.MINECRAFT.font.width(getValue()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, builder.getStyle().getTextColor(active),builder.getStyle().textShadow());
        }
    }


    public void renderScrollingString(GuiGraphicsExtractor GuiGraphicsExtractor, Font font, int i, int j, boolean shadow) {
        int k = this.getX() + i;
        int l = this.getX() + this.getWidth() - i;
        TextBox.renderScrollingString(GuiGraphicsExtractor, font, this.getMessage(), k, this.getY(), l, this.getY() + this.getHeight(), j, shadow);
    }

    // Мелочь
    @Override
    public void onPress(
            //#if MC >= 12109
            InputWithModifiers inputWithModifiers
            //#endif
    ) {
        this.position++;
        if(this.builder.list.length == this.position) this.position = 0;
        if(builder.hasConfigurable()){
            if(builder.defaultString == null) builder.config.setNumber(builder.configType, this.position);
            else builder.config.setString(builder.configType, builder.list[this.position]);
        }
        if(this.builder.getOnPress() != null) this.builder.getOnPress().onPress(this);
    }

    //#if MC >= 12111
    @Override
    public void extractContents(GuiGraphicsExtractor GuiGraphicsExtractor, int i, int j, float f) {
        super.extractContents(GuiGraphicsExtractor, i, j, f);
    }
    //#endif

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
