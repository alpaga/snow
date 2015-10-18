package fr.ozedev.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.ozedev.item.Vote;
import fr.ozedev.snow.Snow;
import fr.ozedev.snow.SnowPlayer;

public class InventoryEvent implements Listener{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		SnowPlayer snowPlayer = Snow.get(player);
		
		if(event.getInventory().getName().equals("Map")){
			if(event.getCurrentItem().equals(Vote.candies())) snowPlayer.setVote(0);
			else if(event.getCurrentItem().equals(Vote.normal())) snowPlayer.setVote(1);
			player.sendMessage("§b[SnowPunch] §aVotre vote a été pris en compte");
		}
		event.setCancelled(true);
	}
}
