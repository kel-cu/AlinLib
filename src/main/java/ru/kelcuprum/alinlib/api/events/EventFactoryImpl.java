package ru.kelcuprum.alinlib.api.events;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.MapMaker;

import net.minecraft.resources.ResourceLocation;

public final class EventFactoryImpl {
    private static final Set<ArrayBackedEvent<?>> ARRAY_BACKED_EVENTS
            = Collections.newSetFromMap(new MapMaker().weakKeys().makeMap());

    private EventFactoryImpl() { }

    public static void invalidate() {
        ARRAY_BACKED_EVENTS.forEach(ArrayBackedEvent::update);
    }

    public static <T> Event<T> createArrayBacked(Class<? super T> type, Function<T[], T> invokerFactory) {
        ArrayBackedEvent<T> event = new ArrayBackedEvent<>(type, invokerFactory);
        ARRAY_BACKED_EVENTS.add(event);
        return event;
    }

    public static void ensureContainsDefault(ResourceLocation[] defaultPhases) {
        for (ResourceLocation id : defaultPhases) {
            if (id.equals(Event.DEFAULT_PHASE)) {
                return;
            }
        }

        throw new IllegalArgumentException("The event phases must contain Event.DEFAULT_PHASE.");
    }

    public static void ensureNoDuplicates(ResourceLocation[] defaultPhases) {
        for (int i = 0; i < defaultPhases.length; ++i) {
            for (int j = i+1; j < defaultPhases.length; ++j) {
                if (defaultPhases[i].equals(defaultPhases[j])) {
                    throw new IllegalArgumentException("Duplicate event phase: " + defaultPhases[i]);
                }
            }
        }
    }
}