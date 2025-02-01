# Changelogs <img src="../icon.webp" width="30" alt="FloatingEntities" />

- [1.x](#1x)
    - [1.0-SNAPSHOT](#10-snapshot)
    - [1.0.0](#100)

### Necessary in upcoming releases:

- [x] A better uuid system for removing floating entitites
    - ~~You currently have to do `/removefloat 1c3f20d7-acad-4761-a703-a2bfa8aec1e8`~~
    - ~~I wish to make this ID shorter or a way to customize the ID.~~
    - **You can now do `/removefloat <custom_name>`**
        - Derived from `/float <entity_name> <custom_name>`
- [ ] **Configuration system** - Ability to configure changes in `config.yml`
    - [x] `default-y-offest` - The distance between the entity and user (in block height)
        - **Default**: `2`
    - [x] `default-x-offset` - The distance between the entity and user (in block width)
        - **Default**: `0`
    - [x] `default-z-offset` - The distance between the entity and user (in block depth)
        - **Default**: `0`
    - [x] Colors of all messages
    - [ ] Permissions for removing only your own and/or all floating entities (`*`)
- [ ] Ability to add more than just materials, like actual mobs, etc.

---

# 1.x ~

## 1.0-SNAPSHOT [BETA]

##### <img src="../icon.webp" width="18" height="14" /> [Download](./downloads/1.0-SNAPSHOT/FloatingEntities-1.0-SNAPSHOT.jar)

- Supports `1.21.4`
    - Supports [paper](https://papermc.io/)
    - Supports [spigot](https://www.spigotmc.org/)
- All commands functioning properly:
    - `/float`
    - `/listfloat`
    - `/removefloat`
- All tests passing

## 1.0.0

##### <img src="../icon.webp" width="18" height="14" /> [Download](./downloads/1.0.0/FloatingEntities-1.0.0.jar)

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