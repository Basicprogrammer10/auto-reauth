package com.connorcode.autoreauth.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.connorcode.autoreauth.AutoReauth.log;
import static com.connorcode.autoreauth.Common.tickAuthStatus;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Nullable public Screen currentScreen;

    @Inject(at = @At("HEAD"), method = "tick")
    void onTick(CallbackInfo ci) {
        // So janky sob but it's needed for meteor client compat
        if (this.currentScreen instanceof DisconnectedScreen)
            tickAuthStatus(this.currentScreen);
    }
}
