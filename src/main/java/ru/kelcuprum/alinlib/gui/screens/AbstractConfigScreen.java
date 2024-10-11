package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.ConfigureScrolWidget;
import ru.kelcuprum.alinlib.gui.components.text.DescriptionBox;

import java.util.List;

public class AbstractConfigScreen extends Screen {

    public ConfigureScrolWidget scroller;
    public ConfigureScrolWidget scroller_panel;
    public final ConfigScreenBuilder builder;
    public DescriptionBox descriptionBox;
    public boolean lastCheck = false;
    public AbstractConfigScreen(ConfigScreenBuilder builder) {
        super(builder.title);
        this.builder = builder;
    }

    @Override
    protected void init() {
        assert this.minecraft != null;
        if (this.minecraft.getLastInputType().isKeyboard()) {
            if (!this.builder.panelWidgets.isEmpty()) this.setFocused(getFirstActiveWidget(this.builder.panelWidgets));
            else if (!this.builder.widgets.isEmpty()) this.setFocused(getFirstActiveWidget(this.builder.widgets));
            else this.setFocused(back);
        }
    }
    public AbstractWidget getFirstActiveWidget(List<AbstractWidget> widgets){
        AbstractWidget widget = widgets
                //#if MC > 12005
                .getFirst();
        //#else
        //$$ .get(0);
        //#endif
        for(AbstractWidget abstractWidget : widgets){
            if(abstractWidget.isActive()) {
                widget = abstractWidget;
                break;
            }
        }
        return widget;
    }
    public AbstractWidget titleW;
    public AbstractWidget back;
    public AbstractWidget reset;

    public void removeWidgetFromBuilder(){
        for (AbstractWidget widget : this.builder.widgets)
            removeWidget(widget);
        this.builder.widgets.clear();
    }
    public void removePanelWidgetFromBuilder(){
        for (AbstractWidget widget : this.builder.panelWidgets)
            removeWidget(widget);
        this.builder.panelWidgets.clear();
    }
    public void initCategory(){

    }
    public void initPanelButtons(){

    }

    public void rebuildPanel(){
        removeWidget(scroller_panel);
        scroller_panel = null;
        removeWidget(titleW);
        removeWidget(back);
        removeWidget(reset);
        removeWidget(descriptionBox);
        for (AbstractWidget widget : this.builder.panelWidgets) {
            removeWidget(widget);
        }
        initPanelButtons();
    }
    public void rebuildCategory(){
        removeWidget(scroller);
        scroller = null;
        for (AbstractWidget widget : this.builder.widgets) {
            removeWidget(widget);
        }
        initCategory();
    }

    // Добавление виджетов
    protected void addRenderableWidgets(@NotNull List<AbstractWidget> widgets){
        for(AbstractWidget widget : widgets)
            addRenderableWidget(widget);
    }

    protected void addRenderableWidgets$scroller(ConfigureScrolWidget scroller, @NotNull List<AbstractWidget> widgets){
        scroller.addWidgets(widgets);
        for(AbstractWidget widget : widgets) addWidget(widget);
    }
    protected void addRenderableWidgets$scroller(@NotNull List<AbstractWidget> widget){
        addRenderableWidgets$scroller(scroller, widget);
    }

    protected void addRenderableWidgets$scroller(@NotNull AbstractWidget widget){
        addRenderableWidgets$scroller(scroller, widget);
    }
    protected void addRenderableWidgets$scroller(ConfigureScrolWidget scroller, @NotNull AbstractWidget widget){
        scroller.addWidget(widget);
        addWidget(widget);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(keyCode == GLFW.GLFW_KEY_ESCAPE){
            if(getFocused() != null && getFocused().isFocused()) {
                getFocused().setFocused(false);
                return true;
            }
        }
        if(keyCode == GLFW.GLFW_KEY_D && (modifiers & GLFW.GLFW_MOD_SHIFT) != 0 && !(getFocused() instanceof EditBox))
            AlinLib.MINECRAFT.setScreen(new ThanksScreen(this));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    // Рендер, скролл, прослушивание кей-биндов
    @Override
    public void tick(){
        if(scroller != null) scroller.onScroll.accept(scroller);
        if(scroller_panel != null) scroller_panel.onScroll.accept(scroller_panel);
        if(builder.onTick != null) builder.onTick.onTick(builder);
        if(builder.onTickScreen != null) builder.onTickScreen.onTick(builder, this);
    }

    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(builder.parent);
    }
}
