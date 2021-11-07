package moguns.events;

import org.lwjgl.glfw.GLFW;

import com.mrcrayfish.guns.event.GunFireEvent.Post;
import com.mrcrayfish.guns.event.GunFireEvent.Pre;

import moguns.MoGuns;
import moguns.items.BurstGunItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoGuns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShootingEvent {
	
	@SubscribeEvent
    public static void onKeyPressed(InputEvent.RawMouseEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        if(player == null)
            return;

        ItemStack heldItem = player.getHeldItemMainhand();
        if(heldItem.getItem() instanceof BurstGunItem) {
        	
            int BUTTON = event.getButton();
            if(event.getAction() == GLFW.GLFW_PRESS && BUTTON == GLFW.GLFW_MOUSE_BUTTON_LEFT)
                BURST = 0;
            
        }
    }
	
	public static int BURST = 0;
	
    @SubscribeEvent
    public static void preShoot(Pre event) {
        if(!(event.getStack().getItem() instanceof BurstGunItem))
            return;
        
        if(BURST > 5)
            event.setCanceled(true);

    }
    
    @SubscribeEvent
    public static void postShoot(Post event) {
    	
    	if(BURST <= 5)
            ++BURST;
    	
    }

}