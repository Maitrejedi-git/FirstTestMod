package org.maitrejedi.firsttestmod.content.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GlassBlockPlacerItem extends Item {

    private boolean isActivated = false;

    public GlassBlockPlacerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if(!pLevel.isClientSide) {
            this.isActivated = !this.isActivated;
        }
        return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pHand), pLevel.isClientSide());
    }

    // Works, but too slow
    /*@Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pEntity instanceof Player) {
            if(isActivated) {
                BlockPos bPos = pEntity.blockPosition().below();
                if(!pLevel.isClientSide) {
                    if(allowedBlocks.contains(pLevel.getBlockState(bPos).getBlock())) {
                        pLevel.setBlockAndUpdate(bPos, Blocks.GLASS.defaultBlockState());
                    }
                }
            }
        }
    }*/

    public boolean isActivated() {
        return isActivated;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return isActivated;
    }
}
