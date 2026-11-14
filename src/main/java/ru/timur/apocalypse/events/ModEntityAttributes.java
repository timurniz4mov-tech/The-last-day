package ru.timur.apocalypse.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.entity.Infected;
import ru.timur.apocalypse.entity.Watcher;
import ru.timur.apocalypse.registry.ModEntities;

@Mod.EventBusSubscriber(modid = PosledniyDen.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributes {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.WATCHER.get(), Watcher.createAttributes().build());
        event.put(ModEntities.WALKER.get(),
            Infected.createAttributes(24.0D, 0.22D, 4.0D, 30.0D).build());
        event.put(ModEntities.RUNNER.get(),
            Infected.createAttributes(16.0D, 0.36D, 3.0D, 36.0D).build());
        event.put(ModEntities.BRUTE.get(),
            Infected.createAttributes(70.0D, 0.18D, 11.0D, 28.0D).build());
        event.put(ModEntities.SPORELING.get(),
            Infected.createAttributes(30.0D, 0.25D, 5.0D, 32.0D).build());
        event.put(ModEntities.DROWNED.get(),
            Infected.createAttributes(38.0D, 0.27D, 6.0D, 34.0D).build());
    }
}
