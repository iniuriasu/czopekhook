package voltik.qpa.czopekhookreborn.events;

import lombok.Getter;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class PluginMessageEvent extends Event {
    @Getter private final Identifier id;
    @Getter private final CustomPayload payload;
    @Getter private final PacketByteBuf buf;

    public PluginMessageEvent(Identifier id, CustomPayload payload, PacketByteBuf buf) {
        this.id = id;
        this.payload = payload;
        this.buf = buf;
    }
}



