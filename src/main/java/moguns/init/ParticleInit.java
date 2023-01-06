package moguns.init;

import moguns.MoGuns;
import moguns.particles.FireballParticle;
import moguns.particles.FlareSmokeParticle;
import moguns.particles.SonicBoomParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = MoGuns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleInit {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MoGuns.MOD_ID);

    public static final RegistryObject<SimpleParticleType> FIREBALL_PARTICLES =
            PARTICLES.register("fireball", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FLARE_SMOKE =
            PARTICLES.register("flare_smoke", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SONIC_BOOM = 
    		PARTICLES.register("sonic_boom", () -> new SimpleParticleType(true));
    

	
	@SuppressWarnings("resource")
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onParticlesRegistry(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleInit.FIREBALL_PARTICLES.get(), FireballParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleInit.FLARE_SMOKE.get(), FlareSmokeParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleInit.SONIC_BOOM.get(), SonicBoomParticle.Provider::new);
    }
    
}