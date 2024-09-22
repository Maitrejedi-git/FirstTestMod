package org.maitrejedi.firsttestmod.content.entities.projectiles;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.maitrejedi.firsttestmod.FirstTestMod;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FirstTestMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<? extends ThrowableItemProjectile>> RED_DOT = ENTITY_TYPES.register("red_dot",
            () -> EntityType.Builder.<RedDotProjectile>of(RedDotProjectile::new, MobCategory.MISC)
            .sized(0.5f, 0.5f).build("red_dot"));
}
