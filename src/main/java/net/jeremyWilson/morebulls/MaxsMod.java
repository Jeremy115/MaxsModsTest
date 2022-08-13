package net.jeremyWilson.morebulls;

import com.mojang.logging.LogUtils;
import net.jeremyWilson.morebulls.entity.ModEntityTypes;
import net.jeremyWilson.morebulls.item.ModItem;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MaxsMod.MOD_ID)
public class MaxsMod
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "morebulls";

    public MaxsMod()
    {
        //Mod Loading event
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModItem.ITEMS.register(eventBus); //Items tab.
        ModEntityTypes.ENTITIES.register(eventBus); //Entities

        GeckoLib.initialize();

        eventBus.addListener(this::setup);//set first.
        //eventBus.addListener(this::clientSetup); //client setup

        //GeckoLib.initialize();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {

            SpawnPlacements.register(ModEntityTypes.ADULT_BULL.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);


    });

    }
}
