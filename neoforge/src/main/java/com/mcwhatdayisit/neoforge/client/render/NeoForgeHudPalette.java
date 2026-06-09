package com.mcwhatdayisit.neoforge.client.render;

public final class NeoForgeHudPalette {
    public int panelBackground(double opacity) {
        return argb((int) Math.round(190.0D * opacity), 15, 18, 24);
    }

    public int panelHighlight(double opacity) {
        return argb((int) Math.round(60.0D * opacity), 255, 255, 255);
    }

    public int label(double opacity) {
        return argb((int) Math.round(190.0D * opacity), 218, 226, 236);
    }

    public int value(double opacity) {
        return argb((int) Math.round(255.0D * opacity), 255, 255, 255);
    }

    public int accent(long dayTime, double opacity) {
        long normalizedTime = Math.floorMod(dayTime, 24000L);

        if (normalizedTime < 6000L) {
            return argb((int) Math.round(230.0D * opacity), 96, 190, 128);
        }

        if (normalizedTime < 12000L) {
            return argb((int) Math.round(230.0D * opacity), 246, 199, 93);
        }

        if (normalizedTime < 18000L) {
            return argb((int) Math.round(230.0D * opacity), 236, 126, 81);
        }

        return argb((int) Math.round(230.0D * opacity), 111, 160, 232);
    }

    private int argb(int alpha, int red, int green, int blue) {
        return clamp(alpha) << 24 | clamp(red) << 16 | clamp(green) << 8 | clamp(blue);
    }

    private int clamp(int channel) {
        return Math.max(0, Math.min(255, channel));
    }
}
