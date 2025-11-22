package voltik.qpa.czopekhookreborn.mixin;



import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;


import net.minecraft.network.packet.UnknownCustomPayload;
import net.minecraft.network.packet.s2c.common.CustomPayloadS2CPacket;
import net.minecraft.util.Identifier;
import voltik.qpa.czopekhookreborn.client.CzopekhookrebornClient;
import voltik.qpa.czopekhookreborn.events.PacketEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import voltik.qpa.czopekhookreborn.events.PluginMessageEvent;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {


    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void onHandlePacketPre(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        PacketEvent.Receive event = new PacketEvent.Receive(packet);
        CzopekhookrebornClient.EVENT_BUS.post(event);
        if (event.isCancelled()) ci.cancel();
    }

    @Inject(method = "handlePacket", at = @At("TAIL"), cancellable = true)
    private static <T extends PacketListener> void onHandlePacketPost(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        PacketEvent.ReceivePost event = new PacketEvent.ReceivePost(packet);
        CzopekhookrebornClient.EVENT_BUS.post(event);

        if (event.isCancelled()) ci.cancel();
    }


    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void onSendPacketPre(Packet<?> packet, CallbackInfo ci) {



        PacketEvent.Send event = new PacketEvent.Send(packet);
        CzopekhookrebornClient.EVENT_BUS.post(event);

        if (event.isCancelled()) ci.cancel();
    }

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("RETURN"), cancellable = true)
    private void onSendPacketPost(Packet<?> packet, CallbackInfo ci) {

        PacketEvent.SendPost event = new PacketEvent.SendPost(packet);
        CzopekhookrebornClient.EVENT_BUS.post(event);

        if (event.isCancelled()) ci.cancel();
    }
}
