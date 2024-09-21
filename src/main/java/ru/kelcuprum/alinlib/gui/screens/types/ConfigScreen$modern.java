package ru.kelcuprum.alinlib.gui.screens.types;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.components.ConfigureScrolWidget;
import ru.kelcuprum.alinlib.gui.components.Description;
import ru.kelcuprum.alinlib.gui.components.Resetable;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.DescriptionBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.AbstractConfigScreen;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import static ru.kelcuprum.alinlib.gui.Icons.EXIT;
import static ru.kelcuprum.alinlib.gui.Icons.RESET;

public class ConfigScreen$modern extends AbstractConfigScreen {
    public ConfigScreen$modern(ConfigScreenBuilder builder) {
        super(builder);
    }

    @Override
    protected void init() {
        initPanelButtons();
        initCategory();
        super.init();
    }

    public void initPanelButtons() {
        // -=-=-=-=-=-=-=-
        titleW = addRenderableWidget(new TextBox(5, 5, this.builder.panelSize - 10, 20, this.builder.title, true));
        // -=-=-=-=-=-=-=-
        this.descriptionBox = new DescriptionBox(10, 35, this.builder.panelSize - 20, height - 70, Component.empty());
        this.descriptionBox.visible = false;
        addRenderableWidget(this.descriptionBox);
        // -=-=-=-=-=-=-=-
        // Exit Buttons
        // 85 before reset button

        back = addRenderableWidget(new ButtonBuilder(CommonComponents.GUI_BACK).setOnPress((OnPress) -> {
            assert this.minecraft != null;
            this.minecraft.setScreen(builder.parent);
        }).setIcon(AlinLib.isAprilFool() ? EXIT : null).setPosition(5, height - 25).setSize(this.builder.panelSize - 35, 20).build());

        reset = addRenderableWidget(new ButtonBuilder(Component.translatable("alinlib.component.reset")).setOnPress((OnPress) -> {
            for (AbstractWidget widget : builder.widgets)
                if (widget instanceof Resetable) ((Resetable) widget).resetValue();
            assert this.minecraft != null;
            new ToastBuilder()
                    .setTitle(title)
                    .setMessage(Component.translatable("alinlib.component.reset.toast"))
                    .setIcon(RESET)
                    .buildAndShow();
            AlinLib.log(Component.translatable("alinlib.component.reset.toast"));
        }).setSprite(RESET).setSize(20, 20).setPosition(this.builder.panelSize - 25, height - 25).build());
        this.scroller_panel = addRenderableWidget(new ConfigureScrolWidget(builder.panelSize-9, 30, 4, this.height - 60, Component.empty(), scroller -> {
            scroller.innerHeight = 5;
            for (AbstractWidget widget : builder.panelWidgets) {
                if (widget.visible) {
                    widget.setY(30+(int) (scroller.innerHeight - scroller.scrollAmount()));
                    scroller.innerHeight += (widget.getHeight() + 5);
                } else widget.setY(-widget.getHeight());
            }
            scroller.innerHeight-=8;
        }));
        addRenderableWidgets$scroller(scroller_panel, builder.panelWidgets);
    }

    public void initCategory() {
        int width = this.width - this.builder.panelSize - 15;
        for (AbstractWidget widget : builder.widgets) {
            widget.setWidth(width);
            widget.setX(this.builder.panelSize + 5);
        }
        this.scroller = addRenderableWidget(new ConfigureScrolWidget(this.width - 8, 0, 4, this.height, Component.empty(), scroller -> {
            scroller.innerHeight = 5;
            boolean descriptionEnable = false;
            CategoryBox lastCategory = null;
            for (AbstractWidget widget : builder.widgets) {
                if (widget.visible) {
                    if (widget instanceof Description) {
                        if (widget.isHoveredOrFocused() && ((Description) widget).getDescription() != null && this.descriptionBox != null) {
                            descriptionEnable = true;
                            this.descriptionBox.setDescription(((Description) widget).getDescription());
                        }
                    }
                    if (widget instanceof CategoryBox) {
                        if (lastCategory != widget && ((CategoryBox) widget).getState())
                            lastCategory = (CategoryBox) widget;
                    }
                    if (lastCategory != null && !(widget instanceof CategoryBox)) {
                        if (!lastCategory.values.contains(widget)) {
                            scroller.innerHeight += 6;
                            lastCategory.setRenderLine(true);
                            lastCategory = null;
                        }
                    }
                    widget.setY((int) (scroller.innerHeight - scroller.scrollAmount()));
                    scroller.innerHeight += (widget.getHeight() + 5);
                } else widget.setY(-widget.getHeight());
            }
            scroller.innerHeight -= 8;
            if (this.lastCheck != descriptionEnable) {
                lastCheck = descriptionEnable;
                for (AbstractWidget widget : builder.panelWidgets) {
                    widget.visible = !lastCheck;
                }
                this.descriptionBox.visible = lastCheck;
            }
        }));
        addRenderableWidgets(builder.widgets);
    }

    @Override
    public boolean mouseClicked(double d, double e, int i) {
        boolean st = true;
        GuiEventListener selected = null;
        for (GuiEventListener guiEventListener : this.children()) {
            if (scroller_panel != null && scroller_panel.widgets.contains(guiEventListener)) {
                if ((d >= 10 && d <= builder.panelSize-10) && (e >= 35 && e <= height-35)) {
                    if (guiEventListener.mouseClicked(d, e, i)) {
                        st = false;
                        selected = guiEventListener;
                        break;
                    }
                }
            } else if (guiEventListener.mouseClicked(d, e, i)) {
                st = false;
                selected = guiEventListener;
                break;
            }
        }

        this.setFocused(selected);
        if (i == 0) {
            this.setDragging(true);
        }

        return st;
    }

    //#if MC >= 12002
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        boolean scr = super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        if (mouseX <= this.builder.panelSize) {
            if (descriptionBox.visible && (mouseX >= 5 && mouseX <= builder.panelSize - 5) && (mouseY >= 40 && mouseY <= height - 30)) {
                scr = descriptionBox.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
            } else if (!scr && scroller_panel != null) {
                scr = scroller_panel.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
            }
        } else {
            if (!scr && scroller != null) {
                scr = scroller.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
            }
        }
        return scr;
    }
    //#elseif MC < 12002
    //$$ @Override
    //$$   public boolean mouseScrolled(double mouseX, double mouseY, double scrollY) {
    //$$      boolean scr = super.mouseScrolled(mouseX, mouseY, scrollY);
    //$$      if(mouseX <= this.builder.panelSize){
    //$$          if(descriptionBox.visible && (mouseX >= 5 && mouseX <= builder.panelSize-5) && (mouseY >= 40 && mouseY <= height - 30)){
    //$$                scr = descriptionBox.mouseScrolled(mouseX, mouseY, scrollY);
    //$$            } else if (!scr && scroller_panel != null) {
    //$$              scr = scroller_panel.mouseScrolled(mouseX, mouseY, scrollY);
    //$$          }
    //$$      } else {
    //$$          if (!scr && scroller != null) {
    //$$              scr = scroller.mouseScrolled(mouseX, mouseY, scrollY);
    //$$          }
    //$$      }
    //$$      return scr;
    //$$  }
    //#endif

    //#if MC >= 12002
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        assert this.minecraft != null;
        super.renderBackground(guiGraphics, i, j, f);
        //#elseif MC < 12002
        //$$  @Override
        //$$  public void renderBackground(GuiGraphics guiGraphics){
        //$$      assert this.minecraft != null;
        //$$      super.renderBackground(guiGraphics);
        //#endif
        guiGraphics.fill(5, 5, this.builder.panelSize-5, 25, Colors.BLACK_ALPHA);
        guiGraphics.fill(5, 30, this.builder.panelSize-5, this.height-30, Colors.BLACK_ALPHA);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        //#if MC < 12002
        //$$ renderBackground(guiGraphics);
        //#endif
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        guiGraphics.enableScissor(10, 35, builder.panelSize-10, this.height-35);
        if (scroller_panel != null) for (AbstractWidget widget : scroller_panel.widgets) widget.render(guiGraphics, mouseX, mouseY, partialTicks);
        guiGraphics.disableScissor();
    }
}
