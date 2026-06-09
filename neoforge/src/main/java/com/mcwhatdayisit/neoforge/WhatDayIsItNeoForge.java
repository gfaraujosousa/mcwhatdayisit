package com.mcwhatdayisit.neoforge;

import com.mcwhatdayisit.common.ModConstants;
import com.mcwhatdayisit.neoforge.client.NeoForgeClientConfig;
import com.mcwhatdayisit.neoforge.client.NeoForgeHudLayerRegistrar;
import com.mcwhatdayisit.neoforge.client.NeoForgeHudSnapshotProvider;
import com.mcwhatdayisit.neoforge.client.render.NeoForgeHudRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(value = ModConstants.MOD_ID, dist = Dist.CLIENT)
public final class WhatDayIsItNeoForge {
    public WhatDayIsItNeoForge(IEventBus modBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, NeoForgeClientConfig.SPEC);

        NeoForgeHudRenderer renderer = new NeoForgeHudRenderer(new NeoForgeHudSnapshotProvider(), NeoForgeClientConfig::hudConfiguration);
        NeoForgeHudLayerRegistrar layerRegistrar = new NeoForgeHudLayerRegistrar(renderer);

        modBus.addListener(layerRegistrar::registerHudLayer);
    }
}
