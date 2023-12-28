package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonSprite;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderPercent;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxColor;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.toast.AlinaToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlinaDemoScreen extends Screen {
    private final Screen parent;
    private static final ResourceLocation icon = new ResourceLocation("alinlib", "textures/gui/widget/test/well.png");
    private static final Component TITLE = Component.literal("AlinLib");
    private static final Component CATEGORY = Component.literal("Example page");
    private static final Component EDIT_BOX = Component.literal("Edit Box");
    private static final Component SECRET_EDIT_BOX = Component.literal("Secret Edit Box");
    private static final Component COLOR_EDIT_BOX = Component.literal("Color Edit Box");
    private static final Component SLIDER_PERCENT = Component.literal("Slider Percent");
    private static final Component SLIDER_INTEGER = Component.literal("Slider Integer");
    private static final Component SOMETHING = Component.translatable("alinlib.something");
    private static final Component GITHUB = Component.literal("GitHub");
    private static final Component EXIT = Component.literal("Exit");
    //
    private int scrolled = 0;
    //
    private InterfaceUtils.DesignType type = InterfaceUtils.DesignType.ALINA;
    //
    private List<AbstractWidget> widgetList = new ArrayList<AbstractWidget>();
    private CategoryBox category;
    private TextBox titleBox;
    private EditBoxString stringEditBox;
    private EditBoxString stringEditBoxSecret;
    private ButtonBoolean booleanButton;
    String[] hell = {
            "Hello",
            ",",
            "World",
            "!",
            "No...",
            "Welcome to Hell :)"
    };
    private SelectorStringButton selectorStringButton;
    private EditBoxColor colorEditBox;
    private SliderPercent sliderPercent;
    private SliderInteger sliderInt;
    //
    private TextBox something;

    private boolean isFollow = false;


    public AlinaDemoScreen(Screen parent) {
        super(CATEGORY);
        this.parent = parent;
    }
    public void tick() {
        int i = 5;
        for(AbstractWidget widget : widgetList){
            if(widget.visible) {
                widget.setY(i - scrolled);
                i += (widget.getHeight() + 5);
            } else widget.setY(-widget.getHeight());
        }
        super.tick();
    }

    @Override
    public void init() {
        scrolled = 0;
        initButton();
        initButtonsCategory();
    }

    private void initButtonsCategory(){
        this.widgetList = new ArrayList<>();
        category = new CategoryBox(140, 5, this.width - 150, 20, CATEGORY, true);
        category.setTooltip(Localization.toText("Hello, world!"));
        addRenderableWidget(category);
        widgetList.add(category);
        //
        booleanButton = new ButtonBoolean(140, 25, this.width - 150, 20, type, AlinLib.bariumConfig, "Boolean", true, Component.literal("Boolean"));
        addRenderableWidget(booleanButton);
        category.addValue(booleanButton);
        widgetList.add(booleanButton);
        //
        stringEditBox = new EditBoxString(140, 50, width-150, 20, AlinLib.bariumConfig.getString("HELLO", "Hello, world!"), type, EDIT_BOX, (string) -> AlinLib.bariumConfig.setString("HELLO", string));
        addRenderableWidget(stringEditBox);
        category.addValue(stringEditBox);
        widgetList.add(stringEditBox);
        //
        stringEditBoxSecret = new EditBoxString(140, 75, width-150, 20, true, AlinLib.bariumConfig.getString("SECRET", "Alina doesn't have a boyfriend"), type, SECRET_EDIT_BOX, (string) -> AlinLib.bariumConfig.setString("SECRET", string));
        addRenderableWidget(stringEditBoxSecret);
        category.addValue(stringEditBoxSecret);
        widgetList.add(stringEditBoxSecret);
        //
        selectorStringButton = new SelectorStringButton(140, 100, this.width - 150, 20, type, hell, AlinLib.bariumConfig, "selector", hell[0], Component.literal("Selector"));
        addRenderableWidget(selectorStringButton);
        category.addValue(selectorStringButton);
        widgetList.add(selectorStringButton);
        //
        colorEditBox = new EditBoxColor(140, 125, width-150, 20, type, AlinLib.bariumConfig, "Color", 0xFFFFFF, COLOR_EDIT_BOX);
        addRenderableWidget(colorEditBox);
        category.addValue(colorEditBox);
        widgetList.add(colorEditBox);
        //
        sliderPercent = new SliderPercent(140, 150, width-150, 20, type, AlinLib.bariumConfig, "Slider_percent", 0, SLIDER_PERCENT);
        addRenderableWidget(sliderPercent);
        category.addValue(sliderPercent);
        widgetList.add(sliderPercent);
        //
        sliderInt = new SliderInteger(140, 175, width-150, 20, type, AlinLib.bariumConfig, "Slider_int", 30, 30, 110, SLIDER_INTEGER);
        sliderInt.setTypeInteger(" Coffee");
        addRenderableWidget(sliderInt);
        category.addValue(sliderInt);
        widgetList.add(sliderInt);
        //
        something = new TextBox(140, 200, this.width - 150, 20, SOMETHING, true, (OnPress) -> {
            if(!this.isFollow){
                this.isFollow = true;
                this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Click me again :)"), new ResourceLocation("alinlib", "textures/gui/widget/test/well.png"), AlinaToast.Type.ERROR));
            } else {
                OnPress.setActive(false);
                Util.getPlatform().openUri("https://cdn.kelcuprum.ru/other/monke.gif");
                AlinLib.log(Component.translatable("alinlib.something.oops"));
                this.minecraft.stop();
            }
        });
        addRenderableWidget(something);
        widgetList.add(something);
    }
    private void initButton(){
        addRenderableWidget(new TextBox(10, 15, 110, this.font.lineHeight, TITLE, true));
        // line 0
        addRenderableWidget(new Button(10, 40, 110, 20, InterfaceUtils.DesignType.VANILLA, Colors.KENNY, Component.literal("DesignType.VANILLA"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.VANILLA"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.VANILLA;
            this.rebuildWidgets();
        }));
        addRenderableWidget(new Button(10, 65, 110, 20, InterfaceUtils.DesignType.ALINA, Colors.KENNY, Component.literal("DesignType.ALINA"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.ALINA"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.ALINA;
            this.rebuildWidgets();
        }));
        addRenderableWidget(new Button(10, 90, 110, 20, InterfaceUtils.DesignType.FLAT, Colors.KENNY, Component.literal("DesignType.FLAT"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Set DesignType.FLAT"), AlinaToast.Type.DEBUG));
            this.type = InterfaceUtils.DesignType.FLAT;
            this.rebuildWidgets();
        }));

        addRenderableWidget(new Button(10, height - 55, 110, 20, InterfaceUtils.DesignType.VANILLA, GITHUB, (OnPress) -> {
            Util.getPlatform().openUri("https://github.com/simply-kel/AlinLib/");
        }));


        addRenderableWidget(new Button(10, height - 30, 85, 20, InterfaceUtils.DesignType.VANILLA, EXIT, (OnPress) -> {
            AlinLib.bariumConfig.save();
            this.minecraft.setScreen(parent);
        }));
        addRenderableWidget(new ButtonSprite(100, height-30, 20, 20, InterfaceUtils.DesignType.VANILLA, icon, Component.literal("Alina"), 20, 20, (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Alina sends her love"), icon));
        }));
    }
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f){
        InterfaceUtils.renderBackground(guiGraphics, this.minecraft);
        InterfaceUtils.renderLeftPanel(guiGraphics, 130, this.height);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseScrolled(double d, double e, double f, double g) {
        int scrolled = (int)((double)this.scrolled + g * -9.0);
        int size = 900;
        if (scrolled <= 0 || size <= this.height) {
            this.scrolled = 0;
        } else this.scrolled = Math.min(scrolled, size - this.height);

        return super.mouseScrolled(d, e, f, g);
    }
}
