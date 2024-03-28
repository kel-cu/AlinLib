package ru.kelcuprum.alinlib.api.clients.lifecycle;

import net.minecraft.client.Minecraft;
import ru.kelcuprum.alinlib.api.AbstractEvent;

import java.util.function.Consumer;

public class ClientStartTickEvent extends AbstractEvent {
    protected Consumer<Minecraft> responder;
    public ClientStartTickEvent(Consumer<Minecraft> responder) {
        super("Client tick start", "?");
        this.responder = responder;
    }

    @Override
    public void execute(Minecraft minecraft) {
        responder.accept(minecraft);
    }
}
