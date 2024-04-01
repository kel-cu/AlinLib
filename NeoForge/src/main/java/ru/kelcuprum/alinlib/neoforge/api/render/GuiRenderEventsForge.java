package ru.kelcuprum.alinlib.neoforge.api.render;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import ru.kelcuprum.alinlib.api.events.client.GuiRenderEvents;

public class GuiRenderEventsForge {
    public static void onPostRenderGui(RenderGuiEvent.Post event) {
        GuiRenderEvents.RENDER.invoker().onRender(event.getGuiGraphics(), event.getPartialTick());
    }
}
