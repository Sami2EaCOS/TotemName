package fr.smourad.totemname.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.smourad.totemname.Armor;
import fr.smourad.totemname.Main;
import fr.smourad.totemname.Team;
import fr.smourad.totemname.TeamType;

public class Start implements CommandExecutor {

	public static int timer = 0;
	public static int task;
	
	public static BossBar timing = Bukkit.getServer().createBossBar("", BarColor.YELLOW, BarStyle.SEGMENTED_6);
	
	Main main;
	
	public Start(Main plugin) {
		this.main = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("start")) {
			
			if (!(Team.getPlayMode())) {
				int pNumber = Bukkit.getOnlinePlayers().size();

				if (pNumber%2 == 1) {
					pNumber++;
				}
				
				Player p = (Player) sender;
				
				Location loc1 = new Location(p.getWorld(), 0, 99, 5);
				Location loc2 = new Location(p.getWorld(), 0, 99, -5);
				
				Block glass1 = loc1.getBlock();
				Block glass2 = loc2.getBlock();
				
				glass1.setType(Material.STAINED_GLASS);
				glass2.setType(Material.STAINED_GLASS);
				
				int team = pNumber/2;
				int red = 0;
				int blue = 0;
				
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (Team.getTeamType(player) != null) {
						if ((Team.getTeamType(player) == TeamType.BLUE) && (blue < team)) {
							Armor.setArmor(Color.BLUE, player);
							blue++;
						} else if ((Team.getTeamType(player).equals(TeamType.RED)) && (red < team)) {
							Armor.setArmor(Color.RED, player);
							red++;
						}
					}
					if (red < blue) {
						Team.removeFromTeam(player);
						Team.addToTeam(TeamType.RED, player);
						Armor.setArmor(Color.RED, player);
						red++;
					} else {
						Team.removeFromTeam(player);
						Team.addToTeam(TeamType.BLUE, player);
						Armor.setArmor(Color.BLUE, player);
						blue++;
					}	
					
					Armor.setWeapon(player);
				
					player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0));
					
					player.setHealth(player.getHealthScale());
					
					if (Team.getTeamType(player).equals(TeamType.BLUE)) {
						player.teleport(new Location(player.getWorld(), 10.5, 101, 0.5, 90.0f, 0.0f));
					} else if (Team.getTeamType(player).equals(TeamType.RED)) {
						player.teleport(new Location(player.getWorld(), -9.5, 101, 0.5, -90.0f, 0.0f));
					}
				}
				Team.changePlayMode();
				
				for (Player player : Bukkit.getOnlinePlayers()) {
					timing.addPlayer(player);
					player.setGameMode(GameMode.SURVIVAL);
				}
				
				timing.setVisible(true);
				
				task = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
					
					@Override
					public void run() {
						
						if (timer == 60) {
							timer = 0;
							
							Team.changePlayMode();
				            for (Player player : Bukkit.getOnlinePlayers()) {
								player.getInventory().clear();
								player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
								player.sendMessage(ChatColor.AQUA + "La partie est termin\u00E9e");
								player.teleport(new Location(player.getWorld(), 0.5, 94.0, 0.5));
								player.setHealth(player.getHealthScale());
							}
							Team.clearTeams();
							
							Player p = (Player) sender;
							
							Location loc1 = new Location(p.getWorld(), 0, 99, 5);
							Location loc2 = new Location(p.getWorld(), 0, 99, -5);
							
							Block glass1 = loc1.getBlock();
							Block glass2 = loc2.getBlock();
							
							glass1.setType(Material.STAINED_GLASS);
							glass2.setType(Material.STAINED_GLASS);
							
							timing.setVisible(false);
							
							switch (Team.getWinner()) {
							
							case 1:
								for(Player player : Bukkit.getOnlinePlayers()) {
									player.sendTitle(ChatColor.BLUE + "L'\u00E9quipe Bleue a gagn\u00E9e", null, 10, 70, 20);
								}
								break;
								
							case 2:
								for(Player player : Bukkit.getOnlinePlayers()) {
									player.sendTitle(ChatColor.RED + "L'\u00E9quipe Rouge a gagn\u00E9e", null, 10, 70, 20);
								}
								break;
							case 0:
								for(Player player : Bukkit.getOnlinePlayers()) {
									player.sendTitle(ChatColor.DARK_AQUA + "Personne n'a gagn\u00E9", ChatColor.GRAY + "(vous \u00EAtes tous nuls)", 10, 70, 20);
								}
							
							} 
							
							Team.resetWinner();
							
							Bukkit.getScheduler().cancelTask(task);
						}
						
						timing.setProgress((1.0/60)*timer);
						timing.setTitle(ChatColor.WHITE + "Temps: " + timer);
						timer++;
					}
					
				}, 20 , 20);
				
			}
			
		}
		
		return true;
	}

	public static void onFinish() {
		timer = 0;
		timing.setVisible(false);
		
		Bukkit.getScheduler().cancelTask(task);
	}

}
