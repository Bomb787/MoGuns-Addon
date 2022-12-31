package moguns.events.loot;

import com.mojang.serialization.Codec;
import moguns.MoGuns;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MoGuns.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> HOG_BONKER_FROM_BRUTE =
            LOOT_MODIFIER_SERIALIZERS.register("hog_bonker_from_brute", HogBonkerFromBruteAdditionModifier.CODEC);

    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}