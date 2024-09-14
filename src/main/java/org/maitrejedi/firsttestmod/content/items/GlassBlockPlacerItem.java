package org.maitrejedi.firsttestmod.content.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;

public class GlassBlockPlacerItem extends Item {

    public GlassBlockPlacerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        BlockPos bPos = pPlayer.getBlockPosBelowThatAffectsMyMovement();
        if(!pLevel.isClientSide) {
            if(
                    pLevel.getBlockState(bPos).isAir() ||
                    pLevel.getBlockState(bPos).getBlock().equals(Blocks.WATER) ||
                    pLevel.getBlockState(bPos).getBlock().equals(Blocks.LAVA)
            ) {
                pLevel.setBlock(bPos, Blocks.GLASS.defaultBlockState(), 3);
            }
        }
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }
}
