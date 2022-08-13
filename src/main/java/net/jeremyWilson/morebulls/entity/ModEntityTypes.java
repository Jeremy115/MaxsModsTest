package net.jeremyWilson.morebulls.entity;

import net.jeremyWilson.morebulls.MaxsMod;
import net.jeremyWilson.morebulls.entity.custom.BullEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntityTypes {

    //Entity:
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, MaxsMod.MOD_ID);

    //Entity: Bull
    public static final RegistryObject<EntityType<BullEntity>> ADULT_BULL = ENTITIES.register("adult_bull",
            () -> EntityType.Builder.of(BullEntity::new, MobCategory.CREATURE).sized(1.8f, 1.8f)
                    .build(new ResourceLocation(MaxsMod.MOD_ID, "adult_bull").toString()));


    //register bull Entities.
    public static void register(IEventBus eventBus){
       ENTITIES.register(eventBus);
    }
}
