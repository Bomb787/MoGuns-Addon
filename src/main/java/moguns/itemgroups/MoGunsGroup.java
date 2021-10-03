package moguns.itemgroups;

import moguns.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/*
 * This is the creative tab for all of our mod's guns.
 * Author: Bomb787
 */
public class MoGunsGroup extends ItemGroup{
	
	public MoGunsGroup() {
		
		super("moguns");
		
	}
	
	/*
	 * We use this to tell the game what item to use as the icon for the tab.
	 * You can just use "return new ItemStack(Items.xxx);" replacing xxx with the item you want, instead of adding ".get()" at the end if you want to use a vanilla item.
	 */
	@Override
	public ItemStack createIcon() {
		
		//Gets the gun item, unneeded if you're not gonna use a gun.
		ItemStack stack = new ItemStack(ItemInit.SCAR.get());
		//Makes sure that the icon gun has full ammo so the durability bar doesn't show up.
		stack.getOrCreateTag().putInt("AmmoCount", ItemInit.SCAR.get().getGun().getGeneral().getMaxAmmo());
		//Returns the loaded gun icon.
        return stack;
		
	}
	
	/**
	 * Adds a search bar to our tab.
	 * You don't need this if you don't want a search bar.
	 */
	@Override
	public boolean hasSearchBar() {
		
		return true;
		
	}
	
	/**
	 * Sets the background image for our tab.
	 * You don't need this if you don't have a search bar.
	 */
	@Override
	public String getBackgroundImageName(){
		
		return ("item_search.png");
		
	}

}
