package nurseangel.ColoredTorches;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;

/**
 * coloredtorchのレンダリング<br />
 */
public class RenderColoredTorchHandler implements ISimpleBlockRenderingHandler {

	/**
	 * 3Dで表示するか<br />
	 * 3D表示がデフォルトみたい
	 */
	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return ColoredTorches.coloredTorchRenderType;
	}

	/**
	 * インベントリ
	 */
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if (modelID != this.getRenderId()) {
			return;
		}
	}

	/**
	 * 設置したときのレンダリング
	 */
	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (modelId != this.getRenderId()) {
			return false;
		}

		renderBlockTorch(renderer, block, x, y, z);
		return true;
	}

	/**
	 * トーチの炎部分のレンダ<br />
	 * metadataではなくblockIDをみるように
	 */
	public boolean renderBlockTorch(RenderBlocks renderer, Block par1Block, int x, int y, int z) {
		int var5 = par1Block.blockID;
		Tessellator var6 = Tessellator.instance;
		var6.setBrightness(par1Block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
		var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		double var7 = 0.4000000059604645D;
		double var9 = 0.5D - var7;
		double var11 = 0.20000000298023224D;

		if (var5 == ColoredTorches.blockIdColoredTorch1) {
			renderTorchAtAngle(renderer, par1Block, (double) x - var9, (double) y + var11, (double) z, -var7, 0.0D);
		} else if (var5 == ColoredTorches.blockIdColoredTorch2) {
			renderTorchAtAngle(renderer, par1Block, (double) x + var9, (double) y + var11, (double) z, var7, 0.0D);
		} else if (var5 == ColoredTorches.blockIdColoredTorch3) {
			renderTorchAtAngle(renderer, par1Block, (double) x, (double) y + var11, (double) z - var9, 0.0D, -var7);
		} else if (var5 == ColoredTorches.blockIdColoredTorch4) {
			renderTorchAtAngle(renderer, par1Block, (double) x, (double) y + var11, (double) z + var9, 0.0D, var7);
		} else {
			renderTorchAtAngle(renderer, par1Block, (double) x, (double) y, (double) z, 0.0D, 0.0D);
		}

		return true;
	}

	/**
	 * トーチの本体部分のレンダ<br />
	 * metadataではなくblockIDをみるように
	 */
	public void renderTorchAtAngle(RenderBlocks renderer, Block par1Block, double x, double y, double z, double par8, double par10) {
		Tessellator var12 = Tessellator.instance;
		int var13 = par1Block.getBlockTextureFromSide(0);

		if (renderer.overrideBlockTexture >= 0) {
			var13 = renderer.overrideBlockTexture;
		}
		/**
		 * TODO ここらへんを整理 <br />
		 * ・テクスチャ番号はメタデータから <br />
		 * ・方向はBlockIDから<br />
		 * としないといけない。
		 * <br />
		 * デフォだとvar13がメタデータ
		 */
		if (FMLCommonHandler.instance().getSide().isClient()) {
			FMLClientHandler.instance().getClient().thePlayer.addChatMessage("x:" + x + " y:" + y + " z:" + z + " overrideBlockTexture:" + var13);
		}

		int var14 = (var13 & 15) << 4;
		int var15 = var13 & 240;
		float var16 = (float) var14 / 256.0F;
		float var17 = ((float) var14 + 15.99F) / 256.0F;
		float var18 = (float) var15 / 256.0F;
		float var19 = ((float) var15 + 15.99F) / 256.0F;
		double var20 = (double) var16 + 0.02734375D;
		double var22 = (double) var18 + 0.0234375D;
		double var24 = (double) var16 + 0.03515625D;
		double var26 = (double) var18 + 0.03125D;
		double var28 = (double) var16 + 0.02734375D;
		double var30 = (double) var18 + 0.05078125D;
		double var32 = (double) var16 + 0.03515625D;
		double var34 = (double) var18 + 0.05859375D;
		x += 0.5D;
		z += 0.5D;
		double var36 = x - 0.5D;
		double var38 = x + 0.5D;
		double var40 = z - 0.5D;
		double var42 = z + 0.5D;
		double var44 = 0.0625D;
		double var46 = 0.625D;
		var12.addVertexWithUV(x + par8 * (1.0D - var46) - var44, y + var46, z + par10 * (1.0D - var46) - var44, var20, var22);
		var12.addVertexWithUV(x + par8 * (1.0D - var46) - var44, y + var46, z + par10 * (1.0D - var46) + var44, var20, var26);
		var12.addVertexWithUV(x + par8 * (1.0D - var46) + var44, y + var46, z + par10 * (1.0D - var46) + var44, var24, var26);
		var12.addVertexWithUV(x + par8 * (1.0D - var46) + var44, y + var46, z + par10 * (1.0D - var46) - var44, var24, var22);
		var12.addVertexWithUV(x + var44 + par8, y, z - var44 + par10, var32, var30);
		var12.addVertexWithUV(x + var44 + par8, y, z + var44 + par10, var32, var34);
		var12.addVertexWithUV(x - var44 + par8, y, z + var44 + par10, var28, var34);
		var12.addVertexWithUV(x - var44 + par8, y, z - var44 + par10, var28, var30);
		var12.addVertexWithUV(x - var44, y + 1.0D, var40, (double) var16, (double) var18);
		var12.addVertexWithUV(x - var44 + par8, y + 0.0D, var40 + par10, (double) var16, (double) var19);
		var12.addVertexWithUV(x - var44 + par8, y + 0.0D, var42 + par10, (double) var17, (double) var19);
		var12.addVertexWithUV(x - var44, y + 1.0D, var42, (double) var17, (double) var18);
		var12.addVertexWithUV(x + var44, y + 1.0D, var42, (double) var16, (double) var18);
		var12.addVertexWithUV(x + par8 + var44, y + 0.0D, var42 + par10, (double) var16, (double) var19);
		var12.addVertexWithUV(x + par8 + var44, y + 0.0D, var40 + par10, (double) var17, (double) var19);
		var12.addVertexWithUV(x + var44, y + 1.0D, var40, (double) var17, (double) var18);
		var12.addVertexWithUV(var36, y + 1.0D, z + var44, (double) var16, (double) var18);
		var12.addVertexWithUV(var36 + par8, y + 0.0D, z + var44 + par10, (double) var16, (double) var19);
		var12.addVertexWithUV(var38 + par8, y + 0.0D, z + var44 + par10, (double) var17, (double) var19);
		var12.addVertexWithUV(var38, y + 1.0D, z + var44, (double) var17, (double) var18);
		var12.addVertexWithUV(var38, y + 1.0D, z - var44, (double) var16, (double) var18);
		var12.addVertexWithUV(var38 + par8, y + 0.0D, z - var44 + par10, (double) var16, (double) var19);
		var12.addVertexWithUV(var36 + par8, y + 0.0D, z - var44 + par10, (double) var17, (double) var19);
		var12.addVertexWithUV(var36, y + 1.0D, z - var44, (double) var17, (double) var18);
	}

}
