package voltik.qpa.czopekhookreborn.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class Watermark {
    public static void render(DrawContext context) {
        context.drawText(MinecraftClient.getInstance().textRenderer, "CzopekHook RECODE", 10, 10, Color.WHITE.getRGB(), false);
        assert MinecraftClient.getInstance().player != null;
        context.drawText(MinecraftClient.getInstance().textRenderer, "Username " + MinecraftClient.getInstance().player.getName(), 10, 20, Color.WHITE.getRGB(), false);
        context.drawText(MinecraftClient.getInstance().textRenderer, "Server " + MinecraftClient.getInstance().getNetworkHandler().getConnection().getAddress(), 10, 30, Color.WHITE.getRGB(), false);
        context.drawText(MinecraftClient.getInstance().textRenderer, "Engine " + MinecraftClient.getInstance().getNetworkHandler().getBrand(), 10, 40, Color.WHITE.getRGB(), false);
    }
}
