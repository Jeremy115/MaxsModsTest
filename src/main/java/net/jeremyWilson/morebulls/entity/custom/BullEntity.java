package net.jeremyWilson.morebulls.entity.custom;

import net.jeremyWilson.morebulls.entity.ModEntityTypes;
import net.jeremyWilson.morebulls.morebullsMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class BullEntity extends Animal implements IAnimatable {

    //Needed for animations.
    private static final ResourceLocation LOOT_TABLE = new ResourceLocation(morebullsMain.MOD_ID,
            "entities/bulldrop" );
    private AnimationFactory factory = new AnimationFactory(this);


    //Bull constructor.
    public BullEntity(EntityType<? extends Animal> entityType, Level level){
        super(entityType, level);
    }


    //Bull Goals.
    @Override
    protected void registerGoals(){
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));


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
    public  AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent){

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

    @Override
    protected ResourceLocation getDefaultLootTable(){
        return LOOT_TABLE;
    }

    //Bull can be leached by player.
    public boolean canBeLeashed(Player player){
        return true;
    }

    //returns animations from object above for animations.
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }



}
