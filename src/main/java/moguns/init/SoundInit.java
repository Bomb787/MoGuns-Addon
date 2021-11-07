package moguns.init;

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
	
	public static final RegistryObject<SoundEvent> SCARL_FIRE = register("item.scarl.fire");
	
	public static final RegistryObject<SoundEvent> SCARL_FIRE_SUPPRESSED = register("item.scarl.suppressed_fire");
	
	public static final RegistryObject<SoundEvent> SCARL_COCK = register("item.scarl.cock");
	
	public static final RegistryObject<SoundEvent> G36C_FIRE = register("item.g36c.fire");
	
	public static final RegistryObject<SoundEvent> G36C_FIRE_SUPPRESSED = register("item.g36c.suppressed_fire");
	
	public static final RegistryObject<SoundEvent> G36C_COCK = register("item.g36c.cock");
	
	public static final RegistryObject<SoundEvent> BOOM = register("item.trashcan.boom");
	
	public static final RegistryObject<SoundEvent> LAUGH = register("item.trashcan.reload");
	
	public static final RegistryObject<SoundEvent> BIG_IRON_FIRE = register("item.big_iron.fire");
	
	//Method to help us register sounds
	private static RegistryObject<SoundEvent> register(String key) {
			
		return SOUNDS.register(key, () -> new SoundEvent(new ResourceLocation(MoGuns.MOD_ID, key)));
	        
	}

}
