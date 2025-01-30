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

import xyz.clxrity.mc.EntityManager;

public class FloatEntity implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c" + "You must be a player to use this command");
            return false;
        }

        Player player = (Player) sender;

        Material material = Material.CYAN_DYE;
        String name = material.name();

        if (args.length > 0) {
            try {
                material = Material.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                player.sendMessage("§cInvalid material: §6" + args[0]);
                return false;
            }
        }

        if (args.length > 1) {
            if (args[1].equals("*") || args[1].equals("**")) {
                player.sendMessage("§cInvalid name: §6" + args[1]);
                return false;
            } else {
                name = args[1];
            }
        }

        World world = player.getWorld();
        Location location = player.getLocation().add(0, 1, 0);

        ItemDisplay floatingBlock = (ItemDisplay) world.spawnEntity(location, EntityType.ITEM_DISPLAY);

        floatingBlock.setItemStack(new ItemStack(material));
        floatingBlock.setCustomName(name);
        floatingBlock.setBillboard(Display.Billboard.FIXED);

        EntityManager.addEntity(floatingBlock);

        player.sendMessage("\u00a7aFloating entity created!" +  "\n" + "\u00a76Material: " + material.name(), "\u00a76 Name: " + "\u00a76" + name + "\n" +  "§9UUID: " + floatingBlock.getUniqueId());

        return true;
    }
}
