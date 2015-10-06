package fr.ozedev.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.ozedev.snow.Snow;
import fr.ozedev.snow.SnowPlayer;

public class DisconnectEvent implements Listener{
	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent event){
		Player player = event.getPlayer();
		SnowPlayer snowPlayer = Snow.get(player);
		
		event.setQuitMessage("§b[SnowPunch] §aLe joueur §c"+player.getName()+" §a vient de se déconnecter");
		
		if(Snow.getGameStart() == true) snowPlayer.setInLife(false);
		Snow.checkIfGameItEnd();
	}
}
