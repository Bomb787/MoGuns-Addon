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

/*
 * Since the SCAR will be using a different model for the stock, we need to override the model.
 * This also allows us to give the charging handle an animation during firing.
 */
public class WaltherPPKModel implements IOverrideModel {

	@SuppressWarnings("resource")
	@Override
	public void render(float partialTicks, TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, int overlay) {
		
		//Renders the static parts of the model, the magazine is separate from the base for possible future use.
		RenderUtil.renderModel(SpecialModels.WALTHER_PPK_MAIN.getModel(), stack, matrixStack, buffer, light, overlay);
		
		if(entity.equals(Minecraft.getInstance().player)) {

            //Always push
            matrixStack.push();

            CooldownTracker tracker = Minecraft.getInstance().player.getCooldownTracker();
            float cooldownOg = tracker.getCooldown(stack.getItem(), Minecraft.getInstance().getRenderPartialTicks());


            if(Gun.hasAmmo(stack))
            {
                // Math provided by Bomb787 on GitHub and Curseforge!!!
                matrixStack.translate(0, 0, 0.1f * (-4.5 * Math.pow(cooldownOg-0.5, 2) + 1.125));
            }
            else if(!Gun.hasAmmo(stack))
            {
                if(cooldownOg > 0.5){
                    // Math provided by Bomb787 on GitHub and Curseforge!!!
                    matrixStack.translate(0, 0, 0.185f * (-4.5 * Math.pow(cooldownOg-0.5, 2) + 0.5));
                }
                else
                {
                    matrixStack.translate(0, 0, 0.185f * (-4.5 * Math.pow(0.5-0.5, 2) + 0.5));
                }
            }
            //matrices.translate(0.00, 0.0, -0.1);
            RenderUtil.renderModel(SpecialModels.WALTHER_PPK_SLIDE.getModel(), stack, matrixStack, buffer, light, overlay);

            //Always pop
            matrixStack.pop();
        }
		
	}

}
