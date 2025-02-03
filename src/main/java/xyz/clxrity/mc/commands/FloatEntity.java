package xyz.clxrity.mc.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import xyz.clxrity.mc.ConfigManager;
import xyz.clxrity.mc.EntityManager;
import xyz.clxrity.mc.util.Permissions;

public class FloatEntity implements CommandExecutor {

    private static final String DEFAULT_MATERIAL = ConfigManager.ConfigKey.DEFAULT_MATERIAL.getValue(String.class);
    private static final int DEFAULT_Y_OFFSET = ConfigManager.ConfigKey.DEFAULT_Y_OFFSET.getValue(Integer.class);
    private static final int DEFAULT_X_OFFSET = ConfigManager.ConfigKey.DEFAULT_X_OFFSET.getValue(Integer.class);
    private static final int DEFAULT_Z_OFFSET = ConfigManager.ConfigKey.DEFAULT_Z_OFFSET.getValue(Integer.class);
    private static final String DEFAULT_ENTITY_NAME = ConfigManager.ConfigKey.DEFAULT_ENTITY_NAME
            .getValue(String.class);
    private static final String DEFAULT_ENTITY_TYPE = ConfigManager.ConfigKey.DEFAULT_ENTITY_TYPE
            .getValue(String.class);
    private static final String PRIMARY_COLOR = ConfigManager.ConfigKey.PRIMARY_COLOR.getValue(String.class);
    private static final String SECONDARY_COLOR = ConfigManager.ConfigKey.SECONDARY__COLOR.getValue(String.class);
    private static final String WARNING_COLOR = ConfigManager.ConfigKey.WARNING_COLOR.getValue(String.class);
    private static final String ERROR_COLOR = ConfigManager.ConfigKey.ERROR_COLOR.getValue(String.class);
    private static final String SUCCESS_COLOR = ConfigManager.ConfigKey.SUCCESS_COLOR.getValue(String.class);

    private static final Permissions permissions = new Permissions();

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            String msg = WARNING_COLOR + "You must be a player to use this command";

            sender.sendMessage(msg);
            return false;
        }

        Player player = (Player) sender;

        if (!permissions.hasPermission(player, "floatingentities.command.float")) {
            player.sendMessage(WARNING_COLOR + "You do not have permission to use this command");
            return false;
        }

        Material material = Material.getMaterial(DEFAULT_MATERIAL);

        if (args.length > 0) {
            try {
                material = Material.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                player.sendMessage(ERROR_COLOR + "Invalid material: " + args[0]);
                return false;
            }
        } else {
            player.sendMessage(ERROR_COLOR + "Material not specified\n" + SECONDARY_COLOR + "Usage: /float <material> [name]");
            return false;
        }

        String name = DEFAULT_ENTITY_NAME;

        if (args.length > 1) {
            if (args[1].equals("*") || args[1].equals("**")) {
                player.sendMessage(ERROR_COLOR + "Invalid name: " + WARNING_COLOR + args[1]);
                return false;
            } else {
                if (EntityManager.getEntityByName(args[1]) != null) {
                    player.sendMessage(ERROR_COLOR + "Name already in use: " + WARNING_COLOR + args[1]);
                    return false;
                }
                name = args[1];
            }
        } else {
            player.sendMessage(ERROR_COLOR + "Name not specified\n" + SECONDARY_COLOR + "Usage: /float <material> [name]");
            return false;
        }

        EntityType entityType = EntityType.valueOf(DEFAULT_ENTITY_TYPE);

        World world = player.getWorld();
        Location location = player.getLocation().add(DEFAULT_X_OFFSET, DEFAULT_Y_OFFSET, DEFAULT_Z_OFFSET);
        ItemDisplay floatingBlock = (ItemDisplay) world.spawnEntity(location, entityType);

        floatingBlock.setItemStack(new ItemStack(material));
        floatingBlock.setCustomName(name);
        floatingBlock.setBillboard(Display.Billboard.FIXED);

        EntityManager.addEntity(floatingBlock, player, name, material.name());

        // Replace the final player.sendMessage with:
        player.sendMessage(String.format("%sFloating entity created!\n%sMaterial: %s\n%sName: %s\n%sUUID: %s",
                SUCCESS_COLOR,
                SECONDARY_COLOR, material.name(),
                SECONDARY_COLOR, name,
                PRIMARY_COLOR, floatingBlock.getUniqueId()));

        return true;
    }
}
