package dev.hbop.resizablestacks.mixin;

import dev.hbop.resizablestacks.util.StackSizeHelper;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemStack.class)
public abstract class M_Codecs {

    // Expand only ItemStack.CODEC's count range, instead of changing every
    // unrelated ExtraCodecs.intRange(1, 99) call in the game.
    @ModifyArg(
            method = "lambda$static$1",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/ExtraCodecs;intRange(II)Lcom/mojang/serialization/Codec;"
            ),
            index = 1
    )
    private static int expandItemStackCodecRange(int value) {
        return value == 99 ? StackSizeHelper.MAX_STACK_SIZE : value;
    }
}
