package org.maitrejedi.firsttestmod.content.blocks.entities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.maitrejedi.firsttestmod.content.blocks.entities.RepairStationBlockEntity;

public class ProjectileLauncherOnRepairStationRenderer implements BlockEntityRenderer<RepairStationBlockEntity> {

    public ProjectileLauncherOnRepairStationRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(RepairStationBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getItemStack();
        //System.out.println("ItemStack in block entity: " + pBlockEntity.getItemStack());

        if (!itemStack.isEmpty()) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.5D, 0.76D, 0.5D);
            pPoseStack.scale(0.5F, 0.5F, 0.5F);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

            itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, pBlockEntity.getLevel(), 0);
            pPoseStack.popPose();
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
