package ru.kelcuprum.alinlib.api.clients.lifecycle;

import net.minecraft.client.Minecraft;
import ru.kelcuprum.alinlib.api.AbstractEvent;

import java.util.function.Consumer;

public class ClientStopTickEvent extends AbstractEvent {
    protected Consumer<Minecraft> responder;
    public ClientStopTickEvent(Consumer<Minecraft> responder) {
        super("Client tick stop", "?");
        this.responder = responder;
    }

    @Override
    public void execute(Minecraft minecraft) {
        responder.accept(minecraft);
    }
}
