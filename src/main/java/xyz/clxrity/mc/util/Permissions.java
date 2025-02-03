package xyz.clxrity.mc.util;

import org.bukkit.command.CommandSender;

public class Permissions {

    public boolean hasPermission(CommandSender sender, String permission) {
        if (sender.isOp()) {
            return true;
        } else {
            return sender.hasPermission(permission);
        }
    }
}
