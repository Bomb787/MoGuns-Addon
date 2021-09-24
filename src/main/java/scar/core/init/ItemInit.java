package scar.core.init;

import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.item.GunItem;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import scar.ScarAddon;

/**
 * This class is where all of the mod's items are registered
 * Author: Bomb787
 */
public class ItemInit {
	
	/*
     * This creates a Deferred Register where all of the items will be registered
     * This is called and added to the event bus in the main mod file.
     */
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			ScarAddon.MOD_ID);
	
	//Gun Items
		public static final RegistryObject<GunItem> SCAR = ITEMS.register("scar", 
				() -> new GunItem(new Item.Properties().maxStackSize(1).group(GunMod.GROUP)));

}