package ru.kelcuprum.alinlib.gui.screens.types;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.*;
import net.minecraft.util.FormattedCharSequence;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.components.*;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.text.*;
import ru.kelcuprum.alinlib.gui.screens.*;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;

import java.util.List;
import java.util.Objects;

import static ru.kelcuprum.alinlib.gui.Icons.*;

public class ConfigScreen$withoutPanel extends AbstractConfigScreen {
    public ConfigScreen$withoutPanel(ConfigScreenBuilder builder) {
        super(builder);
    }

    @Override
    protected void init() {
        initPanelButtons();
        initCategory();
        super.init();
    }

    public void initPanelButtons() {
        int size = Math.min(maxSize, this.width - 10);
        int x = (width - size) / 2;
        // -=-=-=-=-=-=-=-
        titleW = addRenderableWidget(new TextBuilder(this.builder.title).setPosition(x + 25, 5).setSize(size - 50, 20).build());
        // -=-=-=-=-=-=-=-
        // Exit Buttons
        // 85 before reset button

        back = addRenderableWidget(new ButtonBuilder(AlinLib.isAprilFool() ? CommonComponents.GUI_BACK : Component.literal("x")).setOnPress((OnPress) -> {
            assert this.minecraft != null;
            this.minecraft.setScreen(builder.parent);
        }).setSprite(AlinLib.isAprilFool() ? EXIT : null).setPosition(x + size - 20, 5).setSize(20, 20).build());

        if (builder.isResetable)
            reset = addRenderableWidget(new ButtonBuilder(Component.translatable("alinlib.component.reset")).setOnPress((OnPress) ->
                    this.minecraft.setScreen(new ConfirmScreen(this, RESET, Component.translatable("alinlib.title.reset"), Component.translatable("alinlib.title.reset.description"), (bl) -> {
                        if (bl) {
                            for (AbstractWidget widget : builder.widgets)
                                if (widget instanceof Resetable) ((Resetable) widget).resetValue();
                            assert this.minecraft != null;
                            new ToastBuilder()
                                    .setTitle(title)
                                    .setMessage(Component.translatable("alinlib.component.reset.toast"))
                                    .setIcon(RESET)
                                    .buildAndShow();
                            AlinLib.LOG.log(Component.translatable("alinlib.component.reset.toast"));
                        }
                    }))).setSprite(RESET).setSize(20, 20).setPosition(x, 5).build());
        else {

        }
        addRenderableWidgets(builder.panelWidgets);
    }

    Component description = Component.empty();

    private final int maxSize = 400;

    public void initCategory() {
        int size = Math.min(maxSize, this.width - 10);
        int x = (width - size) / 2;
        int oy = 30;
        for (AbstractWidget widget : builder.widgets) {
            widget.setWidth(size);
            widget.setY(oy);
            widget.setX(x);
            oy += (widget.getHeight() + 5);
        }
        this.scroller = addRenderableWidget(new ConfigureScrolWidget(x + size + 1, 30, 4, this.height - 35, Component.empty(), scroller -> {
            scroller.innerHeight = 0;
            CategoryBox lastCategory = null;
            Component lastDescription = null;
            for (AbstractWidget widget : builder.widgets) {
                if (widget.visible) {
                    if (widget instanceof Description) {
                        if (widget.isHoveredOrFocused() && ((Description) widget).getDescription() != null)
                            lastDescription = ((Description) widget).getDescription();
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
                    widget.setY(30 + ((int) (scroller.innerHeight - scroller.scrollAmount())));
                    scroller.innerHeight += (widget.getHeight() + 5);
                } else widget.setY(-widget.getHeight());
            }
            scroller.innerHeight -= 13;
            description = lastDescription != null ? lastDescription : Component.empty();
        }));
        addRenderableWidgets$scroller(builder.widgets);
    }

    @Override
    public boolean mouseClicked(double d, double e, int i
                                //#if MC >= 12109
            , boolean b
                                //#endif
    ) {
        int size = Math.min(maxSize, width - 10);
        int x = (width - size) / 2;
        boolean st = true;
        GuiEventListener selected = null;
        for (GuiEventListener guiEventListener : this.children()) {
            if (scroller != null && scroller.widgets.contains(guiEventListener)) {
                if ((d >= x && d <= x + size) && e >= 30) {
                    if (guiEventListener.mouseClicked(d, e, i
                            //#if MC >= 12109
                            , b
                            //#endif
                    )) {
                        st = false;
                        selected = guiEventListener;
                        break;
                    }
                }
            } else if (guiEventListener.mouseClicked(d, e, i
                    //#if MC >= 12109
                    , b
                    //#endif
            )) {
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
        if (!scr && scroller != null) {
            scr = scroller.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
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
        int size = Math.min(maxSize, width - 10);
        int x = (width - size) / 2;
        guiGraphics.fill(x - 5, 0, x + size + 5, height, Colors.BLACK_ALPHA); // Затемнение
        guiGraphics.fill(x + 25, 5, x + size - 25, 25, Colors.BLACK_ALPHA);
    }
    //#elseif MC < 12002
    //$$
    //$$  @Override
    //$$  public void renderBackground(GuiGraphics guiGraphics){
    //$$      assert this.minecraft != null;
    //$$      super.renderBackground(guiGraphics);
    //$$      int size = Math.min(maxSize, width-10);
    //$$      int x = (width-size) / 2;
    //$$      guiGraphics.fill(x-5, 0, x+size+5, height, Colors.BLACK_ALPHA); // Затемнение
    //$$      guiGraphics.fill(x+25, 5, x+size-25, 25, Colors.BLACK_ALPHA);
    //$$  }
    //#endif

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        //#if MC < 12002
        //$$ renderBackground(guiGraphics);
        //#endif
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        guiGraphics.enableScissor(0, 30, width, this.height - 5);
        if (scroller != null)
            for (AbstractWidget widget : scroller.widgets) widget.render(guiGraphics, mouseX, mouseY, partialTicks);
        guiGraphics.disableScissor();
        if (!Objects.equals(description, Component.empty())) {
            List<FormattedCharSequence> listed = AlinLib.MINECRAFT.font.split(description, width - 20);
            //#if MC <= 12105
            //$$ guiGraphics.renderTooltip(AlinLib.MINECRAFT.font, listed, mouseX, mouseY);
            //#else
            guiGraphics.setTooltipForNextFrame(AlinLib.MINECRAFT.font, listed, mouseX, mouseY);
            //#endif
        }
    }
}
