package ru.kelcuprum.alinlib.api.events.alinlib;

import ru.kelcuprum.alinlib.api.events.Event;
import ru.kelcuprum.alinlib.api.events.EventFactory;
import ru.kelcuprum.alinlib.config.parser.StarScript;

public final class LocalizationEvents {
    private LocalizationEvents() {
    }

    public static final Event<DefaultParserInit> DEFAULT_PARSER_INIT = EventFactory.createArrayBacked(DefaultParserInit.class, callbacks -> parser -> {
        for (DefaultParserInit event : callbacks) {
            event.onParserInit(parser);
        }
    });

    @FunctionalInterface
    public interface DefaultParserInit {
        void onParserInit(StarScript parser);
    }
}
