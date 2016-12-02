package com.codyrex.terravitae.server.api;

import net.minecraft.tileentity.TileEntity;

/**
 * Implementing this automatically registers a TileEntity for this block
 */
public interface BlockEntity {
    Class<? extends TileEntity> getEntity();
}
