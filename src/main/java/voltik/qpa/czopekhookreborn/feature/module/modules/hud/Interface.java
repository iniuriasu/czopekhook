package voltik.qpa.czopekhookreborn.feature.module.modules.hud;

import io.github.humbleui.skija.*;
import io.github.humbleui.types.RRect;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import voltik.qpa.czopekhookreborn.events.SkiaEvent;
import voltik.qpa.czopekhookreborn.feature.module.Categories;
import voltik.qpa.czopekhookreborn.feature.module.Module;
import voltik.qpa.czopekhookreborn.feature.module.ModuleInfo;
import voltik.qpa.czopekhookreborn.skia.SkiaFonts;
import voltik.qpa.czopekhookreborn.skia.SkiaUtil;

@ModuleInfo(category = Categories.MISC, name = "Interface", description = "otworzylem biznes taksowkarski")
public class Interface extends Module {
    {
        toggle();
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

        final RRect rect = RRect.makeXYWH(15, 15, max + 30, 110, 7.9f * 2f, 7.9f * 2f);
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
                        SkiaUtil.colorFromAwt(new java.awt.Color(110, 163, 255)),
                        SkiaUtil.colorFromAwt(new java.awt.Color(44, 78, 133))
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

        SkiaUtil.drawGlowingString(
                event.getSurface().getCanvas(),
                title,
                28, 45,
                SkiaFonts.PRODUCT_SANS,
                paint,
                10.0f,
                110
        );

        SkiaUtil.drawDoubleString(
                event.getSurface().getCanvas(),
                "Username: ",
                MinecraftClient.getInstance().player.getName().getString(),
                28, 45 + 20, 6.0f,
                SkiaFonts.PRODUCT_SANS,
                new Paint().setColor(Color.makeRGB(255, 255, 255)),
                paintg
        );

        SkiaUtil.drawDoubleString(
                event.getSurface().getCanvas(),
                "Server: ",
                MinecraftClient.getInstance().getNetworkHandler().getConnection().getAddress().toString(),
                28, 45 + 20 + 20, 6.0f,
                SkiaFonts.PRODUCT_SANS,
                new Paint().setColor(Color.makeRGB(255, 255, 255)),
                paintg
        );

        SkiaUtil.drawDoubleString(
                event.getSurface().getCanvas(),
                "Engine: ",
                MinecraftClient.getInstance().getNetworkHandler().getBrand(),
                28, 45 + 20 + 20 + 20, 6.0f,
                SkiaFonts.PRODUCT_SANS,
                new Paint().setColor(Color.makeRGB(255, 255, 255)),
                paintg
        );
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }
}