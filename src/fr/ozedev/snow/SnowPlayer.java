package fr.ozedev.snow;

import org.bukkit.Location;

public class SnowPlayer {
	private boolean inLife 		= true;
	private int life			= 5;
	private Location loc;
	private int vote			= 1;
	private long dontSpam;
	
	public long getDontSpam(){return dontSpam;}
	public void setDontSpam(long dontSpam){this.dontSpam = dontSpam;}
	public Location getLoc(){return loc;}
	public void setLoc(Location loc){this.loc = loc;}
	public int getLife(){return life;}
	public void setLife(int life){this.life = life;}
	public boolean getInLife(){return inLife;}
	public void setInLife(boolean inLife){this.inLife = inLife;}
	public int getVote(){return vote;}
	public void setVote(int vote){this.vote = vote;}
}
