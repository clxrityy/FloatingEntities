package xyz.clxrity.mc.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import xyz.clxrity.mc.ConfigManager;
import xyz.clxrity.mc.EntityManager;
import xyz.clxrity.mc.util.Permissions;

public class RemoveFloatEntity implements CommandExecutor {

    private static final String PRIMARY_COLOR = ConfigManager.ConfigKey.PRIMARY_COLOR.getValue(String.class);
    private static final String SECONDARY_COLOR = ConfigManager.ConfigKey.SECONDARY__COLOR.getValue(String.class);
    private static final String WARNING_COLOR = ConfigManager.ConfigKey.WARNING_COLOR.getValue(String.class);

    private static final Permissions permissions = new Permissions();
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (sender != null) {
                sender.sendMessage(WARNING_COLOR + "You must be a player to use this command");
            }
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(WARNING_COLOR + "Usage: /removefloat <name>");
            return false;
        }

        if ("*".equals(args[0]) || "**".equals(args[0])) {
            if (!permissions.hasPermission(sender, "floatingentities.removefloat.all")) {
                sender.sendMessage(WARNING_COLOR + "You do not have permission to remove all floating entities");
                return false;
            }
            EntityManager.removeAllEntities();
            sender.sendMessage(PRIMARY_COLOR + "All floating entities removed");
            return true;
        }

        Player player = (Player) sender;

        String name = args[0];

        Entity entity = EntityManager.getEntityByName(name);

        if (EntityManager.isOwnEntity(entity, player)) {
            if (!permissions.hasPermission(player, "floatingentities.removefloat")) {
                player.sendMessage(WARNING_COLOR + "You do not have permission to remove this floating entity");
                return false;
            }
        } else {
            if (!permissions.hasPermission(player, "floatingentities.removefloat.others")) {
                player.sendMessage(WARNING_COLOR + "You do not have permission to remove this floating entity");
                return false;
            }
        }

        if (entity == null) {
            player.sendMessage(WARNING_COLOR + "No floating entity found with name " + args[0]);
            return false;
        }

        UUID uuid = entity.getUniqueId();

        EntityManager.removeEntity(uuid);

        player.sendMessage(PRIMARY_COLOR + "Floating entity " + SECONDARY_COLOR + entity.getCustomName() + PRIMARY_COLOR + " removed");

        return true;
    }
}
