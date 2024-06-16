package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.ConfigureScrolWidget;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonSprite;
import ru.kelcuprum.alinlib.gui.components.text.DescriptionBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import java.util.List;

import static ru.kelcuprum.alinlib.gui.InterfaceUtils.Icons.RESET;

public class AbstractConfigScreen extends Screen {

    private ConfigureScrolWidget scroller;
    private ConfigureScrolWidget scroller_panel;
    private final ConfigScreenBuilder builder;
    private DescriptionBox descriptionBox;
    private boolean lastCheck = false;
    public AbstractConfigScreen(ConfigScreenBuilder builder) {
        super(builder.title);
        this.builder = builder;
    }

    @Override
    protected void init() {
        initPanelButtons();
        initCategory();
        if (this.minecraft.getLastInputType().isKeyboard()) {
            if (!this.builder.panelWidgets.isEmpty()) this.setFocused(getFirstActiveWidget(this.builder.panelWidgets));
            else if (!this.builder.widgets.isEmpty()) this.setFocused(getFirstActiveWidget(this.builder.widgets));
            else this.setFocused(back);
        }
    }
    protected AbstractWidget getFirstActiveWidget(List<AbstractWidget> widgets){
        AbstractWidget widget = widgets.get(0);
        for(AbstractWidget abstractWidget : widgets){
            if(abstractWidget.isActive()) {
                widget = abstractWidget;
                break;
            }
        }
        return widget;
    }
    AbstractWidget titleW;
    AbstractWidget back;
    AbstractWidget reset;
    protected void initPanelButtons(){
        // -=-=-=-=-=-=-=-
        titleW = addRenderableWidget(new TextBox(10, 5, this.builder.panelSize-20, 30, this.builder.title, true));
        // -=-=-=-=-=-=-=-
        this.descriptionBox = new DescriptionBox(10, 40, this.builder.panelSize-20, height - 75, Component.empty());
        this.descriptionBox.visible = false;
        addRenderableWidget(this.descriptionBox);
        // -=-=-=-=-=-=-=-
        // Exit Buttons
        // 85 before reset button
        back = addRenderableWidget(new Button(10, height - 30, this.builder.panelSize-45, 20, builder.type, CommonComponents.GUI_BACK, (OnPress) -> {
            assert this.minecraft != null;
            this.minecraft.setScreen(builder.parent);
        }));
        reset = addRenderableWidget(new ButtonSprite(this.builder.panelSize-30, height-30, 20, 20, builder.type, RESET, Localization.getText("alinlib.component.reset"), (OnPress) -> {
            for(AbstractWidget widget : builder.widgets){
                if(widget instanceof Resetable){
                    ((Resetable) widget).resetValue();
                }
            }
            assert this.minecraft != null;
            new ToastBuilder()
                    .setTitle(title)
                    .setMessage(Component.translatable("alinlib.component.reset.toast"))
                    .setIcon(RESET)
                    .show(this.minecraft.getToasts());
            AlinLib.log(Component.translatable("alinlib.component.reset.toast"));
        }));
        this.scroller_panel = addRenderableWidget(new ConfigureScrolWidget(-8, 0, 4, this.height, Component.empty(), scroller -> {
            scroller.innerHeight = 5;
            if(lastCheck){
                titleW.setY(5);
                back.setY(height-30);
                reset.setY(height-30);
            } else {
                titleW.setY((int) (scroller.innerHeight - scroller.scrollAmount()));
                scroller.innerHeight+=titleW.getHeight()+5;

                for(AbstractWidget widget : builder.panelWidgets){
                    if(widget.visible){
                        widget.setY((int) (scroller.innerHeight - scroller.scrollAmount()));
                        scroller.innerHeight += (widget.getHeight()+5);
                    } else widget.setY(-widget.getHeight());
                }
                if(scroller.innerHeight >= height-30){
                    back.setY((int) (scroller.innerHeight - scroller.scrollAmount()));
                    reset.setY((int) (scroller.innerHeight - scroller.scrollAmount()));
                    scroller.innerHeight += (20);
                }
            }
        }));
        addRenderableWidgets(builder.panelWidgets);
    }
    protected void initCategory(){
        int width = this.width - this.builder.panelSize - 20;
        for(AbstractWidget widget : builder.widgets){
            widget.setWidth(width);
            widget.setX(this.builder.panelSize+10);
        }
        this.scroller = addRenderableWidget(new ConfigureScrolWidget(this.width - 8, 0, 4, this.height, Component.empty(), scroller -> {
            scroller.innerHeight = 5;
            boolean descriptionEnable = false;
            for(AbstractWidget widget : builder.widgets){
                if(widget.visible){
                    if(widget instanceof Description){
                        if(widget.isHoveredOrFocused() && ((Description) widget).getDescription() != null && this.descriptionBox != null){
                            descriptionEnable = true;
                            this.descriptionBox.setDescription(((Description) widget).getDescription());
                        }
                    }
                    widget.setY((int) (scroller.innerHeight - scroller.scrollAmount()));
                    scroller.innerHeight += (widget.getHeight()+5);
                } else widget.setY(-widget.getHeight());
            }
            if(this.lastCheck != descriptionEnable){
                lastCheck = descriptionEnable;
                for(AbstractWidget widget : builder.panelWidgets){
                    widget.visible = !lastCheck;
                }
                this.descriptionBox.visible = lastCheck;
            }
        }));
        addRenderableWidgets(builder.widgets);
    }

    public void removeWidgetFromBuilder(){
        for (AbstractWidget widget : this.builder.widgets) {
            removeWidget(widget);
        }
        this.builder.widgets.clear();
    }
    public void removePanelWidgetFromBuilder(){
        for (AbstractWidget widget : this.builder.panelWidgets) {
            removeWidget(widget);
        }
        this.builder.panelWidgets.clear();
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
        for(AbstractWidget widget : widgets){
            addRenderableWidget(widget);
        }
    }

    // Рендер, скролл, прослушивание кей-биндов
    @Override
    public void tick(){
        if(scroller != null) scroller.onScroll.accept(scroller);
        if(scroller_panel != null) scroller_panel.onScroll.accept(scroller_panel);
        if(builder.onTick != null) builder.onTick.onTick(builder);
        if(builder.onTickScreen != null) builder.onTickScreen.onTick(builder, this);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        boolean scr = super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        if(mouseX <= this.builder.panelSize){
            if (!scr && scroller_panel != null) {
                scr = scroller_panel.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
            }
        } else {
            if (!scr && scroller != null) {
                scr = scroller.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
            }
        }
        return scr;
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f){
        assert this.minecraft != null;
        super.renderBackground(guiGraphics, i, j, f);
        InterfaceUtils.renderLeftPanel(guiGraphics, this.builder.panelSize, this.height);
    }

    @Override
    public boolean keyPressed(int i, int j, int k) {
        if(i == GLFW.GLFW_KEY_ESCAPE){
            if(getFocused() != null && getFocused().isFocused()) {
                getFocused().setFocused(false);
                return true;
            }
        }
        return super.keyPressed(i, j, k);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }
    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(builder.parent);
    }
}
