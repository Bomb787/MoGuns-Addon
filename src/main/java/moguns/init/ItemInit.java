package moguns.init;

import com.mrcrayfish.guns.item.AmmoItem;
import com.mrcrayfish.guns.item.GunItem;

import moguns.MoGuns;
import moguns.items.BurstGunItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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
			MoGuns.MOD_ID);
	
	//Gun Items
	public static final RegistryObject<GunItem> SCARL = ITEMS.register("scarl", 
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));
	
	public static final RegistryObject<BurstGunItem> G36C = ITEMS.register("g36c", 
			() -> new BurstGunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));
	
	//Ammo Items
	public static final RegistryObject<Item> AMMO762X51 = ITEMS.register("762x51",
			() -> new AmmoItem(new Item.Properties().group(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO556X45 = ITEMS.register("556x45",
			() -> new AmmoItem(new Item.Properties().group(MoGuns.GROUP)));

}