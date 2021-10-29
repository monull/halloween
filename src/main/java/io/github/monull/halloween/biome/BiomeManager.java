package io.github.monull.halloween.biome;

import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class BiomeManager {

    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(
                    Blocks.PODZOL.getDefaultState(),
                    Blocks.DIRT.getDefaultState(),
                    Blocks.JACK_O_LANTERN.getDefaultState()
            ));

    public static final Biome HalloweenBiome = createHalloweenBiome();

    public static final RegistryKey<Biome> BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("halloween", "biome"));

    private static Biome createHalloweenBiome() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(SURFACE_BUILDER);
        DefaultBiomeFeatures.addSprings(generationSettings);

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.DESERT)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.9F)
                .downfall(0.9F)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0xB153EF)
                        .waterFogColor(0xD976ED)
                        .fogColor(0xA7AA9D)
                        .skyColor(0xD87300)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
    public static void registerBiomes() {
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("halloween", "halloween_surface_builder"), SURFACE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, BIOME_KEY.getValue(), HalloweenBiome);

        OverworldBiomes.addContinentalBiome(BIOME_KEY, OverworldClimate.DRY, 5D);
        OverworldBiomes.addContinentalBiome(BIOME_KEY, OverworldClimate.TEMPERATE, 2D);
    }
}
