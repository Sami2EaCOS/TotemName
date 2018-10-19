package fr.smourad.totemname;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Armor {

	public static void setArmor(Color color, Player player) {
		player.getInventory().clear();
		
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta meta1 = (LeatherArmorMeta) boots.getItemMeta();
		meta1.setColor(color);
		meta1.setUnbreakable(true);
		meta1.addEnchant(Enchantment.BINDING_CURSE, 1, true);
		boots.setItemMeta(meta1);
		
		player.getInventory().setBoots(boots);
		
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta meta2 = (LeatherArmorMeta) chestplate.getItemMeta();
		meta2.setColor(color);
		meta2.setUnbreakable(true);
		meta2.addEnchant(Enchantment.BINDING_CURSE, 1, true);
		chestplate.setItemMeta(meta2);
		
		player.getInventory().setChestplate(chestplate);
		
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta meta3 = (LeatherArmorMeta) leggings.getItemMeta();
		meta3.setColor(color);
		meta3.setUnbreakable(true);
		meta3.addEnchant(Enchantment.BINDING_CURSE, 1, true);
		leggings.setItemMeta(meta3);
		
		player.getInventory().setLeggings(leggings);
		
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
		LeatherArmorMeta meta4 = (LeatherArmorMeta) chestplate.getItemMeta();
		meta4.setColor(color);
		meta4.setUnbreakable(true);
		meta4.addEnchant(Enchantment.BINDING_CURSE, 1, true);
		helmet.setItemMeta(meta4);
		
		player.getInventory().setHelmet(helmet);
	} 
	
	public static void setWeapon(Player player) {
		
		ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta meta = (ItemMeta) sword.getItemMeta();
		meta.setUnbreakable(true);
		sword.setItemMeta(meta);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		
		player.getInventory().setItem(0, sword);
		
		ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE, 1);
		ItemMeta meta2 = (ItemMeta) pickaxe.getItemMeta();
		meta2.setUnbreakable(true);
		pickaxe.setItemMeta(meta2);
		
		player.getInventory().setItem(1, pickaxe);
	}
}
