package edu.mum.cs472.slitherio.dao;

import edu.mum.cs472.slitherio.entity.Food;
import edu.mum.cs472.slitherio.entity.Snake;

public interface FoodDao {
	
	public void createMoreFoods();
	
	public void snakeDies(Snake snake);
	
	public void removeFood(Food food);
}
