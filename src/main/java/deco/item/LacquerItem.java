package deco.item;

import java.util.Optional;

import deco.recipe.HandRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LacquerItem extends Item {

	public LacquerItem(Settings settings) {
		
		super(settings);
		
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		
		if (!world.isClient()) {
            // For the sake of simplicity we draw the items off of the player's hands and create an inventory from that.
            // Usually you use an inventory of yours instead.
            SimpleInventory inventory = new SimpleInventory(player.getMainHandStack(), player.getOffHandStack());
            Optional<HandRecipe> match = world.getRecipeManager().getFirstMatch(HandRecipe.Type.INSTANCE, inventory, world);

            if (match.isPresent()) {
                // Give the player the item and remove from what he has. Make sure to copy the ItemStack to not ruin it!
                player.getInventory().offerOrDrop(match.get().getOutput().copy());
                player.getMainHandStack().decrement(1);
                player.getOffHandStack().decrement(1);
            } else {
                // If it doesn't match we tell the player
                player.sendMessage(new TranslatableText("deco.nomatch"), true);
            }
        }
		
		return TypedActionResult.pass(player.getStackInHand(hand));
		
	}

}