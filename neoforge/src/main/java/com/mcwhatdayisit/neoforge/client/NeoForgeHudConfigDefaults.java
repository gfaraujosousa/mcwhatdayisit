package com.mcwhatdayisit.neoforge.client;

import com.mcwhatdayisit.common.hud.HudAnchor;
import com.mcwhatdayisit.common.hud.TimeDisplayFormat;

public final class NeoForgeHudConfigDefaults {
    public static final boolean ENABLED = true;
    public static final HudAnchor ANCHOR = HudAnchor.TOP_LEFT;
    public static final int OFFSET_X = 8;
    public static final int OFFSET_Y = 8;
    public static final double SCALE = 1.0D;
    public static final double OPACITY = 0.92D;
    public static final TimeDisplayFormat TIME_DISPLAY_FORMAT = TimeDisplayFormat.COMPACT;
    public static final boolean SHOW_WORLD_DAY = true;
    public static final boolean SHOW_PLAYTIME = true;

    private NeoForgeHudConfigDefaults() {
    }
}
