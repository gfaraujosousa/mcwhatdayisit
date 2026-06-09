package com.mcwhatdayisit.common.hud;

import com.mcwhatdayisit.common.ModConstants;
import java.util.Locale;

public final class PlaytimeFormatter {
    public String format(long ticks, TimeDisplayFormat displayFormat) {
        long seconds = Math.max(0L, ticks) / ModConstants.TICKS_PER_REAL_SECOND;
        return switch (displayFormat) {
            case COMPACT -> compact(seconds);
            case CLOCK -> clock(seconds);
        };
    }

    private String compact(long seconds) {
        long days = seconds / 86400L;
        long hours = seconds % 86400L / 3600L;
        long minutes = seconds % 3600L / 60L;
        long remainingSeconds = seconds % 60L;

        if (days > 0L) {
            return String.format(Locale.ROOT, "%dd %02dh %02dm", days, hours, minutes);
        }

        if (hours > 0L) {
            return String.format(Locale.ROOT, "%dh %02dm", hours, minutes);
        }

        if (minutes > 0L) {
            return String.format(Locale.ROOT, "%dm %02ds", minutes, remainingSeconds);
        }

        return remainingSeconds + "s";
    }

    private String clock(long seconds) {
        long days = seconds / 86400L;
        long hours = seconds % 86400L / 3600L;
        long minutes = seconds % 3600L / 60L;
        long remainingSeconds = seconds % 60L;

        if (days > 0L) {
            return String.format(Locale.ROOT, "%dd %02d:%02d:%02d", days, hours, minutes, remainingSeconds);
        }

        return String.format(Locale.ROOT, "%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}
