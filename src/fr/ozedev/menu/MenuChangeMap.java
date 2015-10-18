package fr.ozedev.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import fr.ozedev.item.Vote;

public class MenuChangeMap {
	public static void openMenu(Player player){
		Inventory inv = Bukkit.createInventory(player, 9, "Map");
		setInv(inv);
		player.openInventory(inv);
	}
	private static void setInv(Inventory inv){		
		inv.setItem(0, Vote.candies());
		inv.setItem(1, Vote.normal());
	}
}
