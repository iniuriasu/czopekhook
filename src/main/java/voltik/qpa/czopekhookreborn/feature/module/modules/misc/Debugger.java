package voltik.qpa.czopekhookreborn.feature.module.modules.misc;

import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.common.CommonPingS2CPacket;
import net.minecraft.text.Text;
import voltik.qpa.czopekhookreborn.events.PacketEvent;
import voltik.qpa.czopekhookreborn.feature.module.Categories;
import voltik.qpa.czopekhookreborn.feature.module.Module;
import voltik.qpa.czopekhookreborn.feature.module.ModuleInfo;
import org.lwjgl.glfw.GLFW;
import voltik.qpa.czopekhookreborn.utility.Helper;

import java.util.ArrayList;
import java.util.List;

@ModuleInfo(category = Categories.MISC, name = "Debugger", description = "Debug anticheat transactions", keybind = GLFW.GLFW_KEY_I)
public class Debugger extends Module {

    private static List<Integer> cwel = new ArrayList<>();
    private static boolean isEnabled = false;

    @Override
    public void onTick() {
        if (MinecraftClient.getInstance().player != null) {
            if (cwel.isEmpty()) {
                return;
            }
            String lastParameter = String.valueOf(cwel.get(cwel.size() - 1));
            Helper.sendMessageClient(lastParameter);
        }
    }


    @Override
    protected void onEnable() {
        isEnabled = true;
        //eventhandlerqpaok


    }

    @Override
    protected void onDisable() {
        isEnabled = false;

    }

    @EventHandler
    public void onPacketReceived(PacketEvent.Receive event) {
        if(isEnabled) {
            if (event.getPacket() instanceof CommonPingS2CPacket packet) {
                cwel.add(packet.getParameter());
            }
        }

    }


}
