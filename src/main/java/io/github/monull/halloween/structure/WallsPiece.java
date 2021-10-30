package io.github.monull.halloween.structure;


import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.StructurePieceWithDimensions;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
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
        plusXWalls(world, 0, 0, 0, 6, boundingBox);
        plusXWalls(world, 6, 0, 0, 6, boundingBox);
        plusXWalls(world, 12, 0, 0, 4, boundingBox);
        return true;
    }

    public void plusXWalls(StructureWorldAccess world, int x, int y, int z, int sx, BlockBox box) {
        for (int dx = 0; dx < sx; dx++) {
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x + dx, y, z, box);
        }
        int dy = y + 1;
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), x, dy, z, box);
        for (int dx = 1; dx < sx; dx++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x + dx, dy, z, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), x, dy, z, box);
        for (int dx = 1; dx < sx; dx++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x + dx, dy, z, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, dy, z, box);

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), x, dy, z, box);
    }

    public void entrance(StructureWorldAccess world, int x, int y, int z, BlockBox box) {
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, y, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x + 7, y, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, y + 1, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x + 7, y + 1, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, y + 2, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x + 7, y + 2, z, box);
        for (int dx = 0; dx <= 3; dx++) {
            int dy = y + 3;
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState().with(Properties.SLAB_TYPE, SlabType.TOP), x + dx + 2, dy, z, box);
        }
        int dy = y + 3;
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.FACING, Direction.EAST).with(Properties.BLOCK_HALF, BlockHalf.TOP), x - 1, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.FACING, Direction.WEST).with(Properties.BLOCK_HALF, BlockHalf.TOP), x + 1, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.FACING, Direction.EAST).with(Properties.BLOCK_HALF, BlockHalf.TOP), x + 6, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x + 7, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.FACING, Direction.WEST).with(Properties.BLOCK_HALF, BlockHalf.TOP), x + 8, dy, z, box);

        dy += 1;

    }
}
