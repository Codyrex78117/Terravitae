package com.codyrex.terravitae.server.plant;

import com.codyrex.terravitae.server.block.plant.DoublePlantBlock;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PlantSpawner {
    PlantSpawner SINGLE_GROUND = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, PlantBlock block) {
            world.setBlockState(pos, block.getDefaultState(), 2);
        }

        @Override
        public boolean canSpawn(World world, BlockPos pos, IBlockState ground, IBlockState state, IBlockState above, PlantBlock block, boolean generation) {
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
        public boolean canSpawn(World world, BlockPos pos, IBlockState ground, IBlockState state, IBlockState above, PlantBlock block, boolean generation) {
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
        public boolean canSpawn(World world, BlockPos pos, IBlockState ground, IBlockState state, IBlockState above, PlantBlock block, boolean generation) {
            boolean canPlace = (ground.getMaterial() == Material.GROUND || ground.getMaterial() == Material.GRASS || ground.getMaterial() == Material.CLAY || ground.getMaterial() == Material.SAND) && canPlaceAir(state, block) && canPlaceAir(above, block);
            return canPlace && (!generation || PlantSpawner.isNearWater(world, pos, 5, 3));
        }
    };

    PlantSpawner SINGLE_BEACH = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, PlantBlock block) {
            world.setBlockState(pos, block.getDefaultState(), 2);
        }

        @Override
        public boolean canSpawn(World world, BlockPos pos, IBlockState ground, IBlockState state, IBlockState above, PlantBlock block, boolean generation) {
            return ground.getMaterial() == Material.SAND && canPlaceAir(state, block) && canPlaceAir(above, block);
        }
    };
    PlantSpawner DOUBLE_END = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, PlantBlock block) {
            IBlockState state = block.getDefaultState();
            world.setBlockState(pos, state.withProperty(DoublePlantBlock.HALF, DoublePlantBlock.BlockHalf.LOWER), 2);
            world.setBlockState(pos.up(), state.withProperty(DoublePlantBlock.HALF, DoublePlantBlock.BlockHalf.UPPER), 2);
        }

        @Override
        public boolean canSpawn(World world, BlockPos pos, IBlockState ground, IBlockState state, IBlockState above, PlantBlock block, boolean generation) {
            return (ground.getMaterial() == Material.GROUND || state.getBlock() == Blocks.END_STONE && canPlaceAir(state, block) && canPlaceAir(above, block));

        }
    };
    PlantSpawner SINGLE_RIVER = new PlantSpawner() {
        @Override
        public void spawn(World world, BlockPos pos, PlantBlock block) {
            world.setBlockState(pos, block.getDefaultState(), 2);
        }
        @Override
        public boolean canSpawn(World world, BlockPos pos, IBlockState ground, IBlockState state, IBlockState above, PlantBlock block, boolean generation) {
            boolean canPlace = (ground.getMaterial() == Material.GROUND || ground.getMaterial() == Material.GRASS || ground.getMaterial() == Material.CLAY || ground.getMaterial() == Material.SAND) && canPlaceAir(state, block) && canPlaceAir(above, block);
            return canPlace && (!generation || PlantSpawner.isNearWater(world, pos, 5, 3));
        }
    };

    /**
     * Places the actual plant in the world
     *
     * @param world the world to place the plant in
     * @param pos the position to place it
     * @param block the block to place
     */
    void spawn(World world, BlockPos pos, PlantBlock block);

    /**
     * Checks if the current plant can spawn at the given place.
     *
     * @param world the current world
     * @param pos the position being spawned at
     * @param ground the blockstate below the plant
     * @param state the blockstate where the plant will be placed
     * @param above the blockstate above the plant
     * @param block the plant being placed
     * @param generation if this is being called from generation
     * @return if the plant can spawn
     */
    boolean canSpawn(World world, BlockPos pos, IBlockState ground, IBlockState state, IBlockState above, PlantBlock block, boolean generation);

    /**
     * Checks if the given block is replaceable, but not liquid. Used for land plants to make sure it doesn't spawn in liquid or inside another block
     *
     * @param state the state to check
     * @param block the block being placed
     * @return if the given blockstate can be replaced
     */
    static boolean canPlaceAir(IBlockState state, PlantBlock block) {
        Material material = state.getMaterial();
        return (material.isReplaceable() && !material.isLiquid()) || state.getBlock() == block;
    }

    /**
     * Checks if there is water near the given point, using the input ranges
     *
     * @param world the world to search for nearby water in
     * @param pos the position to search around
     * @param range the range on the horizontal axis
     * @param rangeY the range on the vertical axis
     * @return if there is water nearby
     */
    static boolean isNearWater(World world, BlockPos pos, int range, int rangeY) {
        int rangeSquared = range * range;
        for (BlockPos neighbourPos : BlockPos.getAllInBoxMutable(pos.add(-range, -rangeY, -range), pos.add(range, rangeY, range))) {
            Block neighbourState = world.getBlockState(neighbourPos).getBlock();
            if (neighbourState == Blocks.WATER || neighbourState == Blocks.FLOWING_WATER) {
                if (neighbourPos.distanceSq(pos.getX(), pos.getY(), pos.getZ()) < rangeSquared) {
                    return true;
                }
            }
        }
        return false;
    }
}
