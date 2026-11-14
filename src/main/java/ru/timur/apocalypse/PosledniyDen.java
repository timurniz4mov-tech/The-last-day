package ru.timur.apocalypse;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ru.timur.apocalypse.registry.ModItems;
import ru.timur.apocalypse.registry.ModEntities;
import ru.timur.apocalypse.registry.ModCreativeTabs;
import ru.timur.apocalypse.registry.ModEffects;

@Mod(PosledniyDen.MODID)
public class PosledniyDen {
    public static final String MODID = "posledniy_den";

    public PosledniyDen() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModEffects.EFFECTS.register(bus);
        ModCreativeTabs.TABS.register(bus);
    }
}
