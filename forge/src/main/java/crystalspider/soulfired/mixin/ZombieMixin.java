package crystalspider.soulfired.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import crystalspider.soulfired.api.FireManager;
import crystalspider.soulfired.api.type.FireTypeChanger;
import crystalspider.soulfired.api.type.FireTyped;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Zombie;

/**
 * Injects into {@link Zombie} to alter Fire behavior for consistency.
 */
@Mixin(Zombie.class)
public abstract class ZombieMixin implements FireTyped {
  /**
   * Injects into the method {@link Zombie#doHurtTarget(Entity)} after the invocation of {@link Entity#setSecondsOnFire(int)}.
   * <p>
   * Sets the correct FireId to the {@link Entity} being set on fire.
   * 
   * @param entity target {@link Entity}.
   * @param cir {@link CallbackInfoReturnable}.
   */
  @Inject(method = "doHurtTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;setSecondsOnFire(I)V", shift = At.Shift.AFTER))
  private void onDoHurtTarget(Entity entity, CallbackInfoReturnable<Boolean> cir) {
    if (FireManager.isFireId(getFireId())) {
      ((FireTypeChanger) entity).setFireId(getFireId());
    }
  }
}
