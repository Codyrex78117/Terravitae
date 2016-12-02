package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

public class SandRyegrassPlant implements Plant {
    @Override
    public PlantBlock getBlock() {
        return BlockHandler.SAND_RYEGRASS;
    }

    @Override
    public BiomeDictionary.Type[] getSpawnBiomeTypes() {
        return new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH };
    }

    @Override
    public double getSpawnChance() {
        return 40;
    }
}
