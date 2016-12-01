package com.codyrex.terravitae;

import com.codyrex.terravitae.server.ServerProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = Terravitae.MODID, name = "Terravitae", version = Terravitae.VERSION)
public class Terravitae {
    public static final String MODID = "terravitae";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(Terravitae.MODID)
    public static Terravitae INSTANCE;

    @SidedProxy(clientSide = "com.codyrex.terravitae.client.ClientProxy", serverSide = "com.codyrex.terravitae.server.ServerProxy")
    public static ServerProxy proxy;
}
