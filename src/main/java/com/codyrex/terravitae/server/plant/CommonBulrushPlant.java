package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by Codyr on 02/02/2017.
 */
public class CommonBulrushPlant implements Plant {
    @Override
    public PlantBlock getBlock() {
        return BlockHandler.COMMON_BULRUSH;
    }

    @Override
    public BiomeDictionary.Type[] getSpawnBiomeTypes() {
        return new BiomeDictionary.Type[] { BiomeDictionary.Type.RIVER, BiomeDictionary.Type.SWAMP };
    }

    @Override
    public double getSpawnChance() {
        return 80;
    }
}

