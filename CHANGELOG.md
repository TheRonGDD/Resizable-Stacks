# Changelog

All notable changes to this community port are documented here.

## 1.1.0+mc26.3-port.1 - 2026-09-15

- Ported Balanced Stack Sizes / Resizable Stacks to Minecraft 26.3 official mappings.
- Updated the build for Fabric Loader 0.19.5, Fabric API 0.160.5+26.3, Fabric Loom 1.17.21, and Java 25.
- Removed the owo-lib runtime dependency because no compatible 26.3 release exists and its 26.2 mixins fail during startup.
- Replaced the generated owo configuration wrapper with a Gson loader that preserves the existing `balancedstacksizes.json5` format and production overrides.
- Retargeted the ItemStack count codec expansion for the 26.3 implementation.
- Ported container and per-item maximum stack-size behavior, item identifier and tag matching, and four-digit client count-label scaling.
- Verified a clean build and dedicated-server bootstrap with all 47 production stack-size overrides.

Known limitations: removing owo-lib also removes its server-to-client configuration synchronization. Oversized playerdata, the complete empty/filled shulker matrix, and graphical client count-label behavior still require final regression testing.

## 1.1.0+mc26.2-port.1 - 2026-08-20

- Ported Balanced Stack Sizes / Resizable Stacks to Minecraft 26.2 official mappings.
- Updated the build for Java 25, Gradle 9.5.1, Fabric Loader 0.19.3, and Fabric API 0.158.0+26.2.
- Added required owo-lib 0.13.1+26.2 integration.
- Retargeted ItemStack count codec expansion specifically to the ItemStack codec.
- Ported container and per-item maximum stack-size behavior.
- Ported item identifier and tag matching.
- Ported four-digit stack-count scaling to the 26.2 client GUI API.
- Retained the `balancedstacksizes` mod ID and configuration filename for compatibility.

Known limitation: the client four-digit count-label mixin compiles but still needs broader graphical positioning tests.
