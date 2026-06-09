package com.mcwhatdayisit.common.hud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PlaytimeFormatterTest {
    private final PlaytimeFormatter formatter = new PlaytimeFormatter();

    @Test
    void formatsZeroCompactTime() {
        assertEquals("0s", formatter.format(0L, TimeDisplayFormat.COMPACT));
    }

    @Test
    void formatsCompactMinutesAndSeconds() {
        assertEquals("2m 03s", formatter.format(2460L, TimeDisplayFormat.COMPACT));
    }

    @Test
    void formatsCompactHoursAndMinutes() {
        assertEquals("3h 05m", formatter.format(222000L, TimeDisplayFormat.COMPACT));
    }

    @Test
    void formatsClockWithDays() {
        assertEquals("1d 01:01:01", formatter.format(1801220L, TimeDisplayFormat.CLOCK));
    }
}
