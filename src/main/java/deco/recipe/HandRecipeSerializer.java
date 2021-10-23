package deco.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import deco.recipe.HandRecipe.HandRecipeJsonFormat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HandRecipeSerializer implements RecipeSerializer<HandRecipe> {
	
	public static final Identifier ID = new Identifier("deco:hand_recipe");

	@Override
	public HandRecipe read(Identifier id, JsonObject json) {
		
		HandRecipeJsonFormat recipeJson = new Gson().fromJson(json, HandRecipeJsonFormat.class);
		
		// Validate all fields are there
		if (recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.outputItem == null)
			throw new JsonSyntaxException("A required attribute is missing!");
		// We'll allow to not specify the output, and default it to 1.
		if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;
		
		Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
        Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
        
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
        		// Validate the inputed item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);
        
        return new HandRecipe(inputA, inputB, output, id);	
		
	}

	@Override
	public HandRecipe read(Identifier id, PacketByteBuf buf) {

		Ingredient inputA = Ingredient.fromPacket(buf);
        Ingredient inputB = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();
        return new HandRecipe(inputA, inputB, output, id);
		
	}
	
	private HandRecipeSerializer() {
	}
	
	public static final HandRecipeSerializer INSTANCE = new HandRecipeSerializer();

	@Override
	public void write(PacketByteBuf buf, HandRecipe recipe) {

		recipe.getInputA().write(buf);
		recipe.getInputB().write(buf);
		buf.writeItemStack(recipe.getOutput());
		
	}

}