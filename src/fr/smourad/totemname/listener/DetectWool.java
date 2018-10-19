package fr.smourad.totemname.listener;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;

import fr.smourad.totemname.Team;
import fr.smourad.totemname.TeamType;

public class DetectWool implements Listener {

	@EventHandler
	public void changeWool(PlayerInteractEvent e) {
		if (!(Team.getPlayMode())) {
			Player player = e.getPlayer();
			
			Material item = player.getInventory().getItemInMainHand().getType();
			MaterialData itemD = player.getInventory().getItemInMainHand().getData();

			if (itemD instanceof Wool) {
				Wool w = (Wool) itemD;
				if (w.getColor() == DyeColor.BLUE) {
					Team.removeFromTeam(player);
					Team.addToTeam(TeamType.BLUE, player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
				} else if (w.getColor() == DyeColor.RED) {
					Team.removeFromTeam(player);
					Team.addToTeam(TeamType.RED, player);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
				} else if (w.getColor() == DyeColor.WHITE) {
					Team.removeFromTeam(player);
					player.sendTitle("Vous ne faites plus partie d'une \u00E9quipe", null, 10, 70, 20);
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_SNARE, 1.0f, 1.0f);
				}
				e.setCancelled(true);
			}
			
			if (item == Material.ACACIA_DOOR_ITEM) {
				e.setCancelled(true);
			}
		}
	}
}
