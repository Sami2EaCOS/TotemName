package fr.smourad.totemname;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Team {
	
	private static boolean onPlay = false;
	
	private static int color = 0;
	
	private static List<String> redTeam = new ArrayList<String>();
	private static List<String> blueTeam = new ArrayList<String>();
	
	public static void addToTeam(TeamType type, Player player) {
		if (isInTeam(player)) {
			return;
		}
		
		switch (type) {
		case RED:
			redTeam.add(player.getName());
			player.sendTitle("§4Vous avez rejoint l'\u00E9quipe Rouge", null, 10, 70, 20);
			player.setPlayerListName(ChatColor.RED + player.getName());
			break;
		case BLUE:
			blueTeam.add(player.getName());
			player.sendTitle("§1Vous avez rejoint l'\u00E9quipe Bleue", null, 10, 70, 20);
			player.setPlayerListName(ChatColor.BLUE + player.getName());
			break;
		}
	}
	
	public static boolean isInTeam(Player player) {
		return redTeam.contains(player.getName()) || blueTeam.contains(player.getName());
	}
	
	public static void removeFromTeam(Player player) {
		if (!isInTeam(player)) {
			return;
		}
		
		if (redTeam.contains(player.getName())) {
			redTeam.remove(player.getName());
		}
		
		if (blueTeam.contains(player.getName())) {
			blueTeam.remove(player.getName());
		}
		player.setPlayerListName(player.getName());
	}
	
	public static void clearTeams() {
		redTeam.clear();
		blueTeam.clear();
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setPlayerListName(player.getName());
		}
	}
	
	public static List<String> getRedTeam() {
		return redTeam;
	}
	
	public static List<String> getBlueTeam() {
		return blueTeam;
	}
	
	public static List<String> getAllPlayersInTeams() {
		List<String> combinedTeams = new ArrayList<String>();
		combinedTeams.addAll(redTeam);
		combinedTeams.addAll(blueTeam);
		return combinedTeams;
	}
	
	public static int getRedTeamNumber() {
		if (redTeam.isEmpty()) {
			return 0;
		}
		return redTeam.size();
	}
	
	public static int getBlueTeamNumber() {
		if (blueTeam.isEmpty()) {
			return 0;
		}
		return blueTeam.size();
	}	
	
	public static TeamType getTeamType(Player player) {
		if (!isInTeam(player)) {
			return null;
		}
		return (redTeam.contains(player.getName()) ? TeamType.RED : TeamType.BLUE);
	}

	public static void changePlayMode() {
		if (onPlay == false) {
			onPlay = true;
		} else {
			onPlay = false;
		}
	}
	
	public static boolean getPlayMode() {
		return onPlay;
	}

	public static int getWinner() {
		return color;
	}
	
	public static void setWinner(int winner) {
		color = winner;
	}
	
	public static void resetWinner() {
		color = 0;
	}
}