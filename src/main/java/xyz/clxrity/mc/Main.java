package xyz.clxrity.mc;

import java.util.logging.Logger;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.JsonObject;

import xyz.clxrity.mc.commands.FloatEntity;
import xyz.clxrity.mc.commands.ListFloatEntity;
import xyz.clxrity.mc.commands.RemoveFloatEntity;

/*
 * floatingentities java plugin
 */
public class Main extends JavaPlugin {
  private static final Logger LOGGER = Logger.getLogger("FloatingEntities");

  @Override
  public void onEnable() {
    LOGGER.info("FloatingEntities enabled");

    /**
     * CONFIG
     */
    
     ConfigManager.setup(this);

    /**
     * DATA
     */

    DataManager.setup(this);

    JsonObject data = DataManager.loadData();

    if (data.isEmpty()) {
      data.addProperty("version", 1);
      JsonObject entities = new JsonObject();
      data.add("entities", entities);
      DataManager.saveData(data);
      LOGGER.info("Data file created");
    } else {
      LOGGER.info("Data file loaded");
    }

    /**
     * COMMANDS
     */

    PluginCommand floatCommand = getCommand("float");
    PluginCommand listCommand = getCommand("listfloat");
    PluginCommand removeCommand = getCommand("removefloat");

    if (floatCommand != null) {
      floatCommand.setExecutor(new FloatEntity());
    } else {
      LOGGER.warning("Command /float not found");
    }

    if (listCommand != null) {
      listCommand.setExecutor(new ListFloatEntity());
    } else {
      LOGGER.warning("Command /listfloat not found");
    }

    if (removeCommand != null) {
      removeCommand.setExecutor(new RemoveFloatEntity());
    } else {
      LOGGER.warning("Command /removefloat not found");
    }
  }

  @Override
  public void onDisable() {
    LOGGER.info("FloatingEntities disabled");
  }
}
