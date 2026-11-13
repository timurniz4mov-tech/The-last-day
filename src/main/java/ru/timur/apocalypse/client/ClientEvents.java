package ru.timur.apocalypse.client;

import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.entity.Watcher;

@Mod.EventBusSubscriber(modid = PosledniyDen.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ru.timur.apocalypse.registry.ModEntities.WATCHER.get(),
            context -> new ZombieRenderer<Watcher>(context) {
                @Override
                public ResourceLocation getTextureLocation(Watcher entity) {
                    return new ResourceLocation(PosledniyDen.MODID, "textures/entity/watcher.png");
                }
            });
    }
}
