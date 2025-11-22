package voltik.qpa.czopekhookreborn.client;

import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;

import voltik.qpa.czopekhookreborn.feature.module.Module;
import voltik.qpa.czopekhookreborn.feature.module.ModuleManager;
import voltik.qpa.czopekhookreborn.listener.PacketListener;

import java.lang.invoke.MethodHandles;

public class CzopekhookrebornClient implements ClientModInitializer {

    public static IEventBus EVENT_BUS = new EventBus();
    private static ModuleManager moduleManager = new ModuleManager();

    private boolean[] keyPressed = new boolean[512];

    @Override
    public void onInitializeClient() {

        EVENT_BUS.registerLambdaFactory(
                "voltik.qpa.czopekhookreborn",
                (lookup, clazz) -> MethodHandles.lookup()
        );


        EVENT_BUS.subscribe(new PacketListener());




        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            long window = MinecraftClient.getInstance().getWindow().getHandle();
            if(MinecraftClient.getInstance().currentScreen == null && MinecraftClient.getInstance().player != null) {
                if (moduleManager != null) {
                    moduleManager.onTick();
                    handleKeybinds(window);
                }
            }

        });
    }

    private void handleKeybinds(long window) {
        for (Module m : moduleManager.getModules()) {
            int key = m.getKeybind();
            if (key == 0) continue;

            boolean pressed = InputUtil.isKeyPressed(window, key);
            if (pressed && !keyPressed[key]) m.toggle();

            keyPressed[key] = pressed;
        }
    }
}