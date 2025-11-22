package voltik.qpa.czopekhookreborn.skia;

import com.mojang.blaze3d.platform.GlConst;
import io.github.humbleui.skija.*;
import io.github.humbleui.skija.impl.Native;
import io.github.humbleui.skija.impl.ReferenceUtil;
import io.github.humbleui.skija.impl.Stats;
import io.github.humbleui.types.Rect;
import lombok.experimental.UtilityClass;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class SkiaUtil {
    public final String assets = "assets/czopekhookreborn/fonts/";
    private final Map<String, Font> fonts = new HashMap<>();
    private final Map<String, Typeface> typefaces = new HashMap<>();
    private final Map<Integer, Image> textures = new ConcurrentHashMap<>();

    public void drawRect(final Canvas canvas, final Rect rect, final Paint paint) {
        canvas.save();
        canvas.clipRect(rect, ClipMode.INTERSECT);
        canvas.drawRect(rect, paint);
        canvas.restore();
    }

    public Image getImage(DirectContext context, int textureId, int width, int height, boolean hasAlpha) {
        return getImage(context, textureId, width, height, hasAlpha, SurfaceOrigin.BOTTOM_LEFT);
    }

    public Image getImage(DirectContext context, int textureId, int width, int height, boolean hasAlpha, SurfaceOrigin origin) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Width and height must be positive");

        GL11.glBindTexture(GlConst.GL_TEXTURE_2D, textureId);

        Image image = textures.computeIfAbsent(textureId, id -> createImage(context, textureId, width, height, origin, hasAlpha));

        if (image.getWidth() != width || image.getHeight() != height) {
            image = createImage(context, textureId, width, height, origin, hasAlpha);
            textures.put(textureId, image);
        }

        return image;
    }

    private Image createImage(DirectContext context, int textureId, int width, int height, SurfaceOrigin origin, boolean hasAlpha) {
        return Image.adoptGLTextureFrom(context, textureId, GL11.GL_TEXTURE_2D, width, height, GL11.GL_RGBA8, origin, hasAlpha ? ColorType.RGBA_8888 : ColorType.RGB_888X);
    }

    public Font getFont(final String path) {
        return get(path, 10);
    }

    @SuppressWarnings("all")
    public Font get(final String path, final float size) {
        final String key = path + ":" + size;

        return fonts.computeIfAbsent(key, k -> {
            final Typeface typeface = loadTypeface(path);
            final Font font = new Font(typeface, size);

            font.setSubpixel(false);
            font.setHinting(FontHinting.NORMAL);
            font.setEdging(FontEdging.ANTI_ALIAS);

            return font;
        });
    }

    private Typeface loadTypeface(String path) {
        return typefaces.computeIfAbsent(path, k -> {
            try (InputStream stream = SkiaUtil.class.getClassLoader().getResourceAsStream(assets + path)) {
                if (stream == null) {
                    throw new IllegalArgumentException("Font not found: " + assets + path);
                }
                byte[] data = stream.readAllBytes();

                FontMgr fontMgr = FontMgr.getDefault();
                Typeface typeface = fontMgr.makeFromData(Data.makeFromBytes(data), 0);

                if (typeface == null) {
                    throw new IllegalArgumentException("Failed to create Typeface from: " + path);
                }
                return typeface;
            } catch (IOException e) {
                throw new RuntimeException("Failed to load font: " + path, e);
            }
        });
    }

    public Typeface makeFromData(Data data, int index) {
        try {
            Stats.onNativeCall();
            long ptr = Typeface._nMakeFromData(Native.getPtr(data), index);
            if (ptr == 0)
                throw new IllegalArgumentException("Failed to create Typeface from data " + data);
            return new Typeface(ptr);
        } finally {
            ReferenceUtil.reachabilityFence(data);
        }
    }

    public int colorFromAwt(java.awt.Color col) {
        return Color.makeRGB(col.getRed(), col.getGreen(), col.getBlue());
    }

    public void drawDoubleString(Canvas canvas, String first, String last,
                                float x, float y, float space, Font font,
                                Paint firstpaint, Paint lastpaint) {
        canvas.drawString(first, x, y, font, firstpaint);
        float valueX = x + space + font.measureText(first).getWidth();
        canvas.drawString(last, valueX, y, font, lastpaint);
    }
}
