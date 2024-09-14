package org.maitrejedi.firsttestmod;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.core.init.ItemsHL;
import org.zeith.hammerlib.proxy.HLConstants;

@Mod(FirstTestMod.MOD_ID)
public class FirstTestMod
{
	public static final String MOD_ID = "firsttestmod";
	public static final Logger LOGGER = LogUtils.getLogger();
	
	/*@CreativeTab.RegisterTab
	public static final CreativeTab MOD_TAB = new CreativeTab(id("root"),
			builder -> builder
					.icon(() -> ItemsHL.COPPER_GEAR.getDefaultInstance())
					.withTabsBefore(HLConstants.HL_TAB.id())
	);*/
	
	public FirstTestMod()
	{
		LanguageAdapter.registerMod(MOD_ID);
	}
	
	/*public static ResourceLocation id(String path)
	{
		return new ResourceLocation(MOD_ID, path);
	}*/
}