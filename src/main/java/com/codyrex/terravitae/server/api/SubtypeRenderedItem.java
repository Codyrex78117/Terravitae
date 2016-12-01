package com.codyrex.terravitae.server.api;

public interface SubtypeRenderedItem {
    int[] getUsedSubtypes();
    String getResource(String name, int metadata);
}
