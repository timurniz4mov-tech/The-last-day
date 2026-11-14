package ru.timur.apocalypse.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import ru.timur.apocalypse.registry.ModEffects;

/** A usable medkit: removes infection and restores four hearts. */
public class FieldMedkitItem extends Item {
    public FieldMedkitItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean needsTreatment = player.hasEffect(ModEffects.INFECTION.get())
            || player.getHealth() < player.getMaxHealth();

        if (!needsTreatment) {
            return InteractionResultHolder.pass(stack);
        }

        if (!level.isClientSide) {
            player.removeEffect(ModEffects.INFECTION.get());
            player.heal(8.0F);
            player.getCooldowns().addCooldown(this, 20);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            player.displayClientMessage(
                Component.literal("§aВы обработали раны и остановили заражение."), true
            );
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
