package crystalspider.soulfired.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import crystalspider.soulfired.api.FireManager;
import crystalspider.soulfired.api.type.FireTypeChanger;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Injects into {@link AbstractFireBlock} to alter Fire behavior for consistency.
 */
@Mixin(AbstractFireBlock.class)
public abstract class AbstractFireBlockMixin implements FireTypeChanger {
  /**
   * Fire Type.
   */
  private Identifier fireType;

  @Override
  public void setFireType(Identifier fireType) {
    this.fireType = fireType;
  }

  @Override
  public Identifier getFireType() {
    return fireType;
  }

  /**
   * Redirects the call to {@link Entity#damage(DamageSource, float)} inside the method {@link AbstractFireBlock#onEntityCollision(BlockState, World, BlockPos, Entity)}.
   * <p>
   * Hurts the entity with the correct fire damage and {@link DamageSource}.
   * 
   * @param caller {@link Entity} invoking (owning) the redirected method.
   * @param damageSource original {@link DamageSource} (normal fire).
   * @param damage original damage (normal fire).
   * @return the result of calling the redirected method.
   */
  @Redirect(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
  private boolean redirectHurt(Entity caller, DamageSource damageSource, float damage) {
    return FireManager.damageInFire(caller, getFireType());
  }
}
