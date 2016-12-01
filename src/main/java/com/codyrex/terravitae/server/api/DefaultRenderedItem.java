package com.codyrex.terravitae.server.api;

public interface DefaultRenderedItem {
    default String getResource(String unlocalizedName) {
        return unlocalizedName;
    }
}
