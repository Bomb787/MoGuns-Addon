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

/*
 * Since the SCAR will be using a different model for the stock, we need to override the model.
 * This also allows us to give the charging handle an animation during firing.
 */
public class AWPModel implements IOverrideModel {

	@SuppressWarnings("resource")
	@Override
	public void render(float partialTicks, TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
		
		//Renders the static parts of the model, the magazine is separate from the base for possible future use.
		RenderUtil.renderModel(SpecialModels.AWP_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);
		
		if(entity.equals(Minecraft.getInstance().player)) {
			
			//Always push.
            matrixStack.push();
            //Don't touch this, it's better to use the display options in Blockbench.
            matrixStack.translate(0, -5.8 * 0.0625, 0);
            //Gets the cooldown tracker for the item. Items like swords and enderpearls also have this.
            CooldownTracker tracker = Minecraft.getInstance().player.getCooldownTracker();
            float cooldownOg = tracker.getCooldown(stack.getItem(), Minecraft.getInstance().getRenderPartialTicks());
            float cooldown = (float) easeInOutBack(cooldownOg);

            if (cooldownOg != 0 && cooldownOg < 0.86)
            {
                matrixStack.translate(-0.108, -0.11, 0.00);
                matrixStack.rotate(Vector3f.ZN.rotationDegrees(-90F));

                // matrices.translate(0, 0, 0.318f * (-4.5 * Math.pow(cooldownOg +0.19 -0.5, 2) + 1));

                if (cooldownOg < 0.74 && cooldownOg > 0.42)
                {
                    matrixStack.translate(0, 0, -0.03 * -cooldown);
                    matrixStack.translate(0, 0, 0.318f * ((1.0 * -cooldown)+1));
                }
                if (cooldownOg < 0.42 && cooldownOg > 0.07)
                {
                    matrixStack.translate(0, 0, 0.798f * ((1.0 * cooldownOg-0.07)));
                }

            }

            RenderUtil.renderModel(SpecialModels.AWP_BOLT.getModel(), stack, matrixStack, buffer, light, overlay);
            matrixStack.pop();
			
		}
		
	}

    private double easeInOutBack(double x) {
        double c1 = 1.70158;
        double c2 = c1 * 1.525;
        return (x < 0.5 ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2 : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2);
    }
}
