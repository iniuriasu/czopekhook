package voltik.qpa.czopekhookreborn.feature.module.modules.misc;

import net.minecraft.client.MinecraftClient;
import voltik.qpa.czopekhookreborn.feature.module.Categories;
import voltik.qpa.czopekhookreborn.feature.module.Module;
import voltik.qpa.czopekhookreborn.feature.module.ModuleInfo;
import org.lwjgl.glfw.GLFW;

@ModuleInfo(category = Categories.MISC, name = "BanAll", description = "BanAll", keybind = GLFW.GLFW_KEY_P)
public class BanAll extends Module {


    @Override
    public void onTick() {
    }

    @Override
    protected void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();



    mc.getNetworkHandler().getPlayerList().forEach(player -> {
        String username = String.valueOf(player.getProfile().getName());

        System.out.println(username);
    });
    this.disable();

//pozneij to workne ok

    }

    @Override
    protected void onDisable() {

    }
}
