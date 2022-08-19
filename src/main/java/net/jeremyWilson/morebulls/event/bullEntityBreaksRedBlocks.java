package net.jeremyWilson.morebulls.event;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.level.block.Block;



public class bullEntityBreaksRedBlocks extends RemoveBlockGoal {

    //Break block goal for bull entity.
    public bullEntityBreaksRedBlocks(Block pBlockToRemove, PathfinderMob pRemoverMob, double pSpeedModifier, int pSearchRange) {

        super(pBlockToRemove, pRemoverMob,pSpeedModifier, pSearchRange);
    }

}