package moguns;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;

import moguns.client.render.gun.model.AKMCustomModel;
import moguns.client.render.gun.model.AKMModel;
import moguns.client.render.gun.model.ASVALModel;
import moguns.client.render.gun.model.BenelliModel;
import moguns.client.render.gun.model.FamasModel;
import moguns.client.render.gun.model.G36CModel;
import moguns.client.render.gun.model.Glock17Model;
import moguns.client.render.gun.model.LanchesterModel;
import moguns.client.render.gun.model.M14EBRModel;
import moguns.client.render.gun.model.M14Model;
import moguns.client.render.gun.model.M1911Model;
import moguns.client.render.gun.model.M1GarandModel;
import moguns.client.render.gun.model.MossbergModel;
import moguns.client.render.gun.model.PPSH41Model;
import moguns.client.render.gun.model.ReflexSightModel;
import moguns.client.render.gun.model.Remington870Model;
import moguns.client.render.gun.model.ScarHModel;
import moguns.client.render.gun.model.ScarLModel;
import moguns.client.render.gun.model.ThompsonModel;
import moguns.client.render.gun.model.VSSVintorezModel;
import moguns.client.render.gun.model.WaltherPPKModel;
import moguns.client.render.gun.model.WelrodModel;
import moguns.init.ItemInit;
import moguns.init.SoundInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
	public static final ItemGroup GROUP = new ItemGroup(MOD_ID) {
		/*
		 * We use this to tell the game what item to use as the icon for the tab.
		 * You can just use "return new ItemStack(Items.xxx);" replacing xxx with the item you want, instead of adding ".get()" at the end if you want to use a vanilla item.
		 */
		@Override
		public ItemStack makeIcon() {
			
			//Gets the gun item, unneeded if you're not gonna use a gun.
			ItemStack stack = new ItemStack(ItemInit.SCAR_L.get());
			//Makes sure that the icon gun has full ammo so the durability bar doesn't show up.
			stack.getOrCreateTag().putInt("AmmoCount", ItemInit.SCAR_L.get().getGun().getGeneral().getMaxAmmo());
			//Returns the loaded gun icon.
	        return stack;
			
		}
    };
	
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
		System.out.println("Slava Ukraini! Heroiam Slava!");
			
	}
	
	//This is the client setup event.
	private void onClientSetup(FMLClientSetupEvent event) {
		
		//Register all of our models.
		ModelOverrides.register(ItemInit.SCAR_L.get(), new ScarLModel());
		ModelOverrides.register(ItemInit.G36C.get(), new G36CModel());
		ModelOverrides.register(ItemInit.AKM.get(), new AKMModel());
		ModelOverrides.register(ItemInit.AS_VAL.get(), new ASVALModel());
		ModelOverrides.register(ItemInit.FAMAS.get(), new FamasModel());
		ModelOverrides.register(ItemInit.M1_GARAND.get(), new M1GarandModel());
		ModelOverrides.register(ItemInit.REFLEX_SIGHT.get(), new ReflexSightModel());
		ModelOverrides.register(ItemInit.THOMPSON.get(), new ThompsonModel());
		ModelOverrides.register(ItemInit.AKM_CUSTOM.get(), new AKMCustomModel());
		//ModelOverrides.register(ItemInit.AWP.get(), new AWPModel());
		ModelOverrides.register(ItemInit.BENELLI.get(), new BenelliModel());
		ModelOverrides.register(ItemInit.GLOCK17.get(), new Glock17Model());
		ModelOverrides.register(ItemInit.M14_EBR.get(), new M14EBRModel());
		ModelOverrides.register(ItemInit.M14.get(), new M14Model());
		ModelOverrides.register(ItemInit.M1911.get(), new M1911Model());
		ModelOverrides.register(ItemInit.MOSSBERG.get(), new MossbergModel());
		ModelOverrides.register(ItemInit.REMINGTON_870.get(), new Remington870Model());
		ModelOverrides.register(ItemInit.SCAR_H.get(), new ScarHModel());
		ModelOverrides.register(ItemInit.VSS_VINTOREZ.get(), new VSSVintorezModel());
		ModelOverrides.register(ItemInit.WALTHER_PPK.get(), new WaltherPPKModel());
		ModelOverrides.register(ItemInit.WELROD.get(), new WelrodModel());
		ModelOverrides.register(ItemInit.LANCHESTER.get(), new LanchesterModel());
		ModelOverrides.register(ItemInit.PPSH.get(), new PPSH41Model());
	        
	}

}