package ru.timur.apocalypse.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timur.apocalypse.PosledniyDen;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, PosledniyDen.MODID);

    public static final RegistryObject<Item> ASHEN_CORE = ITEMS.register("ashen_core",
        () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FIELD_MEDKIT = ITEMS.register("field_medkit",
        () -> new Item(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> SIGNAL_FLARE = ITEMS.register("signal_flare",
        () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.RARE)));
}
