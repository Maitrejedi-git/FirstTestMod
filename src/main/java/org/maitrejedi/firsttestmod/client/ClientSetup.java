package org.maitrejedi.firsttestmod.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.maitrejedi.firsttestmod.FirstTestMod;
import org.maitrejedi.firsttestmod.content.blocks.entities.renderers.ProjectileLauncherOnRepairStationRenderer;
import org.maitrejedi.firsttestmod.content.blocks.entities.ModBlockEntities;
import org.maitrejedi.firsttestmod.content.entities.projectiles.ModEntities;
import org.maitrejedi.firsttestmod.init.BlocksMI;

@EventBusSubscriber(modid = FirstTestMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.RED_DOT.get(), ThrownItemRenderer::new);
        //BlockEntityRenderers.register(ModBlockEntities.REPAIR_STATION_BLOCK_ENTITY.get(), ProjectileLauncherOnRepairStationRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(BlocksMI.REPAIR_STATION_BLOCK, RenderType.cutout());
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.REPAIR_STATION_BLOCK_ENTITY.get(), ProjectileLauncherOnRepairStationRenderer::new);
    }
}
