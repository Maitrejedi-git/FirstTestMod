package org.maitrejedi.firsttestmod;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.maitrejedi.firsttestmod.content.items.GlassBlockPlacerItem;
import org.maitrejedi.firsttestmod.init.ItemsMI;
import org.slf4j.Logger;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.core.init.ItemsHL;
import org.zeith.hammerlib.proxy.HLConstants;

import java.util.List;

@Mod(FirstTestMod.MOD_ID)
public class FirstTestMod
{
	public static final String MOD_ID = "firsttestmod";
	public static final Logger LOGGER = LogUtils.getLogger();
	private static final List<Block> allowedBlocks = List.of(Blocks.LAVA, Blocks.WATER, Blocks.AIR);

	public FirstTestMod(IEventBus modBus) {
		NeoForge.EVENT_BUS.addListener(FirstTestMod::placeGlassUnderPlayer);
		LanguageAdapter.registerMod(MOD_ID);
	}

	/*@CreativeTab.RegisterTab
	public static final CreativeTab MOD_TAB = new CreativeTab(id("root"),
			builder -> builder
					.icon(() -> ItemsHL.COPPER_GEAR.getDefaultInstance())
					.withTabsBefore(HLConstants.HL_TAB.id())
	);*/
	
	/*public static ResourceLocation id(String path)
	{
		return new ResourceLocation(MOD_ID, path);
	}*/

	private static void placeGlassUnderPlayer(MovementInputUpdateEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = event.getEntity();
			Inventory inventory = player.getInventory();
			var item = ItemsMI.GLASS_BLOCK_PLACER;
			if(inventory.contains(new ItemStack(item))) {
				GlassBlockPlacerItem modItem = (GlassBlockPlacerItem) inventory.getItem(inventory.findSlotMatchingItem(new ItemStack(item))).getItem();
				if(modItem.isActivated()) {
					BlockPos bPos = player.blockPosition().below();
					if(allowedBlocks.contains(player.level().getBlockState(bPos).getBlock())) {
						player.level().setBlockAndUpdate(bPos, Blocks.GLASS.defaultBlockState());
					}
				}
			}
		}
	}
}