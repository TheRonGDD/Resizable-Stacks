# Balanced Stack Sizes 26.3 Port

Built and bootstrap-tested: 2026-09-15

## Provenance

- Upstream: https://github.com/HB0P/Resizable-Stacks
- Pinned commit: `3910e684c081f4b24a2d6db09a5f31e8702f884a`
- License: MIT
- Original source archive SHA-256: `7740EB7DBE90FDE946C5567D254353A48E317FA1E8D00776BFB6D64A930A54BD`

The archived upstream project was renamed from Balanced Stack Sizes to Resizable Stacks. This local port retains the installed server's `balancedstacksizes` mod id and `balancedstacksizes.json5` config name for compatibility.

## Port changes

- Minecraft `26.3`, Fabric Loader `0.19.5`, Fabric API `0.160.5+26.3`, Java `25`, Gradle `9.5.1`, Fabric Loom `1.17.21`.
- Removed the owo-lib runtime dependency because no 26.3 build exists and its 26.2 mixins fail at startup.
- Replaced the generated owo config wrapper with a small Gson loader that preserves the existing `balancedstacksizes.json5` shape and all production overrides.
- Retained the prior Yarn/intermediary-to-official-name migration and verified it against Minecraft 26.3.
- Retargeted the stack count codec to `ItemStack.lambda$static$1` and scoped the range expansion to ItemStack rather than every `ExtraCodecs.intRange(1, 99)` call.
- Ported the container maximum and per-item maximum hooks.
- Ported registry item-id and tag matching.
- Ported four-digit client count-label scaling to `GuiGraphicsExtractor` and `Matrix3x2fStack`.

## Artifact

- Candidate jar: `BalancedStackSizes-1.1.0+mc26.3-port.1.jar`
- SHA-256: `AAB0CB1C2A931890918030E0CFEBC824114B1C5299EE8C342A25484B9ED0A586`
- Source branch: `mc26.3-port`

## Validation

- `gradlew clean build --no-daemon`: successful for common and client source sets on 2026-09-15.
- Dedicated 26.3 test server: loaded all 47 production stack-size overrides, reached `Done`, and shut down cleanly.
- Full stack behavior, oversized playerdata, filled-shulker safety, and graphical client-label tests remain open before release.

Removing owo also removes its server-to-client config synchronization. Dedicated-server behavior is operational, but a 26.3 client test is required before publication or production deployment.
