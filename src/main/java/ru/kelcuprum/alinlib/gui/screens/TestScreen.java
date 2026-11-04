package ru.kelcuprum.alinlib.gui.screens;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.builder.slider.SliderBuilder;

public class TestScreen extends Screen {
    public TestScreen() {
        super(Component.literal("test screen"));
    }

    @Override
    protected void init() {
        addRenderableWidget(new SliderBuilder(Component.literal("test")).setMin(0).setMax(100).setDefaultValue(0).build());
    }
}
