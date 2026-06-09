# Architecture

The project is structured as a multi-loader-ready Gradle build.

`common` contains loader-neutral HUD rules:

- day calculation
- playtime formatting
- HUD content creation
- layout anchoring
- configuration model

`neoforge` contains the first loader implementation:

- NeoForge client entrypoint
- client TOML config
- HUD layer registration
- Minecraft snapshot provider
- GUI rendering
- loader resources

The NeoForge jar packages compiled `common` classes directly so the released artifact is self-contained.

Future Fabric and Forge modules should depend on `common`, provide their own config and renderer adapter, and keep all shared behavior in `common`.

The NeoForge adapter avoids direct references to the renamed Minecraft resource identifier class so the same source can compile for stable NeoForge builds across Minecraft 1.21.x.
