package xyz.clxrity.mc.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import xyz.clxrity.mc.EntityManager;

public class RemoveFloatEntity implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c" + "You must be a player to use this command");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage("§c" + "Usage: /removefloatentity <UUID>");
            return false;
        }

        if ("*".equals(args[0]) || "**".equals(args[0])) {
            EntityManager.removeAllEntities();
            sender.sendMessage("§3" + "All floating entities removed");
            return true;
        }

        Player player = (Player) sender;

        UUID uuid = UUID.fromString(args[0]);

        Entity entity = EntityManager.getEntityByUUID(uuid);

        if (entity == null) {
            player.sendMessage("§c" + "No floating entity found with UUID " + args[0]);
            return false;
        }

        EntityManager.removeEntity(entity);

        player.sendMessage("§3" + "Floating entity " + "§6" + entity.getCustomName() + "§3" + " removed");

        return true;
    }
}
