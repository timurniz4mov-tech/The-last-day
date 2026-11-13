package ru.timur.apocalypse.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.entity.Watcher;
import ru.timur.apocalypse.registry.ModEntities;

@Mod.EventBusSubscriber(
    modid = PosledniyDen.MODID,
    bus = Mod.EventBusSubscriber.Bus.MOD,
    value = Dist.CLIENT
)
public class ClientEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.WATCHER.get(), context -> {
            ZombieRenderer zombieRenderer = new ZombieRenderer(context) {
                @Override
                public ResourceLocation getTextureLocation(Zombie entity) {
                    return new ResourceLocation(
                        PosledniyDen.MODID,
                        "textures/entity/watcher.png"
                    );
                }
            };

            // Watcher extends Zombie, so the vanilla zombie model/renderer is
            // compatible at runtime. The cast only adapts the generic type
            // expected by Forge's renderer registration method.
            @SuppressWarnings("unchecked")
            EntityRenderer<Watcher> watcherRenderer =
                (EntityRenderer<Watcher>) (EntityRenderer<?>) zombieRenderer;
            return watcherRenderer;
        });
    }
}
