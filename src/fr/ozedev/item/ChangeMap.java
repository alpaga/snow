package fr.ozedev.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChangeMap{
	public static ItemStack getTheChangeMap(){
		ItemStack item = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName("§r§6Choisie ta map !");
		item.setItemMeta(itemM);
		return item;
	}
}
