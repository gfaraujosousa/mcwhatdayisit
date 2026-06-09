# Testing

Run all tests and build tasks:

```bash
bash ./gradlew check build
```

Run unit tests only:

```bash
bash ./gradlew test
```

Run integration tests only:

```bash
bash ./gradlew integrationTest
```

Manual NeoForge smoke test:

```bash
bash ./gradlew :neoforge:runClient
```

In a singleplayer world, verify that the HUD appears, respects `F1`, shows the current world day, and updates playtime from the vanilla statistic.
