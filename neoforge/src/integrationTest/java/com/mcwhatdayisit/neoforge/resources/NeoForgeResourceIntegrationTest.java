package com.mcwhatdayisit.neoforge.resources;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.junit.jupiter.api.Test;

class NeoForgeResourceIntegrationTest {
    @Test
    void languageFilesContainRequiredHudKeys() throws IOException {
        String english = resource("assets/whatdayisit/lang/en_us.json");
        String portuguese = resource("assets/whatdayisit/lang/pt_br.json");

        assertTrue(english.contains("hud.whatdayisit.world_day"));
        assertTrue(english.contains("hud.whatdayisit.playtime"));
        assertTrue(portuguese.contains("hud.whatdayisit.world_day"));
        assertTrue(portuguese.contains("hud.whatdayisit.playtime"));
    }

    @Test
    void modMetadataIsPresent() throws IOException {
        List<String> metadataFiles = resources("META-INF/neoforge.mods.toml");

        assertTrue(metadataFiles.stream().anyMatch(metadata -> metadata.contains("modId=\"whatdayisit\"") && metadata.contains("side=\"CLIENT\"")));
    }

    private String resource(String path) throws IOException {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(path)) {
            assertTrue(stream != null, path);
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private List<String> resources(String path) throws IOException {
        Enumeration<URL> resources = getClass().getClassLoader().getResources(path);
        List<String> contents = new ArrayList<>();

        while (resources.hasMoreElements()) {
            try (InputStream stream = resources.nextElement().openStream()) {
                contents.add(new String(stream.readAllBytes(), StandardCharsets.UTF_8));
            }
        }

        return contents;
    }
}
