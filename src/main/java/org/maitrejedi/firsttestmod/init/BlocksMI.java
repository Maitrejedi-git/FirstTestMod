package org.maitrejedi.firsttestmod.init;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.maitrejedi.firsttestmod.content.blocks.RepairStationBlock;
import org.maitrejedi.firsttestmod.content.blocks.entities.ModBlockEntities;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface BlocksMI
{

    @RegistryName("repair_station_block")
    RepairStationBlock REPAIR_STATION_BLOCK = new RepairStationBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion());
}