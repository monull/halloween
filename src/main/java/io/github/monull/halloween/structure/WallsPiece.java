package io.github.monull.halloween.structure;


import io.github.monull.halloween.HalloweenMod;
import io.github.monull.halloween.block.PhaseBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.enums.*;
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

import java.util.ArrayList;
import java.util.Random;

public class WallsPiece extends StructurePieceWithDimensions {
    public WallsPiece(Random random, int x, int y, int z) {
        super(StructureRegistry.WALL_PIECE, x, y, z, 48, 48, 50, getRandomHorizontalDirection(random));
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
        generateGrave1(world, 0, 0);
        generateGrave2(world, 5, 0);
        generateGrave3(world, 10, 0);
        generateGrave6(world, 22, 0);
        generateGrave5(world, 28, 0);
        generateGrave4(world, 34, 0);
        generateGrave5(world, 10, 11);
        generateGrave6(world, 22, 11);
        generateGrave1(world, 29, 11);
        generateGrave3(world, 34, 11);
        generateGrave2(world, 1, 24);
        generateGrave3(world, 6, 24);
        generateGrave4(world, 11, 24);
        generateGrave6(world, 16, 24);
        generateGrave4(world, 20, 24);
        generateGrave1(world, 28, 24);
        generateGrave5(world, 28, 24);
        generateGrave2(world, 33, 24);
        generateGrave3(world, 38, 24);
        this.addBlock(world, HalloweenMod.phase_block.getDefaultState(), 21, 0, 20, boundingBox);

        return true;
    }

    public void randomGenerateGrave(StructureWorldAccess world, int gapX, int gapZ) {
        int i = new Random().nextInt(6);
        if (i == 0) {
            generateGrave1(world, gapX, gapZ);
        } else if (i == 1) {
            generateGrave2(world, gapX, gapZ);
        } else if (i == 2) {
            generateGrave3(world, gapX, gapZ);
        } else if (i == 3) {
            generateGrave4(world, gapX, gapZ);
        } else if (i == 4) {
            generateGrave5(world, gapX, gapZ);
        } else {
            generateGrave6(world, gapX, gapZ);
        }
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

    private void generateGrave1(StructureWorldAccess world, int gapX, int gapZ) {
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 0, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.EAST_WALL_SHAPE, WallShape.TALL), 3 + gapX, 1, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 1, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.WEST_WALL_SHAPE, WallShape.TALL), 5 + gapX, 1, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.CHISELED_STONE_BRICKS.getDefaultState(), 4 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 5 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 4 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.DARK_OAK_WALL_SIGN.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH), 4 + gapX, 0, 9 + gapZ, boundingBox);
    }

    private void generateGrave2(StructureWorldAccess world, int gapX, int gapZ) {
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 0, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.LEVER.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(Properties.POWERED, false).with(LeverBlock.FACE, WallMountLocation.WALL), 3 + gapX, 1, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.LEVER.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(Properties.POWERED, false).with(LeverBlock.FACE, WallMountLocation.WALL), 5 + gapX, 1, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 1, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 3 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 5 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.LEVER.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(Properties.POWERED, true).with(LeverBlock.FACE, WallMountLocation.WALL), 3 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.LEVER.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(Properties.POWERED, true).with(LeverBlock.FACE, WallMountLocation.WALL), 5 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.DARK_OAK_WALL_SIGN.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH), 4 + gapX, 0, 9 + gapZ, boundingBox);
    }

    private void generateGrave3(StructureWorldAccess world, int gapX, int gapZ) {
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 0, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 1, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.DARK_OAK_WALL_SIGN.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH), 4 + gapX, 0, 9 + gapZ, boundingBox);
    }

    private void generateGrave4(StructureWorldAccess world, int gapX, int gapZ) {
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 0, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.UP, true), 4 + gapX, 1, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.UP, true), 4 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.EAST_WALL_SHAPE, WallShape.LOW).with(Properties.WEST_WALL_SHAPE, WallShape.LOW).with(Properties.UP, true), 4 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.EAST_WALL_SHAPE, WallShape.LOW).with(Properties.UP, true), 3 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.WEST_WALL_SHAPE, WallShape.LOW).with(Properties.UP, true), 5 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.UP, true), 4 + gapX, 4, 10 + gapZ, boundingBox);
    }

    private void generateGrave5(StructureWorldAccess world, int gapX, int gapZ) {
        for (int dx = 0; dx <= 1; dx++) {
            for (int dy = 0; dy <= 2; dy++) {
                this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX + dx, dy, 10 + gapZ, boundingBox);
            }
        }
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.STAIR_SHAPE, StairShape.STRAIGHT).with(Properties.BLOCK_HALF, BlockHalf.TOP).with(Properties.HORIZONTAL_FACING, Direction.EAST), 3 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_STAIRS.getDefaultState().with(Properties.STAIR_SHAPE, StairShape.STRAIGHT).with(Properties.BLOCK_HALF, BlockHalf.TOP).with(Properties.HORIZONTAL_FACING, Direction.WEST), 6 + gapX, 2, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 4 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 5 + gapX, 3, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 4 + gapX, 0, 9 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 4 + gapX, 0, 8 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 5 + gapX, 0, 9 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 5 + gapX, 0, 8 + gapZ, boundingBox);
    }

    private void generateGrave6(StructureWorldAccess world, int gapX, int gapZ) {
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 4 + gapX, 0, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 5 + gapX, 0, 10 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 4 + gapX, 0, 9 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 4 + gapX, 0, 8 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 5 + gapX, 0, 9 + gapZ, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 5 + gapX, 0, 8 + gapZ, boundingBox);
    }
}
