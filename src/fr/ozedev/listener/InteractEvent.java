package fr.ozedev.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.ozedev.item.ChangeMap;
import fr.ozedev.menu.MenuChangeMap;

public class InteractEvent implements Listener{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) if(player.getItemInHand().getType().equals(Material.SNOW_BALL)) player.getInventory().addItem(new ItemStack(Material.SNOW_BALL));
		else if(player.getItemInHand().equals(ChangeMap.getTheChangeMap())){
			MenuChangeMap.openMenu(player);
		}
	}
}
