package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.plant.DoublePlantBlock;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PlantSpawner {
    PlantSpawner SINGLE_GROUND = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, PlantBlock block) {
            world.setBlockState(pos, block.getDefaultState(), 2);
        }

        @Override
        public boolean canSpawn(IBlockState ground, IBlockState state, IBlockState above, PlantBlock block) {
            return (ground.getMaterial() == Material.GROUND || ground.getMaterial() == Material.GRASS) && canPlaceAir(state, block) && canPlaceAir(above, block);
        }
    };
    PlantSpawner DOUBLE_GROUND = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, PlantBlock block) {
            IBlockState state = block.getDefaultState();
            world.setBlockState(pos, state.withProperty(DoublePlantBlock.HALF, DoublePlantBlock.BlockHalf.LOWER), 2);
            world.setBlockState(pos.up(), state.withProperty(DoublePlantBlock.HALF, DoublePlantBlock.BlockHalf.UPPER), 2);
        }

        @Override
        public boolean canSpawn(IBlockState ground, IBlockState state, IBlockState above, PlantBlock block) {
            return (ground.getMaterial() == Material.GROUND || ground.getMaterial() == Material.GRASS) && canPlaceAir(state, block) && canPlaceAir(above, block);
        }
    };
    PlantSpawner DOUBLE_RIVER = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, PlantBlock block) {
            IBlockState state = block.getDefaultState();
            world.setBlockState(pos, state.withProperty(DoublePlantBlock.HALF, DoublePlantBlock.BlockHalf.LOWER), 2);
            world.setBlockState(pos.up(), state.withProperty(DoublePlantBlock.HALF, DoublePlantBlock.BlockHalf.UPPER), 2);
        }

        @Override
        public boolean canSpawn(IBlockState ground, IBlockState state, IBlockState above, PlantBlock block) {
            return (ground.getMaterial() == Material.GROUND || ground.getMaterial() == Material.GRASS || ground.getMaterial() == Material.CLAY ||ground.getMaterial() == Material.SAND) && canPlaceAir(state, block) && canPlaceAir(above, block);
        }
    };

    PlantSpawner SINGLE_BEACH = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, PlantBlock block) {
            world.setBlockState(pos, block.getDefaultState(), 2);
        }

        @Override
        public boolean canSpawn(IBlockState ground, IBlockState state, IBlockState above, PlantBlock block) {
            return ground.getMaterial() == Material.SAND && canPlaceAir(state, block) && canPlaceAir(above, block);
        }
    };

    /**
     * Places the actual plant in the world
     * @param world the world to place the plant in
     * @param pos the position to place it
     * @param block the block to place
     */
    void spawn(World world, BlockPos pos, PlantBlock block);

    /**
     * Checks if the current plant can spawn at the given place.
     * @param ground the blockstate below the plant
     * @param state the blockstate where the plant will be placed
     * @param above the blockstate above the plant
     * @param block the plant being placed
     * @return if the plant can spawn
     */
    boolean canSpawn(IBlockState ground, IBlockState state, IBlockState above, PlantBlock block);

    /**
     * Checks if the given block is replaceable, but not liquid. Used for land plants to make sure it doesn't spawn in liquid or inside another block
     * @param state the state to check
     * @param block the block being placed
     * @return if the given blockstate can be replaced
     */
    static boolean canPlaceAir(IBlockState state, PlantBlock block) {
        Material material = state.getMaterial();
        return (material.isReplaceable() && !material.isLiquid()) || state.getBlock() == block;
    }
}
