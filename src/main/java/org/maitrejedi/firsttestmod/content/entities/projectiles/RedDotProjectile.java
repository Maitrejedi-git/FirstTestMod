package org.maitrejedi.firsttestmod.content.entities.projectiles;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.maitrejedi.firsttestmod.init.ItemsMI;

public class RedDotProjectile extends ThrowableItemProjectile {

    public RedDotProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RedDotProjectile(Level pLevel, double pX, double pY, double pZ) {
        super(ModEntities.RED_DOT.get(), pX, pY, pZ, pLevel);
    }

    public RedDotProjectile(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.RED_DOT.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemsMI.RED_DOT;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
