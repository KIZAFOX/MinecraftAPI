# MinecraftAPI

> MinecraftAPI is a library designed to facilitate the development of Minecraft plugins using modern structures and features. This project provides tools for packet management, command handling, and more, built on top of Bukkit's structure.

- Name: MinecraftAPI
- Type: API
- Status: In progress
- Developer(s): [KIZA](https://twitter.com/KIZAFOX)
- Specification (If any):


- Java Version: [JDK:22](https://www.oracle.com/fr/java/technologies/downloads/)
- Spigot Version: [1.21.1](https://www.spigotmc.org)

---

## Table of Contents

<!-- TOC -->
- [Installation](#installation)
- [Usage](#usage)
    - [Basic Setup](#basic-setup)
    - [Event Handling](#event-handling)
    - [Command Handling](#command-handling)
- [Code Examples](#code-examples)
    - [Packets](#packets)
      - [Message](#message)
      - [Title](#title)
      - [Action bar](#actionbar)
- [Informations](#informations)
- [License](#license)
<!-- TOC -->

---

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/KIZAFOX/MinecraftAPI.git
   ```
   
2. **Build the project:**
    ```bash
   mvn clean install
    ```

3. **Import the .jar in your own project**

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
---

## Informations

- ↪️ I'm currently working on the documentation. Create an issues in case of a problem.
- ↪️ Contact me on [twitter](https://twitter.com/KIZAFOX) or [discord](discordapp.com/users/312654382586134529) if you have any questions.

## License

- ↪️ For more details, refer to the license text available [here](LICENSE).