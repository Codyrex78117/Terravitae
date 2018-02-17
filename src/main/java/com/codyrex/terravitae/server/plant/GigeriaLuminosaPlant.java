package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraftforge.common.BiomeDictionary;

public class GigeriaLuminosaPlant implements Plant {
        @Override
        public PlantBlock getBlock() {
            return BlockHandler.GIGERIA_LUMINOSA;
        }

        @Override
        public BiomeDictionary.Type[] getSpawnBiomeTypes() {
            return new BiomeDictionary.Type[] { BiomeDictionary.Type.END };
        }

        @Override
        public double getSpawnChance() {
            return 20;
        }
    }
