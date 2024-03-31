package ru.kelcuprum.alinlib.api.events.client;

import net.minecraft.client.gui.GuiGraphics;
import ru.kelcuprum.alinlib.api.events.Event;
import ru.kelcuprum.alinlib.api.events.EventFactory;

public interface  GuiRenderEvents {

    /**
     * Called at the start of the client tick.
     */
    Event<GuiRenderEvents> RENDER = EventFactory.createArrayBacked(GuiRenderEvents.class, callbacks -> (guiGraphics, partialTick) -> {
        for (GuiRenderEvents event : callbacks) {
            event.onRender(guiGraphics, partialTick);
        }
    });
    void onRender(GuiGraphics guiGraphics, float partialTick);
}
