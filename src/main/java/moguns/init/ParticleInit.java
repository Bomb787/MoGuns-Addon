package moguns.init;

import moguns.MoGuns;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleInit {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MoGuns.MOD_ID);

    public static final RegistryObject<SimpleParticleType> FIREBALL_PARTICLES =
            PARTICLES.register("fireball", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FLARE_SMOKE =
            PARTICLES.register("flare_smoke", () -> new SimpleParticleType(true));
    
}