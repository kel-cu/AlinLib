package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.*;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.Colors;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.flat.FlatButton;
import ru.kelcuprum.alinlib.gui.components.buttons.flat.FlatButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.flat.FlatColoredButton;
import ru.kelcuprum.alinlib.gui.components.buttons.vanilla.VanillaButton;
import ru.kelcuprum.alinlib.gui.components.buttons.vanilla.VanillaButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxSecretString;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.editbox.flat.FlatEditBoxSecretString;
import ru.kelcuprum.alinlib.gui.components.editbox.flat.FlatEditBoxString;
import ru.kelcuprum.alinlib.gui.components.selector.flat.FlatSelectorStringButton;
import ru.kelcuprum.alinlib.gui.components.selector.vanilla.VanillaSelectorStringButton;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.SliderPercent;
import ru.kelcuprum.alinlib.gui.components.sliders.flat.FlatSliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.flat.FlatSliderPercent;
import ru.kelcuprum.alinlib.gui.components.sliders.vanilla.VanillaSliderInteger;
import ru.kelcuprum.alinlib.gui.components.sliders.vanilla.VanillaSliderPercent;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxColor;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.toast.AlinaToast;

import java.util.Iterator;

public class AlinaDemoScreen extends Screen {
    private final Screen parent;
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
    private TextBox titleBox;
    private EditBoxString stringEditBox;
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
    private EditBoxSecretString secretStringEditBox;
    //
    private TextBox flatTitleBox;
    private FlatEditBoxString flatStringEditBox;
    private FlatButtonBoolean flatBooleanButton;
    String[] flatHell = {
            "Hello",
            ",",
            "World",
            "!",
            "No...",
            "Welcome to Hell :)"
    };
    private FlatSelectorStringButton flatSelectorStringButton;
    private FlatSliderPercent flatSliderPercent;
    private FlatSliderInteger flatSliderInt;
    private FlatEditBoxSecretString flatSecretStringEditBox;
    //
    private TextBox vanillaTitleBox;
    private VanillaButton vanillaButton;
    private VanillaButtonBoolean vanillaBooleanButton;
    String[] vanillaHell = {
            "Hello",
            ",",
            "World",
            "!",
            "No...",
            "Welcome to Hell :)"
    };
    private VanillaSelectorStringButton vanillaSelectorStringButton;
    private VanillaSliderPercent vanillaSliderPercent;
    private VanillaSliderInteger vanillaSliderInt;
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
        selectorStringButton.setY(40+(25*2)-scrolled);
        colorEditBox.setY(40+(25*3)-scrolled);
        sliderPercent.setY(40+(25*4)-scrolled);
        sliderInt.setY(40+(25*5)-scrolled);
        secretStringEditBox.setY(40+(25*6)-scrolled);
        //
        flatTitleBox.setY(40+(25*7)-scrolled);
        flatBooleanButton.setY(40+(25*8)-scrolled);
        flatStringEditBox.setY(40+(25*9)-scrolled);
        flatSelectorStringButton.setY(40+(25*10)-scrolled);
        flatSliderPercent.setY(40+(25*11)-scrolled);
        flatSliderInt.setY(40+(25*12)-scrolled);
        flatSecretStringEditBox.setY(40+(25*13)-scrolled);
        //
        vanillaTitleBox.setY(40+(25*14)-scrolled);
        vanillaBooleanButton.setY(40+(25*15)-scrolled);
        vanillaButton.setY(40+(25*16)-scrolled);
        vanillaSelectorStringButton.setY(40+(25*17)-scrolled);
        vanillaSliderPercent.setY(40+(25*18)-scrolled);
        vanillaSliderInt.setY(40+(25*19)-scrolled);
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
        titleBox = new TextBox(140, 15, this.width - 150, this.font.lineHeight, Component.literal("Default design"), true);
        titleBox.setTooltip(Localization.toText("Hello, world!"));
        addRenderableWidget(titleBox);

        booleanButton = new ButtonBoolean(140, 40, this.width - 150, 20, AlinLib.bariumConfig, "Boolean", true, Component.literal("Boolean"));
        addRenderableWidget(booleanButton);
        stringEditBox = new EditBoxString(140, 40+(25), width-150, 20, EDIT_BOX);
        stringEditBox.setValue(AlinLib.bariumConfig.getString("HELLO", "Hello, world!"));
        stringEditBox.setResponder((string) -> {
            AlinLib.bariumConfig.setString("HELLO", string);
        });

        addRenderableWidget(stringEditBox);
        //
        selectorStringButton = new SelectorStringButton(140, 40+(25*2), this.width - 150, 20, hell, AlinLib.bariumConfig, "selector", hell[0], Component.literal("Selector"));
        addRenderableWidget(selectorStringButton);
        //
        colorEditBox = new EditBoxColor(140, 40+(25*3), width-150, 20, AlinLib.bariumConfig, "Color", 0xFFFFFF, COLOR_EDIT_BOX);
        addRenderableWidget(colorEditBox);
        //
        sliderPercent = new SliderPercent(140, 40+(25*4), width-150, 20, AlinLib.bariumConfig, "Slider_percent", 0, SLIDER_PERCENT);
        addRenderableWidget(sliderPercent);
        sliderInt = new SliderInteger(140, 40+(25*5), width-150, 20, AlinLib.bariumConfig, "Slider_int", 30, 30, 110, SLIDER_INTEGER);
        sliderInt.setTypeInteger(" Coffee");
        addRenderableWidget(sliderInt);
        //
        secretStringEditBox = new EditBoxSecretString(140, 40+(25*6), width-150, 20, SECRET_EDIT_BOX);
        secretStringEditBox.setValue(AlinLib.bariumConfig.getString("HELLO_ALINA", "Hello, Alina! How are you?"));
        secretStringEditBox.setResponder((string) -> {
            AlinLib.bariumConfig.setString("HELLO_ALINA", string);
        });

        addRenderableWidget(secretStringEditBox);
        // - - - - - - - - -
        flatTitleBox = new TextBox(140, 40+(25*7), this.width - 150, 20, Component.literal("Flat design"), true);
        flatTitleBox.setTooltip(Localization.toText("Hello, world!"));
        addRenderableWidget(flatTitleBox);

        flatBooleanButton = new FlatButtonBoolean(140, 40+(25*8), this.width - 150, 20, AlinLib.bariumConfig, "Boolean1", true, Component.literal("Boolean"));
        addRenderableWidget(flatBooleanButton);
        flatStringEditBox = new FlatEditBoxString(140, 40+(25*9), width-150, 20, EDIT_BOX);
        flatStringEditBox.setValue(AlinLib.bariumConfig.getString("HELLO_flat", "Hello, world!"));
        flatStringEditBox.setResponder((string) -> {
            AlinLib.bariumConfig.setString("HELLO_flat", string);
        });

        addRenderableWidget(flatStringEditBox);
        //
        flatSelectorStringButton = new FlatSelectorStringButton(140, 40+(25*10), this.width - 150, 20, hell, AlinLib.bariumConfig, "selector1", hell[0], Component.literal("Selector"));
        addRenderableWidget(flatSelectorStringButton);
        //
        flatSliderPercent = new FlatSliderPercent(140, 40+(25*11), width-150, 20, AlinLib.bariumConfig, "Slider_percent1", 0, SLIDER_PERCENT);
        addRenderableWidget(flatSliderPercent);
        flatSliderInt = new FlatSliderInteger(140, 40+(25*12), width-150, 20, AlinLib.bariumConfig, "Slider_int1", 30, 0, 1000, SLIDER_INTEGER);
        flatSliderInt.setTypeInteger(" wires");
        addRenderableWidget(flatSliderInt);
        //
        flatSecretStringEditBox = new FlatEditBoxSecretString(140, 40+(25*13), width-150, 20, SECRET_EDIT_BOX);
        flatSecretStringEditBox.setValue(AlinLib.bariumConfig.getString("HELLO_ALINA1", "Hello, Alina! How are you?"));
        flatSecretStringEditBox.setResponder((string) -> {
            AlinLib.bariumConfig.setString("HELLO_ALINA1", string);
        });
        // - - - - - - - - -
        vanillaTitleBox = new TextBox(140, 40+(25*7), this.width - 150, 20, Component.literal("Vanilla design"), true);
        vanillaTitleBox.setTooltip(Localization.toText("Hello, world!"));
        addRenderableWidget(vanillaTitleBox);

        vanillaBooleanButton = new VanillaButtonBoolean(140, 40+(25*8), this.width - 150, 20, AlinLib.bariumConfig, "Boolean11", true, Component.literal("Boolean"));
        addRenderableWidget(vanillaBooleanButton);
        vanillaButton = new VanillaButton(140, 40+(25*8), this.width - 150, 20, Component.literal("Boolean"), (OnPress) -> {
            this.minecraft.getToasts().addToast(new AlinaToast(Component.literal("AlinLib"), Component.literal("Well..."), false));
        });
        addRenderableWidget(vanillaButton);
        //
        vanillaSelectorStringButton = new VanillaSelectorStringButton(140, 40+(25*10), this.width - 150, 20, hell, AlinLib.bariumConfig, "selector11", hell[0], Component.literal("Selector"));
        addRenderableWidget(vanillaSelectorStringButton);
        //
        vanillaSliderPercent = new VanillaSliderPercent(140, 40+(25*11), width-150, 20, AlinLib.bariumConfig, "Slider_percent11", 0, SLIDER_PERCENT);
        addRenderableWidget(vanillaSliderPercent);
        vanillaSliderInt = new VanillaSliderInteger(140, 40+(25*12), width-150, 20, AlinLib.bariumConfig, "Slider_int11", 30, 0, 1000, SLIDER_INTEGER);
        vanillaSliderInt.setTypeInteger(" wires");
        addRenderableWidget(vanillaSliderInt);
        //
        something = addRenderableWidget(new TextBox(140, 875, this.width - 150, this.font.lineHeight, SOMETHING, true));
    }
    private void initButton(){
        // line 0
        addRenderableWidget(new Button(10, 40, 110, 20, Colors.KENNY, CATEGORY, (OnPress) -> {
            this.minecraft.setScreen(this);
        }));

        addRenderableWidget(new FlatButton(10, height - 55, 110, 20, GITHUB, (OnPress) -> {
            Util.getPlatform().openUri("https://github.com/simply-kel/AlinLib/");
        }));

        addRenderableWidget(new FlatColoredButton(10, height - 30, 110, 20, Colors.GROUPIE, EXIT, (OnPress) -> {
            AlinLib.bariumConfig.save();
            this.minecraft.setScreen(parent);
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
        int scrolled = (int)((double)this.scrolled + g * 10.0 * -1.0);
        int size = 900;
        if (scrolled <= 0 || size <= this.height) {
            this.scrolled = 0;
        } else this.scrolled = Math.min(scrolled, size - this.height);

        return super.mouseScrolled(d, e, f, g);
    }
}
