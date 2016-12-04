package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by Codyr on 03/12/2016.
 */
public class UgandagrassPlant implements Plant {
    @Override
    public PlantBlock getBlock() {
        return BlockHandler.UGANDAGRASS;
    }

    @Override
    public BiomeDictionary.Type[] getSpawnBiomeTypes() {
        return new BiomeDictionary.Type[] { BiomeDictionary.Type.SAVANNA };
    }

    @Override
    public double getSpawnChance() {
        return 1.5;
    }
}
