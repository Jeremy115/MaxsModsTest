package net.jeremyWilson.morebulls.world;


import net.jeremyWilson.morebulls.morebullsMain;
import net.jeremyWilson.morebulls.world.gen.ModEntityGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = morebullsMain.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){


        ModEntityGeneration.onEntitySpawn(event);

    }

}
