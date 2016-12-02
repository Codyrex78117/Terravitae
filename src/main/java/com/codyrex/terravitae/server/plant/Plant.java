package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

public interface Plant {
    /**
     * @return the block for this plant.
     */
    PlantBlock getBlock();

    /**
     * @return the biome types this plant can spawn in.
     */
    BiomeDictionary.Type[] getSpawnBiomeTypes();

    /**
     * @return the spawn chance. (Percentage)
     */
    double getSpawnChance();
}
