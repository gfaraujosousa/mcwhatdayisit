package com.mcwhatdayisit.neoforge.client.render;

import com.mcwhatdayisit.common.hud.HudConfiguration;
import com.mcwhatdayisit.common.hud.HudContent;
import com.mcwhatdayisit.common.hud.HudContentFactory;
import com.mcwhatdayisit.common.hud.HudLayout;
import com.mcwhatdayisit.common.hud.HudLine;
import com.mcwhatdayisit.common.hud.HudSnapshot;
import com.mcwhatdayisit.neoforge.client.NeoForgeHudSnapshotProvider;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.joml.Matrix3x2fStack;

public final class NeoForgeHudRenderer {
    private static final int MINIMUM_PANEL_WIDTH = 96;
    private static final int HORIZONTAL_PADDING = 8;
    private static final int VERTICAL_PADDING = 6;
    private static final int LINE_SPACING = 4;
    private static final int ACCENT_WIDTH = 3;

    private final NeoForgeHudSnapshotProvider snapshotProvider;
    private final Supplier<HudConfiguration> configurationSupplier;
    private final HudContentFactory contentFactory;
    private final HudLayout layout;
    private final NeoForgeHudPalette palette;

    public NeoForgeHudRenderer(NeoForgeHudSnapshotProvider snapshotProvider, Supplier<HudConfiguration> configurationSupplier) {
        this.snapshotProvider = snapshotProvider;
        this.configurationSupplier = configurationSupplier;
        this.contentFactory = new HudContentFactory();
        this.layout = new HudLayout();
        this.palette = new NeoForgeHudPalette();
    }

    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.options.hideGui) {
            return;
        }

        HudConfiguration configuration = configurationSupplier.get();

        if (!configuration.hasVisibleContent()) {
            return;
        }

        Optional<HudSnapshot> snapshot = snapshotProvider.snapshot();

        if (snapshot.isEmpty()) {
            return;
        }

        HudContent content = contentFactory.create(configuration, snapshot.get().dayTime(), snapshot.get().playtimeTicks());

        if (!content.hasLines()) {
            return;
        }

        renderContent(guiGraphics, minecraft.font, configuration, content, snapshot.get().dayTime());
    }

    private void renderContent(GuiGraphics guiGraphics, Font font, HudConfiguration configuration, HudContent content, long dayTime) {
        List<RenderableHudLine> lines = content.lines().stream()
                .map(this::renderableLine)
                .toList();
        int panelWidth = panelWidth(font, lines);
        int panelHeight = panelHeight(font, lines.size());
        int scaledPanelWidth = (int) Math.ceil(panelWidth * configuration.scale());
        int scaledPanelHeight = (int) Math.ceil(panelHeight * configuration.scale());
        HudLayout.Position position = layout.resolve(
                configuration.anchor(),
                guiGraphics.guiWidth(),
                guiGraphics.guiHeight(),
                scaledPanelWidth,
                scaledPanelHeight,
                configuration.offsetX(),
                configuration.offsetY()
        );
        Matrix3x2fStack pose = guiGraphics.pose();

        pose.pushMatrix();
        pose.translate(position.x(), position.y());
        pose.scale((float) configuration.scale(), (float) configuration.scale());
        drawPanel(guiGraphics, font, lines, panelWidth, panelHeight, dayTime, configuration.opacity());
        pose.popMatrix();
    }

    private RenderableHudLine renderableLine(HudLine line) {
        return new RenderableHudLine(Component.translatable(line.type().translationKey()), Component.literal(line.value()));
    }

    private int panelWidth(Font font, List<RenderableHudLine> lines) {
        int longestLine = lines.stream()
                .mapToInt(line -> font.width(line.label()) + font.width(line.value()) + 12)
                .max()
                .orElse(MINIMUM_PANEL_WIDTH);
        return Math.max(MINIMUM_PANEL_WIDTH, longestLine + HORIZONTAL_PADDING * 2);
    }

    private int panelHeight(Font font, int lineCount) {
        int totalLineHeight = lineCount * font.lineHeight;
        int totalLineSpacing = Math.max(0, lineCount - 1) * LINE_SPACING;
        return VERTICAL_PADDING * 2 + totalLineHeight + totalLineSpacing;
    }

    private void drawPanel(GuiGraphics guiGraphics, Font font, List<RenderableHudLine> lines, int panelWidth, int panelHeight, long dayTime, double opacity) {
        guiGraphics.fill(0, 0, panelWidth, panelHeight, palette.panelBackground(opacity));
        guiGraphics.fill(0, 0, panelWidth, 1, palette.panelHighlight(opacity));
        guiGraphics.fill(0, panelHeight - 1, panelWidth, panelHeight, palette.panelHighlight(opacity));
        guiGraphics.fill(0, 0, ACCENT_WIDTH, panelHeight, palette.accent(dayTime, opacity));

        int y = VERTICAL_PADDING;

        for (RenderableHudLine line : lines) {
            guiGraphics.drawString(font, line.label(), HORIZONTAL_PADDING, y, palette.label(opacity), true);
            guiGraphics.drawString(font, line.value(), panelWidth - HORIZONTAL_PADDING - font.width(line.value()), y, palette.value(opacity), true);
            y += font.lineHeight + LINE_SPACING;
        }
    }

    private record RenderableHudLine(Component label, Component value) {
    }
}
