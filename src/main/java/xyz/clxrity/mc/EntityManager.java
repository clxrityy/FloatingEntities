package xyz.clxrity.mc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Entity;

public class EntityManager {
    private static final List<Entity> entities = new ArrayList<>();

    public static void addEntity(Entity entity) {
        entities.add(entity);
    }

    public static void removeEntity(Entity entity) {
        entities.remove(entity);
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
    }

    public static Entity getEntityByUUID(UUID uuid) {
        for (Entity entity : entities) {
            if (entity.getUniqueId().equals(uuid)) {
                return entity;
            }
        }

        return null;
    }
}
