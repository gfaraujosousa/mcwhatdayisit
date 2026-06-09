package com.mcwhatdayisit.common.hud;

public final class HudLayout {
    public Position resolve(HudAnchor anchor, int screenWidth, int screenHeight, int panelWidth, int panelHeight, int offsetX, int offsetY) {
        int safeScreenWidth = Math.max(0, screenWidth);
        int safeScreenHeight = Math.max(0, screenHeight);
        int safePanelWidth = Math.max(0, panelWidth);
        int safePanelHeight = Math.max(0, panelHeight);
        int maximumX = Math.max(0, safeScreenWidth - safePanelWidth);
        int maximumY = Math.max(0, safeScreenHeight - safePanelHeight);
        int anchorX = switch (anchor) {
            case TOP_LEFT, BOTTOM_LEFT -> offsetX;
            case TOP_RIGHT, BOTTOM_RIGHT -> safeScreenWidth - safePanelWidth - offsetX;
        };
        int anchorY = switch (anchor) {
            case TOP_LEFT, TOP_RIGHT -> offsetY;
            case BOTTOM_LEFT, BOTTOM_RIGHT -> safeScreenHeight - safePanelHeight - offsetY;
        };

        return new Position(clamp(anchorX, 0, maximumX), clamp(anchorY, 0, maximumY));
    }

    private static int clamp(int value, int minimum, int maximum) {
        return Math.max(minimum, Math.min(maximum, value));
    }

    public record Position(int x, int y) {
    }
}
