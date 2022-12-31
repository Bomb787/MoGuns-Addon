package moguns.client;

import moguns.MoGuns;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * This class will be used to register special models to be used in animations, like the drum of the grenade launcher in the default mod.
 */
@EventBusSubscriber(modid = MoGuns.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public enum SpecialModels {
	
	//The values in this class are stored here so we can call upon them.
	LIGHT_SCAR_L_STOCK("light_scar_l_stock"),
	TACTICAL_SCAR_L_STOCK("tactical_scar_l_stock"),
	HEAVY_SCAR_L_STOCK("heavy_scar_l_stock"),
	SCAR_L_MAIN("scar_l_main"),
	SCAR_L_CHARGING_HANDLE("scar_l_charging_handle"),
	LIGHT_SCAR_H_STOCK("light_scar_h_stock"),
    TACTICAL_SCAR_H_STOCK("tactical_scar_h_stock"),
    HEAVY_SCAR_H_STOCK("heavy_scar_h_stock"),
    SCAR_H_MAIN("scar_h_main"),
    SCAR_H_CHARGING_HANDLE("scar_h_charging_handle"),
	G36C_MAIN("g36c_main"),
	G36C_CHARGING_HANDLE("g36c_charging_handle"),
	LIGHT_G36C_STOCK("light_g36c_stock"),
	TACTICAL_G36C_STOCK("tactical_g36c_stock"),
	HEAVY_G36C_STOCK("heavy_g36c_stock"),
	AKM_MAIN("akm_main"),
    AKM_CHARGING_HANDLE("akm_charging_handle"),
    AKM_CUSTOM_MAIN("akm_custom_main"),
    AKM_CUSTOM_CHARGING_HANDLE("akm_custom_charging_handle"),
    LIGHT_AKMC_STOCK("light_akmc_stock"),
    TACTICAL_AKMC_STOCK("tactical_akmc_stock"),
    HEAVY_AKMC_STOCK("heavy_akmc_stock"),
    VSS_VINTOREZ_MAIN("vss_vintorez_main"),
    VSS_VINTOREZ_CHARGING_HANDLE("vss_vintorez_charging_handle"),
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
    GLOCK_17_MAIN("glock_17_main"),
    GLOCK_17_SLIDE("glock_17_slide"),
    REMINGTON_870_MAIN("remington_870_main"),
    REMINGTON_870_PUMP("remington_870_pump"),
    MOSSBERG_MAIN("mossberg_main"),
    MOSSBERG_PUMP("mossberg_pump"),
    BENELLI_MAIN("benelli_main"),
    BENELLI_PUMP("benelli_pump"),
    LIGHT_BENELLI_STOCK("light_benelli_stock"),
    TACTICAL_BENELLI_STOCK("tactical_benelli_stock"),
    HEAVY_BENELLI_STOCK("heavy_benelli_stock"),
    M14_MAIN("m14_main"),
    M14_CHARGING_HANDLE("m14_charging_handle"),
    M1911_MAIN("m1911_main"),
    M1911_CHAMBER("m1911_slide"),
    M14_EBR_MAIN("m14_ebr_main"),
    M14_EBR_CHARGING_HANDLE("m14_ebr_charging_handle"),
    LIGHT_M14_EBR_STOCK("light_m14_ebr_stock"),
    TACTICAL_M14_EBR_STOCK("tactical_m14_ebr_stock"),
    HEAVY_M14_EBR_STOCK("heavy_m14_ebr_stock"),
    WALTHER_PPK_MAIN("walther_ppk_main"),
    WALTHER_PPK_SLIDE("walther_ppk_slide"),
    REFLEX_SIGHT("reflex_sight"),
    WELROD_MAIN("welrod_main"),
    WELROD_BOLT("welrod_bolt"),
    LANCHESTER_MAIN("lanchester_main"),
    LANCHESTER_BOLT("lanchester_bolt"),
    PPSH_41_MAIN("ppsh_41_main"),
    PPSH_41_BOLT("ppsh_41_bolt"),
    BUTTERFLY_GUN_MAIN("butterfly_gun_main"),
    BUTTERFLY_GUN_SLIDE("butterfly_gun_slide"),
    WRAPPED_RIFLE_MAIN("wrapped_rifle_main"),
    WRAPPED_RIFLE_CHAMBER("wrapped_rifle_chamber"),
    HELLFIRE_MAIN("hellfire_main"),
    HELLFIRE_SLIDE("hellfire_slide"),
    LIGHT_HELLFIRE_STOCK("light_hellfire_stock"),
    TACTICAL_HELLFIRE_STOCK("tactical_hellfire_stock"),
    HEAVY_HELLFIRE_STOCK("heavy_hellfire_stock"),
    BLUE_HEAT_MAIN("blue_heat_main"),
    BLUE_HEAT_SLIDE("blue_heat_slide"),
    LIGHT_BLUE_HEAT_STOCK("light_blue_heat_stock"),
    TACTICAL_BLUE_HEAT_STOCK("tactical_blue_heat_stock"),
    HEAVY_BLUE_HEAT_STOCK("heavy_blue_heat_stock"),
    HOG_BONKER_MAIN("hog_bonker_main"),
    HOG_BONKER_BARRELS("hog_bonker_barrels"),
    AWP_MAIN("awp_main"),
    AWP_BOLT("awp_bolt"),
    AWP_CHAMBER("awp_chamber"),
    DOUBLE_BARREL_MAIN("double_barrel_main"),
    DOUBLE_BARREL_BARRELS("double_barrel_barrels");
	
	//Variables
    private final ResourceLocation modelLocation;
    private final boolean specialModel;
    @OnlyIn(Dist.CLIENT)
    private BakedModel cachedModel;
    
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
    public BakedModel getModel() {
        if (this.cachedModel == null) {
            BakedModel model = Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
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
            	ForgeModelBakery.addSpecialModel(model.modelLocation);
            }
        }
    }

}