package voltik.qpa.czopekhookreborn.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import voltik.qpa.czopekhookreborn.skia.SkiaRenderer;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    @Final
    private Window window;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void onInitializeReturn(RunArgs args, CallbackInfo ci) {
        int[] width = new int[1],
                height = new int[1];
        GLFW.glfwGetFramebufferSize(this.window.getHandle(), width, height);
        SkiaRenderer.init(width[0] > 0 ? width[0] : 1, height[0] > 0 ? height[0] : 1);
    }
}
