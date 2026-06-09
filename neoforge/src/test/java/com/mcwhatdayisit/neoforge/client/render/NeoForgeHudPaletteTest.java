package com.mcwhatdayisit.neoforge.client.render;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class NeoForgeHudPaletteTest {
    @Test
    void accentChangesAcrossWorldDayPeriods() {
        NeoForgeHudPalette palette = new NeoForgeHudPalette();

        int morning = palette.accent(0L, 1.0D);
        int noon = palette.accent(6000L, 1.0D);
        int evening = palette.accent(12000L, 1.0D);
        int night = palette.accent(18000L, 1.0D);

        assertNotEquals(morning, noon);
        assertNotEquals(noon, evening);
        assertNotEquals(evening, night);
    }
}
