package voltik.qpa.czopekhookreborn.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Unique
    private static final Identifier PATH = Identifier.of("czopekhookreborn", "textures/gui/czopekhookreborn.png");
    @Unique
    private static final Identifier LOGO_PATH = Identifier.of("czopekhookreborn", "textures/gui/minecrafttitle.png");

    @Inject(method = "renderPanoramaBackground", at = @At("HEAD"), cancellable = true)
    private void injectCustomBackground(DrawContext context, float delta, CallbackInfo ci) {
        int w = context.getScaledWindowWidth();
        int h = context.getScaledWindowHeight();
        RenderSystem.enableBlend();
        context.drawTexture(RenderLayer::getGuiTextured, PATH, 0, 0, 0, 0, w, h, w, h);

        RenderSystem.disableBlend();

        ci.cancel();
    }

    @Shadow
    private SplashTextRenderer splashText;

    @Inject(method = "init", at = @At("TAIL"))
    private void changeSplashText(CallbackInfo ci) {
        this.splashText = new SplashTextRenderer("voltik qpa");
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LogoDrawer;draw(Lnet/minecraft/client/gui/DrawContext;IF)V"), cancellable = true)
    private void replaceLogo(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        float scale = context.getScaledWindowWidth() * 0.5f / 512f;
        float x = (context.getScaledWindowWidth() - 512f * scale) / 2f;

        context.getMatrices().push();
        context.getMatrices().translate(x, 30f, 0f);
        context.getMatrices().scale(scale, scale, scale);

        context.drawTexture(RenderLayer::getGuiTextured, LOGO_PATH, 0, 0, 0, 0, 512, 100, 512, 100);

        context.getMatrices().pop();
        ci.cancel();
    }
}




