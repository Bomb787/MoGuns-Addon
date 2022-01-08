package moguns.events;

import com.mrcrayfish.guns.event.GunFireEvent.Post;
import moguns.MoGuns;
import moguns.init.SoundInit;
import moguns.items.GarandGunItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoGuns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GarandPingEvent {
	
	@SubscribeEvent
    public static void postShoot(Post event) {
		
        PlayerEntity player = event.getPlayer();
        ItemStack heldItem = player.getHeldItemMainhand();
        CompoundNBT tag = heldItem.getTag();
    	
    	if(!(heldItem.getItem() instanceof GarandGunItem))
    		return;
    	
    	if(heldItem.getItem() instanceof GarandGunItem && tag != null) {
    		
    		int ammo = tag.getInt("AmmoCount");
    		
    		if(ammo == 1)
    			event.getPlayer().getEntityWorld().playSound(player, player.getPosition(), SoundInit.GARAND_PING.get(), SoundCategory.MASTER, 3.0F, 1.0F);
    		
    	}
    	
    }

}