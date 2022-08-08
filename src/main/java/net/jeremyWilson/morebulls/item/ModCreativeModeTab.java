package net.jeremyWilson.morebulls.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab("creative") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItem.CITRINE.get());
        }
    };

}
