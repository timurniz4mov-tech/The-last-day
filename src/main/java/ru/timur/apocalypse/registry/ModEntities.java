package ru.timur.apocalypse.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.entity.Infected;
import ru.timur.apocalypse.entity.Watcher;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PosledniyDen.MODID);

    public static final RegistryObject<EntityType<Watcher>> WATCHER = ENTITIES.register("watcher",
        () -> EntityType.Builder.of(Watcher::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F).clientTrackingRange(8).build("watcher"));

    public static final RegistryObject<EntityType<Infected>> WALKER =
        ENTITIES.register("walker", () -> infectedType("walker"));
    public static final RegistryObject<EntityType<Infected>> RUNNER =
        ENTITIES.register("runner", () -> infectedType("runner"));
    public static final RegistryObject<EntityType<Infected>> BRUTE =
        ENTITIES.register("brute", () -> infectedType("brute"));
    public static final RegistryObject<EntityType<Infected>> SPORELING =
        ENTITIES.register("sporeling", () -> infectedType("sporeling"));
    public static final RegistryObject<EntityType<Infected>> DROWNED =
        ENTITIES.register("drowned_infected", () -> infectedType("drowned_infected"));

    private static EntityType<Infected> infectedType(String id) {
        return EntityType.Builder.of(Infected::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F).clientTrackingRange(8).build(id);
    }

    private ModEntities() {}
}
