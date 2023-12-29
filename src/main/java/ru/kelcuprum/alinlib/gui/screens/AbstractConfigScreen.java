package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.ConfigureScrolWidget;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonSprite;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.toast.AlinaToast;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConfigScreen extends Screen {
    private static final ResourceLocation icon = new ResourceLocation("alinlib", "textures/gui/widget/buttons/reset.png");

    protected List<AbstractWidget> widgetList = new ArrayList<AbstractWidget>();
    private ConfigureScrolWidget scroller;
    private final Screen parent;
    public AbstractConfigScreen(Screen parent, Component title) {
        super(title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        initPanelButtons();
        initCategory();
    }

    protected void initPanelButtons(){
        // -=-=-=-=-=-=-=-
        addRenderableWidget(new TextBox(10, 15, 110, this.font.lineHeight, this.title, true));
        // -=-=-=-=-=-=-=-
        // Exit Buttons
        // 85 before reset button
        addRenderableWidget(new Button(10, height - 30, 85, 20, InterfaceUtils.DesignType.VANILLA, Localization.getText("alinlib.component.exit"), (OnPress) -> {
            AlinLib.bariumConfig.save();
            this.minecraft.setScreen(parent);
        }));
        addRenderableWidget(new ButtonSprite(100, height-30, 20, 20, InterfaceUtils.DesignType.VANILLA, icon, Localization.getText("alinlib.component.reset"), 20, 20, (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(title, Component.translatable("alinlib.component.reset.toast"), icon));
            for(AbstractWidget widget : widgetList){
                if(widget instanceof Resetable){
                    ((Resetable) widget).resetValue();
                }
            }
        }));
    }
    protected void initCategory(){
        this.widgetList = new ArrayList<>();
        this.scroller = addRenderableWidget(new ConfigureScrolWidget(width - 8, 0, 4, height, Component.empty(), scroller -> {
            scroller.innerHeight = 5;

            for(AbstractWidget widget : widgetList){
                if(widget.visible){
                    if (widget instanceof CategoryBox && scroller.innerHeight > 5) {
                        scroller.innerHeight += 10;
                    }

                    widget.setY((int) (scroller.innerHeight - scroller.scrollAmount()));

                    scroller.innerHeight += (widget.getHeight()+5);
                } else widget.setY(-widget.getHeight());
            }
        }));
        addRenderableWidget(scroller);
        this.scroller.onScroll.accept(scroller);
    }
    protected void addRenderableWidgets(List<AbstractWidget> widgets){
        for(AbstractWidget widget : widgets){
            addRenderableWidget(widget);
        }
    }
    //
    @Override
    public void tick(){
        scroller.onScroll.accept(scroller);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        boolean scr = super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);

        if (!scr && scroller != null) {
            scr = scroller.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        return scr;
    }
    //

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f){
        InterfaceUtils.renderBackground(guiGraphics, this.minecraft);
        InterfaceUtils.renderLeftPanel(guiGraphics, 130, this.height);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }
}
