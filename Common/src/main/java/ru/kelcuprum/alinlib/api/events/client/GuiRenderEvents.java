package ru.kelcuprum.alinlib.api.events.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import ru.kelcuprum.alinlib.api.events.Event;
import ru.kelcuprum.alinlib.api.events.EventFactory;

public interface  GuiRenderEvents {

    /**
     * Called at the start of the client tick.
     */
    Event<GuiRenderEvents> RENDER = EventFactory.createArrayBacked(GuiRenderEvents.class, callbacks -> (guiGraphics, deltaTracker) -> {
        for (GuiRenderEvents event : callbacks) {
            event.onRender(guiGraphics, deltaTracker);
        }
    });
    void onRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker);
}
