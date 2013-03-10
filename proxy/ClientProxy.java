package nurseangel.ColoredTorches.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import nurseangel.ColoredTorches.Reference;
import nurseangel.ColoredTorches.RenderColoredTorchHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	/**
	 * テクスチャを登録。<br />
	 *
	 */
	@Override
	public void registerTextures() {
		MinecraftForgeClient.preloadTexture(Reference.TEXTURE_FILE);
	}

	/**
	 * レンダラを登録する。
	 */
	@Override
	public void registerRenderers() {
		RenderingRegistry.registerBlockHandler(new RenderColoredTorchHandler());
	}

	/**
	 * 一意のレンダリングタイプを取得する
	 */
	@Override
	public int getUniqueRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

}