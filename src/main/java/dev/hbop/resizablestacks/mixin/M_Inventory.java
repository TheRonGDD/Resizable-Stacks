package dev.hbop.resizablestacks.mixin;

import dev.hbop.resizablestacks.util.StackSizeHelper;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Container.class)
public interface M_Inventory {
    
    // increase max stack size
    @ModifyConstant(
            method = "getMaxStackSize",
            constant = @Constant(intValue = 99)
    )
    default int getMaxCountPerStack(int constant) {
        return StackSizeHelper.MAX_STACK_SIZE;
    }
}
