package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

/**
 * Created by Codyr on 20/07/2017.
 */
public class FieldForgetMeNotPlant implements Plant{
        @Override
        public PlantBlock getBlock() {
            return BlockHandler.FIELD_FORGET_ME_NOT;
        }

        @Override
        public BiomeDictionary.Type[] getSpawnBiomeTypes() {
            return new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST};
        }

        @Override
        public double getSpawnChance() {
            return 15;
        }
    }

