package io.github.monull.halloween.structure;

import net.minecraft.block.Blocks;
import net.minecraft.block.enums.WallShape;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.StructurePieceWithDimensions;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;
import java.util.function.Function;

public class Grave1Piece extends StructurePieceWithDimensions {

    public Grave1Piece(Random random, int x, int y, int z, Direction direction) {
        super(StructureRegistry.GRAVE_1, x, y, z, 3, 10, 20, direction);

    }

    public Grave1Piece(ServerWorld serverWorld, NbtCompound nbtCompound) {
        super(StructureRegistry.GRAVE_1, nbtCompound);
    }

    @Override
    public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 0, 0, 0, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.EAST_WALL_SHAPE, WallShape.TALL), -1, 1, 0, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 0, 1, 0, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_WALL.getDefaultState().with(Properties.WEST_WALL_SHAPE, WallShape.TALL), 1, 1, 0, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), -1, 2, 0, boundingBox);
        this.addBlock(world, Blocks.CHISELED_STONE_BRICKS.getDefaultState(), 0, 2, 0, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 2, 0, boundingBox);
        this.addBlock(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 0, 3, 0, boundingBox);
        this.addBlock(world, Blocks.DARK_OAK_WALL_SIGN.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH), 0, 0, -1, boundingBox);
        return true;
    }
}
