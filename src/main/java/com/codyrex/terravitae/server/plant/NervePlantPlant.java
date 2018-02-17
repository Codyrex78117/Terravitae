package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by Codyr on 16/02/2018.
 */
public class NervePlantPlant implements Plant{
    @Override
    public PlantBlock getBlock() {
        return BlockHandler.NERVE_PLANT;
    }

    @Override
    public BiomeDictionary.Type[] getSpawnBiomeTypes() {
        return new BiomeDictionary.Type[] { BiomeDictionary.Type.JUNGLE};
    }

    @Override
    public double getSpawnChance() {
        return 20;
    }
}
