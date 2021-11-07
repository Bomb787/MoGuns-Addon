package moguns.events;

import java.util.Random;

import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.client.handler.RecoilHandler;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.event.GunFireEvent.Post;
import com.mrcrayfish.guns.event.GunFireEvent.Pre;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.util.GunModifierHelper;

import moguns.MoGuns;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 * This class adds functionality for horizontal recoil.
 * Would not have been possible without referencing Mr. Pineapple's code.
 * Author: Bomb787
 */
@Mod.EventBusSubscriber(modid = MoGuns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RecoilShootingEvent {
	
	//Our variable, currently null but will be assigned either a 1 or 0 every time a gun is shot.
	public static int recoilRand;
		
	//This method is called every time a gun is shot.
	@SubscribeEvent
	public static void preShoot(Pre event) {
		if(!(event.getStack().getItem() instanceof GunItem))
			return;
	    recoilRand = new Random().nextInt(2);

	}
		
	//These two variables determine the amount and progress of the recoil.
	protected static float cameraRecoil;
	protected static float progressCameraRecoil;
		
	@SubscribeEvent
	public static void recoil(Post event) {
			
		ItemStack heldItem = event.getStack();
		GunItem gunItem = (GunItem) heldItem.getItem();
	    Gun modifiedGun = gunItem.getModifiedGun(heldItem);
	    float recoilModifier = 1.0F - GunModifierHelper.getRecoilModifier(heldItem);
	    recoilModifier *= RecoilHandler.get().getAdsRecoilReduction(modifiedGun);
	    cameraRecoil = modifiedGun.getGeneral().getRecoilAngle() * recoilModifier;
	    progressCameraRecoil = 0F;
	        
	}
		
	/*
	This method is called multiple times after a gun is shot to smoothly move the recoil horizontally.
	The recoilRand variable determines whether it moves left or right.
	*/
	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {
	    	
	    if(!Config.SERVER.enableCameraRecoil.get())
	        return;

	    if(event.phase != TickEvent.Phase.END || cameraRecoil <= 0)
	        return;

	    Minecraft mc = Minecraft.getInstance();
	    if(mc.player == null)
	        return;

	    float recoilAmount = cameraRecoil * mc.getTickLength() * 0.1F;
	    float startProgress = progressCameraRecoil / cameraRecoil;
	    float endProgress = (progressCameraRecoil + recoilAmount) / cameraRecoil;

	    if(startProgress < 0.2F) {
	        	
	        if(recoilRand == 1)
	        	mc.player.rotationYaw -= ((endProgress - startProgress) / 0.2F) * cameraRecoil/2;
	        else
	        	mc.player.rotationYaw -= ((endProgress - startProgress) / 0.2F) * -cameraRecoil/2;
	            
	    }
	    else {
	        	
	        if(recoilRand == 1)
	        	mc.player.rotationYaw -= ((endProgress - startProgress) / 0.8F) * -cameraRecoil/2;
	        else
	        	mc.player.rotationYaw -= ((endProgress - startProgress) / 0.8F) * cameraRecoil/2;
	            
	    }

	    progressCameraRecoil += recoilAmount;

	    if(progressCameraRecoil >= cameraRecoil) {
	        	
	        cameraRecoil = 0;
	        progressCameraRecoil = 0;
	            
	    }
	        
	}

}