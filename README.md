# Balanced Stack Sizes - Minecraft 26.2 Port

An unofficial community port of [HB0P's Resizable Stacks / Balanced Stack Sizes](https://github.com/HB0P/Resizable-Stacks) for Minecraft Java Edition 26.2.

The mod lets a server configure maximum stack sizes for individual items or item tags. It retains the original `balancedstacksizes` mod ID and configuration filename so existing Balanced Stack Sizes configurations can continue to work.

> This fork is not an official HB0P release. HB0P created the original mod; TheRonGDD maintains this Minecraft 26.2 compatibility port.

## Requirements

- Minecraft 26.2
- Java 25 or newer
- Fabric Loader 0.19.3 or newer
- [Fabric API](https://modrinth.com/mod/fabric-api)
- [owo-lib 0.13.1+26.2](https://modrinth.com/mod/owo-lib)

Install the mod and its dependencies on the server and connecting clients. The server configuration is synchronized to clients by owo-lib.

## Installation

1. Install Fabric Loader for Minecraft 26.2.
2. Put Fabric API, owo-lib, and the Balanced Stack Sizes jar in the `mods` folder.
3. Start Minecraft once to generate `config/balancedstacksizes.json5`.
4. Stop Minecraft, edit the configuration, and start it again.

## Configuration

Map an item identifier to its desired maximum stack size:

```json5
{
  "stackSizes": {
    "minecraft:totem_of_undying": 16,
    "minecraft:ender_pearl": 64,
    "minecraft:potion": 16
  }
}
```

Item tags are supported by prefixing the identifier with `#`:

```json5
{
  "stackSizes": {
    "#minecraft:music_discs": 64
  }
}
```

Entries with a value less than or equal to zero are ignored. Unlisted items keep their normal Minecraft maximum.

## What changed for 26.2

- Migrated the codebase from Yarn/intermediary names to Minecraft's official names.
- Ported the ItemStack codec, container limit, and per-item maximum hooks.
- Ported item identifier and item-tag matching.
- Ported long stack-count rendering to Minecraft 26.2's GUI extraction API.
- Updated the build to Java 25, Gradle 9.5.1, Fabric Loader 0.19.3, Fabric API 0.158.0+26.2, and owo-lib 0.13.1+26.2.

See [PORT-REPORT.md](PORT-REPORT.md) for provenance and validation details.

## Building

With Java 25 selected:

```text
./gradlew clean build --no-daemon
```

On Windows:

```powershell
.\gradlew.bat clean build --no-daemon
```

Artifacts are written to `build/libs`.

## Validation status

- Common and client source sets compile against Minecraft 26.2.
- Dedicated Fabric 26.2 server startup, command handling, and clean shutdown were tested.
- Existing playerdata containing a totem stack above vanilla's limit decoded without stack errors.
- Four-digit client count-label rendering compiles, but its exact visual positioning still needs broader graphical testing.

## License and attribution

The original project and this port are distributed under the [MIT License](LICENSE.txt). The original copyright and license notice are retained.
