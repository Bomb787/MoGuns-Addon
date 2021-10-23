package deco.init;

import deco.PizzaGummiRat;
import deco.item.LacquerItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
	
	//Brass
	public static final Item BRASS_INGOT = register(new Item(new Item.Settings().group(PizzaGummiRat.Deco)), "brass_ingot");
	public static final Item BRASS_NUGGET = register(new Item(new Item.Settings().group(PizzaGummiRat.Deco)), "brass_nugget");
	
	//Silver
	public static final Item SILVER_INGOT = register(new Item(new Item.Settings().group(PizzaGummiRat.Deco)), "silver_ingot");
	public static final Item SILVER_NUGGET = register(new Item(new Item.Settings().group(PizzaGummiRat.Deco)), "silver_nugget");
	public static final Item RAW_SILVER = register(new Item(new Item.Settings().group(PizzaGummiRat.Deco)), "raw_silver");
	
	//Zinc
	public static final Item ZINC_INGOT = register(new Item(new Item.Settings().group(PizzaGummiRat.Deco)), "zinc_ingot");
	public static final Item ZINC_NUGGET = register(new Item(new Item.Settings().group(PizzaGummiRat.Deco)), "zinc_nugget");
	public static final Item RAW_ZINC = register(new Item(new Item.Settings().group(PizzaGummiRat.Deco)), "raw_zinc");
	
	public static final LacquerItem LACQUER = register(new LacquerItem(new Item.Settings().group(PizzaGummiRat.Deco)), "lacquer");
	
	static <T extends Item> T register(T item, String id) {
		Registry.register(Registry.ITEM, new Identifier(PizzaGummiRat.MOD_ID, id), item);
		return item;
	}

}
