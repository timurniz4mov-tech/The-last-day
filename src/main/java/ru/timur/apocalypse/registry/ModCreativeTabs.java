
package ru.timur.apocalypse.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.timur.apocalypse.PosledniyDen;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
        DeferredRegister.create(ForgeRegistries.CREATIVE_MODE_TABS, PosledniyDen.MODID);

    public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("main",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.posledniy_den.main"))
            .icon(() -> new ItemStack(ModItems.ASHEN_CORE.get()))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.ASHEN_CORE.get());
                output.accept(ModItems.FIELD_MEDKIT.get());
                output.accept(ModItems.SIGNAL_FLARE.get());
            }).build());
}
