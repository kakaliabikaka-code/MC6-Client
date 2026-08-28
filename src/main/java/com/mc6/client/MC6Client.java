package com.github.kakaliabikakacode.mc6client;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "mc6client", name = "MC6 Client", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class MC6Client {
    public static final String MODID = "mc6client";
    public static final String NAME = "MC6 Client";
    public static final String VERSION = "1.0";

    public static MC6Client INSTANCE;
    private ModuleManager moduleManager;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        INSTANCE = this;
        this.moduleManager = new ModuleManager();
        
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        MinecraftForge.EVENT_BUS.register(new HUDManager());
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
