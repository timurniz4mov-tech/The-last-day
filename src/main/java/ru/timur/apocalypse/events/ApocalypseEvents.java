
package ru.timur.apocalypse.events;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timur.apocalypse.PosledniyDen;
import ru.timur.apocalypse.entity.Watcher;
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

        if (day >= 3 && level.getGameTime() - lastSpawnTick > 900) {
            lastSpawnTick = level.getGameTime();
            level.getPlayers(player -> player.isAlive() && !player.isSpectator()).forEach(player -> {
                if (level.random.nextInt(4) != 0) return;
                BlockPos pos = player.blockPosition().offset(level.random.nextInt(25)-12, 0, level.random.nextInt(25)-12);
                pos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos);
                if (level.isEmptyBlock(pos) && level.getMaxLocalRawBrightness(pos) < 8) {
                    Watcher watcher = ModEntities.WATCHER.get().create(level);
                    if (watcher != null) {
                        watcher.moveTo(pos, level.random.nextFloat()*360F, 0);
                        watcher.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.EVENT, null, null);
                        level.addFreshEntity(watcher);
                    }
                }
            });
        }
    }
}
