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
import net.minecraft.util.math.vector.Vector3f;

/**
 * Since we want to have an animation for the bolt, we will be overriding the standard model rendering.
 */
public class WelrodModel implements IOverrideModel {
	
	@SuppressWarnings("resource")
	@Override
	public void render(float partialTicks, TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
		
		//Renders the static parts of the model.
		RenderUtil.renderModel(SpecialModels.WELROD_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);
		
		if(entity.equals(Minecraft.getInstance().player)) {

            //Always push
            matrixStack.pushPose();
            //Don't touch this, it's better to use the display options in Blockbench.
            CooldownTracker tracker = Minecraft.getInstance().player.getCooldowns();
            float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
            
            if (cooldown != 0 && cooldown < 0.86) {
            	
                matrixStack.translate(-0.225, -0.0975, 0);
                matrixStack.mulPose(Vector3f.ZN.rotationDegrees(-45F));
                
                if (cooldown < 0.74 && cooldown > 0.42)
                	matrixStack.translate(0, 0, (-cooldown+0.74)/4);
                if (cooldown < 0.42 && cooldown > 0.07)
                	matrixStack.translate(0, 0, (cooldown-0.07)/4);
                
            }
            //Renders the moving part of the gun.
            RenderUtil.renderModel(SpecialModels.WELROD_BOLT.getModel(), stack, matrixStack, buffer, light, overlay);
            //Always pop
            matrixStack.popPose();
            
        }
		
	}

}