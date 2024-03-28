package ru.kelcuprum.alinlib.api;

import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;

import java.util.ArrayList;
import java.util.List;

public class EventRegisters {
    public static List<AbstractEvent> events = new ArrayList<>();
    public static void registerEvent(AbstractEvent event){
        if(event != null){
            events.add(event);
            AlinLib.log(String.format("Event \"%1$s\" registered, description: \"%2$s\"", event.name, event.description), Level.DEBUG);
        } else throw new RuntimeException("Event is null");
    }
}
