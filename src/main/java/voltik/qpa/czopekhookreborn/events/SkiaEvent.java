package voltik.qpa.czopekhookreborn.events;

import io.github.humbleui.skija.BackendRenderTarget;
import io.github.humbleui.skija.DirectContext;
import io.github.humbleui.skija.Surface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SkiaEvent {
    private Surface surface;
    private DirectContext context = null;
    private BackendRenderTarget renderTarget;
}
