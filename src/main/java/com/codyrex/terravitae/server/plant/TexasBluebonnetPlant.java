package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by Codyr on 18/02/2018.
 */
public class TexasBluebonnetPlant implements Plant {
    @Override
    public PlantBlock getBlock() {
        return BlockHandler.TEXAS_BLUEBONNET;
    }

    @Override
    public BiomeDictionary.Type[] getSpawnBiomeTypes() {
        return new BiomeDictionary.Type[]{BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY};
    }

    @Override
    public double getSpawnChance() {
        return 1;
    }

}
