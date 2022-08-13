package net.jeremyWilson.morebulls.event;


import net.jeremyWilson.morebulls.MaxsMod;
import net.jeremyWilson.morebulls.entity.ModEntityTypes;
import net.jeremyWilson.morebulls.entity.client.bullRender;
import net.jeremyWilson.morebulls.entity.custom.BullEntity;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MaxsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusEvents {


    //Needed for bull entity attributes.
    @SubscribeEvent
    public static void entityAttributesEvent(EntityAttributeCreationEvent event){
        //event.put(ModEntityTypes.ADULT_BULL.get(), BullEntity.setAttributes());
        event.put(ModEntityTypes.ADULT_BULL.get(), BullEntity.setAttributes());
    }


    //Event for FMLClientSetupEvent for forge to load.
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event){
        //Bull
        EntityRenderers.register(ModEntityTypes.ADULT_BULL.get(), bullRender::new);
    }


}
