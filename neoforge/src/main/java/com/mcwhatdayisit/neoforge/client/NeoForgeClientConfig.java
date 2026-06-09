package com.mcwhatdayisit.neoforge.client;

import com.mcwhatdayisit.common.hud.HudAnchor;
import com.mcwhatdayisit.common.hud.HudConfiguration;
import com.mcwhatdayisit.common.hud.TimeDisplayFormat;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class NeoForgeClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue ENABLED;
    private static final ModConfigSpec.EnumValue<HudAnchor> ANCHOR;
    private static final ModConfigSpec.IntValue OFFSET_X;
    private static final ModConfigSpec.IntValue OFFSET_Y;
    private static final ModConfigSpec.DoubleValue SCALE;
    private static final ModConfigSpec.DoubleValue OPACITY;
    private static final ModConfigSpec.EnumValue<TimeDisplayFormat> TIME_DISPLAY_FORMAT;
    private static final ModConfigSpec.BooleanValue SHOW_WORLD_DAY;
    private static final ModConfigSpec.BooleanValue SHOW_PLAYTIME;

    public static final ModConfigSpec SPEC;

    static {
        BUILDER.push("hud");
        ENABLED = BUILDER.define("enabled", NeoForgeHudConfigDefaults.ENABLED);
        ANCHOR = BUILDER.defineEnum("anchor", NeoForgeHudConfigDefaults.ANCHOR);
        OFFSET_X = BUILDER.defineInRange("offset_x", NeoForgeHudConfigDefaults.OFFSET_X, -1000, 1000);
        OFFSET_Y = BUILDER.defineInRange("offset_y", NeoForgeHudConfigDefaults.OFFSET_Y, -1000, 1000);
        SCALE = BUILDER.defineInRange("scale", NeoForgeHudConfigDefaults.SCALE, 0.5D, 2.0D);
        OPACITY = BUILDER.defineInRange("opacity", NeoForgeHudConfigDefaults.OPACITY, 0.15D, 1.0D);
        TIME_DISPLAY_FORMAT = BUILDER.defineEnum("time_format", NeoForgeHudConfigDefaults.TIME_DISPLAY_FORMAT);
        SHOW_WORLD_DAY = BUILDER.define("show_world_day", NeoForgeHudConfigDefaults.SHOW_WORLD_DAY);
        SHOW_PLAYTIME = BUILDER.define("show_playtime", NeoForgeHudConfigDefaults.SHOW_PLAYTIME);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private NeoForgeClientConfig() {
    }

    public static HudConfiguration hudConfiguration() {
        return new HudConfiguration(
                ENABLED.get(),
                ANCHOR.get(),
                OFFSET_X.get(),
                OFFSET_Y.get(),
                SCALE.get(),
                OPACITY.get(),
                TIME_DISPLAY_FORMAT.get(),
                SHOW_WORLD_DAY.get(),
                SHOW_PLAYTIME.get()
        );
    }
}
