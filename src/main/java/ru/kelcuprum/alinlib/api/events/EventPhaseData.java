package ru.kelcuprum.alinlib.api.events;


import java.lang.reflect.Array;
import java.util.Arrays;
import net.minecraft.resources.
        //#if MC < 12111
        //$$ResourceLocation
        //#else
        Identifier
        //#endif
        ;

/**
 * Data of an {@link ArrayBackedEvent} phase.
 */
class EventPhaseData<T> extends SortableNode<EventPhaseData<T>> {
    final
    //#if MC < 12111
    //$$ResourceLocation
    //#else
    Identifier
            //#endif
            id;
    T[] listeners;

    @SuppressWarnings("unchecked")
    EventPhaseData(
            //#if MC < 12111
            //$$ResourceLocation
            //#else
            Identifier
                    //#endif
                    id, Class<?> listenerClass) {
        this.id = id;
        this.listeners = (T[]) Array.newInstance(listenerClass, 0);
    }

    void addListener(T listener) {
        int oldLength = listeners.length;
        listeners = Arrays.copyOf(listeners, oldLength + 1);
        listeners[oldLength] = listener;
    }

    @Override
    protected String getDescription() {
        return id.toString();
    }
}