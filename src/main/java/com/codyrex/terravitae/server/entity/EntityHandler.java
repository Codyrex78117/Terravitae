package com.codyrex.terravitae.server.entity;

import com.codyrex.terravitae.Terravitae;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler {
    private static int id = 0;

    public static void register() {
    }

    /**
     * Registers an entity with a spawn egg
     * @param entity the entity class to register
     * @param name the entity name (must be lowercased with no spaces)
     * @param eggPrimaryColour the egg primary colour
     * @param eggSecondaryColour the egg secondary colour
     */
    private static void register(Class<? extends Entity> entity, String name, int eggPrimaryColour, int eggSecondaryColour) {
        EntityRegistry.registerModEntity(entity, name, id++, Terravitae.INSTANCE, 64, 1, true, eggPrimaryColour, eggSecondaryColour);
    }

    /**
     * Registers this entity to be spawned in the given biome types
     * @param entity the entity to add
     * @param spawnProbability the probability of a spawn
     * @param minSpawn the minimum amount of entities to spawn together
     * @param maxSpawn the maximum amount of entities to spawn together
     * @param type the entity type (Pigs: CREATURE, Zombies: MONSTER)
     * @param biomeTypes the biome types to spawn
     */
    private static void registerSpawn(Class<? extends EntityLiving> entity, int spawnProbability, int minSpawn, int maxSpawn, EnumCreatureType type, BiomeDictionary.Type... biomeTypes) {
        Biome[] biomes = EntityHandler.getBiomesForTypes(biomeTypes);
        EntityRegistry.addSpawn(entity, spawnProbability, minSpawn, maxSpawn, type, biomes);
    }

    private static Biome[] getBiomesForTypes(BiomeDictionary.Type... types) {
        List<Biome> biomes = new ArrayList<>();
        for (BiomeDictionary.Type type : types) {
            Biome[] typeBiomes = BiomeDictionary.getBiomesForType(type);
            for (Biome biome : typeBiomes) {
                if (!biomes.contains(biome)) {
                    biomes.add(biome);
                }
            }
        }
        return biomes.toArray(new Biome[biomes.size()]);
    }
}
