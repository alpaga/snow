package fr.ozedev.snow;

import org.bukkit.Location;

public class SnowPlayer {
	private boolean inLife 	= true;
	private int life		= 5;
	private Location loc;
	
	public Location getLoc(){return loc;}
	public void setLoc(Location loc){this.loc = loc;}
	public int getLife(){return life;}
	public void setLife(int life){this.life = life;}
	public boolean getInLife(){return inLife;}
	public void setInLife(boolean inLife){this.inLife = inLife;}
}
