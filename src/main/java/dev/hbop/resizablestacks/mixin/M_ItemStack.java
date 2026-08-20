package dev.hbop.resizablestacks.mixin;

import dev.hbop.resizablestacks.util.StackSizeHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public abstract class M_ItemStack {

    // ItemStack inherits this default method from ItemInstance in 26.2.
    // Supplying it on ItemStack overrides the interface default.
    public int getMaxStackSize() {
        ItemStack stack = (ItemStack) (Object) this;
        int modifiedStackSize = StackSizeHelper.getModifiedStackSize(stack);
        return modifiedStackSize != -1
                ? modifiedStackSize
                : stack.getOrDefault(DataComponents.MAX_STACK_SIZE, 1);
    }
}
