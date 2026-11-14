package ru.timur.apocalypse.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

/** Shared zombie combat AI for the five infected variants. */
public class Infected extends Zombie {
    public Infected(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes(
        double health, double speed, double damage, double followRange
    ) {
        return Zombie.createAttributes()
            .add(Attributes.MAX_HEALTH, health)
            .add(Attributes.MOVEMENT_SPEED, speed)
            .add(Attributes.ATTACK_DAMAGE, damage)
            .add(Attributes.FOLLOW_RANGE, followRange);
    }

    @Override
    public boolean isSunSensitive() {
        return false;
    }
}
