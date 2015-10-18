package fr.ozedev.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Vote {
	public static ItemStack candies(){
		ItemStack snow1 = new ItemStack(Material.SNOW_BALL);
		ItemMeta snow1M = snow1.getItemMeta();
		snow1M.setDisplayName("§r§c1).§r §6Candies");
		snow1.setItemMeta(snow1M);
		return snow1;
	}
	public static ItemStack normal(){
		ItemStack snow2 = new ItemStack(Material.SNOW_BALL);
		ItemMeta snow2M = snow2.getItemMeta();
		snow2M.setDisplayName("§r§c2).§r §6Normal");
		snow2.setItemMeta(snow2M);
		return snow2;
	}
}
