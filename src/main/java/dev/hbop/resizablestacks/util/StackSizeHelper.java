package dev.hbop.resizablestacks.util;

import dev.hbop.resizablestacks.ResizableStacks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

public class StackSizeHelper {

    public static final int MAX_STACK_SIZE = Integer.MAX_VALUE - 1;
    
    public static int getModifiedStackSize(ItemStack stack) {
        int stackSize = getRawModifiedStackSize(stack);
        if (stackSize <= 0) return -1;

        // Shulker boxes may be configured like any other item, but only empty
        // boxes are safe to stack. Their color remains part of the item ID, so
        // normal item-stack matching still keeps different colors separate.
        if (isNonEmptyShulkerBox(stack)) return 1;

        return Math.min(stackSize, MAX_STACK_SIZE);
    }

    private static boolean isNonEmptyShulkerBox(ItemStack stack) {
        if (!stack.typeHolder().is(ItemTags.SHULKER_BOXES)) return false;

        // Treat unresolved loot-table contents conservatively as non-empty.
        if (stack.has(DataComponents.CONTAINER_LOOT)) return true;

        ItemContainerContents contents = stack.getOrDefault(
                DataComponents.CONTAINER,
                ItemContainerContents.EMPTY
        );
        return contents.nonEmptyItems().iterator().hasNext();
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
