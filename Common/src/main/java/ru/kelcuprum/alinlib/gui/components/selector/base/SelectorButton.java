package ru.kelcuprum.alinlib.gui.components.selector.base;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SelectorButton extends Button {
    protected String[] list;
    protected int position;
    protected String buttonMessage;
    protected OnPress onPress;
    public SelectorButton(int x, int y, InterfaceUtils.DesignType type, String[] list, int position, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, list, position, label, onPress);
    }
    public SelectorButton(int x, int y, int width, int height, InterfaceUtils.DesignType type, String[] list, int position, Component label, OnPress onPress) {
        super(x, y, width, height, type, label, null);
        this.list = list;
        this.onPress = onPress;
        this.position = position;
        this.buttonMessage = label.getString();
    }


    // Получить
    public Component getValue(){
        assert this.list !=null;
        try {
            return Component.literal(this.list[this.position]);
        } catch (Exception ex){
            AlinLib.log(ex.getLocalizedMessage(), Level.ERROR);
            return Component.literal(this.list[0]);
        }
    }
    public int getPosition(){
        return position;
    }
    public String[] getList(){
        return list;
    }

    // Заменить
    public SelectorButton setList(String[] list){
        this.list = list;
        return this;
    }
    public SelectorButton setOnPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }
    public SelectorButton setPosition(int position){
        this.position = position;
        return this;
    }

    // Рендер
    @Override
    public void renderText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if(InterfaceUtils.isDoesNotFit(Component.literal(buttonMessage).append(": ").append(getValue()), getWidthComponent(), getHeight())){
            if(isHoveredOrFocused()){
                this.setMessage(getValue());
            } else {
                this.setMessage(Component.literal(buttonMessage).append(": ").append(getValue()));
            }
            this.renderScrollingString(guiGraphics, AlinLib.MINECRAFT.font, 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(AlinLib.MINECRAFT.font, buttonMessage, getXComponent() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            // VOLUME
            guiGraphics.drawString(AlinLib.MINECRAFT.font, getValue(), getX() + getWidth() - AlinLib.MINECRAFT.font.width(getValue()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

    // Мелочь
    @Override
    public void onPress() {
        this.position++;
        if(this.list.length == this.position) this.position = 0;
        if(this.onPress != null) this.onPress.onPress(this);
    }
    public interface OnPress {
        void onPress(SelectorButton onPress);
    }


    protected Component description;
    public SelectorButton setDescription(Component description){
        this.description = description;
        return this;
    }
    public Component getDescription(){
        return this.description;
    }
}
