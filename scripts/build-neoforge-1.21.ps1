param(
    [string]$VersionFile = "versions/neoforge-1.21.json"
)

$ErrorActionPreference = "Stop"
$versions = Get-Content -Raw $VersionFile | ConvertFrom-Json

foreach ($version in $versions) {
    $arguments = @(
        "clean",
        "check",
        "build",
        "-Pminecraft_version=$($version.minecraft)",
        "-Pminecraft_version_range=$($version.minecraftRange)",
        "-Pneo_version=$($version.neoForge)",
        "-Pneo_version_range=$($version.neoForgeRange)",
        "-Pneoform_version=$($version.neoForm)",
        "-Pmod_version=0.1.0"
    )

    & .\gradlew.bat @arguments

    if ($LASTEXITCODE -ne 0) {
        throw "Build failed for Minecraft $($version.minecraft)"
    }
}
