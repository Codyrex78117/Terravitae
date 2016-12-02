package com.codyrex.terravitae.server.block.plant;

import net.minecraft.block.material.Material;

public class StationaryPlantBlock extends PlantBlock<StationaryPlantBlock> {
    public StationaryPlantBlock(Material material, String name) {
        super(material, name);
    }

    @Override
    public boolean shouldSpread() {
        return false;
    }
}
