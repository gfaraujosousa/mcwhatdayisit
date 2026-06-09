package com.mcwhatdayisit.common.hud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HudLayoutTest {
    private final HudLayout layout = new HudLayout();

    @Test
    void resolvesTopLeftPosition() {
        HudLayout.Position position = layout.resolve(HudAnchor.TOP_LEFT, 320, 180, 100, 40, 12, 8);

        assertEquals(new HudLayout.Position(12, 8), position);
    }

    @Test
    void resolvesBottomRightPosition() {
        HudLayout.Position position = layout.resolve(HudAnchor.BOTTOM_RIGHT, 320, 180, 100, 40, 12, 8);

        assertEquals(new HudLayout.Position(208, 132), position);
    }

    @Test
    void clampsPanelInsideScreen() {
        HudLayout.Position position = layout.resolve(HudAnchor.TOP_LEFT, 80, 40, 100, 60, 999, 999);

        assertEquals(new HudLayout.Position(0, 0), position);
    }
}
