package voltik.qpa.czopekhookreborn.feature.module.modules.misc;

import voltik.qpa.czopekhookreborn.feature.module.Categories;
import voltik.qpa.czopekhookreborn.feature.module.Module;
import voltik.qpa.czopekhookreborn.feature.module.ModuleInfo;
import org.lwjgl.glfw.GLFW;

@ModuleInfo(category = Categories.MISC, name = "cwel", description = "cwel", keybind = GLFW.GLFW_KEY_X)
public class cwel extends Module {

    @Override
    public void onTick() {
        System.out.println("cwel");
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
