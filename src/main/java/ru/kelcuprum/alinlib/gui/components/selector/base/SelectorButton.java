package ru.kelcuprum.alinlib.gui.components.selector.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_HEIGHT;
import static ru.kelcuprum.alinlib.gui.InterfaceUtils.DEFAULT_WIDTH;

public class SelectorButton extends Button {
    protected String[] list;
    protected int position;
    protected String buttonMessage;
    protected OnPress onPress;
    public SelectorButton(int x, int y, InterfaceUtils.DesignType type, int color, String[] list, int position, Component label, OnPress onPress) {
        this(x, y, DEFAULT_WIDTH(), DEFAULT_HEIGHT, type, color, list, position, label, onPress);
    }
    public SelectorButton(int x, int y, int width, int height, InterfaceUtils.DesignType type, int color, String[] list, int position, Component label, OnPress onPress) {
        super(x, y, width, height, type, color, label, null);
        this.list = list;
        this.onPress = onPress;
        this.position = position;
        this.buttonMessage = label.getString();
        this.setMessage(Component.literal(buttonMessage).append(": ").append(getValue()));
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
        if(InterfaceUtils.isDoesNotFit(getMessage(), getWidth(), getHeight())){
            if(isHoveredOrFocused()){
                this.setMessage(getValue());
            } else {
                this.setMessage(Component.literal(buttonMessage).append(": ").append(getValue()));
            }
            this.renderScrollingString(guiGraphics, Minecraft.getInstance().font, 2, 0xFFFFFF);
        } else {
            guiGraphics.drawString(Minecraft.getInstance().font, buttonMessage, getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, 0xffffff);
            // VOLUME
            guiGraphics.drawString(Minecraft.getInstance().font, getValue(), getX() + getWidth() - Minecraft.getInstance().font.width(getValue()) - ((getHeight() - 8) / 2), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }

    // Мелочь
    @Override
    public void onPress() {
        this.position++;
        if(this.list.length == this.position) this.position = 0;
        if(this.onPress != null) this.onPress.onPress(this);
    }
    @Environment(EnvType.CLIENT)
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
