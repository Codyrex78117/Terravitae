package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by Codyr on 04/12/2016.
 */
public class MelocactusAzureusPlant implements Plant {

    @Override
    public PlantBlock getBlock() {
        return BlockHandler.MELOCACTUS_AZUREUS;
    }

    @Override
    public BiomeDictionary.Type[] getSpawnBiomeTypes() {
        return new BiomeDictionary.Type[] { BiomeDictionary.Type.MESA };
    }

    @Override
    public double getSpawnChance() {
        return 0.2;
    }
}

