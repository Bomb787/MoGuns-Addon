package deco;

import deco.init.BlockInit;
import deco.init.ItemInit;
import deco.recipe.HandRecipe;
import deco.recipe.HandRecipeSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PizzaGummiRat implements ModInitializer {
	
	public static final String MOD_ID = "deco";
	
	public static ItemInit ITEMS;
	
	//Creative Tab
	public static final ItemGroup Deco = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "deco"), () -> new ItemStack(Items.POPPY));
	
	@Override
	public void onInitialize() {
		
		System.out.println("PizzaGummiRat initialization, if you're reading this then I hope you're having a nice day :)");
		
		BlockInit.init();
		ITEMS = new ItemInit();
		
		Registry.register(Registry.RECIPE_SERIALIZER, HandRecipeSerializer.ID, HandRecipeSerializer.INSTANCE);
		Registry.register(Registry.RECIPE_TYPE, new Identifier("deco", HandRecipe.Type.ID), HandRecipe.Type.INSTANCE);
		
	}

}