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

    /**
     * Called at the start of a ClientWorld's tick.
     */
    public static final Event<StartLevelTick> START_LEVEL_TICK = EventFactory.createArrayBacked(StartLevelTick.class, callbacks -> world -> {
        for (StartLevelTick callback : callbacks) {
            callback.onStartTick(world);
        }
    });

    /**
     * Called at the end of a ClientWorld's tick.
     *
     * <p>End of world tick may be used to start async computations for the next tick.
     */
    public static final Event<EndLevelTick> END_LEVEL_TICK = EventFactory.createArrayBacked(EndLevelTick.class, callbacks -> world -> {
        for (EndLevelTick callback : callbacks) {
            callback.onEndTick(world);
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

    @FunctionalInterface
    public interface StartLevelTick {
        void onStartTick(ClientLevel world);
    }

    @FunctionalInterface
    public interface EndLevelTick {
        void onEndTick(ClientLevel world);
    }
}
