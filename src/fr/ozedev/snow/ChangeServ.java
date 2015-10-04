package fr.ozedev.snow;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ChangeServ {
	public static void changeServ(String serv, Player player, Plugin pl){
		  Bukkit.getMessenger().registerOutgoingPluginChannel(pl, "BungeeCord");
		  ByteArrayDataOutput out = ByteStreams.newDataOutput();
		  out.writeUTF("Connect");
		  out.writeUTF(serv);
		  player.sendPluginMessage(pl, "BungeeCord", out.toByteArray());
	}
}
