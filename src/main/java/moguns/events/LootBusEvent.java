package moguns.events;

import javax.annotation.Nonnull;

import moguns.MoGuns;
import moguns.events.loot.HogBonkerFromBruteAdditionModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoGuns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LootBusEvent {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new HogBonkerFromBruteAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(MoGuns.MOD_ID,"hog_bonker_from_brute"))
        );
    }
}
