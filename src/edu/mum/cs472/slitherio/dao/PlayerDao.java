package edu.mum.cs472.slitherio.dao;

import edu.mum.cs472.slitherio.entity.Player;

public interface PlayerDao {
	
	public void addPlayer(Player player);
	
	public Player getPlayer(int id);
	
	public void removePlayer(int id);
	
}
