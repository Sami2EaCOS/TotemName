package fr.smourad.totemname.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Inventory implements Listener {

	
	@EventHandler
	public void onMoveInventory(InventoryClickEvent e) {
		if (!(e.getWhoClicked().isOp())) {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (!(e.getPlayer().isOp())) {
			e.setCancelled(true);
		}
	}
}
