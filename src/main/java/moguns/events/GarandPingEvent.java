package moguns.events;

import com.mrcrayfish.guns.event.GunFireEvent.Post;

import moguns.MoGuns;
import moguns.init.ItemInit;
import moguns.init.SoundInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Adds a ping sound when the Garand is empty
 */
@Mod.EventBusSubscriber(modid = MoGuns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GarandPingEvent {
	
	@SubscribeEvent
    public static void postShoot(Post event) {
		
        PlayerEntity player = event.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        CompoundNBT tag = heldItem.getTag();
    	
    	if(heldItem.getItem() == ItemInit.M1_GARAND.get() && tag != null) {
    		
    		if(tag.getInt("AmmoCount") == 1)
    			event.getPlayer().level.playSound(player, player.blockPosition(), SoundInit.GARAND_PING.get(), SoundCategory.MASTER, 3.0F, 1.0F);
    		
    	}
    	
    }

}