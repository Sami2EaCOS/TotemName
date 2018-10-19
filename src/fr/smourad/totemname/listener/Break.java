package fr.smourad.totemname.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.smourad.totemname.Team;
import fr.smourad.totemname.TeamType;

public class Break implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public boolean onQuartzBreak(BlockBreakEvent e) {		
		
		if (Team.getPlayMode()) {
			Block breakBlock = e.getBlock();
			Material m = breakBlock.getType();
			
			if (!(m == Material.QUARTZ || m == Material.QUARTZ_BLOCK || m == Material.QUARTZ_STAIRS)) {
				e.setCancelled(true);
				return true;
			}
			
			Player player = e.getPlayer();
			
			String takeTotem = "§9Votre équipe viens de capturer le totem !";
			String loseTotem = "§cVotre équipe viens de perdre le totem !";
			
			Location loc1 = new Location(player.getWorld(), 0, 99, 5);
			Location loc2 = new Location(player.getWorld(), 0, 99, -5);
			
			Block glass1 = loc1.getBlock();
			Block glass2 = loc2.getBlock();
			
			glass1.setType(Material.STAINED_GLASS);
			glass2.setType(Material.STAINED_GLASS);
			
			if (Team.getTeamType(player).equals(TeamType.BLUE)) {
				glass1.setData((byte) 11);
				glass2.setData((byte) 11);
				player.resetTitle();
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (Team.getTeamType(p) == TeamType.RED) {
						p.sendTitle(null, loseTotem, 10, 70, 20);
					} else if (Team.getTeamType(p) == TeamType.BLUE) {
						p.sendTitle(null, takeTotem, 10, 70, 20);
					}
				}
				
				Team.setWinner(1);
			} else if (Team.getTeamType(player).equals(TeamType.RED)) {
				glass1.setData((byte) 14);
				glass2.setData((byte) 14);
				player.resetTitle();
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (Team.getTeamType(p) == TeamType.BLUE) {
						p.sendTitle(null, loseTotem, 10, 70, 20);
					} else if (Team.getTeamType(p) == TeamType.RED) {
						p.sendTitle(null, takeTotem, 10, 70, 20);
					}
				}
				
				Team.setWinner(2);
			}
			
			e.setCancelled(true);	
		}
		
		return false;
	}
}





























