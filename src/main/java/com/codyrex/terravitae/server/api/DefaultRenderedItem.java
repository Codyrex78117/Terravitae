package com.codyrex.terravitae.server.api;

/**
 * Implementing this class automatically registers an item renderer for the implementing item / block.
 */
public interface DefaultRenderedItem {
    default String getResource(String unlocalizedName) {
        return unlocalizedName;
    }
}
