package moguns;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;

import moguns.client.gun.model.ScarModel;
import moguns.core.init.ItemInit;
import moguns.core.init.SoundInit;
import moguns.itemgroups.MoGunsGroup;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("moguns")
public class MoGuns {
	
	public static final String MOD_ID = "moguns";
	
	//Creative Tab
	public static final ItemGroup GROUP = new MoGunsGroup();
	
	public MoGuns() {
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
		
		MinecraftForge.EVENT_BUS.register(this);
		
		//Registers all of the Deferred Registers from our init classes.
		ItemInit.ITEMS.register(bus);
		SoundInit.SOUNDS.register(bus);
		
		bus.addListener(this::onClientSetup);
		
	}
	
	//This is the common setup event, it doesn't do much for this addon.
	private void setup(final FMLCommonSetupEvent event) {
			
		System.out.println("MoGuns preinit, if you're reading this then I hope you're having a nice day :)");
			
	}
	
	//This is the client setup event.
	private void onClientSetup(FMLClientSetupEvent event) {

		//Register all of our models.
		ModelOverrides.register(ItemInit.SCAR.get(), new ScarModel());
	        
	}

}