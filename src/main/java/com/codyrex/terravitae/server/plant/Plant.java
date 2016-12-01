package com.codyrex.terravitae.server.plant;

import net.minecraft.block.Block;
import net.minecraftforge.common.BiomeDictionary;

public interface Plant {
    Block getBlock();
    BiomeDictionary.Type[] getSpawnBiomeTypes();
    PlantSpawner getSpawner();
    int getSpawnChance();
}
