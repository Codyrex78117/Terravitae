package com.codyrex.terravitae.server;

import com.codyrex.terravitae.server.plant.Plant;
import com.codyrex.terravitae.server.plant.PlantHandler;
import com.codyrex.terravitae.server.plant.PlantSpawner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Random;

public class ServerEventHandler {
    @SubscribeEvent
    public void onBiomeDecorate(DecorateBiomeEvent.Pre event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Random rand = event.getRand();

        Biome biome = world.getBiome(pos);

        List<Plant> plants = PlantHandler.getPlantsToSpawn(biome);
        if (plants != null) {
            for (Plant plant : plants) {
                if (rand.nextInt(plant.getSpawnChance()) == 0) {
                    PlantSpawner spawner = PlantHandler.getSpawner(plant);
                    BlockPos surface = world.getTopSolidOrLiquidBlock(pos);
                    IBlockState ground = world.getBlockState(surface.down());
                    IBlockState state = world.getBlockState(surface);
                    IBlockState above = world.getBlockState(surface.up());
                    if (spawner.canSpawn(ground, state, above)) {
                        spawner.spawn(world, surface, plant.getBlock());
                    }
                }
            }
        }
    }
}
