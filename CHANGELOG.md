# Changelog

All notable changes to this community port are documented here.

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
