package org.maitrejedi.firsttestmod.content.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.maitrejedi.firsttestmod.content.blocks.entities.RepairStationBlockEntity;
import org.maitrejedi.firsttestmod.content.items.ProjectileLauncherItem;
import org.maitrejedi.firsttestmod.util.TickableBlockEntity;

public class RepairStationBlock extends BaseEntityBlock {
    public static final BooleanProperty HAS_ITEM = BlockStateProperties.POWERED;
    public static final VoxelShape SHAPE = Block.box(0, 0,0, 16, 12, 16);

    public RepairStationBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(HAS_ITEM, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_ITEM);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(RepairStationBlock::new);
    }


    @Override
    public ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            RepairStationBlockEntity repairStationBlockEntity = (RepairStationBlockEntity) blockEntity;
            ItemStack heldItem = pPlayer.getItemInHand(pHand);

            if (repairStationBlockEntity.hasItem()) {
                ItemStack extractedItem = repairStationBlockEntity.extractItem();
                pPlayer.addItem(extractedItem);
                pLevel.setBlock(pPos, pState.setValue(HAS_ITEM, false), 3);
            } else if (!heldItem.isEmpty() && heldItem.getItem() instanceof ProjectileLauncherItem) {
                repairStationBlockEntity.insertItem(heldItem.split(1));
                pLevel.setBlock(pPos, pState.setValue(HAS_ITEM, true), 3);
            }
        }
        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new RepairStationBlockEntity(pPos, pState);
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pType) {
        return TickableBlockEntity.getTicker(pLevel);
    }
}
