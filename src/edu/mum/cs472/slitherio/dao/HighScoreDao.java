package edu.mum.cs472.slitherio.dao;

import java.util.List;

import edu.mum.cs472.slitherio.entity.Player;
import edu.mum.cs472.slitherio.entity.Score;

public interface HighScoreDao {
	
	public void updateHighScore (Player player);
	
	public List<Score> getHighScore(int top);
	
}
