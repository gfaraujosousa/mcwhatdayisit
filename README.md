# What days is it?

Client-side Minecraft HUD mod for NeoForge that shows the current world day and the player's total vanilla playtime.

## Status

- Minecraft: `1.21.11`
- Loader: NeoForge `21.11.42`
- Java: `21`
- Mod id: `whatdayisit`
- Version: `0.1.0`

Fabric and Forge are planned after the NeoForge MVP. The project already separates common HUD rules from the NeoForge adapter.

## Supported NeoForge Versions

The release workflow builds the stable NeoForge patches listed in `versions/neoforge-1.21.json`:

- Minecraft `1.21`
- Minecraft `1.21.1`
- Minecraft `1.21.3`
- Minecraft `1.21.4`
- Minecraft `1.21.5`
- Minecraft `1.21.8`
- Minecraft `1.21.10`
- Minecraft `1.21.11`

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

Build every supported NeoForge 1.21.x target locally:

```powershell
.\scripts\build-neoforge-1.21.ps1
```

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

## Releases

GitHub Releases are the source of truth for published jars. Built jars are not committed to the repository.

Tags use `v<mod-version>+mc<minecraft-version>`, for example `v0.1.0+mc1.21.11`.

Publish all supported NeoForge 1.21.x releases from GitHub Actions:

```bash
gh workflow run Release --ref main -f mod_version=0.1.0
```

The workflow builds every supported Minecraft version, uploads each jar as a workflow artifact, creates the matching tag when needed and publishes the jar as a GitHub Release asset.
