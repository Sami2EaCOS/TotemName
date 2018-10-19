package fr.smourad.totemname;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.smourad.totemname.commands.Finish;
import fr.smourad.totemname.commands.Start;
import fr.smourad.totemname.listener.Break;
import fr.smourad.totemname.listener.Connection;
import fr.smourad.totemname.listener.Death;
import fr.smourad.totemname.listener.DetectWool;
import fr.smourad.totemname.listener.Fall;
import fr.smourad.totemname.listener.Inventory;

public class Main extends JavaPlugin {
	
	public static int task;
	
	@Override
	public void onEnable() {
		Team.clearTeams();
		loadEvents();
		loadCommands();
		pickaxeRunnable();
	}
	
	@Override
	public void onDisable() {
		Team.clearTeams();
	}
	
	public void loadEvents() {
		this.getServer().getPluginManager().registerEvents(new Break(), this);
		this.getServer().getPluginManager().registerEvents(new DetectWool(), this);
		this.getServer().getPluginManager().registerEvents(new Death(), this);
		this.getServer().getPluginManager().registerEvents(new Inventory(), this);
		this.getServer().getPluginManager().registerEvents(new Connection(this), this);
		this.getServer().getPluginManager().registerEvents(new Fall(), this);
	}
	
	public void loadCommands() {
		this.getCommand("start").setExecutor(new Start(this)); 
		this.getCommand("finish").setExecutor(new Finish(this));
	}
	
	public void pickaxeRunnable() {
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				if (Team.getPlayMode()) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.getInventory().getItemInMainHand().getType() == Material.STONE_PICKAXE) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 0));
						} else {
							p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
						}
					}
				}
			}
		}, 1, 1);
	}
	
}
