package de.tomalbrc.smootherservercosmetics.mixin;

import net.minecraft.client.renderer.entity.DisplayRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.DisplayEntityRenderState;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisplayRenderer.class)
public abstract class DisplayRendererMixin<T extends Display, S extends DisplayEntityRenderState> extends EntityRenderer<T, S> {
    protected DisplayRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Display;Lnet/minecraft/client/renderer/entity/state/DisplayEntityRenderState;F)V", at = @At("RETURN"))
    private void ssc$applyPlayerVehicleRot(T display, S displayEntityRenderState, float f, CallbackInfo ci) {
        if (display.isPassenger() && display instanceof Display.ItemDisplay && display.getVehicle() instanceof Player player) {
            displayEntityRenderState.entityYRot = player.getPreciseBodyRotation(f);
        }
    }
}
