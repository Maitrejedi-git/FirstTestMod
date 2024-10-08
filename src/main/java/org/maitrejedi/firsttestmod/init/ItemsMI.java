package org.maitrejedi.firsttestmod.init;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.maitrejedi.firsttestmod.content.items.GlassBlockPlacerItem;
import org.maitrejedi.firsttestmod.content.items.ProjectileLauncherItem;
import org.maitrejedi.firsttestmod.content.items.RedDotItem;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.maitrejedi.firsttestmod.FirstTestMod;

@SimplyRegister
public interface ItemsMI
{
	/*@RegistryName("test")
	Item TEST_ITEM = FirstTestMod.MOD_TAB.add(new Item(new Item.Properties())
	{
		@Override
		public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
		{
			System.out.println(player.getItemInHand(hand));
			return InteractionResultHolder.success(player.getItemInHand(hand));
		}
	});*/

	@RegistryName("glass_block_placer")
	GlassBlockPlacerItem GLASS_BLOCK_PLACER = new GlassBlockPlacerItem(new Item.Properties().stacksTo(1));
	@RegistryName("projectile_launcher")
	ProjectileLauncherItem PROJECTILE_LAUNCHER = new ProjectileLauncherItem(new Item.Properties().durability(100));
	@RegistryName("red_dot")
	RedDotItem RED_DOT = new RedDotItem(new Item.Properties());
}