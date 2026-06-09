package com.mcwhatdayisit.common.hud;

public enum HudLineType {
    WORLD_DAY("hud.whatdayisit.world_day"),
    PLAYTIME("hud.whatdayisit.playtime");

    private final String translationKey;

    HudLineType(String translationKey) {
        this.translationKey = translationKey;
    }

    public String translationKey() {
        return translationKey;
    }
}
