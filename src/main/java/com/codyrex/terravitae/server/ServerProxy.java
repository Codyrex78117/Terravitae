package com.codyrex.terravitae.server;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.entity.EntityHandler;
import com.codyrex.terravitae.server.item.ItemHandler;
import com.codyrex.terravitae.server.plant.PlantHandler;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy {
    public void onPreInit() {
        ItemHandler.register();
        BlockHandler.register();
        PlantHandler.register();
        EntityHandler.register();

        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
    }

    public void onInit() {

    }

    public void onPostInit() {

    }
}
