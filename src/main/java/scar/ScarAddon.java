package scar;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import scar.core.init.ItemInit;
import scar.core.init.SoundInit;

@Mod("scar")
public class ScarAddon {
	
	public static final String MOD_ID = "scar";
	
	public ScarAddon() {
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
		
		MinecraftForge.EVENT_BUS.register(this);
		
		//Registers all of the Deferred Registers from our init classes.
		ItemInit.ITEMS.register(bus);
		SoundInit.SOUNDS.register(bus);
		
	}
	
	//This is the common setup event, it doesn't do much for this addon.
	private void setup(final FMLCommonSetupEvent event) {
			
		System.out.println("SCAR Addon preinit, if you're reading this then I hope you're having a nice day :)");
			
	}

}
