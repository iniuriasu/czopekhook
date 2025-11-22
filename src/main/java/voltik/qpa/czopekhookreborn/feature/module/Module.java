package voltik.qpa.czopekhookreborn.feature.module;

import lombok.Getter;
import lombok.Setter;
import voltik.qpa.czopekhookreborn.client.CzopekhookrebornClient;

public abstract class Module {


    @Getter
    private final Categories category;

    @Getter
    private final String name;

    @Getter
    private final String description;


    @Getter
    private boolean enabled;

    @Getter
    private final int keybind;




    protected Module() {
        ModuleInfo info =this.getClass().getAnnotation(ModuleInfo.class);
        this.category = info.category();
        this.name = info.name();
        this.description = info.description();
        this.enabled = false;
        this.keybind = info.keybind();
    }

    public void toggle() {
    setEnabled(!enabled);
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
            CzopekhookrebornClient.EVENT_BUS.subscribe(this);
        }
        else {
            CzopekhookrebornClient.EVENT_BUS.unsubscribe(this);
            onDisable();
        }
    }

    public void disable() {
        setEnabled(false);
    }

    public void onTick(){}


    protected abstract void onEnable();
    protected abstract void onDisable();


}
