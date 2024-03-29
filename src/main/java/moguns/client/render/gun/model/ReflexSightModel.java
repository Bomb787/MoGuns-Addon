package moguns.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mrcrayfish.guns.Reference;
import com.mrcrayfish.guns.client.handler.AimingHandler;
import com.mrcrayfish.guns.client.render.gun.IOverrideModel;
import com.mrcrayfish.guns.client.util.RenderUtil;
import com.mrcrayfish.guns.util.OptifineHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * Provides the model for the Reflex Sight
 */
public class ReflexSightModel implements IOverrideModel {
	
    private static final ResourceLocation RED_DOT_RETICLE = new ResourceLocation(Reference.MOD_ID, "textures/effect/red_dot_reticle.png");
    private static final ResourceLocation RED_DOT_RETICLE_GLOW = new ResourceLocation(Reference.MOD_ID, "textures/effect/red_dot_reticle_glow.png");
    private static final ResourceLocation VIGNETTE = new ResourceLocation(Reference.MOD_ID, "textures/effect/scope_vignette.png");

    @SuppressWarnings("unused")
	@Override
    public void render(float partialTicks, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int light, int overlay) {
    	
        if(OptifineHelper.isShadersEnabled()) {
        	
            double transition = 1.0 - Math.pow(1.0 - AimingHandler.get().getNormalisedAdsProgress(), 2);
            double zScale = 0.05 + 0.95 * (1.0 - transition);
            matrixStack.scale(1.0F, 1.0F, (float) zScale);
            
        }

        RenderUtil.renderModel(stack, parent, matrixStack, renderTypeBuffer, light, overlay);

        if(transformType.firstPerson() && entity.equals(Minecraft.getInstance().player)) {
        	
            matrixStack.pushPose();

            Matrix4f matrix = matrixStack.last().pose();
            Matrix3f normal = matrixStack.last().normal();
            
            /*
            This section here is for changing size and position of the decal
            Default values
            float size = 1.4F / 16.0F;
            matrixStack.translate(-size / 2, 0.85 * 0.0625, -0.3 * 0.0625);
            */
            float size = 1.4F / 12.50F;
            matrixStack.translate(-size / 2, 0.85 * 0.0775, -0.3 * 0.2575);
            
            VertexConsumer builder;
            
            if(!OptifineHelper.isShadersEnabled()) {
            
            	builder = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(VIGNETTE));
                builder.vertex(matrix, 0, 0, 0).color(1.0F, 1.0F, 1.0F, 1.0F).uv(1.0F, 1.0F).overlayCoords(overlay).uv2(light).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
                builder.vertex(matrix, size, 0, 0).color(1.0F, 1.0F, 1.0F, 1.0F).uv(0.0F, 1.0F).overlayCoords(overlay).uv2(light).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
                builder.vertex(matrix, size, size, 0).color(1.0F, 1.0F, 1.0F, 1.0F).uv(0.0F, 0.0F).overlayCoords(overlay).uv2(light).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
                builder.vertex(matrix, 0, size, 0).color(1.0F, 1.0F, 1.0F, 1.0F).uv(1.0F, 0.0F).overlayCoords(overlay).uv2(light).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
                
            }
            
            double invertProgress = (1.0 - AimingHandler.get().getNormalisedAdsProgress());
            matrixStack.translate(-0.04 * invertProgress, 0.01 * invertProgress, 0);
            
            double scale = 6.0;
            matrixStack.translate(size / 2, size / 2, 0);
            matrixStack.translate(-(size / scale) / 2, -(size / scale) / 2, 0);
            matrixStack.translate(0, 0, 0.0001);
            
            int reticleGlowColor = RenderUtil.getItemStackColor(stack, parent, 0);
            CompoundTag tag = stack.getTag();
            if(tag != null && tag.contains("ReticleColor", Tag.TAG_INT))
                reticleGlowColor = tag.getInt("ReticleColor");
            
            float red = ((reticleGlowColor >> 16) & 0xFF) / 255F;
            float green = ((reticleGlowColor >> 8) & 0xFF) / 255F;
            float blue = ((reticleGlowColor >> 0) & 0xFF) / 255F;
            float alpha = (float) AimingHandler.get().getNormalisedAdsProgress();
            
            //Color changing stuff 1
            if(!OptifineHelper.isShadersEnabled()) {
                
            	builder = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(RED_DOT_RETICLE_GLOW));
                builder.vertex(matrix, 0, (float) (size / scale), 0).color(1.0F, 0F, 0F, alpha).uv(0.0F, 0.9375F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
                builder.vertex(matrix, 0, 0, 0).color(1.0F, 0F, 0F, alpha).uv(0.0F, 0.0F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
                builder.vertex(matrix, (float) (size / scale), 0, 0).color(1.0F, 0F, 0F, alpha).uv(0.9375F, 0.0F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
                builder.vertex(matrix, (float) (size / scale), (float) (size / scale), 0).color(1.0F, 0F, 0F, alpha).uv(0.9375F, 0.9375F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
                
            }
            
            alpha = (float) (0.75F * AimingHandler.get().getNormalisedAdsProgress());
            
            //Color changing stuff 2
            builder = renderTypeBuffer.getBuffer(RenderType.entityTranslucent(RED_DOT_RETICLE));
            builder.vertex(matrix, 0, (float) (size / scale), 0).color(1.0F, 0F, 0F, alpha).uv(0.0F, 0.9375F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            builder.vertex(matrix, 0, 0, 0).color(1.0F, 0F, 0F, alpha).uv(0.0F, 0.0F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            builder.vertex(matrix, (float) (size / scale), 0, 0).color(1.0F, 0F, 0F, alpha).uv(0.9375F, 0.0F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            builder.vertex(matrix, (float) (size / scale), (float) (size / scale), 0).color(1.0F, 0F, 0F, alpha).uv(0.9375F, 0.9375F).overlayCoords(overlay).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            
            matrixStack.popPose();
            
        }
        
    }
    
}