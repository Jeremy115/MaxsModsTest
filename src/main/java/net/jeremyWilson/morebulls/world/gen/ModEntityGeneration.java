package net.jeremyWilson.morebulls.world.gen;

import net.jeremyWilson.morebulls.entity.ModEntityTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Arrays;
import java.util.List;

public class ModEntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event){

        //Biomes where adult_bull can spawn
        addEntityToAllOverworldBiomes(event, ModEntityTypes.ADULT_BULL.get(), 30, 2, 4);

    }


    //All biomes, including neather, end, etc...
    private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type,
                                                        int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
        boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(!isBiomeSelected) {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
        }
    }

    @SafeVarargs
    private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                  int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
        boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(isBiomeSelected) {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
        }
    }

    //Mod will only spawn in overworld.
    private static void addEntityToAllOverworldBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                      int weight, int minCount, int maxCount) {
        if(!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToAllBiomesNoNether(BiomeLoadingEvent event, EntityType<?> type,
                                                     int weight, int minCount, int maxCount) {
        if(!event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
            base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
        }
    }

    private static void addEntityToAllBiomesNoEnd(BiomeLoadingEvent event, EntityType<?> type,
                                                  int weight, int minCount, int maxCount) {
        if(!event.getCategory().equals(Biome.BiomeCategory.THEEND)) {
            List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
            base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
        }
    }

    private static void addEntityToAllBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                             int weight, int minCount, int maxCount) {
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
        base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
    }

}
