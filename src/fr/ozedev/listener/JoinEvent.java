package fr.ozedev.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.ozedev.snow.Snow;

public class JoinEvent implements Listener{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		Snow.initPlayer(player);
		
		if(Snow.get(player).getInLife() == true){
			event.setJoinMessage("§b[SnowPunch] §aJe joueur §c"+player.getName().toString()+"§a a rejoind le jeu §6["+Bukkit.getOnlinePlayers().size()+"/4]");
			if(Bukkit.getOnlinePlayers().size() >= 2) Snow.startGame();
		}else{
			event.setJoinMessage("");
		}
	}
}
