package net.jeremyWilson.morebulls.item;

import net.jeremyWilson.morebulls.morebullsMain;
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
            DeferredRegister.create(ForgeRegistries.ITEMS, morebullsMain.MOD_ID);


    //Your newly created items.
    public static final RegistryObject<Item> CITRINE = ITEMS.register("citrine",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register("raw_citrine",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));






    public static final RegistryObject<ForgeSpawnEggItem> ADULT_BULL_SPAWN_EGG = ITEMS.register("adult_bull_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntityTypes.ADULT_BULL,
                    0x221703, 0x553D13, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
