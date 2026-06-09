package com.mcwhatdayisit.common.hud;

import com.mcwhatdayisit.common.ModConstants;

public final class WorldDayCalculator {
    public long currentWorldDay(long dayTime) {
        long normalizedDayTime = Math.max(0L, dayTime);
        return normalizedDayTime / ModConstants.TICKS_PER_MINECRAFT_DAY + 1L;
    }
}
