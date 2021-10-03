package moguns.core.init;

import moguns.MoGuns;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class is where all of the mod's sounds are registered
 * Author: Bomb787
 */
public class SoundInit {
	
	/*
     * This creates a Deferred Register where all of the sounds will be registered
     * This is called and added to the event bus in the main mod file.
     */
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MoGuns.MOD_ID);
	
	public static final RegistryObject<SoundEvent> SCAR_FIRE = register("item.scar.fire");
	
	public static final RegistryObject<SoundEvent> SCAR_FIRE_SUPPRESSED = register("item.scar.suppressed_fire");
	
	public static final RegistryObject<SoundEvent> SCAR_COCK = register("item.scar.cock");
	
	//Method to help us register sounds
	private static RegistryObject<SoundEvent> register(String key) {
			
		return SOUNDS.register(key, () -> new SoundEvent(new ResourceLocation(MoGuns.MOD_ID, key)));
	        
	}

}