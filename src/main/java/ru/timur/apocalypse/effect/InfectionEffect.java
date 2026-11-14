package ru.timur.apocalypse.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/** A slow, survivable infection that gives the player time to find medicine. */
public class InfectionEffect extends MobEffect {
    public InfectionEffect() {
        super(MobEffectCategory.HARMFUL, 0x657A35);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Infection weakens the victim, but cannot finish them off by itself.
        if (entity.getHealth() > 1.0F) {
            entity.hurt(entity.damageSources().magic(), 0.5F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 100 == 0;
    }
}
