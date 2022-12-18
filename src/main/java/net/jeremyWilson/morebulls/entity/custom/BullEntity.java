package net.jeremyWilson.morebulls.entity.custom;

import net.jeremyWilson.morebulls.entity.ModEntityTypes;
import net.jeremyWilson.morebulls.MaxsMod;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class BullEntity extends Animal implements IAnimatable {



    // Add a constructor that takes a World object as an argument

    //Variable(geckoLibrary)
    private AnimationFactory factory = new AnimationFactory(this);

    //Needed for animations.
    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(MaxsMod.MOD_ID,
            "entities/bulldrop" );



    //Bull constructor.
    public BullEntity(EntityType<? extends Animal> entityType, Level level){
        super(entityType, level);
    }


    //Bull Goals.
    @Override
    protected void registerGoals(){
        super.registerGoals();
        //this.goalSelector.addGoal(1, new bullEntityBreaksRedBlocks(Blocks.REDSTONE_BLOCK, this, 1.0D, 12));
        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(8, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));

    }

    //Attributes bull can have.
    public static AttributeSupplier setAttributes(){

        //Health and movement speed.
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.ATTACK_DAMAGE, 3.0f).build();
    }

    //Returns offSpring of the bull .
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent){

        return ModEntityTypes.ADULT_BULL.get().create(level);
        //return EntityType.COW.create(level);

    }
    //Food which can breed bull.
    public boolean isFood(ItemStack pStack){
        return pStack.getItem() == Items.WHEAT;
    }

    //Animal animations from resources folder.
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event){
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.adult_bull.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.adult_bull.idle", true));
        return PlayState.CONTINUE;
    }

    //add Animations to controller.
    @Override
    public void registerControllers(AnimationData data) {

        data.addAnimationController(new AnimationController(this, "controller",
                0,this::predicate));
    }


    //Allow player to breed bulls.
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand){

        ItemStack itemstack = player.getItemInHand(hand);

        if(isFood(itemstack)){
            return super.mobInteract(player, hand);
        }
        return InteractionResult.FAIL;
    }


    //Required for mob drops. Currently, it matches that of a cow. Dropping beef and item.
    @Override
    protected ResourceLocation getDefaultLootTable(){
        return LOOT_TABLE;
    }

    //Bull can be leashed by player.
    public boolean canBeLeashed(Player player){
        return true;
    }


    //returns animations from object above for animations.
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }



    private BlockPos targetPos;
    private BlockPos direction;

    private BlockPos findNearestBlock(BlockPos startPos, Block... blocks) {

        // Get the entity's current position
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        BlockPos entityPos = new BlockPos(x, y, z);

        // Create a list of the target blocks
        List<Block> targetBlocks = Arrays.asList(blocks);

        // Set the minimum distance to the maximum possible value
        double minDistance = Double.MAX_VALUE;
        BlockPos nearestPos = null;

        // Get the blocks within a radius of 16 blocks from the start position
        BlockPos minPos = new BlockPos(startPos.getX() - 16, startPos.getY() - 16, startPos.getZ() - 16);
        BlockPos maxPos = new BlockPos(startPos.getX() + 16, startPos.getY() + 16, startPos.getZ() + 16);
        List<BlockPos> posList = new ArrayList<>();

        for (int x2 = minPos.getX(); x2 <= maxPos.getX(); x2++) {
            for (int y2 = minPos.getY(); y2 <= maxPos.getY(); y2++) {
                for (int z2 = minPos.getZ(); z2 <= maxPos.getZ(); z2++) {
                    posList.add(new BlockPos(x2, y2, z2));
                }
            }
        }

        // Search for blocks within the list
        for (BlockPos pos : posList) {
            // Get the block at the current position
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            // If the block is a target block
            if (targetBlocks.contains(block)) {
                // Calculate the distance between the entity and the block
                double distance = Math.sqrt(Math.pow(pos.getX() - entityPos.getX(), 2) +
                        Math.pow(pos.getY() - entityPos.getY(), 2) +
                        Math.pow(pos.getZ() - entityPos.getZ(), 2));

                // If the distance is smaller than the current minimum distance
                if (distance < minDistance) {
                    // Update the minimum distance and nearest block position
                    minDistance = distance;
                    nearestPos = pos;
                }
            }
        }

        // Return the nearest block position
        return nearestPos;
    }
    @Override
    public void tick()
    {
        super.tick();

        // Get the entity's current position
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        BlockPos entityPos = new BlockPos(x, y, z);

        // If the target block has not been set or has been destroyed
        if (targetPos == null || this.getLevel().getBlockState(targetPos).isAir())
        {
            // Find the nearest redstone block or red wool block
            targetPos = findNearestBlock(entityPos, Blocks.REDSTONE_BLOCK, Blocks.RED_WOOL, Blocks.RED_BANNER);

            // If a target block was found
            if (targetPos != null)
            {
                // Calculate the direction to the target block
                direction = targetPos.subtract(entityPos);
            }
        }

        // If a target block is set
        if (targetPos != null)
        {
            // Set the entity's motion
            this.setDeltaMovement(direction.getX(), direction.getY(), direction.getZ());

            // If the entity is close enough to the target block
            if (this.distanceToSqr(Vec3.atCenterOf(targetPos)) <= 2.0)
            {
                // Break the block
                this.getLevel().destroyBlock(targetPos, true);

                // Clear the target block
                targetPos = null;
                direction = null;
            }
        }
    }


}

