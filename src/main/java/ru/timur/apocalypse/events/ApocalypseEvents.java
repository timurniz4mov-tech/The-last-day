package ru.timur.apocalypse.events;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.entity.Infected;
import ru.timur.apocalypse.entity.Watcher;
import ru.timur.apocalypse.registry.ModEffects;
import ru.timur.apocalypse.registry.ModEntities;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = PosledniyDen.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ApocalypseEvents {
    private static final Set<Long> ANNOUNCED_WORLDS = new HashSet<>();
    private static long lastSpawnTick = 0L;

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !(event.level instanceof ServerLevel level)) return;
        if (level.dimension() != Level.OVERWORLD) return;

        long day = level.getDayTime() / 24000L;
        if (day >= 2 && ANNOUNCED_WORLDS.add(level.getSeed())) {
            level.getPlayers(p -> true).forEach(p ->
                p.sendSystemMessage(Component.literal("§4[ПОСЛЕДНИЙ ДЕНЬ] §cНебо потемнело. Что-то проснулось...")));
        }

        // Threats begin on day three. New infected types enter the world on day four.
        if (day < 3 || level.getGameTime() - lastSpawnTick <= 900) return;
        lastSpawnTick = level.getGameTime();

        level.getPlayers(player -> player.isAlive() && !player.isSpectator()).forEach(player -> {
            if (level.random.nextInt(4) != 0) return;

            int nearbyThreats = level.getEntitiesOfClass(
                Mob.class, player.getBoundingBox().inflate(32.0D),
                mob -> mob instanceof Watcher || mob instanceof Infected
            ).size();
            if (nearbyThreats >= 5) return;

            BlockPos pos = player.blockPosition().offset(
                level.random.nextInt(25) - 12, 0, level.random.nextInt(25) - 12
            );
            pos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos);
            if (!level.isEmptyBlock(pos) || level.getMaxLocalRawBrightness(pos) >= 8) return;

            EntityType<? extends Mob> type = chooseThreat(level, day);
            Mob mob = type.create(level);
            if (mob == null) return;

            mob.moveTo(pos, level.random.nextFloat() * 360.0F, 0.0F);
            mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.EVENT, null, null);
            level.addFreshEntity(mob);
        });
    }

    private static EntityType<? extends Mob> chooseThreat(ServerLevel level, long day) {
        if (day == 3) return ModEntities.WATCHER.get();

        int roll = level.random.nextInt(9);
        if (roll == 0) return ModEntities.WATCHER.get();
        if (roll <= 3) return ModEntities.WALKER.get();
        if (roll <= 5) return ModEntities.RUNNER.get();
        if (roll == 6) return ModEntities.BRUTE.get();
        if (roll == 7) return ModEntities.SPORELING.get();
        return ModEntities.DROWNED.get();
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Watcher)
            && !(event.getSource().getEntity() instanceof Infected)) {
            return;
        }

        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (player.getRandom().nextFloat() >= 0.40F) return;
        if (player.hasEffect(ModEffects.INFECTION.get())) return;

        player.addEffect(new MobEffectInstance(ModEffects.INFECTION.get(), 20 * 90, 0));
        player.sendSystemMessage(Component.literal(
            "§2[ЗАРАЖЕНИЕ] §cВас укусили! Найдите аптечку, пока инфекция не ослабила вас."
        ));
    }
}
