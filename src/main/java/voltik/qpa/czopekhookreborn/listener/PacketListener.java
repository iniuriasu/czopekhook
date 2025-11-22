package voltik.qpa.czopekhookreborn.listener;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.s2c.common.ResourcePackSendS2CPacket;
import voltik.qpa.czopekhookreborn.events.PacketEvent;

public class PacketListener {
    @EventHandler
    public void onPacketReceive(PacketEvent.Receive event) {


        if(event.getPacket() instanceof ResourcePackSendS2CPacket packet) {
            System.out.println("Server resource pack: " + packet.url());
        }


    }
    @EventHandler
    public void onPluginMessage(PacketEvent.Receive event) {
        //bedzie work pozniej ok
    }






}
