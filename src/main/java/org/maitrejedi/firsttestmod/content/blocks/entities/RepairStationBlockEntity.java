package org.maitrejedi.firsttestmod.content.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.maitrejedi.firsttestmod.content.items.ProjectileLauncherItem;
import org.maitrejedi.firsttestmod.util.TickableBlockEntity;

public class RepairStationBlockEntity extends BlockEntity implements TickableBlockEntity {
    private ItemStack itemStack;

    public RepairStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.REPAIR_STATION_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.itemStack = ItemStack.EMPTY;
    }

    public boolean hasItem() {
        return !itemStack.isEmpty();
    }

    public void insertItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        setChanged();
        if (level != null && !this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    public ItemStack extractItem() {
        ItemStack itemStack = this.itemStack;
        this.itemStack = ItemStack.EMPTY;
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
        return itemStack;
    }

    public void tick() {
        if (level != null && !level.isClientSide) {
            if (level.isDay() && !level.isRaining() && level.canSeeSky(getBlockPos())) {
                if (!itemStack.isEmpty() && itemStack.getDamageValue() > 0 && itemStack.getItem() instanceof ProjectileLauncherItem) {
                    //System.out.println("ItemStack in block entity: " + this.itemStack);
                    itemStack.setDamageValue(itemStack.getDamageValue() - 1);
                    setChanged();
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }
        }
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        if (pTag.contains("Item")) {
            this.itemStack = ItemStack.parseOptional(pRegistries, pTag.getCompound("Item"));
        } else {
            this.itemStack = ItemStack.EMPTY;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        if (!this.itemStack.isEmpty()) {
            CompoundTag itemTag = new CompoundTag();
            this.itemStack.save(pRegistries, itemTag);
            pTag.put("Item", itemTag);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag tag = super.getUpdateTag(pRegistries);
        if (!this.itemStack.isEmpty()) {
            tag.put("Item", this.itemStack.save(pRegistries, new CompoundTag()));
        }
        return tag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        this.loadAdditional(pkt.getTag(), lookupProvider);
        BlockState state =  this.level.getBlockState(this.worldPosition);
        this.handleUpdateTag(pkt.getTag(), lookupProvider);
        this.level.sendBlockUpdated(this.worldPosition, state, state, 3);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);
        this.itemStack = ItemStack.parseOptional(lookupProvider, tag.getCompound("Item"));
    }
}
