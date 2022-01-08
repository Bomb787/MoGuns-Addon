package moguns.client;

import moguns.MoGuns;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/*
 * This class will be used to register special models (like the grenade launcher)
 * We can 'copy' from the SpecialModels class in the base gun mod as there
 * isn't an interface provided to implement.
 */

/**
 * Author: Mr. Pineapple and Bomb787
 */
@EventBusSubscriber(modid = MoGuns.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public enum SpecialModels {
	
	//The values in this class are stored here so we can call upon them.
	SCARL("scarl"),
	LIGHT_SCARL_STOCK("light_scarl_stock"),
	TACTICAL_SCARL_STOCK("tactical_scarl_stock"),
	HEAVY_SCARL_STOCK("heavy_scarl_stock"),
	SCARL_MAIN("scarl_main"),
	SCARL_CHARGING_HANDLE("scarl_charging_handle"),
	G36C_MAIN("g36c_main"),
	G36C_CHARGING_HANDLE("g36c_charging_handle"),
	LIGHT_G36C_STOCK("light_g36c_stock"),
	TACTICAL_G36C_STOCK("tactical_g36c_stock"),
	HEAVY_G36C_STOCK("heavy_g36c_stock"),
	AKM_MAIN("akm_main"),
    AKM_CHARGING_HANDLE("akm_charging_handle"),
    AS_VAL_MAIN("as_val_main"),
    AS_VAL_CHARGING_HANDLE("as_val_charging_handle"),
    LIGHT_THOMPSON_GRIP("light_thompson_grip"),
    SPECIALISED_THOMPSON_GRIP("specialised_thompson_grip"),
    THOMPSON_MAIN("thompson_main"),
    THOMPSON_CHARGING_HANDLE("thompson_charging_handle"),
    FAMAS_MAIN("famas_main"),
    FAMAS_CHARGING_HANDLE("famas_charging_handle"),
    M1_GARAND_MAIN("m1_garand_main"),
    M1_GARAND_CHARGING_HANDLE("m1_garand_charging_handle"),
    M1_GARAND_MAGAZINE("m1_garand_magazine"),
    REFLEX_SIGHT("reflex_sight");
	
	//Variables
    private final ResourceLocation modelLocation;
    private final boolean specialModel;
    @OnlyIn(Dist.CLIENT)
    private IBakedModel cachedModel;
    
    SpecialModels(String modelName) {
        //Get the file path for the special modes, and set them to true (the are going to be special models)
        this(new ResourceLocation(MoGuns.MOD_ID, "special/" + modelName), true);
    }

    //Second Constructor to feed variables
    SpecialModels(ResourceLocation resourceLocation, boolean specialModel) {
        this.modelLocation = resourceLocation;
        this.specialModel = specialModel;
    }
    
    //Get the item's model
    @OnlyIn(Dist.CLIENT)
    public IBakedModel getModel() {
        if (this.cachedModel == null) {
            IBakedModel model = Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
            if (model == Minecraft.getInstance().getModelManager().getMissingModel()) {
                return model;
            }
            this.cachedModel = model;
        }
        return this.cachedModel;
    }

    //Register a new model to that item
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void register(ModelRegistryEvent event) {
        for (SpecialModels model : values()) {
            if (model.specialModel) {
                ModelLoader.addSpecialModel(model.modelLocation);
            }
        }
    }

}