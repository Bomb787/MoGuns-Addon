package moguns.events;

import com.mrcrayfish.guns.event.GunFireEvent.Post;
import moguns.MoGuns;
import moguns.init.ItemInit;
import moguns.init.SoundInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Adds a ping sound when the Garand is empty
 */
@Mod.EventBusSubscriber(modid = MoGuns.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GarandPingEvent {

    @SubscribeEvent
    public static void postShoot(Post event) {

        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        CompoundTag tag = heldItem.getTag();

        if (heldItem.getItem() == ItemInit.M1_GARAND.get() && tag != null) {

            if (tag.getInt("AmmoCount") == 1)
                event.getEntity().level.playSound(player, player.blockPosition(), SoundInit.GARAND_PING.get(), SoundSource.MASTER, 3.0F, 1.0F);

        }
    }

}