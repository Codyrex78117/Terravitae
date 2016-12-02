package com.codyrex.terravitae.server.block.plant;

import com.codyrex.terravitae.server.api.DefaultRenderedItem;
import com.codyrex.terravitae.server.plant.PlantSpawner;
import com.codyrex.terravitae.server.tab.TabHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class PlantBlock<T extends PlantBlock> extends BlockBush implements DefaultRenderedItem {
    public static final AxisAlignedBB FLOWER = new AxisAlignedBB(0.3, 0.0, 0.3, 0.7, 0.6, 0.7);
    public static final AxisAlignedBB BUSH = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.8, 0.9);
    public static final AxisAlignedBB DOUBLE = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 1.0, 0.9);

    private PlantSpawner spawner = PlantSpawner.SINGLE_GROUND;
    private AxisAlignedBB bounds = PlantBlock.BUSH;

    public PlantBlock(Material material, String name) {
        super(material);
        this.setCreativeTab(TabHandler.PLANTS);
        this.setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);
        this.setUnlocalizedName(name);
    }

    public PlantBlock(String name) {
        this(Material.PLANTS, name);
    }

    public T withSpawner(PlantSpawner spawner) {
        this.spawner = spawner;
        return (T) this;
    }

    public T withBounds(AxisAlignedBB bounds) {
        this.bounds = bounds;
        return (T) this;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (this.shouldSpread()) {
            int light = world.getLight(pos);

            if (light >= 5) {
                if (rand.nextInt((15 - light) / 2 + 10) == 0) {
                    int allowedInArea = this.getDensityPerArea();

                    BlockPos nextPos = null;
                    int placementAttempts = 3;

                    while (nextPos == null && placementAttempts > 0) {
                        int spreadRadius = this.getSpreadRadius();
                        int doubleRadius = spreadRadius * 2;
                        BlockPos tmp = pos.add(rand.nextInt(doubleRadius) - spreadRadius, -spreadRadius, rand.nextInt(doubleRadius) - spreadRadius);
                        nextPos = this.findGround(world, tmp);
                        placementAttempts--;
                    }

                    if (nextPos != null) {
                        for (BlockPos neighbourPos : BlockPos.getAllInBoxMutable(nextPos.add(-2, -3, -2), nextPos.add(2, 3, 2))) {
                            if (world.getBlockState(neighbourPos).getBlock() instanceof BlockBush) {
                                allowedInArea--;

                                if (allowedInArea <= 0) {
                                    return;
                                }
                            }
                        }

                        if (this.isNearWater(world, pos)) {
                            this.spread(world, nextPos);
                        }
                    }
                }
            }
        }
    }

    protected boolean isNearWater(World world, BlockPos nextPos) {
        for (BlockPos neighbourPos : BlockPos.getAllInBoxMutable(nextPos.add(-8, -3, -8), nextPos.add(8, 3, 8))) {
            Block neighbourState = world.getBlockState(neighbourPos).getBlock();

            if (neighbourState == Blocks.WATER || neighbourState == Blocks.FLOWING_WATER) {
                if (neighbourPos.getDistance(nextPos.getX(), nextPos.getY(), nextPos.getZ()) < 9) {
                    return true;
                }
            }
        }

        return false;
    }

    protected void spread(World world, BlockPos position) {
        world.setBlockState(position, this.getDefaultState());
    }

    protected BlockPos findGround(World world, BlockPos start) {
        BlockPos pos = start;

        IBlockState down = world.getBlockState(pos.down());
        IBlockState here = world.getBlockState(pos);
        IBlockState up = world.getBlockState(pos.up());

        for (int i = 0; i < 8; ++i) {
            if (this.canPlace(down, here, up)) {
                return pos.down();
            }

            down = here;
            here = up;
            pos = pos.up();
            up = world.getBlockState(pos);
        }

        return null;
    }

    protected boolean canPlace(IBlockState down, IBlockState here, IBlockState up) {
        return this.spawner.canSpawn(down, here, up);
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        return this.canPlace(world.getBlockState(pos.down()), state, world.getBlockState(pos.up()));
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return this.canBlockStay(world, pos, world.getBlockState(pos));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return this.bounds;
    }

    public int getDensityPerArea() {
        return 4;
    }

    public int getSpreadRadius() {
        return 6;
    }

    public boolean shouldSpread() {
        return true;
    }

    public PlantSpawner getSpawner() {
        return this.spawner;
    }
}
