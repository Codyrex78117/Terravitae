package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

public class GardenAngelicaPlant implements Plant {
    @Override
    public PlantBlock getBlock() {
        return BlockHandler.GARDEN_ANGELICA;
    }

    @Override
    public BiomeDictionary.Type[] getSpawnBiomeTypes() {
        return new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.RIVER };
    }

    @Override
    public double getSpawnChance() {
        return 1.5;
    }
}
