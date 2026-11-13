
package ru.timur.apocalypse.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.entity.Watcher;
import ru.timur.apocalypse.registry.ModEntities;

@Mod.EventBusSubscriber(modid = PosledniyDen.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributes {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.WATCHER.get(), Watcher.createAttributes().build());
    }
}
