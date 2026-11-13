package ru.timur.apocalypse.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class Watcher extends Zombie {
    public Watcher(EntityType<? extends Zombie> type, Level level) { super(type, level); }
    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes()
            .add(Attributes.MAX_HEALTH, 42.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.31D)
            .add(Attributes.ATTACK_DAMAGE, 7.0D)
            .add(Attributes.FOLLOW_RANGE, 40.0D);
    }
    @Override public boolean isSunSensitive() { return false; }
}
