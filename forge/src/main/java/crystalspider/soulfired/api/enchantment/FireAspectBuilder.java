package crystalspider.soulfired.api.enchantment;

import net.minecraft.resources.ResourceLocation;

/**
 * Builder for {@link FireTypedFireAspectEnchantment}.
 */
public final class FireAspectBuilder extends FireEnchantmentBuilder<FireTypedFireAspectEnchantment> {
  /**
   * @param fireType {@link FireEnchantmentBuilder#fireType fireType}.
   */
  public FireAspectBuilder(ResourceLocation fireType) {
    super(fireType);
  }

  @Override
  public final FireTypedFireAspectEnchantment build() {
    return new FireTypedFireAspectEnchantment(fireType, rarity, isTreasure, isCurse, isTradeable, isDiscoverable, enabled, compatibility);
  }
}
