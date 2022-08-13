package net.jeremyWilson.morebulls.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties COOKED_BULL_MEAT = (new FoodProperties.Builder()).nutrition(4)
            .saturationMod(0.4F).meat().build();

    public static final FoodProperties UNCOOKED_BULL_MEAT = (new FoodProperties.Builder()).nutrition(2)
            .saturationMod(0.2F).meat().build();

}
