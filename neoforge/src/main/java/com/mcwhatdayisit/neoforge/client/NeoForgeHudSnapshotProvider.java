package com.mcwhatdayisit.neoforge.client;

import com.mcwhatdayisit.common.hud.HudSnapshot;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.stats.Stats;

public final class NeoForgeHudSnapshotProvider {
    public Optional<HudSnapshot> snapshot() {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.level == null || minecraft.player == null) {
            return Optional.empty();
        }

        long dayTime = minecraft.level.getDayTime();
        long playtimeTicks = minecraft.player.getStats().getValue(Stats.CUSTOM.get(Stats.PLAY_TIME));

        return Optional.of(new HudSnapshot(dayTime, playtimeTicks));
    }
}
