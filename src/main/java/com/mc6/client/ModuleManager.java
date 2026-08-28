package com.mc6.client;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        modules.add(new FPSBoost());
        modules.add(new CPSCounter());
        modules.add(new Keystrokes());
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
}

class Module {
    private String name;
    private boolean toggled;

    public Module(String name) {
        this.name = name;
        this.toggled = false;
    }

    public String getName() {
        return name;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void toggle() {
        this.toggled = !this.toggled;
    }
}

class FPSBoost extends Module {
    public FPSBoost() {
        super("FPS Boost");
    }
}

class CPSCounter extends Module {
    public CPSCounter() {
        super("CPS Counter");
        setToggled(true);
    }
}

class Keystrokes extends Module {
    public Keystrokes() {
        super("Keystrokes");
        setToggled(true);
    }
}
