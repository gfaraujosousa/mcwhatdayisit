package com.mcwhatdayisit.neoforge.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mcwhatdayisit.common.hud.HudAnchor;
import com.mcwhatdayisit.common.hud.TimeDisplayFormat;
import org.junit.jupiter.api.Test;

class NeoForgeHudConfigDefaultsTest {
    @Test
    void definesExpectedHudDefaults() {
        assertTrue(NeoForgeHudConfigDefaults.ENABLED);
        assertEquals(HudAnchor.TOP_LEFT, NeoForgeHudConfigDefaults.ANCHOR);
        assertEquals(8, NeoForgeHudConfigDefaults.OFFSET_X);
        assertEquals(8, NeoForgeHudConfigDefaults.OFFSET_Y);
        assertEquals(1.0D, NeoForgeHudConfigDefaults.SCALE);
        assertEquals(0.92D, NeoForgeHudConfigDefaults.OPACITY);
        assertEquals(TimeDisplayFormat.COMPACT, NeoForgeHudConfigDefaults.TIME_DISPLAY_FORMAT);
        assertTrue(NeoForgeHudConfigDefaults.SHOW_WORLD_DAY);
        assertTrue(NeoForgeHudConfigDefaults.SHOW_PLAYTIME);
    }
}
