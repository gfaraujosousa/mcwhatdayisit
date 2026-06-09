package com.mcwhatdayisit.common.hud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HudConfigurationTest {
    @Test
    void clampsScaleAndOpacityToSupportedRanges() {
        HudConfiguration configuration = new HudConfiguration(true, HudAnchor.TOP_LEFT, 0, 0, 10.0D, 0.01D, TimeDisplayFormat.COMPACT, true, true);

        assertEquals(2.0D, configuration.scale());
        assertEquals(0.15D, configuration.opacity());
    }

    @Test
    void reportsVisibleContentOnlyWhenEnabledAndAnyLineIsEnabled() {
        HudConfiguration configuration = new HudConfiguration(true, HudAnchor.TOP_LEFT, 0, 0, 1.0D, 1.0D, TimeDisplayFormat.COMPACT, false, true);

        assertTrue(configuration.hasVisibleContent());
    }
}
