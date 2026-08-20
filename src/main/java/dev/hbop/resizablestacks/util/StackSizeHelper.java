package dev.hbop.resizablestacks.util;

import dev.hbop.resizablestacks.ResizableStacks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class StackSizeHelper {

    public static final int MAX_STACK_SIZE = Integer.MAX_VALUE - 1;
    
    public static int getModifiedStackSize(ItemStack stack) {
        int stackSize = getRawModifiedStackSize(stack);
        if (stackSize <= 0) return -1;
        return Math.min(stackSize, MAX_STACK_SIZE);
    }
    
    private static int getRawModifiedStackSize(ItemStack stack) {
        for (String id : ResizableStacks.CONFIG.stackSizes().keySet()) {
            if (id.startsWith("#")) {
                Identifier tagId = Identifier.tryParse(id.substring(1));
                if (tagId != null && stack.typeHolder().is(TagKey.create(Registries.ITEM, tagId))) {
                    return ResizableStacks.CONFIG.stackSizes().get(id);
                }
            }
            else {
                if (id.equals(stack.typeHolder().getRegisteredName())) {
                    return ResizableStacks.CONFIG.stackSizes().get(id);
                }
            }
        }
        return -1;
    }
}
