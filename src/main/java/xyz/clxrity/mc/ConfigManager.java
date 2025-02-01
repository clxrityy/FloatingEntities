package xyz.clxrity.mc;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
    private static JavaPlugin plugin;
    private static File file;

    public enum ConfigKey {
        VERSION("version"),
        DEFAULT_Y_OFFSET("default-y-offset"),
        DEFAULT_X_OFFSET("default-x-offset"),
        DEFAULT_Z_OFFSET("default-z-offset"),
        DEFAULT_MATERIAL("default-material"),
        DEFAULT_ENTITY_TYPE("default-entity-type"),
        DEFAULT_ENTITY_NAME("default-entity-name"),
        WARNING_COLOR("warning-color"),
        ERROR_COLOR("error-color"),
        SUCCESS_COLOR("success-color"),
        PRIMARY_COLOR("primary-color"),
        SECONDARY__COLOR("secondary-color");

        private final String key;

        ConfigKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        @SuppressWarnings("unchecked")
        public <T> T getValue(Class<T> type) {
            Object value = ConfigManager.getConfig().get(key);
            if (type.isInstance(value)) {
                return (T) value;
            }
            return null; // Or handle unexpected types
        }

    }

    public static void setup(JavaPlugin pl) {
        plugin = pl;

        file = new File(plugin.getDataFolder(), "config.yml");

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        if (!file.exists()) {
            plugin.getLogger().info("config.yml not found, creating...");
            plugin.saveDefaultConfig();
        } else {
            plugin.getLogger().info("config.yml found, loading...");
        }

    }

    public static FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public static void saveConfig() {
        plugin.saveConfig();
    }

    public static void setDefaultConfigValues() {
        FileConfiguration config = getConfig();

        if (!config.contains("version")) {
            config.set("version", 1);
        }

        if (!config.contains("default-y-offset")) {
            config.set("default-y-offset", 2);
        }

        if (!config.contains("default-x-offset")) {
            config.set("default-x-offset", 0);
        }

        if (!config.contains("default-material")) {
            config.set("default-material", "CYAN_DYE");
        }

        if (!config.contains("default-entity-name")) {
            config.set("default-entity-name", "FloatingEntity");
        }

        if (!config.contains("default-entity-type")) {
            config.set("default-entity-type", "ITEM_DISPLAY");
        }

        if (!config.contains("warning-color")) {
            config.set("warning-color", "§c");
        }

        if (!config.contains("error-color")) {
            config.set("error-color", "§4");
        }

        if (!config.contains("success-color")) {
            config.set("success-color", "§a");
        }

        if (!config.contains("primary-color")) {
            config.set("primary-color", "§3");
        }

        if (!config.contains("secondary-color")) {
            config.set("secondary-color", "§b");
        }


        saveConfig();
    }
}
