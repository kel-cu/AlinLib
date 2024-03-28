package ru.kelcuprum.alinlib.api.clients.lifecycle;

import net.minecraft.client.Minecraft;
import ru.kelcuprum.alinlib.api.AbstractEvent;

import java.util.function.Consumer;

public class ClientStopEvent extends AbstractEvent {
    protected Consumer<Minecraft> responder;
    public ClientStopEvent(Consumer<Minecraft> responder) {
        super("Client stop", "Ивент срабатывает тогда, когда майнкрафт завершает работу");
        this.responder = responder;
    }

    @Override
    public void execute(Minecraft minecraft) {
        responder.accept(minecraft);
    }
}
