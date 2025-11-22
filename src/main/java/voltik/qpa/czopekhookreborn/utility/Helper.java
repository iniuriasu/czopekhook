package voltik.qpa.czopekhookreborn.utility;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Helper {

    public static void sendMessageClient(String message){
        MinecraftClient.getInstance().player.sendMessage(Text.literal("CzopekHook " + message), false);
    }
}
