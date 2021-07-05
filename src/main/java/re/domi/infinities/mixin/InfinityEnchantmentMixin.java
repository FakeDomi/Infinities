package re.domi.infinities.mixin;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import re.domi.infinities.Config;

@Mixin(InfinityEnchantment.class)
public class InfinityEnchantmentMixin extends Enchantment
{
    protected InfinityEnchantmentMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes)
    {
        super(weight, type, slotTypes);
    }

    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    public void canAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir)
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
    public boolean isAcceptableItem(ItemStack stack)
    {
        return super.isAcceptableItem(stack) || (Config.infinityOnCrossbow && stack.getItem() == Items.CROSSBOW);
    }
}
