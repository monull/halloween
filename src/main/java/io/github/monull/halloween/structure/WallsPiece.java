package io.github.monull.halloween.structure;


import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePieceWithDimensions;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class WallsPiece extends StructurePieceWithDimensions {
    public WallsPiece(Random random, int x, int y, int z) {
        super(StructureRegistry.WALL_PIECE, x, y, z, 48, 48, 16, getRandomHorizontalDirection(random));
    }

    public WallsPiece(ServerWorld serverWorld, NbtCompound nbtCompound) {
        super(StructureRegistry.WALL_PIECE, nbtCompound);
    }

    @Override
    public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
        for (int x = 0; x < 48; x++) {
            for (int z = 0; z < 48; z++) {
                for (int y = 0; y < 16; y++) {
                    this.addBlock(world, Blocks.DIAMOND_BLOCK.getDefaultState(), x, y, z, boundingBox);
                }
            }
        }
        return true;
    }
}
