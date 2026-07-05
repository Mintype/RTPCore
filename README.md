# RTPCore

RTPCore is a lightweight Random Teleport (RTP) plugin for Paper servers. It provides safe random teleports with configurable settings, cooldowns, delays, and administrator commands.

## Features

- Safe random teleportation
- Configurable settings
- Simple administrative commands
- Permission support

## Commands

| Command | Description |
|---------|-------------|
| `/rtp` | Randomly teleports you. |
| `/rtp help` | Displays the help menu. |
| `/rtp info` | Shows the current RTP settings. |
| `/rtp reload` | Reloads the plugin configuration. |
| `/rtp set radius <value>` | Sets the maximum RTP radius. |
| `/rtp set minradius <value>` | Sets the minimum RTP radius. |
| `/rtp set cooldown <seconds>` | Sets the RTP cooldown. |
| `/rtp set delay <seconds>` | Sets the teleport warmup delay. |
| `/rtp set world <world>` | Sets the world used for random teleports. |

## Permissions

| Permission | Description |
|------------|-------------|
| `rtpcore.rtp` | Allows use of `/rtp`. |
| `rtpcore.help` | Allows access to the help command. |
| `rtpcore.reload` | Allows reloading the configuration. |
| `rtpcore.admin` | Allows access to administrator commands. |
| `rtpcore.bypass.cooldown` | Bypasses the teleport cooldown. |
| `rtpcore.bypass.delay` | Bypasses the teleport delay. |
| `rtpcore.bypass.world` | Bypasses world restrictions. |

## Configuration

Example `config.yml`:

```yaml
radius: 1000
min-radius: 100
cooldown: 60
delay: 3
world: world
```

## Building

Clone the repository and build with Gradle:

```bash
./gradlew build
```

The compiled JAR will be located in:

```
build/libs/
```

## Requirements

- Paper 26.2
- Java 25

## License

This project is licensed under the MIT License.
