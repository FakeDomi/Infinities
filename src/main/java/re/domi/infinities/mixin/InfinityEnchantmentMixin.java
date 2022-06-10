package re.domi.infinities.mixin;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import re.domi.infinities.Config;

@Mixin(value = InfinityEnchantment.class, priority = 1500)
public class InfinityEnchantmentMixin extends Enchantment
{
    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
    public void infinities_canAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir)
    {
        if (!Config.infinityWithMultishot && other instanceof MultishotEnchantment)
        {
            cir.setReturnValue(false);
        }
        else if (Config.infinityWithMending && other instanceof MendingEnchantment)
        {
            cir.setReturnValue(true);
        }
    }

    @Override
    @Unique(silent = true)
    public boolean isAcceptableItem(ItemStack stack)
    {
        return super.isAcceptableItem(stack);
    }

    @Inject(method = { "isAcceptableItem", "method_8192" }, at = @At("HEAD"), cancellable = true, remap = false)
    public void infinities_isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        if (Config.infinityOnCrossbow && stack.getItem() == Items.CROSSBOW)
        {
            cir.setReturnValue(true);
        }
    }

    protected InfinityEnchantmentMixin()
    {
        super(null, null, null);
    }
}
