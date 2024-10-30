# MinecraftAPI

> MinecraftAPI is a library designed to facilitate the development of Minecraft plugins using modern structures and features. This project provides tools for packet management, command handling, and more, built on top of Bukkit's structure.

- Name: MinecraftAPI
- Type: API
- Status: In progress
- Developer(s): [KIZA](https://twitter.com/KIZAFOX)
- Specification (If any):


- Java Version: [JDK:22](https://www.oracle.com/fr/java/technologies/downloads/)
- Spigot Version: [1.21.1](https://www.spigotmc.org)


## Table of Contents

<!-- TOC -->
* [MinecraftAPI](#minecraftapi)
  * [Table of Contents](#table-of-contents)
  * [Installation](#installation)
    * [Option 1:](#option-1)
    * [Option 2:](#option-2)
      * [Maven:](#maven)
      * [Gradle:](#gradle)
  * [Usage](#usage)
      * [Basic Setup](#basic-setup)
    * [Event Handling](#event-handling)
    * [Command Handling](#command-handling)
  * [Code Examples](#code-examples)
    * [Packets](#packets)
        * [_Message_](#_message_)
        * [_Title_](#_title_)
        * [_Action Bar_](#_action-bar_)
    * [Command Tab Completer](#command-tab-completer)
  * [Informations](#informations)
  * [License](#license)
<!-- TOC -->

---

## Installation

### Option 1:

1. **Clone the repository:**

```bash
  git clone https://github.com/KIZAFOX/HikariAPI.git
```

2. **Build the project:**
```bash
  mvn clean install
```

3. **Import the .jar into your own project**


### Option 2:

#### Maven:

```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>


<dependency>
    <groupId>com.github.KIZAFOX</groupId>
    <artifactId>MinecraftAPI</artifactId>
    <version>Tag</version>
</dependency>
```

#### Gradle:

```xml
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
	}
}

dependencies {
    implementation 'com.github.KIZAFOX:MinecraftAPI:Tag'
}
```

🚨 For the 'Tag' refer to the release page on GitHub !

## Usage

#### Basic Setup

⚠️ Do not forget to add [_@MinecraftAPI_](https://github.com/KIZAFOX/MinecraftAPI/blob/dev/src/main/java/fr/kiza/minecraftapi/init/MinecraftAPI.java) above your class name.

```java
import fr.kiza.minecraftapi.init.APIInitializer;
import fr.kiza.minecraftapi.init.MinecraftAPI;
    
import org.bukkit.plugin.java.JavaPlugin;

@MinecraftAPI
public final class MyGame extends JavaPlugin {
    @Override
    public void onEnable() {
        APIInitializer.init(this);
    }
}
```

Note: This API requires a database connection to function properly. Please ensure that you configure a database connection using the HikariAPI, as this API operates in conjunction with HikariCP for optimal database management and performance. Make sure to include the appropriate configuration in your code.

Just add the code below:

```java
import fr.kiza.hikariapi.HikariAPI;
import fr.kiza.minecraftapi.init.APIInitializer;
import fr.kiza.minecraftapi.init.MinecraftAPI;

import org.bukkit.plugin.java.JavaPlugin;

@MinecraftAPI
public final class MyGame extends JavaPlugin {
    @Override 
    public void onEnable() {
        HikariAPI.connect("username_mysql", "password_mysql", "host_url", 3306, "database_name");
        APIInitializer.init(this);
    }
}
```

And do not forget to close the pool:

```java
HikariAPI.disconnect();
```

### Event Handling

⚠️ Do not forget to add [_@PlayerListener(YourEvent.class)_](https://github.com/KIZAFOX/MinecraftAPI/blob/dev/src/main/java/fr/kiza/minecraftapi/handler/player/PlayerListener.java) above your event method name.

```java
import fr.kiza.minecraftapi.module.player.PlayerListener;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@PlayerListener(PlayerJoinEvent.class)
public final class MyListener implements Listener {
    @PlayerListener(PlayerJoinEvent.class)
    public void onLogin(final PlayerJoinEvent event) {
        /*
         * Your stuff...
         */
    }
}
```

🚨 With _MinecraftAPI_ you do not need to register the listener/event !

### Command Handling

⚠️ Command Handling is a bit more tricky you have to put two annotations above your class name :

1) [**@CommandRegisterer**](https://github.com/KIZAFOX/MinecraftAPI/blob/dev/src/main/java/fr/kiza/minecraftapi/handler/commands/handler/CommandRegisterer.java)
2) [**@CommandHandler(_Name, Description, Usage, Aliases_)**](https://github.com/KIZAFOX/MinecraftAPI/blob/dev/src/main/java/fr/kiza/minecraftapi/handler/commands/handler/CommandHandler.java)

🚨 **AND do not forget to initialize your commands path in your onEnable !** 🚨

At first :

```java
import fr.kiza.minecraftapi.module.controller.commands.CommandManager;
import fr.kiza.minecraftapi.init.APIInitializer;
import fr.kiza.minecraftapi.init.MinecraftAPI;

import org.bukkit.plugin.java.JavaPlugin;

@MinecraftAPI
public final class MyGame extends JavaPlugin {
  @Override
  public void onEnable() {
    APIInitializer.init(this);

    new CommandManager().registerCommands("YOUR.PATH-PACKAGE.TO.COMMANDS");
  }
}
```

And in your command class :

````java
import fr.kiza.minecraftapi.module.controller.commands.AbstractCommand;
import fr.kiza.minecraftapi.module.controller.commands.handler.CommandRegisterer;
import fr.kiza.minecraftapi.module.controller.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.module.packet.PacketFactory;
import fr.kiza.minecraftapi.module.packet.PacketType;
import fr.kiza.minecraftapi.module.packet.sender.PacketSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandRegisterer
@CommandHandler(
        name = "ping",
        description = "This is a ping command",
        usage = "/ping",
        aliases = {"p", "pong"}
)
public class CommandPing extends AbstractCommand {
  @Override
  public boolean execute(CommandSender sender, Command command, String label, String[] args) {
    PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.MESSAGE_PLAYER)
            .message("Pong!")
            .build()
    );

    return false;
  }

  @Override
  public List<String> tabComplete(CommandSender sender, Command command, String label, String[] args) {
    return List.of();
  }
}
````

---

## Code Examples

### Packets

|         Packet name          | Version | Type | Status |
|:----------------------------:|:-------:|:----:|:------:|
| [MESSAGE_PLAYER](#_message_) |   1.0   |  📩  |   ✅    |
|      [TITLE](#_title_)       |   1.0   |  📩  |   ✅    |
| [ACTION_BAR](#_action-bar_)  |   1.0   |  📩  |   ✅    |


##### _Message_

```java
import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.packet.PacketFactory;
import fr.kiza.minecraftapi.module.packet.PacketType;
import fr.kiza.minecraftapi.module.packet.sender.PacketSender;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener {
    @PlayerListener(PlayerJoinEvent.class)
    public void onLogin(final PlayerJoinEvent event) {
        Core.getInstance().getPlayerHandler().performAction(((player) -> PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.MESSAGE_PLAYER)
                .message(ChatColor.YELLOW + "Welcome to my server " + ChatColor.AQUA + player.getName() + ChatColor.YELLOW + "!")
                .build()
        )), event);
    }
}
```

##### _Title_

```java
import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.packet.PacketFactory;
import fr.kiza.minecraftapi.module.packet.PacketType;
import fr.kiza.minecraftapi.module.packet.sender.PacketSender;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    @PlayerListener(PlayerJoinEvent.class)
    public void onLogin(final PlayerJoinEvent event) {
        Core.getInstance().getPlayerHandler().performAction(((player) -> PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.TITLE)
                .title(ChatColor.BLUE + "|| " + ChatColor.AQUA + player.getName() + ChatColor.BLUE + " ||")
                .subTitle(ChatColor.GRAY + "" + ChatColor.ITALIC + "Welcome to my server...")
                .fadeIn(20)
                .stay(30)
                .fadeOut(20)
                .build()
        )), event);
    }
}
```

##### _Action Bar_

```java
import fr.kiza.minecraftapi.core.Core;
import fr.kiza.minecraftapi.module.packet.PacketFactory;
import fr.kiza.minecraftapi.module.packet.PacketType;
import fr.kiza.minecraftapi.module.packet.sender.PacketSender;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    @PlayerListener(PlayerJoinEvent.class)
    public void onLogin(final PlayerJoinEvent event) {
        Core.getInstance().getPlayerHandler().performAction(((player) -> PacketSender.sendPacket(PacketFactory.getBuilder(PacketType.ACTION_BAR)
                .message(ChatColor.LIGHT_PURPLE + "Hello, " + ChatColor.BOLD + player.getName())
                .build())), event);
    }
}
```

### Command Tab Completer

```java
import fr.kiza.minecraftapi.module.controller.commands.AbstractCommand;
import fr.kiza.minecraftapi.module.controller.commands.handler.CommandHandler;
import fr.kiza.minecraftapi.module.controller.commands.handler.CommandRegisterer;
import fr.kiza.minecraftapi.module.packet.sender.FastPacket;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CommandRegisterer
@CommandHandler(
        name = "tool",
        description = "Tool command",
        usage = "/tool",
        aliases = {"tools", "t"}
)
public class CommandTool extends AbstractCommand {

  private final Material[] TOOLS = {
          Material.IRON_SWORD,
          Material.DIAMOND_PICKAXE,
          Material.IRON_AXE,
          Material.DIAMOND_SHOVEL,
          Material.IRON_HOE,
  };

  @Override
  public boolean execute(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof final Player player)) {
      sender.sendMessage(ChatColor.RED + "This command can only be executed by a player");
      return true;
    }

    if (args.length == 0) {
      this.sendUsage();
      return true;
    } else if (args.length == 1) {
      final String toolName = args[0].toUpperCase();

      try {
        final Material tool = Material.valueOf(toolName);

        if (!Arrays.asList(TOOLS).contains(tool)) {
          this.sendUsage();
        } else {
          player.getInventory().addItem(new ItemStack(tool));
          FastPacket.sendMessage(ChatColor.GREEN + "You have been given a " + tool.name().toLowerCase().replace('_', ' ') + ".");
        }
      } catch (final IllegalArgumentException e) {
        FastPacket.sendMessage(ChatColor.RED + "Invalid material. Please use a valid tool name.");
        this.sendUsage();
        return true;
      }
    }
    return false;
  }

  @Override
  public List<String> tabComplete(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 1) {
      return Arrays.stream(TOOLS)
              .map(Material::toString)
              .collect(Collectors.toList());
    }
    return List.of();
  }

  private void sendUsage() {
    FastPacket.sendMessage(ChatColor.RED + "Usage: /tool <material>");
    Arrays.stream(this.TOOLS).forEach(tools -> FastPacket.sendMessage(ChatColor.GRAY + "- " + tools));
  }
}

```

### Quest

I'll put the entire doc about Quest later.
For the moment please refer to the test [side](https://github.com/KIZAFOX/MinecraftAPI/blob/dev/src/test/java/fr/kiza/test/minecraftapi/MinecraftAPITest.java).

---

## Informations

- ↪️ I'm currently working on the documentation. Create an issues in case of a problem.
- ↪️ Contact me on [twitter](https://twitter.com/KIZAFOX) or [discord](https://discordapp.com/users/312654382586134529) if you have any questions.

## License

- ↪️ For more details, refer to the license text available [here](LICENSE).