package org.maitrejedi.firsttestmod.content.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.maitrejedi.firsttestmod.content.entities.projectiles.RedDotProjectile;

public class ProjectileLauncherItem extends Item {

    public ProjectileLauncherItem(Properties pProperties) {
        super(pProperties.durability(100));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound(
                null,
                pPlayer.getX(),
                pPlayer.getY(),
                pPlayer.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if (!pLevel.isClientSide) {
            RedDotProjectile redDot = new RedDotProjectile(pLevel, pPlayer);
            redDot.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(redDot);
            //itemstack.hurtAndBreak(1, pPlayer, LivingEntity.getSlotForHand(pHand));
            itemstack.hurtAndBreak(1, (ServerLevel) pLevel, pPlayer, (entity) -> {
                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                        SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
                pPlayer.swing(pHand, true);
            });
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
