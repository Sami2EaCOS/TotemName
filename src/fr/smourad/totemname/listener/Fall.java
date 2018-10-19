package fr.smourad.totemname.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.smourad.totemname.Team;
import fr.smourad.totemname.TeamType;

public class Fall implements Listener {

	@EventHandler
	public void onVoid(PlayerMoveEvent e) {
		if (Team.getPlayMode()) {
			Player player = e.getPlayer();
			if (e.getTo().getY() < 60.0) {
				if (Team.getTeamType(player).equals(TeamType.BLUE)) {
					player.teleport(new Location(player.getWorld(), 10.5, 101, 0.5, 90.0f, 0.0f));
				} else if (Team.getTeamType(player).equals(TeamType.RED)) {
					player.teleport(new Location(player.getWorld(), -9.5, 101, 0.5, -90.0f, 0.0f));
				}
			}
		}
	}
}
