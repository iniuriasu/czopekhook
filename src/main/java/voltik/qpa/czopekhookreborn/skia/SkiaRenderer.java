package voltik.qpa.czopekhookreborn.skia;

import io.github.humbleui.skija.*;
import io.github.humbleui.types.Rect;
import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.opengl.GL11;
import voltik.qpa.czopekhookreborn.client.CzopekhookrebornClient;
import voltik.qpa.czopekhookreborn.events.SkiaEvent;
import voltik.qpa.czopekhookreborn.skia.state.UIState;

import java.util.Objects;

@UtilityClass
public class SkiaRenderer {
    private DirectContext context = null;
    private BackendRenderTarget renderTarget;
    private Surface surface;

    private void createSurface(final int width, final int height) {
        if (surface != null) {
            surface.close();
        }

        if (renderTarget != null) {
            renderTarget.close();
        }

        renderTarget = BackendRenderTarget.makeGL(
                width, height,
                0, 8, 0,
                FramebufferFormat.GR_GL_RGBA8
        );

        surface = Surface.wrapBackendRenderTarget(
                Objects.requireNonNull(context),
                Objects.requireNonNull(renderTarget),
                SurfaceOrigin.BOTTOM_LEFT,
                SurfaceColorFormat.RGBA_8888,
                ColorSpace.getSRGB()
        );
    }

    public void render() {
        if (MinecraftClient.getInstance().player == null) {
            return;
        }

        if (context == null || surface == null) {
            return;
        }

        UIState.backup();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        context.resetGL(GLBackendState.BLEND,
                GLBackendState.VERTEX,
                GLBackendState.PIXEL_STORE,
                GLBackendState.TEXTURE_BINDING,
                GLBackendState.MISC
        );

        CzopekhookrebornClient.EVENT_BUS.post(new SkiaEvent(surface, context, renderTarget));

        context.flushAndSubmit(true);

        UIState.restore();
    }

    public void init(int width, int height) {
        if (context == null) {
            context = DirectContext.makeGL();
        }
        createSurface(width, height);
    }

    public void applyAMDCleanupHack() {
        if (surface != null) {
            surface.close();
            surface = null;
        }
        if (renderTarget != null) {
            renderTarget.close();
            renderTarget = null;
        }
        if (context != null) {
            context.close();
            context = null;
        }
    }
}
