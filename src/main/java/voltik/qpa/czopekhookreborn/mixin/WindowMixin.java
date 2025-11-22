package voltik.qpa.czopekhookreborn.mixin;

import net.minecraft.client.util.Window;
import net.minecraft.client.util.tracy.TracyFrameCapturer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import voltik.qpa.czopekhookreborn.skia.SkiaRenderer;

@Mixin(Window.class)
public class WindowMixin {
    @Inject(method = "onFramebufferSizeChanged", at = @At("RETURN"))
    private void onFramebufferSizeChanged(long window, int width, int height, CallbackInfo ci) {
        SkiaRenderer.init(width > 0 ? width : 1, height > 0 ? height : 1);
    }

    @Inject(method = "swapBuffers", at = @At("HEAD"))
    private void onSwapBuffers(TracyFrameCapturer capturer, CallbackInfo ci) {
        SkiaRenderer.render();
    }
}
