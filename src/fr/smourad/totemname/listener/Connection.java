package fr.smourad.totemname.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.smourad.totemname.Main;
import fr.smourad.totemname.Team;

public class Connection implements Listener {

	private int task;
	
	Main main;
	
	public Connection(Main plugin) {
		this.main = plugin;
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		e.setJoinMessage(ChatColor.GOLD + p.getName() + " a rejoint la partie");
		p.teleport(new Location(p.getWorld(), 0.5, 94.0, 0.5));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0));
		p.setHealth(p.getHealthScale());
		p.getInventory().clear();
		
		task = Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
            
            @Override
            public void run() {
            	p.getInventory().clear();
                p.getInventory().setItem(8, new ItemStack(Material.ACACIA_DOOR_ITEM, 1));                
                p.getInventory().setItem(0, new Wool(DyeColor.BLUE).toItemStack(1));
                p.getInventory().setItem(1, new Wool(DyeColor.WHITE).toItemStack(1));
                p.getInventory().setItem(2, new Wool(DyeColor.RED).toItemStack(1));
                Bukkit.getScheduler().cancelTask(task);
            }
            
        });
		
		if (Team.getPlayMode()) {
			p.kickPlayer(ChatColor.RED + "La partie est déjà en cours");
		}
	}
}
