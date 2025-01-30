package xyz.clxrity.mc;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import xyz.clxrity.mc.commands.FloatEntity;
import xyz.clxrity.mc.commands.ListFloatEntity;
import xyz.clxrity.mc.commands.RemoveFloatEntity;


/*
 * floatingentities java plugin
 */
public class Main extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("FloatingEntities");


  @Override
  public void onEnable()
  {
    LOGGER.info("FloatingEntities enabled");

    // CONFIG
    
    // COMMANDS

    // // /floatentity <material> <name>
    // getCommand("float").setExecutor(new FloatEntity());

    // // /listfloatentity
    // getCommand("listfloat").setExecutor(new ListFloatEntity());

    // // /removefloatentity <UUID>
    // getCommand("removefloat").setExecutor(new RemoveFloatEntity());

    if (getCommand("float") != null) {
      getCommand("float").setExecutor(new FloatEntity());
    } else {
      LOGGER.warning("Command /float not found");
    }

    if (getCommand("listfloat") != null) {
      getCommand("listfloat").setExecutor(new ListFloatEntity());
    } else {
      LOGGER.warning("Command /listfloat not found");
    }

    if (getCommand("removefloat") != null) {
      getCommand("removefloat").setExecutor(new RemoveFloatEntity());
    } else {
      LOGGER.warning("Command /removefloat not found");
    }
  }

  @Override
  public void onDisable()
  {
    LOGGER.info("FloatingEntities disabled");
  }
}
