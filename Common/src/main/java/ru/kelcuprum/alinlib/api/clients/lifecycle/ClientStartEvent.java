package ru.kelcuprum.alinlib.api.clients.lifecycle;

import net.minecraft.client.Minecraft;
import ru.kelcuprum.alinlib.api.AbstractEvent;

import java.util.function.Consumer;

public class ClientStartEvent extends AbstractEvent {
    protected Consumer<Minecraft> responder;
    public ClientStartEvent(Consumer<Minecraft> responder) {
        super("Client start", "Ивент срабатывает тогда, когда майнкрафт начинает работу");
        this.responder = responder;
    }

    @Override
    public void execute(Minecraft minecraft) {
        responder.accept(minecraft);
    }
}
