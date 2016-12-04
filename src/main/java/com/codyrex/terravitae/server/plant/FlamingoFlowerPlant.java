package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by Codyr on 04/12/2016.
 */
public class FlamingoFlowerPlant implements Plant {

    @Override
    public PlantBlock getBlock() {
        return BlockHandler.FLAMINGO_FLOWER;
    }

    @Override
    public BiomeDictionary.Type[] getSpawnBiomeTypes() {
        return new BiomeDictionary.Type[] { BiomeDictionary.Type.JUNGLE };
    }

    @Override
    public double getSpawnChance() {
        return 5;
    }
}

