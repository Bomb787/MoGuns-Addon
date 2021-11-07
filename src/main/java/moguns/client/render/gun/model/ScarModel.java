package moguns.client.render.gun.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.init.ModItems;
import com.mrcrayfish.guns.item.attachment.IAttachment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import moguns.client.SpecialModels;

/*
 * Since the SCAR will be using a different model for the stock, we need to override the model.
 * This also allows us to give the charging handle an animation during firing.
 */
public class ScarModel implements IOverrideModel {

	@Override
	public void render(float partialTicks, TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
		
		//Renders the static parts of the model, the magazine is separate from the base for possible future use.
		RenderUtil.renderModel(SpecialModels.SCARL_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);
		
		if(Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.LIGHT_STOCK.get()) {
			
            RenderUtil.renderModel(SpecialModels.LIGHT_SCARL_STOCK.getModel(), stack, matrixStack, buffer, light, overlay);

        }
		if(Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.TACTICAL_STOCK.get()) {
			
            RenderUtil.renderModel(SpecialModels.TACTICAL_SCARL_STOCK.getModel(), stack, matrixStack, buffer, light, overlay);

        }
		if(Gun.getAttachment(IAttachment.Type.STOCK, stack).getItem() == ModItems.WEIGHTED_STOCK.get()) {
	
			RenderUtil.renderModel(SpecialModels.HEAVY_SCARL_STOCK.getModel(), stack, matrixStack, buffer, light, overlay);

		}
		
		if(entity.equals(Minecraft.getInstance().player)) {
			
			//Always push.
            matrixStack.push();
            //Don't touch this, it's better to use the display options in Blockbench.
            matrixStack.translate(0, -5.8 * 0.0625, 0);
            //Gets the cooldown tracker for the item. Items like swords and enderpearls also have this.
            CooldownTracker tracker = Minecraft.getInstance().player.getCooldownTracker();
            float cooldown = tracker.getCooldown(stack.getItem(), Minecraft.getInstance().getRenderPartialTicks());
            cooldown = (float) ease(cooldown);
            /**
             * We are moving whatever part is moving.
             * X,Y,Z, use Z for moving back and forth.
             */
            matrixStack.translate(0, 0, cooldown/8);
            matrixStack.translate(0, 5.8 * 0.0625, 0);
            //Renders the moving part of the gun.
            RenderUtil.renderModel(SpecialModels.SCARL_CHARGING_HANDLE.getModel(), stack, matrixStack, buffer, light, overlay);
            //Always pop
            matrixStack.pop();
			
		}
		
	}
	
	private double ease(double x) {
		
		return 1 - Math.pow(1 - (2 * x), 4);
        
    }

}