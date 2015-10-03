package fr.ozedev.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.ozedev.snow.Snow;
import fr.ozedev.snow.SnowPlayer;

public class MoveEvent implements Listener{
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		SnowPlayer snowPlayer = Snow.get(player);
		player.setSaturation(20);
		
		if(player.getLocation().getBlockY() <= 30){
			if(snowPlayer.getLife() > 1){
				player.teleport(snowPlayer.getLoc());
				snowPlayer.setLife(snowPlayer.getLife()-1);
				player.setMaxHealth(snowPlayer.getLife()*2);
				player.sendMessage("§b[SnowPunch] §aVous êtes tomber il ne vous reste que §c"+snowPlayer.getLife()+"§a vie(s)");
			}else{
				player.teleport(new Location(player.getWorld(), 1, 86, 96));
				player.setGameMode(GameMode.SPECTATOR);
				snowPlayer.setInLife(false);
				Bukkit.broadcastMessage("§b[SnowPunch] §aLe joueur §c"+player.getName().toString()+"§a est mort");
				player.sendMessage("§b[SnowPunch] §aVous êtes mort");
				Snow.checkIfGameItEnd();
			}
		}
	}
}
