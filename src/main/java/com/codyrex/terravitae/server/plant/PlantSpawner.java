package com.codyrex.terravitae.server.plant;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PlantSpawner {
    PlantSpawner SINGLE_GROUND = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, Block block) {
            world.setBlockState(pos, block.getDefaultState());
        }

        @Override
        public boolean canSpawn(IBlockState ground, IBlockState state, IBlockState above) {
            return ground.getMaterial() == Material.GROUND && state.getMaterial() == Material.AIR && above.getMaterial() == Material.AIR;
        }
    };

    void spawn(World world, BlockPos pos, Block block);
    boolean canSpawn(IBlockState ground, IBlockState state,  IBlockState above);
}
