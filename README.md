# What days is it?

Client-side Minecraft HUD mod for NeoForge that shows the current world day and the player's total vanilla playtime.

## Status

- Minecraft: `1.21.11`
- Loader: NeoForge `21.11.42`
- Java: `21`
- Mod id: `whatdayisit`
- Version: `0.1.0`

Fabric and Forge are planned after the NeoForge MVP. The project already separates common HUD rules from the NeoForge adapter.

## Features

- Current world day based on world day time.
- Total playtime from the vanilla player statistic.
- Client TOML configuration for visibility, anchor, offsets, scale, opacity and time format.
- English and Brazilian Portuguese HUD translations.
- Unit and integration tests for common behavior and NeoForge resources.

## Build

```bash
bash ./gradlew check build
```

The NeoForge jar is produced in `neoforge/build/libs`.

## Run Client

```bash
bash ./gradlew :neoforge:runClient
```

The HUD config file is created by NeoForge under the run directory after the client starts once.

## Configuration

Client config keys:

- `hud.enabled`
- `hud.anchor`
- `hud.offset_x`
- `hud.offset_y`
- `hud.scale`
- `hud.opacity`
- `hud.time_format`
- `hud.show_world_day`
- `hud.show_playtime`

Supported anchors are `TOP_LEFT`, `TOP_RIGHT`, `BOTTOM_LEFT` and `BOTTOM_RIGHT`.

Supported time formats are `COMPACT` and `CLOCK`.
