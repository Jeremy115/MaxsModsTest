package net.jeremyWilson.morebulls.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jeremyWilson.morebulls.MaxsMod;
import net.jeremyWilson.morebulls.entity.custom.BullEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class bullRender extends GeoEntityRenderer<BullEntity> {


    //Render the bull
    public bullRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new bullModel());
        this.shadowRadius = 0.5f;
    }

    //Get the texture location.
    @Override
    public ResourceLocation getTextureLocation(BullEntity instance) {
        return new ResourceLocation(MaxsMod.MOD_ID, "textures/entity/adult_bull/adult_bull.png");
    }

    //What type
    @Override
    public RenderType getRenderType(BullEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation){
        if(animatable.isBaby()){
            stack.scale(0.4f,0.4F, 0.4F);
        }else{
            stack.scale(1.0F, 1.0F, 1.0F);
        }
        return super.getRenderType(animatable, partialTicks, stack,
                renderTypeBuffer,vertexBuilder, packedLightIn, textureLocation);
    }


}
