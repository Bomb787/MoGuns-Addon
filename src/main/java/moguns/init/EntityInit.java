package moguns.init;

import java.util.function.BiFunction;

import moguns.MoGuns;
import moguns.entities.TakiProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class is where all of the mod's entities are registered.
 */
/**
 */
public class EntityInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MoGuns.MOD_ID);
	
	public static final RegistryObject<EntityType<TakiProjectileEntity>> TAKI = registerBasic("taki", TakiProjectileEntity::new);
	
	/**
     * This is a helper method when registering projectiles.
     * All of the stuff in this method can be written each time we create a new projectile - but that isn't needed.
     * With this we can register things with much more ease.
     * 
     * @author Mr. Pineapple
     */
    private static <T extends Entity> RegistryObject<EntityType<T>> registerBasic(String id, BiFunction<EntityType<T>, World, T> function) {
    	
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC)
                .sized(0.25F, 0.25F)
                .setTrackingRange(100)
                .setUpdateInterval(1)
                .fireImmune()
                .setShouldReceiveVelocityUpdates(true)
                .build(id);
        return ENTITIES.register(id, () -> type);
        
    }

}