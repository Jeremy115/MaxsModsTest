package net.jeremyWilson.morebulls.item;

import net.jeremyWilson.morebulls.MaxsMod;
import net.jeremyWilson.morebulls.entity.ModEntityTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItem {

    //Registering items and mod id into the item register.
    //Necessary to register your item into the register to identify item.
    //Item:
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MaxsMod.MOD_ID);


    //EXAMPLE ITEM.
    //public static final RegistryObject<Item> ANYTHING = ITEMS.register("ANYTHING",
            //() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));




    //Bull Meat.
    public static final RegistryObject<Item> BULL_MEAT = ITEMS.register("bullmeat",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.UNCOOKED_BULL_MEAT)));


    public static final RegistryObject<Item> BULL_MEAT_COOKED = ITEMS.register("cooked_bullmeat",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.COOKED_BULL_MEAT)));


    //Bull Spawn Egg.
    public static final RegistryObject<ForgeSpawnEggItem> ADULT_BULL_SPAWN_EGG = ITEMS.register("adult_bull_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntityTypes.ADULT_BULL,
                    0x221703, 0x553D13, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
