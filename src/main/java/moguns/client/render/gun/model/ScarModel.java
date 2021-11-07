package moguns.client.render.gun.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.init.ModItems;
import com.mrcrayfish.guns.item.attachment.IAttachment;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import moguns.client.SpecialModels;

/*
 * Since the SCAR will be using a different model for the stock, we need to override the model.
 */
public class ScarModel implements IOverrideModel {

	@Override
	public void render(float partialTicks, TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
		
		if(Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.LIGHT_STOCK.get()) {
			
            RenderUtil.renderModel(SpecialModels.LIGHT_SCARL_STOCK.getModel(), stack, matrixStack, buffer, light, overlay);

        }
		if(Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.TACTICAL_STOCK.get()) {
			
            RenderUtil.renderModel(SpecialModels.TACTICAL_SCARL_STOCK.getModel(), stack, matrixStack, buffer, light, overlay);

        }
		if(Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.WEIGHTED_STOCK.get()) {
	
			RenderUtil.renderModel(SpecialModels.HEAVY_SCARL_STOCK.getModel(), stack, matrixStack, buffer, light, overlay);

		}
		
		RenderUtil.renderModel(SpecialModels.SCARL.getModel(), stack, matrixStack, buffer, light, overlay);
		
	}

}