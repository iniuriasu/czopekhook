package voltik.qpa.czopekhookreborn.client;

import io.github.humbleui.skija.*;
import io.github.humbleui.types.RRect;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import voltik.qpa.czopekhookreborn.events.SkiaEvent;
import voltik.qpa.czopekhookreborn.feature.module.Module;
import voltik.qpa.czopekhookreborn.feature.module.ModuleManager;
import voltik.qpa.czopekhookreborn.listener.PacketListener;
import voltik.qpa.czopekhookreborn.skia.SkiaFonts;
import voltik.qpa.czopekhookreborn.skia.SkiaUtil;

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
        EVENT_BUS.subscribe(this);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            long window = MinecraftClient.getInstance().getWindow().getHandle();
            if (MinecraftClient.getInstance().currentScreen == null && MinecraftClient.getInstance().player != null) {
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


    @EventHandler
    public void nigganigga(SkiaEvent event) {
        final Image image = SkiaUtil.getImage(
                event.getContext(),
                MinecraftClient.getInstance().getFramebuffer().getColorAttachment(),
                MinecraftClient.getInstance().getFramebuffer().textureWidth,
                MinecraftClient.getInstance().getFramebuffer().textureHeight,
                false
        );

        String title = "CzopekHook RECODE";
        String username = "Username: " + MinecraftClient.getInstance().player.getName().getString();
        String server = "Server: " + MinecraftClient.getInstance().getNetworkHandler().getConnection().getAddress().toString();
        String engine = "Engine: " + MinecraftClient.getInstance().getNetworkHandler().getBrand();

        float max = Math.max(
                Math.max(SkiaFonts.PRODUCT_SANS.measureText(title).getWidth(), SkiaFonts.PRODUCT_SANS.measureText(username).getWidth()),
                Math.max(SkiaFonts.PRODUCT_SANS.measureText(server).getWidth(), SkiaFonts.PRODUCT_SANS.measureText(engine).getWidth())
        );

        final RRect rect = RRect.makeXYWH(15, 15, max + 20, 110, 7.9f * 2f, 7.9f * 2f);
        final Paint background = new Paint()
                .setAntiAlias(true)
                .setColor(Color.makeRGB(0, 0, 0))
                .setAlpha(120);

        final Paint blur = new Paint()
                .setAntiAlias(true)
                .setImageFilter(ImageFilter.makeBlur(10f, 10f, FilterTileMode.REPEAT));

        final Paint glow = new Paint()
                .setAntiAlias(true)
                .setColor(Color.makeRGB(0, 0, 0))
                .setAlpha(140)
                .setImageFilter(ImageFilter.makeBlur(15f, 15f, FilterTileMode.DECAL));

        event.getSurface().getCanvas().save();
        event.getSurface().getCanvas().clipRRect(rect, ClipMode.DIFFERENCE);
        event.getSurface().getCanvas().drawRRect(rect, glow);
        event.getSurface().getCanvas().restore();

        event.getSurface().getCanvas().save();
        event.getSurface().getCanvas().clipRRect(rect, ClipMode.INTERSECT);
        event.getSurface().getCanvas().drawImage(image, 0f, 0f, blur);
        event.getSurface().getCanvas().drawRRect(rect, background);
        event.getSurface().getCanvas().restore();

        Shader g = Shader.makeLinearGradient(
                25, 45,
                200, 45,
                new int[]{
                        SkiaUtil.colorFromAwt(new java.awt.Color(115, 218, 236)),
                        SkiaUtil.colorFromAwt(new java.awt.Color(122, 0, 255))
                }
        );

        Shader g2 = Shader.makeLinearGradient(
                25, 45,
                200, 45,
                new int[]{
                        SkiaUtil.colorFromAwt(new java.awt.Color(79, 79, 79)),
                        SkiaUtil.colorFromAwt(new java.awt.Color(220, 220, 220))
                }
        );

        Paint paint = new Paint().setShader(g);
        Paint paintg = new Paint().setShader(g2);

        event.getSurface().getCanvas().drawString(
                "CzopekHook RECODE",
                25, 45,
                SkiaFonts.PRODUCT_SANS,
                paint
        );

        SkiaUtil.drawDoubleString(
                event.getSurface().getCanvas(),
                "Username: ",
                MinecraftClient.getInstance().player.getName().getString(),
                25, 45 + 20, 6.0f,
                SkiaFonts.PRODUCT_SANS,
                new Paint().setColor(Color.makeRGB(255, 255, 255)),
                paintg
        );

        SkiaUtil.drawDoubleString(
                event.getSurface().getCanvas(),
                "Server: ",
                MinecraftClient.getInstance().getNetworkHandler().getConnection().getAddress().toString(),
                25, 45 + 20 + 20, 6.0f,
                SkiaFonts.PRODUCT_SANS,
                new Paint().setColor(Color.makeRGB(255, 255, 255)),
                paintg
        );

        SkiaUtil.drawDoubleString(
                event.getSurface().getCanvas(),
                "Engine: ",
                MinecraftClient.getInstance().getNetworkHandler().getBrand(),
                25, 45 + 20 + 20 + 20, 6.0f,
                SkiaFonts.PRODUCT_SANS,
                new Paint().setColor(Color.makeRGB(255, 255, 255)),
                paintg
        );
    }
}