package nurseangel.ColoredTorches;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemColoredTorch extends ItemBlock {

	public ItemColoredTorch(int par1) {
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
        setTextureFile(Reference.TEXTURE_FILE);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		return (new StringBuilder()).append(getItemName()).append(i).toString();
	}
	@Override
	public String getLocalItemName(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		return (new StringBuilder()).append(getItemName()).append(i).toString();
	}

}
