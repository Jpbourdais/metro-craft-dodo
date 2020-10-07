package fr.theyoungpegasus.metrocraftdodo;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.Set;
import java.util.HashSet;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
	
	private static double speed_multiplier;
	private static double slow_multiplier;
	private static double speed_station;
	
	public static double getSpeedMultiplier() {
        return speed_multiplier;
    }
	
	public static double getSlowMultiplier() {
        return slow_multiplier;
    }
	
	public static double getSpeedStation() {
        return speed_station;
    }
	
	private static Set<CommandSender> receivedHeadsUp = new HashSet<>();
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		Logger logger = getLogger();
        logger.info("Reading config");
        
        // Get config
        speed_multiplier = getConfig().getDouble("speedMultiplier");
        slow_multiplier = getConfig().getDouble("slowMultiplier");
        speed_station = getConfig().getDouble("stationSpeed");
        
        // Display config
        if (speed_multiplier <= 0 || speed_multiplier > 4) {
        	logger.warning("Warning: speed multiplier is not between 0.1 and 4. Using value of 4 as fallback.");
            speed_multiplier = 4;
        } else {
        	logger.info("The speed multiplier value is "+speed_multiplier);
        }
        
        if (slow_multiplier <= 0 || slow_multiplier > 4) {
        	logger.warning("Warning: slow multiplier is not between 0.1 and 4. Using value of 0.5 as fallback.");
        	slow_multiplier = 0.5;
        } else {
        	logger.info("The speed multiplier value is "+slow_multiplier);
        }
        
        if (speed_station <= 0 || speed_station > 4) {
        	logger.warning("Warning: station speed is not between 0.1 and 4. Using value of 0.2 as fallback.");
        	speed_station = 0.2;
        } else {
        	logger.info("The speed multiplier value is "+speed_station);
        }
        logger.info("Registering event listener");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new MinecartListener(), this);
        
        logger.info("Metro Craft Dodo is enabled uwu~");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("unloading... Goodbye :'(");
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("metrocd")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("metrocd.cmd")) {
                    player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                    return true;
                }
            }

            try {
                String type = args[0];
                Double value = Double.parseDouble(args[1]);
                if (type == "speedMultiplier") {
                	speed_multiplier = value;
                } else if (type == "slowMultiplier") {
                	slow_multiplier = value;
                } else if (type == "speedStation") {
                	speed_station = value;
                } else {
                	return true;
                }
            }
            catch (Exception ignore) {
                sender.sendMessage(ChatColor.RED + "The type should be 'speedMultiplier', 'slowMultiplier' or 'speedStation'.");
                sender.sendMessage(ChatColor.RED + "The value should be a number.");
                return false;
            }
            
            sender.sendMessage(ChatColor.GREEN + "Oki");
            return true;
        }

        return false;
    }
	
}
