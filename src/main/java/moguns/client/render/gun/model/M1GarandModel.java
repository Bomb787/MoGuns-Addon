package moguns.client.render.gun.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import moguns.client.SpecialModels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;

/**
 * Since we want to have an animation for the bolt, we will be overriding the standard model rendering.
 */
public class M1GarandModel implements IOverrideModel {

	@SuppressWarnings("resource")
	@Override
	public void render(float partialTicks, TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
		
		//Renders the static parts of the model.
		RenderUtil.renderModel(SpecialModels.M1_GARAND_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);
		
		if(entity.equals(Minecraft.getInstance().player)) {
			
			//Always push.
            matrixStack.pushPose();
            //Don't touch this, it's better to use the display options in Blockbench.
            matrixStack.translate(0, -5.8 * 0.0625, 0);
            //Gets the cooldown tracker for the item. Items like swords and enderpearls also have this.
            CooldownTracker tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            cooldown = (float) ease(cooldown);
            /**
             * We are moving whatever part is moving.
             * X,Y,Z, use Z for moving back and forth.
             * The higher the number, the shorter the distance.
             */
            matrixStack.translate(0, 0, cooldown/8);
            matrixStack.translate(0, 5.8 * 0.0625, 0);
            //Renders the moving part of the gun.
            RenderUtil.renderModel(SpecialModels.M1_GARAND_CHARGING_HANDLE.getModel(), stack, matrixStack, buffer, light, overlay);
            //Always pop
            matrixStack.popPose();
			
		}
		
	}
	
	private double ease(double x) {
		
		return 1 - Math.pow(1 - (2 * x), 4);
        
    }

}