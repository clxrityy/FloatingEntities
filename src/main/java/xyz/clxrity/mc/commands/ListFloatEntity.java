package xyz.clxrity.mc.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import xyz.clxrity.mc.ConfigManager;
import xyz.clxrity.mc.EntityManager;

public class ListFloatEntity implements CommandExecutor {

    private static final String PRIMARY_COLOR = ConfigManager.ConfigKey.PRIMARY_COLOR.getValue(String.class);
    private static final String SECONDARY_COLOR = ConfigManager.ConfigKey.SECONDARY__COLOR.getValue(String.class);
    private static final String WARNING_COLOR = ConfigManager.ConfigKey.WARNING_COLOR.getValue(String.class);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(WARNING_COLOR + "You must be a player to use this command");
            return false;
        }

        Player player = (Player) sender;

        List<Entity> entities;
        entities = EntityManager.getEntities();

        if (entities.isEmpty()) {
            player.sendMessage(WARNING_COLOR + "No floating entities found");
            return false;
        }

        player.sendMessage(PRIMARY_COLOR + "Floating entities:");

        for (Entity entity : entities) {
            player.sendMessage(PRIMARY_COLOR + entity.getCustomName() + SECONDARY_COLOR + "\n- " + PRIMARY_COLOR + entity.getLocation().toString() + "\n" + SECONDARY_COLOR + "UUID: " + entity.getUniqueId());
        }

        player.sendMessage(PRIMARY_COLOR + "To remove an entity, use " + SECONDARY_COLOR + "/removefloat <name>");

        return true;
    }
}