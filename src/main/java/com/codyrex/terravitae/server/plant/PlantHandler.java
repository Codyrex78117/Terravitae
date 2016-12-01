package com.codyrex.terravitae.server.plant;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantHandler {
    private static final Map<Plant, PlantSpawner> SPAWNERS = new HashMap<>();
    private static final Map<Biome, List<Plant>> BIOME_PLANTS = new HashMap<>();

    public static void register() {
        try {
            for (Field field : PlantHandler.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Plant) {
                    PlantHandler.registerPlant((Plant) obj);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerPlant(Plant plant) {
        SPAWNERS.put(plant, plant.getSpawner());
        BiomeDictionary.Type[] types = plant.getSpawnBiomeTypes();
        for (BiomeDictionary.Type type : types) {
            Biome[] biomes = BiomeDictionary.getBiomesForType(type);
            for (Biome biome : biomes) {
                List<Plant> biomePlants = BIOME_PLANTS.get(biome);
                if (biomePlants == null) {
                    biomePlants = new ArrayList<>();
                    BIOME_PLANTS.put(biome, biomePlants);
                }
                if (!biomePlants.contains(plant)) {
                    biomePlants.add(plant);
                }
            }
        }
    }

    public static List<Plant> getPlantsToSpawn(Biome biome) {
        return BIOME_PLANTS.get(biome);
    }

    public static PlantSpawner getSpawner(Plant plant) {
        return SPAWNERS.get(plant);
    }
}
