package fr.smourad.totemname.listener;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.smourad.totemname.Team;
import fr.smourad.totemname.TeamType;

public class Death implements Listener {

	@EventHandler
	public void onDeath(EntityDamageByEntityEvent e) {
		if (Team.getPlayMode()) {		
			Entity entity = e.getEntity();
			if (!(entity instanceof Player)) {
				return;
			}
			
			Player player = (Player) entity;
			Player damager = (Player) e.getDamager();
			
			if (Team.getTeamType(player).equals(Team.getTeamType(damager))) {
				e.setCancelled(true);
				return;
			}
			
			if ((player.getHealth() - e.getDamage()) < 1) {
				player.setHealth(player.getHealthScale());
				e.setCancelled(true);
				
				if (Team.getTeamType(player).equals(TeamType.BLUE)) {
					player.teleport(new Location(player.getWorld(), 10.5, 101, 0.5, 90.0f, 0.0f));
				} else if (Team.getTeamType(player).equals(TeamType.RED)) {
					player.teleport(new Location(player.getWorld(), -9.5, 101, 0.5, -90.0f, 0.0f));
				}
			}
		} else {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if (Team.getPlayMode()) {
			if (!(e.getEntity() instanceof Player)) {
				return;
			}
			
			Player player = (Player) e.getEntity();
			if (e.getCause() == DamageCause.VOID) {
				if (Team.getTeamType(player).equals(TeamType.BLUE)) {
					player.teleport(new Location(player.getWorld(), 10.5, 101, 0.5, 90.0f, 0.0f));
				} else if (Team.getTeamType(player).equals(TeamType.RED)) {
					player.teleport(new Location(player.getWorld(), -9.5, 101, 0.5, -90.0f, 0.0f));
				}
				player.setHealth(player.getHealthScale());
				e.setCancelled(true);
			}
			
			if (e.getCause() == DamageCause.FALL) {
				e.setCancelled(true);
			}
		} else {
			e.setCancelled(true);
		}
	}
}
