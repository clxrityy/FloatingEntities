# FloatingEntities

- [Changelogs](#changelogs)
- [Overview](#overview)
- [Upcoming releases](#upcoming-releases)


## Changelogs <img src="../icon.webp" width="30" alt="FloatingEntities" />

- [1.x](#1x)
    - [1.0-SNAPSHOT](#10-snapshot)
    - [1.0.0](#100)
    - [1.0.1](#101)

## Overview

| Release | Feature(s) | Supported Version(s) |
| :-- | :---- | :--- |
| [1.0-SNAPSHOT](#10-snapshot-beta) | <h6>Commands:</h6><ul><li>`/foat`</li><li>`/listfloat`</li><li>`/removefloat`</li></ul> | <li>`1.21.4`</li> |
| [1.0.0](#100) | <li>Data manager & file</li><li>Config manager & file</li> | <li>`1.21.4`</li> |
| [1.0.1](#101) | <li>Permissions</li> | <li>`1.21.4`</li> |



## Ucoming releases:

- [ ] Ability to add more than just materials, like actual mobs, etc.
- [ ] Add support for earlier versions (at least 1.8+ ideal)

---

# 1.x ~

## 1.0-SNAPSHOT [BETA]

[<kbd><img src="../icon.webp" width="18" height="14" /> Download </kbd>](./downloads/v1/1.0-SNAPSHOT/FloatingEntities-1.0-SNAPSHOT.jar)

- Supports `1.21.4`
    - Supports [paper](https://papermc.io/)
    - Supports [spigot](https://www.spigotmc.org/)
- All commands functioning properly:
    - `/float`
    - `/listfloat`
    - `/removefloat`
- All tests passing

## 1.0.0

[<kbd><img src="../icon.webp" width="18" height="14" /> Download </kbd>](./downloads/v1/1.0.0/FloatingEntities-1.0.0.jar)

- Fixed bug where the name of the floating entity would by default be `CYAN_DYE` if the user hadn't set it.
    - Defined the name after the material is defined.
- Added [`DataManager`](../src/main/java/xyz/clxrity/mc/DataManager.java)
    - A class to manage the data file (`data.json`)
    - This will allow for data to be retained & altered even when the plugin is off/removed.
    - Default `data.json`:
    ```json
    {
        entities: []
    }
    ```
    - With an entity:
    ```json
    {
        "entities": [
            {
                "owner": "c90536de-95cb-4fcf-b41d-a56068556f45",
                "type": "ITEM_DISPLAY",
                "name": "cyan_at_spawn",
                "material": "CYAN_DYE"
                "x": 227.48203614022864,
                "y": 65.0,
                "z": 61.14233950011795,
                "world": "world",
                "uuid": "1c3f20d7-acad-4761-a703-a2bfa8aec1e8"
            }
        ]
    }
    ```
- Added [`ConfigManager`](../src/main/java/xyz/clxrity/mc/ConfigManager.java)
    - A class to manage the config file (`config.yml`)
    - This will auto generate the file if it does not exist.
    - Also set all config default values if they are non existent.

## 1.0.1

[<kbd><img src="../icon.webp" width="18" height="14" /> Download </kbd>](./downloads/v1/1.0.1/FloatingEntities-1.0.1.jar)

- Added functionality for permissions:
    - #### `/float`
        - `floatingentities.command.float`
    - #### `/listfloat`
        - `floatingentities.command.listfloat`
    - #### `/removefloat`
        - `floatingentities.command.removefloat.all`
            - `/removefloat *`
            - Remove all floating entities
            - **MUST ALSO HAVE** `floatingentities.command.removefloat.others`
                - This will be changed in future releases to allow for removing only your own floating entities.
        - `floatingentities.command.removefloat.others`
            - Remove floating entities created by other players
> All permissions are `op` by default