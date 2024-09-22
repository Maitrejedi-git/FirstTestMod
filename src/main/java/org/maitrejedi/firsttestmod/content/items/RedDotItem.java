package org.maitrejedi.firsttestmod.content.items;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.maitrejedi.firsttestmod.content.entities.projectiles.RedDotProjectile;

public class RedDotItem extends Item implements ProjectileItem {

    public RedDotItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Projectile asProjectile(Level pLevel, Position pPos, ItemStack pStack, Direction pDirection) {
        RedDotProjectile redDot = new RedDotProjectile(pLevel, pPos.x(), pPos.y(), pPos.z());
        redDot.setItem(pStack);
        return redDot;
    }
}
