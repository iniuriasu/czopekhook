package voltik.qpa.czopekhookreborn.feature.module;

import voltik.qpa.czopekhookreborn.client.CzopekhookrebornClient;
import voltik.qpa.czopekhookreborn.feature.module.modules.crashers.Szudlar1;
import voltik.qpa.czopekhookreborn.feature.module.modules.hud.Interface;
import voltik.qpa.czopekhookreborn.feature.module.modules.misc.BanAll;
import voltik.qpa.czopekhookreborn.feature.module.modules.crashers.ConsoleSpammer;
import voltik.qpa.czopekhookreborn.feature.module.modules.misc.Debugger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        CzopekhookrebornClient.EVENT_BUS.registerLambdaFactory(
                "voltik.qpa.czopekhookreborn.feature.module.modules",
                (lookup, clazz) -> MethodHandles.lookup()
        );

        register(new Szudlar1());
        register(new ConsoleSpammer());
        register(new BanAll());
        register(new Debugger());
        register(new Interface());
    }

    public void register(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void onTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }
}
