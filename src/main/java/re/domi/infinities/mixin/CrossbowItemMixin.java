package re.domi.infinities.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin
{
    @Inject(method = "shoot",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/CrossbowItem;createArrow(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/projectile/PersistentProjectileEntity;"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private static void infinities_shoot(World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated, CallbackInfo ci, boolean isRocket, ProjectileEntity projectileEntity)
    {
        if (simulated == 0F && projectile.getItem() == Items.ARROW && EnchantmentHelper.getLevel(Enchantments.INFINITY, crossbow) > 0)
        {
            ((PersistentProjectileEntity)projectileEntity).pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        }
    }

    @ModifyVariable(method = "loadProjectile", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", shift = At.Shift.BY, by = -2, ordinal = 0), ordinal = 0)
    private static boolean infinities_keepArrow(boolean orig, LivingEntity shooter, ItemStack crossbow, ItemStack projectile)
    {
        return orig || (projectile.getItem() == Items.ARROW && EnchantmentHelper.getLevel(Enchantments.INFINITY, crossbow) > 0);
    }
}
