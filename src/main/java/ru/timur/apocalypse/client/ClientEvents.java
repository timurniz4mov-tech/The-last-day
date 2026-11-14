package ru.timur.apocalypse.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.entity.Infected;
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
        event.registerEntityRenderer(ModEntities.WATCHER.get(), ClientEvents::watcherRenderer);
        event.registerEntityRenderer(ModEntities.WALKER.get(), context ->
            infectedRenderer(context, "walker.png"));
        event.registerEntityRenderer(ModEntities.RUNNER.get(), context ->
            infectedRenderer(context, "runner.png"));
        event.registerEntityRenderer(ModEntities.BRUTE.get(), context ->
            infectedRenderer(context, "brute.png"));
        event.registerEntityRenderer(ModEntities.SPORELING.get(), context ->
            infectedRenderer(context, "sporeling.png"));
        event.registerEntityRenderer(ModEntities.DROWNED.get(), context ->
            infectedRenderer(context, "drowned_infected.png"));
    }

    private static EntityRenderer<Watcher> watcherRenderer(EntityRendererProvider.Context context) {
        ZombieRenderer renderer = new ZombieRenderer(context) {
            @Override
            public ResourceLocation getTextureLocation(Zombie entity) {
                return new ResourceLocation(PosledniyDen.MODID, "textures/entity/watcher.png");
            }
        };
        @SuppressWarnings("unchecked")
        EntityRenderer<Watcher> result = (EntityRenderer<Watcher>) (EntityRenderer<?>) renderer;
        return result;
    }

    private static EntityRenderer<Infected> infectedRenderer(
        EntityRendererProvider.Context context, String textureName
    ) {
        ZombieRenderer renderer = new ZombieRenderer(context) {
            @Override
            public ResourceLocation getTextureLocation(Zombie entity) {
                return new ResourceLocation(PosledniyDen.MODID, "textures/entity/" + textureName);
            }
        };
        @SuppressWarnings("unchecked")
        EntityRenderer<Infected> result = (EntityRenderer<Infected>) (EntityRenderer<?>) renderer;
        return result;
    }
}
