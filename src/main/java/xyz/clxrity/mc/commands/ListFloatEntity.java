package xyz.clxrity.mc.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import xyz.clxrity.mc.EntityManager;

public class ListFloatEntity implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c" + "You must be a player to use this command");
            return false;
        }

        Player player = (Player) sender;

        List<Entity> entities;
        entities = EntityManager.getEntities();

        if (entities.isEmpty()) {
            player.sendMessage("§c" + "No floating entities found");
            return false;
        }

        player.sendMessage("§3" + "Floating entities:");

        for (Entity entity : entities) {
            // player.sendMessage(Color.AQUA + entity.getCustomName() + Color.YELLOW + " - " + Color.AQUA + entity.getLocation().toString() + "\n" + Color.YELLOW + "UUID: " + entity.getUniqueId());
            player.sendMessage("§3" + entity.getCustomName() + "§e" + " - " + "§3" + entity.getLocation().toString() + "\n" + "§e" + "UUID: " + entity.getUniqueId());
        }

        player.sendMessage("§3" + "To remove an entity, use " + "§6" + "/removefloatentity " + "§3" + "<UUID>");

        return true;
    }
}