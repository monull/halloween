package io.github.monull.halloween.mixin;

import io.github.monull.halloween.structure.StructureRegistry;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.biome.GenerationSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DefaultBiomeCreator.class)
public class MixinDefaultBiomeCreator {
    @ModifyVariable(method = "createPlains", ordinal = 0, at = @At(value = "STORE", ordinal = 0))
    private static GenerationSettings.Builder addCustomStructure(GenerationSettings.Builder builder, boolean sunflower) {
        if (!sunflower) {
            builder.structureFeature(StructureRegistry.CONFIGURED_STRUCTURE);
        }

        return builder;
    }
}
