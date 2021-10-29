package io.github.monull.halloween;

import io.github.monull.halloween.biome.BiomeManager;
import io.github.monull.halloween.block.PhaseBlock;
import io.github.monull.halloween.structure.StructureRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HalloweenMod implements ModInitializer {

    public static ServerWorld world;
    @Override
    public void onInitialize() {
        BiomeManager.registerBiomes();
        StructureRegistry.registerStructures();
        Registry.register(Registry.BLOCK, new Identifier("halloween", "phase_block"), new PhaseBlock(FabricBlockSettings.of(Material.METAL).breakByHand(false)));
        ServerTickEvents.START_WORLD_TICK.register(world1 -> {
            world = world1.getServer().getOverworld();
        });
    }
}
