package nurseangel.ColoredTorches;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nurseangel.TargetMark.TargetMark;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * カラートーチ
 */
public class BlockColoredTorch extends BlockTorch {

	public int direction = 0;

	@Override
	public int getRenderType() {
		return ColoredTorches.coloredTorchRenderType;
	}

	/**
	 * コンストラクタ
	 *
	 * @param BlockID
	 */
	protected BlockColoredTorch(int par1, int direction) {
		super(par1, 0);
		this.direction = direction;

		setHardness(0.0F);
		setLightValue(0.9375F);
		setStepSound(soundWoodFootstep);
		setRequiresSelfNotify();

		setTextureFile(Reference.TEXTURE_FILE);
	}

	/**
	 * 使用するテクスチャ番号
	 *
	 * @param int 方向
	 * @param int メタデータ
	 */
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		return metadata;
	}

	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 15; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	/**
	 * 設置したときに呼ばれる<br />
	 *
	 * @param int X
	 * @param int Y
	 * @param int Z
	 * @param int side
	 * @param float
	 * @param float
	 * @param float
	 * @param int metadata
	 */
	@Override
	public int onBlockPlaced(World par1World, int x, int y, int z, int side, float par6, float par7, float par8, int metadata) {
		/*
		 * 設置時にメタデータではなくBlockIDを入れ替えるように
		 */
		int nowBlockID = par1World.getBlockId(x, y, z);

		int targetBlockID = nowBlockID;

		if (side == 1 && this.canPlaceTorchOn(par1World, x, y - 1, z)) {
		}

		if (side == 2 && par1World.isBlockSolidOnSide(x, y, z + 1, NORTH, true)) {
			targetBlockID = ColoredTorches.blockIdColoredTorch1;
		}

		if (side == 3 && par1World.isBlockSolidOnSide(x, y, z - 1, SOUTH, true)) {
			targetBlockID = ColoredTorches.blockIdColoredTorch2;
		}

		if (side == 4 && par1World.isBlockSolidOnSide(x + 1, y, z, WEST, true)) {
			targetBlockID = ColoredTorches.blockIdColoredTorch3;
		}

		if (side == 5 && par1World.isBlockSolidOnSide(x - 1, y, z, EAST, true)) {
			targetBlockID = ColoredTorches.blockIdColoredTorch4;
		}

		// 入れ替え
		if (nowBlockID != targetBlockID) {
			par1World.setBlockAndMetadataWithNotify(x, y, z, targetBlockID, metadata);
		}

		return metadata;
	}

	/**
	 * トーチを設置できる場所かを判定<br />
	 * BlockTorchのコピペ
	 */
	private boolean canPlaceTorchOn(World par1World, int par2, int par3, int par4) {
		if (par1World.doesBlockHaveSolidTopSurface(par2, par3, par4)) {
			return true;
		} else {
			int var5 = par1World.getBlockId(par2, par3, par4);
			return (Block.blocksList[var5] != null && Block.blocksList[var5].canPlaceTorchOnTop(par1World, par2, par3, par4));
		}
	}
}
