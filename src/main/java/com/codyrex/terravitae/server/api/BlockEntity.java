package com.codyrex.terravitae.server.api;

import net.minecraft.tileentity.TileEntity;

public interface BlockEntity {
    Class<? extends TileEntity> getEntity();
}
