package edu.mum.cs472.slitherio.dao;

import edu.mum.cs472.slitherio.entity.Direction;
import edu.mum.cs472.slitherio.entity.Move;

public interface SnakeDao {
	
	public Move move(int playerId);
	
	public void changeDirection(int playerId, Direction direction);
	
}
