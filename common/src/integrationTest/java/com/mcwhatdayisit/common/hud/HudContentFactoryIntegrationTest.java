package com.mcwhatdayisit.common.hud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class HudContentFactoryIntegrationTest {
    @Test
    void createsWorldDayAndPlaytimeLinesFromSnapshotValues() {
        HudConfiguration configuration = new HudConfiguration(true, HudAnchor.TOP_LEFT, 8, 8, 1.0D, 0.9D, TimeDisplayFormat.CLOCK, true, true);
        HudContent content = new HudContentFactory().create(configuration, 48000L, 73220L);

        assertEquals(2, content.lines().size());
        assertEquals(HudLineType.WORLD_DAY, content.lines().get(0).type());
        assertEquals("3", content.lines().get(0).value());
        assertEquals(HudLineType.PLAYTIME, content.lines().get(1).type());
        assertEquals("01:01:01", content.lines().get(1).value());
    }

    @Test
    void createsNoLinesWhenBothSectionsAreDisabled() {
        HudConfiguration configuration = new HudConfiguration(true, HudAnchor.TOP_LEFT, 8, 8, 1.0D, 0.9D, TimeDisplayFormat.CLOCK, false, false);
        HudContent content = new HudContentFactory().create(configuration, 48000L, 73220L);

        assertFalse(content.hasLines());
    }
}
