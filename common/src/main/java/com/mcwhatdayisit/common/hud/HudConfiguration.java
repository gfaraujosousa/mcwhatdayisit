package com.mcwhatdayisit.common.hud;

import java.util.Objects;

public record HudConfiguration(
        boolean enabled,
        HudAnchor anchor,
        int offsetX,
        int offsetY,
        double scale,
        double opacity,
        TimeDisplayFormat timeDisplayFormat,
        boolean showWorldDay,
        boolean showPlaytime
) {
    public HudConfiguration {
        anchor = Objects.requireNonNull(anchor);
        timeDisplayFormat = Objects.requireNonNull(timeDisplayFormat);
        scale = clamp(scale, 0.5D, 2.0D);
        opacity = clamp(opacity, 0.15D, 1.0D);
    }

    public boolean hasVisibleContent() {
        return enabled && (showWorldDay || showPlaytime);
    }

    private static double clamp(double value, double minimum, double maximum) {
        return Math.max(minimum, Math.min(maximum, value));
    }
}
