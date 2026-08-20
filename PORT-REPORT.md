# Balanced Stack Sizes 26.2 Port

Built and tested: 2026-08-19

## Provenance

- Upstream: https://github.com/HB0P/Resizable-Stacks
- Pinned commit: `3910e684c081f4b24a2d6db09a5f31e8702f884a`
- License: MIT
- Original source archive SHA-256: `7740EB7DBE90FDE946C5567D254353A48E317FA1E8D00776BFB6D64A930A54BD`

The archived upstream project was renamed from Balanced Stack Sizes to Resizable Stacks. This local port retains the installed server's `balancedstacksizes` mod id and `balancedstacksizes.json5` config name for compatibility.

## Port changes

- Minecraft `26.2`, Fabric Loader `0.19.3`, Fabric API `0.158.0+26.2`, Java `25`, Gradle `9.5.1`, Fabric Loom `1.17.19`.
- owo-lib `0.13.1+26.2` is a required external dependency.
- Migrated old Yarn/intermediary APIs to Minecraft 26.2 official names.
- Retargeted the stack count codec to `ItemStack.lambda$static$1` and scoped the range expansion to ItemStack rather than every `ExtraCodecs.intRange(1, 99)` call.
- Ported the container maximum and per-item maximum hooks.
- Ported registry item-id and tag matching.
- Ported four-digit client count-label scaling to `GuiGraphicsExtractor` and `Matrix3x2fStack`.

## Artifact

- Tested development jar: `BalancedStackSizes-1.1.0+mc26.2-local.1.jar`
- First public port version: `1.1.0+mc26.2-port.1`
- Public candidate SHA-256: `FA79CC1247D988E7DEB0FE16E0B5E10804ACD8C23CB2D737CD5127DDE893E6CF`
- SHA-256: `AF00E09C24BD378655055D5A1449874A9DBFDB9C65CD778279F5CE1E1D9240CA`
- SHA-512: `1A42B8B2D9522EF920548F8624946CB4EC1A52EC504AA2635DB0F5F9D522621018CB4A274BB33063238CB8BC1E7AFEDE26F64160DB6CE6669B414A8F113D69DA`

## Validation

- `gradlew clean build --no-daemon`: successful for common and client source sets.
- Dedicated server: reached ready state, Dynmap returned HTTP 200, console checks passed, and shutdown exited 0.
- Copied production playerdata: Carpet fake player `VonRoter` joined and Minecraft decoded a totem stack of 13 (above vanilla's maximum of 1) with no item-stack decode errors.
- Test playerdata restored byte-for-byte after the test.

The client mixin compiles against 26.2 but its visual positioning has not been checked in a graphical client.
