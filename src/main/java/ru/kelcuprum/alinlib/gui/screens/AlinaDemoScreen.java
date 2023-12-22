package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.json.JSONObject;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonSprite;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderPercent;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxColor;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.toast.AlinaToast;

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


    public AlinaDemoScreen(Screen parent) {
        super(CATEGORY);
        this.parent = parent;
    }
    public void tick() {
        titleBox.setY(15-scrolled);
        booleanButton.setY(40-scrolled);
        stringEditBox.setY(40+(25)-scrolled);
        stringEditBoxSecret.setY(40+(25*2)-scrolled);
        selectorStringButton.setY(40+(25*3)-scrolled);
        colorEditBox.setY(40+(25*4)-scrolled);
        sliderPercent.setY(40+(25*5)-scrolled);
        sliderInt.setY(40+(25*6)-scrolled);
        //
        something.setY(875-scrolled);
        super.tick();
    }

    @Override
    public void init() {
        scrolled = 0;
        initButton();
        initButtonsCategory();
    }

    private void initButtonsCategory(){
        titleBox = new TextBox(140, 15, this.width - 150, this.font.lineHeight, CATEGORY, true);
        titleBox.setTooltip(Localization.toText("Hello, world!"));
        addRenderableWidget(titleBox);

        booleanButton = new ButtonBoolean(140, 40, this.width - 150, 20, type, AlinLib.bariumConfig, "Boolean", true, Component.literal("Boolean"));
        addRenderableWidget(booleanButton);
        stringEditBox = new EditBoxString(140, 40+(25), width-150, 20, false, EDIT_BOX, type);
        stringEditBox.setValue(AlinLib.bariumConfig.getString("HELLO", "Hello, world!"));
        stringEditBox.setResponder((string) -> {
            AlinLib.bariumConfig.setString("HELLO", string);
        });

        addRenderableWidget(stringEditBox);

        stringEditBoxSecret = new EditBoxString(140, 40+(25*2), width-150, 20, true, SECRET_EDIT_BOX, type);
        stringEditBoxSecret.setValue(AlinLib.bariumConfig.getString("SECRET", "Alina doesn't have a boyfriend"));
        stringEditBoxSecret.setResponder((string) -> {
            AlinLib.bariumConfig.setString("SECRET", string);
        });

        addRenderableWidget(stringEditBoxSecret);
        //
        selectorStringButton = new SelectorStringButton(140, 40+(25*3), this.width - 150, 20, type, hell, AlinLib.bariumConfig, "selector", hell[0], Component.literal("Selector"));
        addRenderableWidget(selectorStringButton);
        //
        colorEditBox = new EditBoxColor(140, 40+(25*4), width-150, 20, type, AlinLib.bariumConfig, "Color", 0xFFFFFF, COLOR_EDIT_BOX);
        addRenderableWidget(colorEditBox);
        //
        sliderPercent = new SliderPercent(140, 40+(25*5), width-150, 20, type, AlinLib.bariumConfig, "Slider_percent", 0, SLIDER_PERCENT);
        addRenderableWidget(sliderPercent);
        sliderInt = new SliderInteger(140, 40+(25*6), width-150, 20, type, AlinLib.bariumConfig, "Slider_int", 30, 30, 110, SLIDER_INTEGER);
        sliderInt.setTypeInteger(" Coffee");
        addRenderableWidget(sliderInt);
        //
        something = addRenderableWidget(new TextBox(140, 875, this.width - 150, this.font.lineHeight, SOMETHING, true, (OnPress) -> {
            OnPress.setActive(false);
            AlinLib.log(Component.translatable("alinlib.something.oops"));
            this.minecraft.stop();
        }));
    }
    private void initButton(){
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
        InterfaceUtils.renderTextureLeftPanel(guiGraphics, 130, this.height);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {

        guiGraphics.drawCenteredString(this.minecraft.font, TITLE, 120 / 2+5, 15, -1);
        //
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
