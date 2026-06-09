package com.mcwhatdayisit.common.hud;

import java.util.List;

public record HudContent(List<HudLine> lines) {
    public HudContent {
        lines = List.copyOf(lines);
    }

    public boolean hasLines() {
        return !lines.isEmpty();
    }
}
