package io.github.monull.halloween.structure;


import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureRegistry {

    public static final StructureFeature<DefaultFeatureConfig> GRAVE_STRUCTURE = new GraveStructure(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> CONFIGURED_STRUCTURE = GRAVE_STRUCTURE.configure(new DefaultFeatureConfig());
    public static final StructurePieceType WALL_PIECE = WallsPiece::new;
    public static final StructurePieceType GRAVE_1 = Grave1Piece::new;

    public static void registerStructures() {
        FabricStructureBuilder.create(new Identifier("halloween", "grave_structure"), GRAVE_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .superflatFeature(CONFIGURED_STRUCTURE)
                .adjustsSurface()
                .register();
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier("halloween", "wall_piece"), WALL_PIECE);
    }
}
