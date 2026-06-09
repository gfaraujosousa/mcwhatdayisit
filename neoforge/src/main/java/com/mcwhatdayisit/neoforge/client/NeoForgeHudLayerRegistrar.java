package com.mcwhatdayisit.neoforge.client;

import com.mcwhatdayisit.common.ModConstants;
import com.mcwhatdayisit.neoforge.client.render.NeoForgeHudRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

public final class NeoForgeHudLayerRegistrar {
    private static final Identifier HUD_LAYER_ID = Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, "hud");

    private final NeoForgeHudRenderer renderer;

    public NeoForgeHudLayerRegistrar(NeoForgeHudRenderer renderer) {
        this.renderer = renderer;
    }

    public void registerHudLayer(RegisterGuiLayersEvent event) {
        event.registerBelow(VanillaGuiLayers.CHAT, HUD_LAYER_ID, renderer::render);
    }
}
