package xyz.clxrity.mc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class EntityManager {
    private static final List<Entity> entities = new ArrayList<>();
    private static final JsonObject data = DataManager.loadData();
    private static final JsonArray entityData = data.get("entities").getAsJsonArray();
    private static final JsonObject entityObject = new JsonObject();

    private static void saveEntityData() {
        DataManager.saveData(data);
    }

    public static void addEntity(Entity entity, Player player, String name, String material) {

        entities.add(entity);

        entityObject.addProperty("owner", player.getUniqueId().toString());
        entityObject.addProperty("type", entity.getType().name());
        entityObject.addProperty("x", entity.getLocation().getX());
        entityObject.addProperty("y", entity.getLocation().getY());
        entityObject.addProperty("z", entity.getLocation().getZ());
        entityObject.addProperty("world", entity.getWorld().getName());
        entityObject.addProperty("uuid", entity.getUniqueId().toString());
        entityObject.addProperty("name", name);
        entityObject.addProperty("material", material);

        entityData.add(entityObject);

        saveEntityData();
    }

    public static void removeEntity(UUID uuid) {
        Entity entity = getEntityByUUID(uuid);

        if (entity == null) {
            return;
        }

        if (!entity.isDead()) {
            entity.remove();
        }

        entities.remove(entity);

        for (int i = 0; i < entityData.size(); i++) {
            if (entityData.get(i).getAsJsonObject().get("uuid").getAsString().equals(uuid.toString())) {
                entityData.remove(i);
                break;
            }
        }

        saveEntityData();
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static void removeAllEntities() {
        for (Entity entity : entities) {
            if (!entity.isDead()) {
                entity.remove();
            }
        }

        entities.clear();

        for (int i = 0; i < entityData.size(); i++) {
            entityData.remove(i);
        }

        saveEntityData();
    }

    public static Entity getEntityByName(String name) {
        for (JsonElement entity : entityData) {
            JsonObject obj = entity.getAsJsonObject();
            if (name.equals(obj.get("name").getAsString())) {
                UUID uuid = UUID.fromString(obj.get("uuid").getAsString());
                return getEntityByUUID(uuid);
            }
        }
        return null;
    }

    public static Entity getEntityByUUID(UUID uuid) {
        for (Entity entity : entities) {
            if (entity.getUniqueId().equals(uuid)) {
                return entity;
            }
        }

        return null;
    }

    public static boolean isOwnEntity(Entity entity, Player player) {
        for (JsonElement entityElement : entityData) {
            JsonObject obj = entityElement.getAsJsonObject();
            if (entity.getUniqueId().toString().equals(obj.get("uuid").getAsString())) {
                return player.getUniqueId().toString().equals(obj.get("owner").getAsString());
            }
        }
        return false;
    }
}
