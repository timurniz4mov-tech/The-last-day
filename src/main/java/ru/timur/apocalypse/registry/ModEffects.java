package ru.timur.apocalypse.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.effect.InfectionEffect;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
        DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PosledniyDen.MODID);

    public static final RegistryObject<MobEffect> INFECTION = EFFECTS.register(
        "infection", InfectionEffect::new
    );

    private ModEffects() {}
}
