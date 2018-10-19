package fr.smourad.totemname.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import fr.smourad.totemname.Main;
import fr.smourad.totemname.Team;
import net.md_5.bungee.api.ChatColor;

public class Finish implements CommandExecutor {

	Main main;
	
	public Finish(Main plugin) {
		this.main = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("finish")) {
			if (Team.getPlayMode()) {
				Team.changePlayMode();
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.getInventory().clear();
					player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
					player.sendMessage(ChatColor.AQUA + "La partie est termin\u00E9e de force");
					player.teleport(new Location(player.getWorld(), 0.5, 94.0, 0.5));
				}
				Team.clearTeams();
				
				Player p = (Player) sender;
				
				Location loc1 = new Location(p.getWorld(), 0, 99, 5);
				Location loc2 = new Location(p.getWorld(), 0, 99, -5);
				
				Block glass1 = loc1.getBlock();
				Block glass2 = loc2.getBlock();
				
				glass1.setType(Material.STAINED_GLASS);
				glass2.setType(Material.STAINED_GLASS);
				
				Start.onFinish();
				
				Team.resetWinner();
				
				return true;
			}
			sender.sendMessage(ChatColor.AQUA + "La partie n'est pas encore lanc\u00E9e");
		}	
		return true;
	}

}
