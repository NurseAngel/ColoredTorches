package nurseangel.ColoredTorches;

import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import nurseangel.ColoredTorches.proxy.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ColoredTorches {
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	/** ブロックID */
	public static int blockIdColoredTorch,blockIdColoredTorch1,blockIdColoredTorch2,blockIdColoredTorch3,blockIdColoredTorch4,blockIdColoredTorch5;
	/** 色トーチブロック */
	public static BlockColoredTorch blockColoredTorch;
	public static int coloredTorchRenderType;

	/**
	 * コンストラクタ的なもの
	 *
	 * @param event
	 */
	@Mod.PreInit
	public void myPreInitMethod(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

		try {
			cfg.load();
			int blockIdStart = 3000;

			// blockid
			blockIdColoredTorch = cfg.getBlock("Colored Torch", blockIdStart++).getInt();
			blockIdColoredTorch1 = cfg.getBlock("Colored Torch 1", blockIdStart++).getInt();
			blockIdColoredTorch2 = cfg.getBlock("Colored Torch 2", blockIdStart++).getInt();
			blockIdColoredTorch3 = cfg.getBlock("Colored Torch 3", blockIdStart++).getInt();
			blockIdColoredTorch4 = cfg.getBlock("Colored Torch 4", blockIdStart++).getInt();
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " loadding configuration failure");
		} finally {
			cfg.save();
		}
	}

	/**
	 * load()
	 *
	 * @param event
	 */
	@Mod.Init
	public void myInitMethod(FMLInitializationEvent event) {

		// トーチ
		if (blockIdColoredTorch > 1) {

			coloredTorchRenderType = proxy.getUniqueRenderType();

			proxy.registerTextures();
			proxy.registerRenderers();

			setColoredTorch();
		}
	}

	/**
	 * 色トーチを追加
	 *
	 */
	private void setColoredTorch() {
		blockColoredTorch = new BlockColoredTorch(blockIdColoredTorch, 0);
		blockColoredTorch.setBlockName("blockFigurePlus");

		GameRegistry.registerBlock(blockColoredTorch, ItemColoredTorch.class, "blockFigurePlus");

		for (int i = 0; i < 15; i++) {
			LanguageRegistry.addName(new ItemStack(blockColoredTorch, 1, i), "color torch:" + i);
		}
		BlockColoredTorch blockColoredTorch1 = new BlockColoredTorch(blockIdColoredTorch1, 1);
		BlockColoredTorch blockColoredTorch2 = new BlockColoredTorch(blockIdColoredTorch2, 2);
		BlockColoredTorch blockColoredTorch3 = new BlockColoredTorch(blockIdColoredTorch3, 3);
		BlockColoredTorch blockColoredTorch4 = new BlockColoredTorch(blockIdColoredTorch4, 4);
	}

}