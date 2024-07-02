package ru.kelcuprum.alinlib.api.events.client;

import net.minecraft.client.renderer.texture.TextureManager;
import ru.kelcuprum.alinlib.api.events.Event;
import ru.kelcuprum.alinlib.api.events.EventFactory;

public interface TextureManagerEvent {

    /**
     * Called at the start of the client tick.
     */
    Event<TextureManagerEvent> INIT = EventFactory.createArrayBacked(TextureManagerEvent.class, callbacks -> (textureManager) -> {
        for (TextureManagerEvent event : callbacks) {
            event.onInit(textureManager);
        }
    });
    void onInit(TextureManager textureManager);
}
