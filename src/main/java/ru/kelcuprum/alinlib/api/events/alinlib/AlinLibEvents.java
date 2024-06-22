package ru.kelcuprum.alinlib.api.events.alinlib;

import ru.kelcuprum.alinlib.api.events.Event;
import ru.kelcuprum.alinlib.api.events.EventFactory;

public final class AlinLibEvents {
    private AlinLibEvents() {
    }

    public static final Event<Init> INIT = EventFactory.createArrayBacked(Init.class, callbacks -> () ->  {
        for (Init event : callbacks) {
            event.onInit();
        }
    });

    @FunctionalInterface
    public interface Init {
        void onInit();
    }
}
