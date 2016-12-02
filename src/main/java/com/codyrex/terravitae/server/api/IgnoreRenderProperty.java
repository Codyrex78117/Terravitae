package com.codyrex.terravitae.server.api;

import net.minecraft.block.properties.IProperty;

/**
 * Implementing this ignores the given properties from the blockstate json file
 */
public interface IgnoreRenderProperty {
    IProperty<?>[] getIgnoredProperties();
}
