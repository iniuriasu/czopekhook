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
    private static final Identifier PATH =
            Identifier.of("czopekhookreborn", "textures/gui/czopekhookreborn.png");
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
    }




