package deco.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class HandRecipe implements Recipe<Inventory> {
	
	private final Ingredient inputA;
	private final Ingredient inputB;
	private final ItemStack output;
	private final Identifier id;
	
	public HandRecipe(Ingredient inputA, Ingredient inputB, ItemStack output, Identifier id) {
		
        this.inputA = inputA;
        this.inputB = inputB;
        this.output = output;
        this.id = id;
        
    }

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean matches(Inventory inventory, World world) {
		
		if(inventory.size() < 2)
			return false;
		return this.inputA.equals(inventory.getStack(0).getItem()) && this.inputB.equals(inventory.getStack(1).getItem());	
		
	}
	
	public static class Type implements RecipeType<HandRecipe> {
        
        private Type() {
        }
        public static final Type INSTANCE = new Type();
        
        public static final String ID = "two_slot_recipe";
        
    }
	
    class HandRecipeJsonFormat {
        JsonObject inputA;
        JsonObject inputB;
        String outputItem;
        int outputAmount;
    }

	@Override
	public ItemStack craft(Inventory inventory) {
		
		return ItemStack.EMPTY;
		
	}

	@Override
	public boolean fits(int width, int height) {
		
		return false;
		
	}

	@Override
	public ItemStack getOutput() {
		
		return this.output;
		
	}

	@Override
	public Identifier getId() {
		
		return this.id;
		
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		
		return HandRecipeSerializer.INSTANCE;
		
	}

	@Override
	public RecipeType<?> getType() {
		
		return Type.INSTANCE;
		
	}
	
	public Ingredient getInputA() {
		
        return inputA;
        
    }

    public Ingredient getInputB() {
    	
        return inputB;
        
    }

}