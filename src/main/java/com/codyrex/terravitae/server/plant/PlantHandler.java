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

    public static final SandRyegrassPlant SAND_RYEGRASS = new SandRyegrassPlant();
    public static final GardenAngelicaPlant GARDEN_ANGELICA = new GardenAngelicaPlant();
    public static final UgandagrassPlant UGANDAGRASS = new UgandagrassPlant();
    public static final FlamingoFlowerPlant FLAMINGO_FLOWER = new FlamingoFlowerPlant();
    public static final MelocactusAzureusPlant MELOCACTUS_AZUREUS = new MelocactusAzureusPlant();
    public static final CommonBulrushPlant COMMON_BULRUSH_PLANT = new CommonBulrushPlant();
    public static final GigeriaLuminosaPlant GIGERIA_LUMINOSA_PLANT = new GigeriaLuminosaPlant();
    public static final FieldForgetMeNotPlant FIELD_FORGET_ME_NOT_PLANT = new FieldForgetMeNotPlant();
    public static final GoldLaceCactusPlant GOLD_LACE_CACTUS_PLANT = new GoldLaceCactusPlant();
    public static final AriocarpusKotschoubeyanusPlant ARIOCARPUS_KOTSCHOUBEYANUS = new AriocarpusKotschoubeyanusPlant();
    public static final NervePlantPlant NERVE_PLANT_PLANT = new NervePlantPlant();
    public static final TexasBluebonnetPlant TEXAS_BLUEBONNET_PLANT = new TexasBluebonnetPlant();
    public static final WesternSwordFernPlant WESTERN_SWORD_FERN_PLANT = new WesternSwordFernPlant();


    /**
     * Registers all plants that are a static field on this class.
     */
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

    /**
     * Registers the given plant.
     * @param plant the plant to register
     */
    public static void registerPlant(Plant plant) {
        SPAWNERS.put(plant, plant.getBlock().getSpawner());
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

    /**
     * Gets a list of plants that can spawn in the given biome
     * @param biome the biome to find plants for
     */
    public static List<Plant> getPlantsToSpawn(Biome biome) {
        return BIOME_PLANTS.get(biome);
    }

    /**
     * Gets a PlantSpawner for the given plant
     * @param plant the plant to get for
     */
    public static PlantSpawner getSpawner(Plant plant) {
        return SPAWNERS.get(plant);
    }
}
