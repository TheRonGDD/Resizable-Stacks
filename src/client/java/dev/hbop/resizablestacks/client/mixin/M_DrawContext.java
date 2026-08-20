package dev.hbop.resizablestacks.client.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix3x2fStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphicsExtractor.class)
public abstract class M_DrawContext {
    @Shadow @Final private Matrix3x2fStack pose;

    @Shadow
    public abstract void text(Font font, String text, int x, int y, int color, boolean shadow);

    // Minecraft's normal label overflows an item slot at four digits.
    @Inject(method = "itemCount", at = @At("HEAD"), cancellable = true)
    private void balancedStackSizes$drawLongCount(
            Font font, ItemStack stack, int x, int y, String stackCountText, CallbackInfo ci) {
        String text = stackCountText == null ? String.valueOf(stack.getCount()) : stackCountText;
        if (text.length() < 4) return;

        pose.pushMatrix();
        pose.translate(x + 17.0f, y + 9.0f);
        pose.scale(0.5f, 0.5f);
        this.text(font, text, -font.width(text), 0, -1, true);
        pose.popMatrix();
        ci.cancel();
    }
}
