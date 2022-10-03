package moguns.client.render.gun.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.common.Gun;
import moguns.client.SpecialModels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.math.vector.Vector3f;

/**
 * Since we want to have an animation for the slide, we will be overriding the standard model rendering.
 */
public class HogBonkerModel implements IOverrideModel {

	@SuppressWarnings("resource")
	@Override
	public void render(float partialTicks, TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
		
		//Renders the static parts of the model.
		RenderUtil.renderModel(SpecialModels.HOG_BONKER_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);
		
		if(entity.equals(Minecraft.getInstance().player)) {

            //Always push
            matrixStack.pushPose();
            //Gets the cooldown tracker for the item. Items like swords and enderpearls also have this.
            CooldownTracker tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());

            if(!Gun.hasAmmo(stack)) {
            	
                if(cooldown > 0.5)
                    matrixStack.translate(0, 0, 0.185f * (-4.5 * Math.pow(cooldown-0.5, 2) + 0.5));
                else
                    matrixStack.mulPose(Vector3f.YN.rotationDegrees(45F * cooldown));
                
            }
            //Renders the moving part of the gun.
            RenderUtil.renderModel(SpecialModels.HOG_BONKER_BARRELS.getModel(), stack, matrixStack, buffer, light, overlay);
            //Always pop
            matrixStack.popPose();
            
        }
		
	}

}