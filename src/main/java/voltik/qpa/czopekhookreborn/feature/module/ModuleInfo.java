package voltik.qpa.czopekhookreborn.feature.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    Categories category();
    String name() default "unkown";
    String description() default "";
    int keybind() default 0;
}
