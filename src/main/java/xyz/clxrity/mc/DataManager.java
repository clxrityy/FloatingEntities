package xyz.clxrity.mc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DataManager {

    private static File file;
    private static JavaPlugin plugin;

    public static void setup(JavaPlugin pl) {
        plugin = pl;
        file = new File(plugin.getDataFolder(), "data.json");

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                JsonObject defaultData = new JsonObject();
                JsonArray entities = new JsonArray();
                defaultData.add("entities", entities);
                saveData(defaultData);
            } catch (IOException e) {
                plugin.getLogger().warning(e.getMessage());
            }
        }
    }

    public static JsonObject loadData() {
        if (!file.exists()) return new JsonObject();

        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            JsonElement element = JsonParser.parseReader(reader);

            if (element == null || !element.isJsonObject()) {
                return new JsonObject();
            }

            return element.getAsJsonObject();
        } catch (IOException e) {
            plugin.getLogger().warning(e.getMessage());
            return new JsonObject();
        }
    }

    public static void saveData(JsonObject data) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
        } catch (IOException e) {
            plugin.getLogger().warning(e.getMessage());
        }
    }
}
