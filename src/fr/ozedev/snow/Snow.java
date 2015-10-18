package fr.ozedev.snow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import fr.ozedev.item.ChangeMap;
import fr.ozedev.listener.*;
import fr.ozedev.snow.SnowPlayer;

public class Snow extends JavaPlugin{
	private static Plugin instance;
	private static boolean gameStart						 = false;
	private static Map<String, SnowPlayer> snowPlayer		 = new HashMap<>();
	private static int compteur								 = 30;
	static Location[] loc 									 = {new Location(Bukkit.getWorld("world"),-5,49,86),new Location(Bukkit.getWorld("world"),-5,49,100),new Location(Bukkit.getWorld("world"),-19,49,100),new Location(Bukkit.getWorld("world"),-19,48,86)};
	static Location[] loc2 									 = {new Location(Bukkit.getWorld("world"),109,50.30,113),new Location(Bukkit.getWorld("world"),123,50.30,113),new Location(Bukkit.getWorld("world"),116,50.30,120),new Location(Bukkit.getWorld("world"),116,50.30,106)};
	
	public void onEnable(){
		PluginManager pm = Bukkit.getPluginManager();
		instance = this;
		pm.registerEvents(new JoinEvent(), instance);
		pm.registerEvents(new MoveEvent(), instance);
		pm.registerEvents(new DamageEvent(), instance);
		pm.registerEvents(new InteractEvent(), instance);
		pm.registerEvents(new DropEvent(), instance);
		pm.registerEvents(new DisconnectEvent(), instance);
		pm.registerEvents(new InventoryEvent(), instance);
	}
	public static Plugin getInstance(){return instance;}
	public static boolean getGameStart() {return gameStart;}
	public static void setGameStart(boolean gameStart) {Snow.gameStart = gameStart;}
	
	public static void initPlayer(Player player){
		if (get(player) == null) snowPlayer.put(player.getName(), new SnowPlayer());
		SnowPlayer snowPlayer = get(player);
		
		if(Snow.getGameStart() == true){
			player.sendMessage("§b[SnowPunch] §aLe jeu a déjà commencer vous êtes donc un spectateur");
			player.teleport(new Location(player.getWorld(), 14, 59, 94));
			player.setGameMode(GameMode.SPECTATOR);
			snowPlayer.setInLife(false);
		}else{
			player.setGameMode(GameMode.ADVENTURE);
			player.teleport(new Location(player.getWorld(), 14, 59, 94));
			player.setMaxHealth(snowPlayer.getLife()*2);
			player.setHealth(snowPlayer.getLife()*2);
			player.getInventory().clear();
			player.getInventory().addItem(ChangeMap.getTheChangeMap());
		}
	}
	public static void startGame(){
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(instance, new Runnable(){
            @Override
            public void run() {
            	if(compteur > 0 && Bukkit.getOnlinePlayers().size() >= 2){
            		if(compteur == 30 || compteur == 15 || compteur == 10 || compteur <= 5) Bukkit.broadcastMessage("§b[SnowPunch] §aLe jeu commence dans §c"+compteur+" §a seconde(s)");
            		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	                scheduler.scheduleSyncDelayedTask(instance, this, 20L);
	                compteur --;
            	}else if(compteur <= 0){
            		Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
            		
            		int voteForCandies = 0;
            		int voteForNormal = 0;
            		
            		Snow.setGameStart(true);
            		
            		int i = 0;
            		
            		
            		while(iterator.hasNext()){
            			Player player = iterator.next();
            			SnowPlayer snowPlayer = Snow.get(player);
            			if(snowPlayer.getVote() == 0) voteForCandies++;
            			else if(snowPlayer.getVote() == 1) voteForNormal++;
            		}
            		
            		if(voteForCandies > voteForNormal) Bukkit.broadcastMessage("§b[SnowPunch] §aLa map choisie est §aCandies");
            		else Bukkit.broadcastMessage("§b[SnowPunch] §aLa map choisie est §cNormal");
            		
            		Iterator<? extends Player> iterator2 = Bukkit.getOnlinePlayers().iterator();
            		
            		while(iterator2.hasNext()){
            			Player player = iterator2.next();
            			if(voteForCandies > voteForNormal){
	            			player.teleport(loc[i]);
	            			get(player).setLoc(loc[i]);
	            			i++;
            			}else{
            				player.teleport(loc2[i]);
	            			get(player).setLoc(loc2[i]);
	            			i++;
            			}
            			player.getInventory().clear();
            			player.getInventory().addItem(new ItemStack(Material.SNOW_BALL));
            		}
            	}else{
            		Bukkit.broadcastMessage("§b[SnowPunch]§a Un joueur viens de se déconnecter §c["+Bukkit.getOnlinePlayers().size() +"/4]§a, le compte a rebour est donc stopé");
            	}
            }
        }, 20L);
	}
	public static SnowPlayer get(Player player) {
		return snowPlayer.get(player.getName());
	}
	public static void checkIfGameItEnd(){
		int playerInLife = 0;
		Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
		while(iterator.hasNext()){
			Player player = iterator.next();
			SnowPlayer snowPlayer = get(player);
			if(snowPlayer != null){
				if(snowPlayer.getInLife() == true) playerInLife++;
			}
		}
		if(playerInLife >= 2){
			return;
		}else{
			Iterator<? extends Player> iterator2 = Bukkit.getOnlinePlayers().iterator();
			while(iterator2.hasNext()){
				Player player = iterator2.next();
				SnowPlayer snowPlayer = get(player);
				if(snowPlayer != null) if(snowPlayer.getInLife() == true){
					Bukkit.broadcastMessage("§b[SnowPunch] §aLa partie est terminé");
					Bukkit.broadcastMessage("§b[SnowPunch] §c"+player.getName()+" §aest le gagnant");	
				} 
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable(){
	            @Override
	            public void run() {
	            	Iterator<? extends Player> iterator = Bukkit.getOnlinePlayers().iterator();
	        		while(iterator.hasNext()){
	        			ChangeServ.changeServ("lobby", iterator.next(), instance);
	        		}
	            	Bukkit.getServer().reload();
	            }
			}, 20L*5);
		}
	}
}
