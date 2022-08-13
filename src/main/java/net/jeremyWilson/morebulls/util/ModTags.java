package net.jeremyWilson.morebulls.util;


import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;



public class ModTags {
    //Items
    public static final TagKey<Item> UNCOOKED_BULLMEAT = registerItemTag("food/uncookedbullmeat");
    public static final TagKey<Item> COOKED_BULLMEAT = registerItemTag("food/cookedbullmeat");

    //Mobs
    public static final TagKey<EntityType<?>> BULL_ENTITY = registerEntityTag("entity/bull");


    //Item registry
    public static TagKey<Item> registerItemTag(String name){
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", name));
    }

    //Entity registry
    public static TagKey<EntityType<?>> registerEntityTag(String name) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge", name));
    }

}
