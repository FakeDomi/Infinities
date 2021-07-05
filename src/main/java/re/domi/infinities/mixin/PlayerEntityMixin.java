package re.domi.infinities.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import re.domi.infinities.Config;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    @Inject(method = "getArrowType", at = @At("RETURN"), cancellable = true)
    public void getArrowType(ItemStack stack, CallbackInfoReturnable<ItemStack> cir)
    {
        if (cir.getReturnValue().isEmpty() && (Config.infinityOnCrossbow || stack.getItem() != Items.CROSSBOW) && EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0)
        {
            cir.setReturnValue(new ItemStack(Items.ARROW));
        }
    }
}
