package ru.kelcuprum.alinlib.api.events.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import ru.kelcuprum.alinlib.api.events.Event;
import ru.kelcuprum.alinlib.api.events.EventFactory;

public final class ClientTickEvents {
    private ClientTickEvents() {
    }

    /**
     * Called at the start of the client tick.
     */
    public static final Event<StartTick> START_CLIENT_TICK = EventFactory.createArrayBacked(StartTick.class, callbacks -> client -> {
        for (StartTick event : callbacks) {
            event.onStartTick(client);
        }
    });

    /**
     * Called at the end of the client tick.
     */
    public static final Event<EndTick> END_CLIENT_TICK = EventFactory.createArrayBacked(EndTick.class, callbacks -> client -> {
        for (EndTick event : callbacks) {
            event.onEndTick(client);
        }
    });

    @FunctionalInterface
    public interface StartTick {
        void onStartTick(Minecraft client);
    }

    @FunctionalInterface
    public interface EndTick {
        void onEndTick(Minecraft client);
    }
}
