package org.maitrejedi.firsttestmod.content.blocks.entities;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.maitrejedi.firsttestmod.FirstTestMod;
import org.maitrejedi.firsttestmod.init.BlocksMI;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FirstTestMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RepairStationBlockEntity>> REPAIR_STATION_BLOCK_ENTITY = BLOCK_ENTITIES.register("repair_station_block_entity",
            () -> BlockEntityType.Builder.of(RepairStationBlockEntity::new, BlocksMI.REPAIR_STATION_BLOCK).build(null));
}