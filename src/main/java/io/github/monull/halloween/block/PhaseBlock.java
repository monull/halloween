package io.github.monull.halloween.block;

import io.github.monull.halloween.phase.HalloweenPhase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PhaseBlock extends Block {

    int i = 0;

    public PhaseBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        i++;
        if (i == 1) {
            new HalloweenPhase().start(pos, 5, 0, false);
        }

        return ActionResult.SUCCESS;
    }


}
