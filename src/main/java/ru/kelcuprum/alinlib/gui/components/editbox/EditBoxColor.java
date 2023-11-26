package ru.kelcuprum.alinlib.gui.components.editbox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Config;

import java.awt.*;

public class EditBoxColor extends EditBox {
    public int volume;
    public int defaultConfig;
    public Config config;
    public String typeConfig;
    public boolean isError = false;
    public EditBoxColor(int x, int y, int width, int height, Config config, String typeConfig, int defaultConfig, Component label) {
        super(Minecraft.getInstance().font, x, y, width, height, label);
        this.setMaxLength(20);
        this.config = config;
        this.typeConfig = typeConfig;
        this.defaultConfig = defaultConfig;
        this.volume = config.getInt(typeConfig, defaultConfig);
        this.setValue("#"+Integer.toHexString(new Color(this.volume).getRGB()).substring(2));
        this.setResponder(s->{
            try {
                this.volume = Color.decode(s).getRGB();
                this.config.setInt(this.typeConfig, volume);
                isError = false;
            } catch (Exception ex){
                AlinLib.log(ex.getLocalizedMessage(), Level.ERROR);
                isError = true;
            }
        });
    }
    public void setActive(boolean active){
        this.active = active;
    }

    public void setYPos(int i) {
        this.setY(i);
    }

    public void setXPos(int i) {
        this.setX(i);
    }


    public void setPos(int i, int j) {
        this.setPosition(i, j);
    }

    @Override
    public void onClick(double d, double e) {
    }

    private int getPositionContent(String content){
        int pos = getX() + getWidth()-Minecraft.getInstance().font.width(content)-((getHeight() - 8) / 2);
        if(getX() + Minecraft.getInstance().font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2 - Minecraft.getInstance().font.width(content) > pos) pos = getX() + Minecraft.getInstance().font.width(this.getMessage()) + ((getHeight() - 8) / 2)*2;
        return pos;
    }
    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            float state = !active ? 3 : isHoveredOrFocused() ? 2 : 1;
            final float f = state / 2 * 0.9F + 0.1F;
            final int color = (int) (255.0F * f);
            Color fColor = new Color(volume);

            guiGraphics.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight()-1, color / 2 << 24);
            guiGraphics.fill(getX(), getY() + getHeight()-1, getX() + getWidth(), getY() + getHeight(), fColor.getRGB());


            guiGraphics.drawString(Minecraft.getInstance().font, getMessage(), getX() + (getHeight() - 8) / 2, getY() + (getHeight() - 8) / 2, isError ? Colors.GROUPIE : 0xffffff);
            Component volume = Component.literal(this.getValue());
            if(isFocused()){
                if(this.getValue().length() != this.getCursorPosition()){
                    int position = Minecraft.getInstance().font.width(this.getValue().substring(0, this.getCursorPosition()));
                    int symbolSize = Minecraft.getInstance().font.width(this.getValue().split("(?!^)")[this.getCursorPosition()]);
                    int renderPosition = getPositionContent(volume.getString()) + position;
                    int y = (getY() + (getHeight() - 8) / 2)+Minecraft.getInstance().font.lineHeight+1;

                    guiGraphics.fill(renderPosition-1, y, renderPosition+symbolSize, y+1, new Color(0xFFFFFFFF, true).getRGB());
                }

            }
            guiGraphics.drawString(Minecraft.getInstance().font, volume, getPositionContent(volume.getString()), getY() + (getHeight() - 8) / 2, 0xffffff);
        }
    }
}
