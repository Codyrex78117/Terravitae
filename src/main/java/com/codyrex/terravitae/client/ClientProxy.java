package com.codyrex.terravitae.client;

import com.codyrex.terravitae.client.render.RenderingHandler;
import com.codyrex.terravitae.server.ServerProxy;
import net.minecraft.client.Minecraft;

public class ClientProxy extends ServerProxy {
    public static final Minecraft MINECRAFT = Minecraft.getMinecraft();
    public static final RenderingHandler RENDER_HANDLER = new RenderingHandler();

    @Override
    public void onPreInit() {
        super.onPreInit();
        RENDER_HANDLER.onPreInit();
    }

    @Override
    public void onInit() {
        super.onInit();
        RENDER_HANDLER.onInit();
    }

    @Override
    public void onPostInit() {
        super.onPostInit();
        RENDER_HANDLER.onPostInit();
    }
}
