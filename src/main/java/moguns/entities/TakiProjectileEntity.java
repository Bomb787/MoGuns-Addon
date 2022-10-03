package moguns.entities;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.event.GunProjectileHitEvent;
import com.mrcrayfish.guns.item.GunItem;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;

/**
 * Projectile entity for the Taki item, referenced from Mr. Pineapple and borrows most of its code from {@link ProjectileEntity}
 */
public class TakiProjectileEntity extends ProjectileEntity {
	
	private static final Predicate<BlockState> IGNORE_LEAVES = input -> input != null && Config.COMMON.gameplay.ignoreLeaves.get() && input.getBlock() instanceof LeavesBlock;

	public TakiProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}
	
	public TakiProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World worldIn, LivingEntity shooter, ItemStack stack, GunItem item, Gun modifiedGun) {
		super(entityType, worldIn, shooter, stack, item, modifiedGun);
	}
	
	@Override
	protected void onProjectileTick() {
		
		if(this.level.isClientSide) {
			
			for(int i = 0; i < 5; i++) {
				
				this.level.addParticle(ParticleTypes.LAVA, true, this.getX() - (this.getDeltaMovement().x() / i), this.getY() - (this.getDeltaMovement().y() / i), this.getZ() - (this.getDeltaMovement().z() / i), 0, 0, 0);
				
			}
			
		}
		
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(!this.level.isClientSide) {
			
			Vector3d startVec = this.position();
            Vector3d endVec = startVec.add(this.getDeltaMovement());
            
            RayTraceResult result = rayTraceBlocks(this.level, new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this), IGNORE_LEAVES);
			
            this.onHit(result, startVec, endVec);
            
		}
		
		
	}
	
	@Override
	protected void onHitEntity(Entity entity, Vector3d hitVec, Vector3d startVec, Vector3d endVec, boolean headshot) {
		super.onHitEntity(entity, hitVec, startVec, endVec, headshot);
		
		entity.setSecondsOnFire(5);
		
	}
	
	/**
	 * Sets blocks on fire
	 */
	private void onHit(RayTraceResult result, Vector3d startVec, Vector3d endVec) {
		
		if(MinecraftForge.EVENT_BUS.post(new GunProjectileHitEvent(result, this))) {
			
            return;
            
        }

        if(result instanceof BlockRayTraceResult) {
        	
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) result;
            
            if(blockRayTraceResult.getType() == RayTraceResult.Type.MISS) {
                return;
            }

            Vector3d hitVec = result.getLocation();
            BlockPos pos = blockRayTraceResult.getBlockPos();
            
            if(Config.COMMON.gameplay.enableGunGriefing.get()) {
            	
                BlockPos offsetPos = pos.relative(blockRayTraceResult.getDirection());
                
                if(AbstractFireBlock.canBePlacedAt(this.level, offsetPos, blockRayTraceResult.getDirection())) {
                	
                    BlockState fireState = AbstractFireBlock.getState(this.level, offsetPos);
                    this.level.setBlock(offsetPos, fireState, 11);
                    ((ServerWorld) this.level).sendParticles(ParticleTypes.LAVA, hitVec.x - 1.0 + this.random.nextDouble() * 2.0, hitVec.y, hitVec.z - 1.0 + this.random.nextDouble() * 2.0, 4, 0, 0, 0, 0);
                
                }
                
            }
            
            return;
            
        }
		
	}
	
	/**
     * A custom implementation of {@link net.minecraft.world.IWorldReader#clip(RayTraceContext)}
     * that allows you to pass a predicate to ignore certain blocks when checking for collisions.
     * 
     * @author: Mr. Crayfish
     *
     * @param world     the world to perform the ray trace
     * @param context   the ray trace context
     * @param ignorePredicate the block state predicate
     * @return a result of the raytrace
     */
    private static BlockRayTraceResult rayTraceBlocks(World world, RayTraceContext context, Predicate<BlockState> ignorePredicate) {
    	
        return performRayTrace(context, (rayTraceContext, blockPos) -> {
            BlockState blockState = world.getBlockState(blockPos);
            if(ignorePredicate.test(blockState)) return null;
            FluidState fluidState = world.getFluidState(blockPos);
            Vector3d startVec = rayTraceContext.getFrom();
            Vector3d endVec = rayTraceContext.getTo();
            VoxelShape blockShape = rayTraceContext.getBlockShape(blockState, world, blockPos);
            BlockRayTraceResult blockResult = world.clipWithInteractionOverride(startVec, endVec, blockPos, blockShape, blockState);
            VoxelShape fluidShape = rayTraceContext.getFluidShape(fluidState, world, blockPos);
            BlockRayTraceResult fluidResult = fluidShape.clip(startVec, endVec, blockPos);
            double blockDistance = blockResult == null ? Double.MAX_VALUE : rayTraceContext.getFrom().distanceToSqr(blockResult.getLocation());
            double fluidDistance = fluidResult == null ? Double.MAX_VALUE : rayTraceContext.getFrom().distanceToSqr(fluidResult.getLocation());
            return blockDistance <= fluidDistance ? blockResult : fluidResult;
        }, (rayTraceContext) -> {
            Vector3d Vector3d = rayTraceContext.getFrom().subtract(rayTraceContext.getTo());
            return BlockRayTraceResult.miss(rayTraceContext.getTo(), Direction.getNearest(Vector3d.x, Vector3d.y, Vector3d.z), new BlockPos(rayTraceContext.getTo()));
        });
        
    }
    
    /*
     * Also by Mr. Crayfish
     */
    private static <T> T performRayTrace(RayTraceContext context, BiFunction<RayTraceContext, BlockPos, T> hitFunction, Function<RayTraceContext, T> p_217300_2_) {
        Vector3d startVec = context.getFrom();
        Vector3d endVec = context.getTo();
        if(startVec.equals(endVec))
        {
            return p_217300_2_.apply(context);
        }
        else
        {
            double startX = MathHelper.lerp(-0.0000001, endVec.x, startVec.x);
            double startY = MathHelper.lerp(-0.0000001, endVec.y, startVec.y);
            double startZ = MathHelper.lerp(-0.0000001, endVec.z, startVec.z);
            double endX = MathHelper.lerp(-0.0000001, startVec.x, endVec.x);
            double endY = MathHelper.lerp(-0.0000001, startVec.y, endVec.y);
            double endZ = MathHelper.lerp(-0.0000001, startVec.z, endVec.z);
            int blockX = MathHelper.floor(endX);
            int blockY = MathHelper.floor(endY);
            int blockZ = MathHelper.floor(endZ);
            BlockPos.Mutable mutablePos = new BlockPos.Mutable(blockX, blockY, blockZ);
            T t = hitFunction.apply(context, mutablePos);
            if(t != null)
            {
                return t;
            }

            double deltaX = startX - endX;
            double deltaY = startY - endY;
            double deltaZ = startZ - endZ;
            int signX = MathHelper.sign(deltaX);
            int signY = MathHelper.sign(deltaY);
            int signZ = MathHelper.sign(deltaZ);
            double d9 = signX == 0 ? Double.MAX_VALUE : (double) signX / deltaX;
            double d10 = signY == 0 ? Double.MAX_VALUE : (double) signY / deltaY;
            double d11 = signZ == 0 ? Double.MAX_VALUE : (double) signZ / deltaZ;
            double d12 = d9 * (signX > 0 ? 1.0D - MathHelper.frac(endX) : MathHelper.frac(endX));
            double d13 = d10 * (signY > 0 ? 1.0D - MathHelper.frac(endY) : MathHelper.frac(endY));
            double d14 = d11 * (signZ > 0 ? 1.0D - MathHelper.frac(endZ) : MathHelper.frac(endZ));

            while(d12 <= 1.0D || d13 <= 1.0D || d14 <= 1.0D)
            {
                if(d12 < d13)
                {
                    if(d12 < d14)
                    {
                        blockX += signX;
                        d12 += d9;
                    }
                    else
                    {
                        blockZ += signZ;
                        d14 += d11;
                    }
                }
                else if(d13 < d14)
                {
                    blockY += signY;
                    d13 += d10;
                }
                else
                {
                    blockZ += signZ;
                    d14 += d11;
                }

                T t1 = hitFunction.apply(context, mutablePos.set(blockX, blockY, blockZ));
                if(t1 != null)
                {
                    return t1;
                }
            }

            return p_217300_2_.apply(context);
        }
    }

}