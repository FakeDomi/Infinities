package re.domi.infinities.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity
{
    @Inject(method = "getProjectileType", at = @At("RETURN"), cancellable = true)
    public void getArrowType(ItemStack stack, CallbackInfoReturnable<ItemStack> cir)
    {
        if (cir.getReturnValue().isEmpty() && EnchantmentHelper.getLevel(this.getEntityWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.INFINITY.getValue()).orElseThrow(), stack) > 0)
        {
            cir.setReturnValue(new ItemStack(Items.ARROW));
        }
    }

    @SuppressWarnings("DataFlowIssue")
    protected PlayerEntityMixin()
    {
        super(null, null);
    }
}
