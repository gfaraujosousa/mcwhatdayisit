package com.mcwhatdayisit.common.hud;

import java.util.ArrayList;
import java.util.List;

public final class HudContentFactory {
    private final WorldDayCalculator worldDayCalculator;
    private final PlaytimeFormatter playtimeFormatter;

    public HudContentFactory() {
        this(new WorldDayCalculator(), new PlaytimeFormatter());
    }

    public HudContentFactory(WorldDayCalculator worldDayCalculator, PlaytimeFormatter playtimeFormatter) {
        this.worldDayCalculator = worldDayCalculator;
        this.playtimeFormatter = playtimeFormatter;
    }

    public HudContent create(HudConfiguration configuration, long dayTime, long playtimeTicks) {
        if (!configuration.hasVisibleContent()) {
            return new HudContent(List.of());
        }

        List<HudLine> lines = new ArrayList<>();

        if (configuration.showWorldDay()) {
            long worldDay = worldDayCalculator.currentWorldDay(dayTime);
            lines.add(new HudLine(HudLineType.WORLD_DAY, Long.toString(worldDay)));
        }

        if (configuration.showPlaytime()) {
            String playtime = playtimeFormatter.format(playtimeTicks, configuration.timeDisplayFormat());
            lines.add(new HudLine(HudLineType.PLAYTIME, playtime));
        }

        return new HudContent(lines);
    }
}
