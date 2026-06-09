package com.mcwhatdayisit.common.hud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WorldDayCalculatorTest {
    private final WorldDayCalculator calculator = new WorldDayCalculator();

    @Test
    void startsAtDayOne() {
        assertEquals(1L, calculator.currentWorldDay(0L));
    }

    @Test
    void keepsDayOneUntilFirstMinecraftDayEnds() {
        assertEquals(1L, calculator.currentWorldDay(23999L));
    }

    @Test
    void advancesAtExactDayBoundary() {
        assertEquals(2L, calculator.currentWorldDay(24000L));
    }

    @Test
    void clampsNegativeTimeToDayOne() {
        assertEquals(1L, calculator.currentWorldDay(-24000L));
    }
}
