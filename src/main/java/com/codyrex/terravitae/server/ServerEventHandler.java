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
            int spawns = rand.nextInt(10) + 15;
            for (int i = 0; i < spawns; i++) {
                BlockPos spawnPos = pos.add(rand.nextInt(16), 0, rand.nextInt(16));
                for (Plant plant : plants) {
                    if (rand.nextDouble() * 100.0 <= plant.getSpawnChance()) {
                        PlantSpawner spawner = PlantHandler.getSpawner(plant);
                        BlockPos surface = world.getTopSolidOrLiquidBlock(spawnPos);
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
}
