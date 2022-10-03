package moguns.client;

import com.mrcrayfish.guns.client.render.entity.ProjectileRenderer;

import moguns.MoGuns;
import moguns.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoGuns.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoGunsClient {
	
	/**
	 * Registers the default CGM bullet renderer to the Taki entity
	 */
	@SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		
        event.registerEntityRenderer(EntityInit.TAKI.get(), ProjectileRenderer::new);
        
    }

}