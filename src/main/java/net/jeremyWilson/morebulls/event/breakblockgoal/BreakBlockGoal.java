package net.jeremyWilson.morebulls.event.breakblockgoal;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.ArrayList;
import java.util.List;

public class BreakBlockGoal extends Goal {


    protected final Mob living;

    private final List<BlockPos> breakList = new ArrayList<>();


    //make entity path find to block.
    public BreakBlockGoal(Mob living){

        this.living = living;
        int digWidth = living.getBbWidth() < 1 ? 0: Mth.ceil(living.getBbWidth());
        int digHeight = (int) living.getBbHeight() + 1;

        for(int i = digHeight; i >=0; i--){

            this.breakList.add(new BlockPos(0, i, 0));

        }
        for(int z = digWidth + 1; z >= -digWidth; z--){
            for(int y = digHeight; y >=0; y--){
                for(int x = 0; x <=digWidth; x++){
                    if(z!=0){
                        this.breakList.add(new BlockPos(x, y, z));
                        if(x != 0){
                            this.breakList.add(new BlockPos(-x, y, z));
                        }
                    }
                }
            }
        }

    }


    @Override
    public boolean canUse() {
        return false;
    }
}
