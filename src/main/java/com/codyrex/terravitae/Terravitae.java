package com.codyrex.terravitae;

import com.codyrex.terravitae.server.ServerProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Terravitae.MODID, name = "Terravitae", version = Terravitae.VERSION)
public class Terravitae {
    public static final String MODID = "terravitae";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(Terravitae.MODID)
    public static Terravitae INSTANCE;

    @SidedProxy(clientSide = "com.codyrex.terravitae.client.ClientProxy", serverSide = "com.codyrex.terravitae.server.ServerProxy")
    public static ServerProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        proxy.onPreInit();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit();
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostInit();
    }
}
