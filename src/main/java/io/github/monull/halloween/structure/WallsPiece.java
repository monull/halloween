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
        super(StructureRegistry.WALL_PIECE, x, y, z, 48, 48, 100, getRandomHorizontalDirection(random));
    }

    public WallsPiece(ServerWorld serverWorld, NbtCompound nbtCompound) {
        super(StructureRegistry.WALL_PIECE, nbtCompound);
    }

    @Override
    public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
        plusXWalls(world, 0, 0, 0, 7, boundingBox);
        plusXWalls(world, 7, 0, 0, 6, boundingBox);
        plusXWalls(world, 13, 0, 0, 4, boundingBox);
        minusXWalls(world, 28, 0, 0, 4, boundingBox);
        plusXWalls(world, 28, 0, 0, 7, boundingBox);
        plusXWalls(world, 35, 0, 0, 7, boundingBox);
        entrance(world, 17, 0, 0, boundingBox);
        plusZWalls(world, 42, 0, 0, 14, boundingBox);
        plusZWalls(world, 42, 0, 14, 14, boundingBox);
        plusZWalls(world, 42, 0, 28, 14, boundingBox);
        minusXWalls(world, 42, 0, 42, 14, boundingBox);
        minusXWalls(world, 28, 0, 42, 14, boundingBox);
        minusXWalls(world, 14, 0, 42, 14, boundingBox);
        minusZWalls(world, 0, 0, 42, 14, boundingBox);
        minusZWalls(world, 0, 0, 28, 14, boundingBox);
        minusZWalls(world, 0, 0, 14, 14, boundingBox);

        return true;
    }

    public void plusXWalls(StructureWorldAccess world, int x, int y, int z, int sx, BlockBox box) {
        for (int dx = 0; dx < sx; dx++) {
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x + dx, y, z, box);
        }
        int dy = y + 1;
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState(), x, dy, z, box);
        for (int dx = 1; dx < sx; dx++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x + dx, dy, z, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState(), x, dy, z, box);
        for (int dx = 1; dx < sx; dx++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x + dx, dy, z, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, dy, z, box);

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), x, dy, z, box);
    }

    public void minusXWalls(StructureWorldAccess world, int x, int y, int z, int sx, BlockBox box) {
        for (int dx = 0; dx < sx; dx++) {
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x - dx, y, z, box);
        }
        int dy = y + 1;
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState(), x, dy, z, box);
        for (int dx = 1; dx < sx; dx++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x - dx, dy, z, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState(), x, dy, z, box);
        for (int dx = 1; dx < sx; dx++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x - dx, dy, z, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, dy, z, box);

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), x, dy, z, box);
    }

    public void plusZWalls(StructureWorldAccess world, int x, int y, int z, int sz, BlockBox box) {
        for (int dz = 0; dz < sz; dz++) {
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, y, z + dz, box);
        }
        int dy = y + 1;
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState(), x, dy, z, box);
        for (int dz = 1; dz < sz; dz++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x, dy, z + dz, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState(), x, dy, z, box);
        for (int dz = 1; dz < sz; dz++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x, dy, z + dz, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, dy, z, box);

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), x, dy, z, box);
    }

    public void minusZWalls(StructureWorldAccess world, int x, int y, int z, int sz, BlockBox box) {
        for (int dz = 0; dz < sz; dz++) {
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, y, z - dz, box);
        }
        int dy = y + 1;
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState(), x, dy, z, box);
        for (int dz = 1; dz < sz; dz++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x, dy, z - dz, box);
        }

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState(), x, dy, z, box);
        for (int dz = 1; dz < sz; dz++) {
            this.addBlock(world, Blocks.IRON_BARS.getDefaultState(), x, dy, z - dz, box);
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
            this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState().with(Properties.SLAB_TYPE, SlabType.TOP), x + dx + 2, dy, z, box);
        }
        int dy = y + 3;
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(Properties.BLOCK_HALF, BlockHalf.TOP), x - 1, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(Properties.BLOCK_HALF, BlockHalf.TOP), x + 1, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(Properties.BLOCK_HALF, BlockHalf.TOP), x + 6, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x + 7, dy, z, box);
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(Properties.BLOCK_HALF, BlockHalf.TOP), x + 8, dy, z, box);

        dy += 1;
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(Properties.BLOCK_HALF, BlockHalf.BOTTOM), x, dy, z, box);
        for (int dx = 0; dx <= 5; dx++) {
            this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), x + dx + 1, dy, z, box);
        }
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(Properties.BLOCK_HALF, BlockHalf.BOTTOM), x + 7, dy, z, box);

        dy += 1;
        for (int dx = 1; dx <= 6; dx++) {
            this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), x + dx, dy, z, box);
        }
    }
}
