package ru.kelcuprum.alinlib.api.events.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import ru.kelcuprum.alinlib.api.events.Event;
import ru.kelcuprum.alinlib.api.events.EventFactory;

public final class ScreenEvents {

    /**
     * Called at the start of the client tick.
     */
    public static final Event<ScreenRender> SCREEN_RENDER = EventFactory.createArrayBacked(ScreenRender.class, callbacks -> (screen, guiGraphics, mouseX, mouseY, partialTick) -> {
        for (ScreenRender event : callbacks) {
            event.onScreenRender(screen, guiGraphics, mouseX, mouseY, partialTick);
        }
    });
    public static final Event<KeyPress> KEY_PRESS = EventFactory.createArrayBacked(KeyPress.class, callbacks -> (screen, keyCode, scanCode, modifiers) -> {
        for (KeyPress event : callbacks) {
            event.onKeyPressed(screen, keyCode, scanCode, modifiers);
        }
    });
    @FunctionalInterface
    public interface ScreenRender {
        void onScreenRender(Screen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);
    }

    @FunctionalInterface
    public interface KeyPress {
        void onKeyPressed(Screen screen, int keyCode, int scanCode, int modifiers);
    }
}
