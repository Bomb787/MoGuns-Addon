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

/**
 * Since we want to have an animation for the slide, we will be overriding the standard model rendering.
 */
public class WaltherPPKModel implements IOverrideModel {

	@SuppressWarnings("resource")
	@Override
	public void render(float partialTicks, TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
		
		//Renders the static parts of the model.
		RenderUtil.renderModel(SpecialModels.WALTHER_PPK_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);
		
		if(entity.equals(Minecraft.getInstance().player)) {

            //Always push
            matrixStack.pushPose();
            //Don't touch this, it's better to use the display options in Blockbench.
            CooldownTracker tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());

            if(Gun.hasAmmo(stack))
                matrixStack.translate(0, 0, 0.1f * (-4.5 * Math.pow(cooldown-0.5, 2) + 1.125));
            else if(!Gun.hasAmmo(stack)) {
            	
                if(cooldown > 0.5)
                    matrixStack.translate(0, 0, 0.185f * (-4.5 * Math.pow(cooldown-0.5, 2) + 0.5));
                else
                    matrixStack.translate(0, 0, 0.185f * (-4.5 * Math.pow(0.5-0.5, 2) + 0.5));
                
            }
            //Renders the moving part of the gun.
            RenderUtil.renderModel(SpecialModels.WALTHER_PPK_SLIDE.getModel(), stack, matrixStack, buffer, light, overlay);
            //Always pop
            matrixStack.popPose();
            
        }
		
	}

}