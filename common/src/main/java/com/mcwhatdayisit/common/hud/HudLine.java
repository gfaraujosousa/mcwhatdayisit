package com.mcwhatdayisit.common.hud;

import java.util.Objects;

public record HudLine(HudLineType type, String value) {
    public HudLine {
        type = Objects.requireNonNull(type);
        value = Objects.requireNonNull(value);
    }
}
