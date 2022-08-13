package net.jeremyWilson.morebulls.entity.client;

import net.jeremyWilson.morebulls.MaxsMod;
import net.jeremyWilson.morebulls.entity.custom.BullEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class bullModel extends AnimatedGeoModel<BullEntity> {

    //Retrieve Bull model from resources folder.
    @Override
    public ResourceLocation getModelLocation(BullEntity object) {
        return new ResourceLocation(MaxsMod.MOD_ID, "geo/adult_bull.geo.json");
    }

    //Retrieve bull textures(.png file)
    @Override
    public ResourceLocation getTextureLocation(BullEntity object) {
        return new ResourceLocation(MaxsMod.MOD_ID, "textures/entity/adult_bull/adult_bull.png");
    }

    //Get animations from bull
    @Override
    public ResourceLocation getAnimationFileLocation(BullEntity animatable) {
        //Bull animation.
        return new ResourceLocation(MaxsMod.MOD_ID, "animations/adult_bull.animation.json");

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(BullEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 1000F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 1100F));
        }
    }
}